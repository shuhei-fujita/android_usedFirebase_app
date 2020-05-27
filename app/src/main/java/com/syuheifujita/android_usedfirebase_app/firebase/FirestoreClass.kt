package com.syuheifujita.android_usedfirebase_app.firebase

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.auth.User
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

    fun signInUser(activity: SignInActivity) {
        mFireStore.collection(Constants.USERS)
            .document(getCurrentUserId())
            .get()
            .addOnSuccessListener { document ->
                val loggedInUser = document.toObject(UserModel::class.java)!!
                if(loggedInUser != null)
                    activity.signInSuccess(loggedInUser)
            }.addOnFailureListener {
                e ->
                Log.e(activity.javaClass.simpleName, "Error")
            }
    }

    fun getCurrentUserId(): String {


        return FirebaseAuth.getInstance().currentUser!!.uid
    }
}
