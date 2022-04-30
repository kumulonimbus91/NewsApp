package com.nenad.newsapp.view.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import androidx.preference.PreferenceManager
import com.google.firebase.auth.FirebaseAuth
import com.nenad.newsapp.R
import com.nenad.newsapp.databinding.ActivityMainBinding
import com.nenad.newsapp.utils.Constants
import com.nenad.newsapp.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mNavController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var actionBar: ActionBar

    lateinit var viewModel: MainViewModel

    // A global variable for SharedPreferences
    private lateinit var mSharedPreferences: SharedPreferences






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)

       // mBinding.viewModel = viewModel

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]


        //  supportActionBar?.hide()
        actionBar = supportActionBar!!

        val colorDrawable: ColorDrawable = ColorDrawable(Color.parseColor("#5E5C21"))

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

        mSharedPreferences =
            this.getSharedPreferences(Constants.PREFS_NAME, Context.MODE_PRIVATE)





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

                R.id.nav_sign_out -> {

                    FirebaseAuth.getInstance().signOut()

                    mSharedPreferences.edit().clear().apply()

                    val intent = Intent(this@MainActivity, IntroScreen::class.java)
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


}


