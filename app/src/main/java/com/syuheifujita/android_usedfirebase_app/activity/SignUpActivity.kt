package com.syuheifujita.android_usedfirebase_app.activity

import android.os.Bundle
import android.text.TextUtils
import android.view.WindowManager
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.syuheifujita.android_usedfirebase_app.R
import com.syuheifujita.android_usedfirebase_app.firebase.FirestoreClass
import com.syuheifujita.android_usedfirebase_app.model.UserModel
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        setUpActionBar()

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
    }

    fun userRegisteredSuccess() {
        Toast.makeText(this, "you have succucefly registered", Toast.LENGTH_SHORT)
            .show()
        hideProgressDialog()

        FirebaseAuth.getInstance().signOut()
        finish()
    }

    private fun setUpActionBar() {
        setSupportActionBar(tb_sign_up_activity)

        val actionBar = supportActionBar
        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_black_back_24dp)
        }

        tb_sign_up_activity.setNavigationOnClickListener {
            onBackPressed()
        }

        btn_sign_up.setOnClickListener {
            registerUser()
        }
    }

    private fun registerUser() {
        val name: String = et_name.text.toString().trim {it <= ' '}
        val email: String = et_email.text.toString().trim {it <= ' '}
        val password: String = et_password.text.toString().trim {it <= ' '}

        if(validateForm(name, email, password)) {
            showProgressDialog(resources.getString(R.string.tv_dialog_please_wait))

            FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                    hideProgressDialog()
                    if (task.isSuccessful) {
                        val firebaseUser : FirebaseUser = task.result!!.user!!
                        val registerEmail = firebaseUser.email!!
                        val user = UserModel(firebaseUser.uid, name, registerEmail)
                        FirestoreClass().registerUser(this, user)
                    } else {
                        Toast.makeText(this,
                            task.exception!!.message, Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }

//    ユーザが何を入力したかを確認する
    private fun validateForm(name: String, email: String, password: String) : Boolean{
        return when {
            TextUtils.isEmpty(name) -> {
                showErrorSnackBar("Plase enter a name")
                false
            }
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
