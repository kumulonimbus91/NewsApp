package com.nenad.newsapp.view.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.preference.PreferenceManager
import com.nenad.newsapp.R
import com.nenad.newsapp.databinding.ActivitySettingsBinding
import com.nenad.newsapp.utils.Constants

class SettingsActivity : AppCompatActivity() {
    lateinit var mBinding: ActivitySettingsBinding

    lateinit var sharedPrefs: SharedPreferences

    // A global variable for SharedPreferences
    private lateinit var mSharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivitySettingsBinding.inflate(layoutInflater)

        supportActionBar?.hide()

        mSharedPreferences =
            this.getSharedPreferences(Constants.PREFS_NAME, Context.MODE_PRIVATE)

        val shared: SharedPreferences? =
            PreferenceManager.getDefaultSharedPreferences(applicationContext)
        val sharedEditor: SharedPreferences.Editor? = shared?.edit()

        val spinner = mBinding.countriesSpinner

        // val country: String? = shared.getString("United States", "us")


        mBinding.countriesSpinner.apply {
            setOnSpinnerItemSelectedListener<String> { _, _, newIndex, _ ->
                //38
                when (newIndex) {
                    0 -> sharedEditor?.putString("country", "ae")?.apply()
                    1 -> sharedEditor?.putString("country", "au")?.apply()
                    2 -> sharedEditor?.putString("country", "at")?.apply()
                    3 -> sharedEditor?.putString("country", "be")?.apply()
                    4 -> sharedEditor?.putString("country", "br")?.apply()
                    5 -> sharedEditor?.putString("country", "bg")?.apply()
                    6 -> sharedEditor?.putString("country", "ca")?.apply()
                    7 -> sharedEditor?.putString("country", "cn")?.apply()
                    8 -> sharedEditor?.putString("country", "co")?.apply()
                    9 -> sharedEditor?.putString("country", "cu")?.apply()
                    10 -> sharedEditor?.putString("country", "cz")?.apply()
                    11 -> sharedEditor?.putString("country", "eg")?.apply()
                    12 -> sharedEditor?.putString("country", "fr")?.apply()
                    13 -> sharedEditor?.putString("country", "de")?.apply()
                    14 -> sharedEditor?.putString("country", "gr")?.apply()
                    15 -> sharedEditor?.putString("country", "hk")?.apply()
                    16 -> sharedEditor?.putString("country", "hu")?.apply()
                    17 -> sharedEditor?.putString("country", "in")?.apply()
                    18 -> sharedEditor?.putString("country", "id")?.apply()
                    19 -> sharedEditor?.putString("country", "ie")?.apply()
                    20 -> sharedEditor?.putString("country", "il")?.apply()
                    21 -> sharedEditor?.putString("country", "it")?.apply()
                    22 -> sharedEditor?.putString("country", "jp")?.apply()
                    23 -> sharedEditor?.putString("country", "lv")?.apply()
                    24 -> sharedEditor?.putString("country", "lt")?.apply()
                    25 -> sharedEditor?.putString("country", "my")?.apply()
                    26 -> sharedEditor?.putString("country", "mx")?.apply()
                    27 -> sharedEditor?.putString("country", "ma")?.apply()
                    28 -> sharedEditor?.putString("country", "nl")?.apply()
                    29 -> sharedEditor?.putString("country", "nz")?.apply()
                    30 -> sharedEditor?.putString("country", "ng")?.apply()
                    31 -> sharedEditor?.putString("country", "no")?.apply()
                    32 -> sharedEditor?.putString("country", "pl")?.apply()
                    33 -> sharedEditor?.putString("country", "pt")?.apply()
                    34 -> sharedEditor?.putString("country", "ro")?.apply()
                    35 -> sharedEditor?.putString("country", "rs")?.apply()
                    36 -> sharedEditor?.putString("country", "us")?.apply()
                    37 -> sharedEditor?.putString("country", "gb")?.apply()

                }

            }
        }

        mBinding.btnApply.setOnClickListener {

            Toast.makeText(
                this,
                shared?.getString(mBinding.countriesSpinner.selectedIndex.toString(), "selected"),
                Toast.LENGTH_SHORT
            ).show()

            sharedEditor?.apply()




            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()


        }



        mBinding.backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }








        setContentView(mBinding.root)
    }


}


