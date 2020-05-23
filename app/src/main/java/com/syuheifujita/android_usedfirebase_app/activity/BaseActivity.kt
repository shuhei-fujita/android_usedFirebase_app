package com.syuheifujita.android_usedfirebase_app.activity

import android.app.Dialog
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.syuheifujita.android_usedfirebase_app.R
import kotlinx.android.synthetic.main.dialog_progress.*

open class BaseActivity : AppCompatActivity() {

    private var doubleBackToExitPressOnce = false

    private lateinit var mProgressDialog: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
    }

//    Dialog
    fun showProgressDialog(text: String) {
        mProgressDialog = Dialog(this)

        mProgressDialog.setContentView(R.layout.dialog_progress)

        mProgressDialog.tv_dialog_progress.text = text

        mProgressDialog.show()
    }

    fun hideProgressDialog() {
        mProgressDialog.dismiss()
    }

//  firebase
    fun getCurrentUserId(): String {
        return FirebaseAuth.getInstance().currentUser!!.uid
    }

//  ここからはエラー処理
    fun doubleBackToExit() {
        if(doubleBackToExitPressOnce) {
            super.onBackPressed()
            return
        }

        this.doubleBackToExitPressOnce = true
        Toast.makeText(
            this,
            resources.getString(R.string.please_click_back_again_to_exit),
            Toast.LENGTH_SHORT
        ).show()

        Handler().postDelayed({doubleBackToExitPressOnce = false}, 2000)
    }

    fun showErrorSnackBar(message: String) {
        val snackBar = Snackbar.make(
            findViewById(android.R.id.content),
            message,
            Snackbar.LENGTH_LONG
        )

        val snackBarView = snackBar.view
        snackBarView.setBackgroundColor(ContextCompat.getColor(this, R.color.sb_error_color))

        snackBar.show()
    }
}
