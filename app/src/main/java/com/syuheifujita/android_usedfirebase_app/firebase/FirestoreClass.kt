package com.syuheifujita.android_usedfirebase_app.firebase

import android.app.Activity
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.syuheifujita.android_usedfirebase_app.activity.MainActivity
import com.syuheifujita.android_usedfirebase_app.activity.SignInActivity
import com.syuheifujita.android_usedfirebase_app.activity.SignUpActivity
import com.syuheifujita.android_usedfirebase_app.model.UserModel
import com.syuheifujita.android_usedfirebase_app.util.Constants

class FirestoreClass {

    private val mFireStore = FirebaseFirestore.getInstance()

    fun registerUser(activity: SignUpActivity, userInfo: UserModel) {
        mFireStore.collection(Constants.USERS)
            .document(getCurrentUserId())
            .set(userInfo, SetOptions.merge())
            .addOnSuccessListener {
                activity.userRegisteredSuccess()
            }
    }

    fun updateNavigationUserDetails(user: UserModel) {

    }

    fun signInUser(activity: Activity) {
        mFireStore.collection(Constants.USERS)
            .document(getCurrentUserId())
            .get()
            .addOnSuccessListener { document ->
                val loggedInUser = document.toObject(UserModel::class.java)!!

                when(activity) {
                    is SignInActivity -> {
                        activity.signInSuccess(loggedInUser)
                    }
                    is MainActivity -> {
                        activity.updateNavigationUserDetails(loggedInUser)
                    }
                }
            }.addOnFailureListener {e ->
                when (activity) {
                    is SignInActivity -> {
                        activity.hideProgressDialog()
                    }
                    is MainActivity -> {
                        activity.hideProgressDialog()
                    }
                    // END
                }
                // END
                Log.e(
                    activity.javaClass.simpleName,
                    "Error while getting loggedIn user details",
                    e
                )
            }
    }

    fun getCurrentUserId(): String {


        return FirebaseAuth.getInstance().currentUser!!.uid
    }
}
