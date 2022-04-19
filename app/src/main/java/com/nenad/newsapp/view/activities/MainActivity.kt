package com.nenad.newsapp.view.activities

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.appcompat.app.ActionBar
import androidx.core.view.GravityCompat
import androidx.customview.widget.Openable
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.nenad.newsapp.R
import com.nenad.newsapp.databinding.ActivityMainBinding
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
        return NavigationUI.navigateUp(mNavController, appBarConfiguration )

        //
    }

    private fun drawerItemSelectedListener() {
        mBinding.navView.setNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.business -> {
                    Toast.makeText(this, "Business category selected", Toast.LENGTH_SHORT).show()

                    if (mBinding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
                        mBinding.drawerLayout.closeDrawer(GravityCompat.START)
                    }

                    return@setNavigationItemSelectedListener true
                }
                R.id.technology -> {
                    Toast.makeText(this, "Tech category selected", Toast.LENGTH_SHORT).show()
                    if (mBinding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
                        mBinding.drawerLayout.closeDrawer(GravityCompat.START)
                    }

                    return@setNavigationItemSelectedListener true
                }
                R.id.science -> {
                    Toast.makeText(this, "Science category selected", Toast.LENGTH_SHORT).show()
                    if (mBinding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
                        mBinding.drawerLayout.closeDrawer(GravityCompat.START)
                    }

                    return@setNavigationItemSelectedListener true

                }
                R.id.health -> {
                    Toast.makeText(this, "Health category selected", Toast.LENGTH_SHORT).show()
                    if (mBinding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
                        mBinding.drawerLayout.closeDrawer(GravityCompat.START)
                    }

                    return@setNavigationItemSelectedListener true

                }
                R.id.entertainment -> {
                    Toast.makeText(this, "Health category selected", Toast.LENGTH_SHORT).show()
                    if (mBinding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
                        mBinding.drawerLayout.closeDrawer(GravityCompat.START)
                    }

                    return@setNavigationItemSelectedListener true
                }
                R.id.sport -> {
                    Toast.makeText(this, "Health category selected", Toast.LENGTH_SHORT).show()
                    if (mBinding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
                        mBinding.drawerLayout.closeDrawer(GravityCompat.START)
                    }

                    return@setNavigationItemSelectedListener true
                }


                else -> {
                    return@setNavigationItemSelectedListener false
                }
            }

        }


    }
}


