package com.nenad.newsapp.view.activities

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import androidx.preference.PreferenceManager
import com.nenad.newsapp.R
import com.nenad.newsapp.databinding.ActivityMainBinding
import com.nenad.newsapp.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mNavController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var actionBar: ActionBar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)

        //  supportActionBar?.hide()
        actionBar = supportActionBar!!

        val colorDrawable: ColorDrawable = ColorDrawable(Color.parseColor("#E7E0E0"))

        actionBar.setBackgroundDrawable(colorDrawable)


        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        mNavController = navHostFragment.findNavController()

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.homeFragment,
                R.id.searchFragment,
                R.id.savedNewsFragment
            )
        )

        appBarConfiguration = AppBarConfiguration(mNavController.graph, mBinding.drawerLayout)





        mBinding.navViewBottom.setupWithNavController(mNavController)
        NavigationUI.setupActionBarWithNavController(this, mNavController, mBinding.drawerLayout)

        NavigationUI.setupWithNavController(mBinding.navView, mNavController)

        drawerItemSelectedListener()





        setContentView(mBinding.root)
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(mNavController, appBarConfiguration)

        //
    }

    private fun drawerItemSelectedListener() {
        mBinding.navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.business -> {
                    saveSp("category", "business")



                    if (mBinding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
                        mBinding.drawerLayout.closeDrawer(GravityCompat.START)
                    }

                    return@setNavigationItemSelectedListener true
                }
                R.id.technology -> {
                    saveSp("category", "technology")
                    if (mBinding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
                        mBinding.drawerLayout.closeDrawer(GravityCompat.START)
                    }

                    return@setNavigationItemSelectedListener true
                }
                R.id.science -> {
                    saveSp("category", "science")
                    if (mBinding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
                        mBinding.drawerLayout.closeDrawer(GravityCompat.START)
                    }

                    return@setNavigationItemSelectedListener true

                }
                R.id.health -> {
                    saveSp("category", "health")
                    if (mBinding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
                        mBinding.drawerLayout.closeDrawer(GravityCompat.START)
                    }

                    return@setNavigationItemSelectedListener true

                }
                R.id.entertainment -> {
                    saveSp("category", "entertainment")
                    if (mBinding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
                        mBinding.drawerLayout.closeDrawer(GravityCompat.START)
                    }

                    return@setNavigationItemSelectedListener true
                }
                R.id.sport -> {
                    saveSp("category", "sports")
                    if (mBinding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
                        mBinding.drawerLayout.closeDrawer(GravityCompat.START)
                    }

                    return@setNavigationItemSelectedListener true
                }
                R.id.settings -> {

                    val intent = Intent(this@MainActivity, SettingsActivity::class.java)
                    startActivity(intent)
                    finish()


                    return@setNavigationItemSelectedListener true
                }


                else -> {
                    return@setNavigationItemSelectedListener false
                }
            }

        }


    }

    private fun saveSp(key: String, value: String) {
        PreferenceManager.getDefaultSharedPreferences(this)
            .edit()
            .putString(key, value).apply()
    }
}


