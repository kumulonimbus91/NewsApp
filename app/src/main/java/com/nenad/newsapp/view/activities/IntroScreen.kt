package com.nenad.newsapp.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nenad.newsapp.R
import com.nenad.newsapp.databinding.ActivityIntroScreenBinding

class IntroScreen : AppCompatActivity() {

    private lateinit var mBinding: ActivityIntroScreenBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityIntroScreenBinding.inflate(layoutInflater)

        supportActionBar?.hide()

        mBinding.btnLogIn.setOnClickListener {
            val intent = Intent(this, LogInActivity::class.java)

            startActivity(intent)
        }

        mBinding.btnSignIn.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }



        setContentView(mBinding.root)
    }
}