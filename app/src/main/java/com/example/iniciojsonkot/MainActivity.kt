package com.example.iniciojsonkot

import android.content.Context
import android.support.design.widget.TabLayout
import android.support.v7.app.AppCompatActivity

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.os.Bundle
import com.example.iniciojsonkot.Global.Companion.basicitems
import com.example.iniciojsonkot.Global.Companion.items
import com.example.iniciojsonkot.Global.Companion.spells

import kotlinx.android.synthetic.main.activity_main_fragments.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder


class MainActivity : AppCompatActivity() {

    /**
     * The [android.support.v4.view.PagerAdapter] that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * [android.support.v4.app.FragmentStatePagerAdapter].
     */
    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_fragments)
        loadDataFromGit()

        setSupportActionBar(toolbar)
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)

        // Set up the ViewPager with the sections adapter.
        container.adapter = mSectionsPagerAdapter

        container.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))
        tabs.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(container))


    }

/*
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId

        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }
*/

    /**
     * A [FragmentPagerAdapter] that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    inner class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {

            var frag: Fragment? = null

            when (position) {
                0 -> frag = Frag1K()
                1 -> frag = SpellsFrament()
                2 -> frag = Frag3K()
                3 -> frag = Frag1K()
            }
            return frag!!
        }

        override fun getCount(): Int {
            // Show 3 total pages.
            return 4
        }

    }



    fun loadDataFromGit(){
        spells = ModifyStatsActivity.DownloadGit().execute("https://raw.githubusercontent.com/TheGiddyLimit/TheGiddyLimit.github.io/master/data/spells/spells-phb.json").get()
        basicitems = ModifyStatsActivity.DownloadGit().execute("https://raw.githubusercontent.com/TheGiddyLimit/TheGiddyLimit.github.io/master/data/basicitems.json").get()
        items = ModifyStatsActivity.DownloadGit().execute("https://raw.githubusercontent.com/TheGiddyLimit/TheGiddyLimit.github.io/master/data/items.json").get()


        /*val file = File("prueba.json")
        file.createNewFile()
        File("prueba.json").writeText("{}")*/
        val prueba = openFileOutput("dondeEsta.json", Context.MODE_PRIVATE)
        prueba.use {
            prueba.write("DONDE ESTÁS ==========================================================".toByteArray())
        }
        val fileInputStream = openFileInput("dondeEsta.json")
        val inputStream = InputStreamReader(fileInputStream)
        val buffered = BufferedReader(inputStream)
        val stringBuilder = StringBuilder()

        stringBuilder.append(buffered.readText())
        println("==========")
        println(stringBuilder.toString())
    }

    /**
     * A placeholder fragment containing a simple view.
     */

    /*
    class PlaceholderFragment : Fragment() {

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            val rootView = inflater.inflate(R.layout.fragment_main, container, false)
            rootView.section_label.text = getString(R.string.section_format, arguments?.getInt(ARG_SECTION_NUMBER))
            return rootView
        }

        companion object {
            /**
             * The fragment argument representing the section number for this
             * fragment.
             */
            private val ARG_SECTION_NUMBER = "section_number"

            /**
             * Returns a new instance of this fragment for the given section
             * number.
             */
            fun newInstance(sectionNumber: Int): PlaceholderFragment {
                val fragment = PlaceholderFragment()
                val args = Bundle()
                args.putInt(ARG_SECTION_NUMBER, sectionNumber)
                fragment.arguments = args
                return fragment
            }
        }
    }*/
}
