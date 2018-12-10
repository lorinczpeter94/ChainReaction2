package com.example.lorinczpeter94.chainreaction2

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.lorinczpeter94.chainreaction2.welcome_activity.WelcomeActivity
import com.google.firebase.auth.FirebaseAuth

class LoggedInActivity : AppCompatActivity() {

    private var logOutButton: Button? = null
    private var auth: FirebaseAuth? = null
    private var authListener: FirebaseAuth.AuthStateListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logged_in)


        auth = FirebaseAuth.getInstance()
        authListener = FirebaseAuth.AuthStateListener {
            if (it.currentUser == null){
                startActivity(Intent(this, WelcomeActivity::class.java ))
            }
        }
        logOutButton = findViewById(R.id.buttonLogOut)
        logOutButton?.setOnClickListener {

            auth?.signOut()
        }
    }

    override fun onStart() {
        super.onStart()

        authListener?.let { auth?.addAuthStateListener(it) }
    }
}
