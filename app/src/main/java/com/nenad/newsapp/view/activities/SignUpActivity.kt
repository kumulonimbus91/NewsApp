package com.nenad.newsapp.view.activities

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.nenad.newsapp.R
import com.nenad.newsapp.database.model.User
import com.nenad.newsapp.databinding.ActivitySignUpBinding
import com.nenad.newsapp.firebase.FirestoreClass

class SignUpActivity : BaseActivity() {
    private lateinit var mBinding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivitySignUpBinding.inflate(layoutInflater)

        supportActionBar?.hide()




        mBinding.btnregister.setOnClickListener {

            try {

                if (registerUser()) {
                    registerUser()

                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this,"Please fill all the fields", Toast.LENGTH_SHORT).show()
                }

            }catch (e: Exception) {
                e.printStackTrace()
            }

            }


        mBinding.backbutton.setOnClickListener {
            onBackPressed()
        }

        mBinding.logInHere.setOnClickListener {
            val intent = Intent(this, LogInActivity::class.java)
            startActivity(intent)
            finish()
        }

        setContentView(mBinding.root)
    }

    /**
     * A function to register a user to our app using the Firebase.
     * For more details visit: https://firebase.google.com/docs/auth/android/custom-auth
     */
    private fun registerUser(): Boolean {
        // Here we get the text from editText and trim the space
        val name: String = mBinding.etName.text.toString().trim { it <= ' ' }
        val email: String = mBinding.etEmail.text.toString().trim { it <= ' ' }
        val password: String = mBinding.etPassword.text.toString().trim { it <= ' ' }
        val passwordConfirmed: String = mBinding.etPasswordconfirm.text.toString().trim { it <= ' ' }

        if (validateForm(name, email, password, passwordConfirmed)) {
            // Show the progress dialog.
            showProgressDialog(resources.getString(R.string.please_wait))
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(
                    OnCompleteListener<AuthResult> { task ->

                        // If the registration is successfully done
                        if (task.isSuccessful) {

                            // Firebase registered user
                            val firebaseUser: FirebaseUser = task.result!!.user!!
                            // Registered Email
                            val registeredEmail = firebaseUser.email!!

                            val user = User(
                                firebaseUser.uid, name, registeredEmail
                            )

                            // call the registerUser function of FirestoreClass to make an entry in the database.
                            FirestoreClass().registerUser(this@SignUpActivity, user)
                        } else {
                            Toast.makeText(
                                this@SignUpActivity,
                                task.exception!!.message,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    })
            return true
        } else {
            return false
        }
    }

    /**
     * A function to be called the user is registered successfully and entry is made in the firestore database.
     */
    fun userRegisteredSuccess() {

        Toast.makeText(
            this@SignUpActivity,
            "You have successfully registered.",
            Toast.LENGTH_SHORT
        ).show()

        // Hide the progress dialog
        hideProgressDialog()

        /**
         * Here the new user registered is automatically signed-in so we just sign-out the user from firebase
         * and send him to Intro Screen for Sign-In
         */
        FirebaseAuth.getInstance().signOut()
        // Finish the Sign-Up Screen
        finish()
    }









    /**
     * A function to validate the entries of a new user.
     */
    private fun validateForm(name: String, email: String, password: String, passwordConfirmed: String): Boolean {
        return when {
            TextUtils.isEmpty(name) -> {
                showErrorSnackBar("Please enter name.")
                false
            }
            TextUtils.isEmpty(email) -> {
                showErrorSnackBar("Please enter email.")
                false
            }
            TextUtils.isEmpty(password) -> {
                showErrorSnackBar("Please enter password.")
                false
            }
            TextUtils.isEmpty(passwordConfirmed) -> {
                showErrorSnackBar("Please confirm your password")
                false
            }
            !TextUtils.equals(password, passwordConfirmed) -> {
                showErrorSnackBar("Passwords are not the same")
                false
            }


            else -> {
                true
            }

        }


    }
}