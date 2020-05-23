package com.syuheifujita.android_usedfirebase_app.activity

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.syuheifujita.android_usedfirebase_app.R
import kotlinx.android.synthetic.main.activity_sign_in.*
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignInActivity : BaseActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        auth = FirebaseAuth.getInstance()

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        btn_sign_in.setOnClickListener {
            signIn()
        }

        setUpActionBar()
    }

    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
    }

    private fun setUpActionBar() {
        setSupportActionBar(tb_sign_in_activity)

        val actionBar = supportActionBar
        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_black_back_24dp)
        }

        tb_sign_in_activity.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    private fun signIn() {
        val email: String = et_email_sign_in.text.toString().trim {it <= ' '}
        val password: String = et_password_sign_in.text.toString().trim {it <= ' '}

        if(validateForm(email, password)) {
            showProgressDialog(resources.getString(R.string.tv_dialog_please_wait))

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    hideProgressDialog()
                    if (task.isSuccessful) {
                        Log.d("sign in", "signInWithEmail:success")
                        val user = auth.currentUser
                        startActivity(Intent(this, MainActivity::class.java))
                    } else {
                        Log.w("sign in", "signInWithEmail:failure", task.exception)
                        Toast.makeText(this,
                            "Registration failed", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }

    private fun validateForm(email: String, password: String) : Boolean{
        return when {
            TextUtils.isEmpty(email) -> {
                showErrorSnackBar("Plase enter a email")
                false
            }
            TextUtils.isEmpty(password) -> {
                showErrorSnackBar("Plase enter a password")
                false
            } else -> {
                true
            }
        }
    }
}
