package com.vacuum.avaz.main

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.TabLayout
import android.support.v4.view.GravityCompat
import android.support.v4.view.ViewPager
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.vacuum.avaz.Activities.SettingsActivity
import com.vacuum.avaz.MyPagerAdapter
import com.vacuum.avaz.ui.main.MainContract
import com.vacuum.avaz.ui.list.ListFragment
import com.vacuum.avaz.R
import com.vacuum.avaz.ui.about.AboutFragment
import com.vacuum.avaz.ui.main.ActivityModule
import com.vacuum.avaz.util.Utility
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper
import java.util.*
import javax.inject.Inject

/**
 * Created by ogulcan on 07/02/2018.
 */
class MainActivity: AppCompatActivity(), MainContract.View, NavigationView.OnNavigationItemSelectedListener {

    @Inject lateinit var presenter: MainContract.Presenter
    private lateinit var drawer: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var tabs_main: TabLayout
    private lateinit var pagerAdapter: MyPagerAdapter
    private lateinit var viewpager_main: ViewPager
    private lateinit var mContext: Context
    private val MY_PREFS_NAME = "avaz"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Utility().onActivityCreateSetTheme(this)
        mContext = this
        val prefs = getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE)
        val slangage = prefs.getString("language", "en")
        val res = mContext.getResources()
        val dm = res.getDisplayMetrics()
        val conf = res.getConfiguration()
        conf.setLocale(Locale(slangage))
        // API 17+ only.
        res.updateConfiguration(conf, dm)
        setContentView(R.layout.activity_main)
        injectDependency()

        presenter.attach(this)
        val toolbar: Toolbar = findViewById(R.id.toolbar_main)
        toolbar.setTitle("")
        setSupportActionBar(toolbar)
        drawer = findViewById(R.id.drawer_layout)
        val navigationView: NavigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        pagerAdapter = MyPagerAdapter(supportFragmentManager,mContext)
        viewpager_main = findViewById(R.id.viewpager_main)
        viewpager_main.adapter = pagerAdapter
        tabs_main = findViewById(R.id.tabs_main)
        tabs_main.setupWithViewPager(viewpager_main)

        toggle = ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
    }

    override fun onResume() {
        super.onResume()
        test()
    }

    override fun showAboutFragment() {
        if (supportFragmentManager.findFragmentByTag(AboutFragment.TAG) == null) {
            supportFragmentManager.beginTransaction()
                    .addToBackStack(null)
                    //.setCustomAnimations(AnimType.FADE.getAnimPair().first, AnimType.FADE.getAnimPair().second)
                    .replace(R.id.frame, AboutFragment().newInstance(), AboutFragment.TAG)
                    .commit()
        } else {
            // Maybe an animation like shake hello text
        }
    }

    override fun showListFragment() {
        supportFragmentManager.beginTransaction()
                .disallowAddToBackStack()
                //.setCustomAnimations(AnimType.SLIDE.getAnimPair().first, AnimType.SLIDE.getAnimPair().second)
                .replace(R.id.frame, ListFragment().newInstance(), ListFragment.TAG)
                .commit()
    }
    override fun onBackPressed() {
        val fragmentManager = supportFragmentManager
        val fragment = fragmentManager.findFragmentByTag(AboutFragment.TAG)

        if (fragment == null) {
            super.onBackPressed()
        } else {
            supportFragmentManager.popBackStack()
        }
    }

    private fun injectDependency() {
        val activityComponent = DaggerActivityComponent.builder()
                .activityModule(ActivityModule(this))
                .build()

        activityComponent.inject(this)
    }

    private fun test() {
        //hello.setText("Hello world with kotlin extensions")
    }



    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        toggle.syncState()
    }
    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        toggle.onConfigurationChanged(newConfig)
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true }
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.top_stories ->{
                Toast.makeText(this, "Clicked item one", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, SettingsActivity::class.java)
                startActivity(intent) }

            R.id.world -> Toast.makeText(this, "Clicked item two", Toast.LENGTH_SHORT).show()
            R.id.technology -> Toast.makeText(this, "Clicked item three", Toast.LENGTH_SHORT).show()
            R.id.culture -> Toast.makeText(this, "Clicked item four", Toast.LENGTH_SHORT).show()
            R.id.religion -> Toast.makeText(this, "Clicked item four", Toast.LENGTH_SHORT).show()
            R.id.business -> Toast.makeText(this, "Clicked item four", Toast.LENGTH_SHORT).show()
            R.id.health -> Toast.makeText(this, "Clicked item four", Toast.LENGTH_SHORT).show()
            R.id.sports -> Toast.makeText(this, "Clicked item four", Toast.LENGTH_SHORT).show()
        }
        drawer.closeDrawer(GravityCompat.START)
        return true
    }
    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }
}