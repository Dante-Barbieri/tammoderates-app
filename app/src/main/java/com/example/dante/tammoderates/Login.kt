package com.example.dante.tammoderates

//import com.example.dante.tammodertates.R.id.detail
//import com.example.dante.tammodertates.R.id.email_sign_in_button

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.Snackbar
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.dante.tammodertates.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.android.synthetic.main.activity_login.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * A login screen that offers login via email/password.
 */
class Login : BaseActivity(), View.OnClickListener {

    private lateinit var auth: FirebaseAuth

    private lateinit var googleSignInClient: GoogleSignInClient

    private lateinit var firebaseAnalytics: FirebaseAnalytics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Obtain the FirebaseAnalytics instance.
        firebaseAnalytics = FirebaseAnalytics.getInstance(this)

        // Button listeners
        signInButton.setOnClickListener(this)
        signOutButton.setOnClickListener(this)

        // Configure Google Sign In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("334039861540-7k7si28qq1fnnvg1m91hmkd3r6cllmqg.apps.googleusercontent.com")
            .requestEmail()
            .build()

        // Build a GoogleSignInClient with the options specified by gso.
        googleSignInClient = GoogleSignIn.getClient(this, gso)

        auth = FirebaseAuth.getInstance()
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e)
                // ...
            }
        }
        val handler =  Handler()
        handler.postDelayed({returnToMain()}, 1500)
    }

    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.id!!)

        showProgressDialog()

        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success")
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    Snackbar.make(main_layout, "Authentication Failed.", Snackbar.LENGTH_SHORT).show()
                    updateUI(null)
                }

                hideProgressDialog()
                // ...
            }
    }

    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    private fun signOut() {
        // Firebase sign out
        auth.signOut()

        // Google sign out
        googleSignInClient.signOut().addOnCompleteListener(this) {
            updateUI(null)
        }
        val handler =  Handler()
        handler.postDelayed({returnToMain()}, 500)
    }

    private fun getCurrentDateTime(strDateFormat: String = "mm/dd/yyyy hh:mm:ss a"): String? {
        val date = Date()
        val dateFormat = SimpleDateFormat(strDateFormat)
        return dateFormat.format(date)
    }

    fun logMeeting(view: View) {
        //TODO: Use Firebase Analytics logging to process User Properties
        val bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, auth.currentUser?.displayName)
        bundle.putString(FirebaseAnalytics.Param.START_DATE, getCurrentDateTime("yyyy-mm-dd"))
        firebaseAnalytics.logEvent("log_meeting", bundle)
        val date = getCurrentDateTime()
        Snackbar.make(main_layout, "Meeting on " + date + " logged!", Snackbar.LENGTH_SHORT).show()
    }

    private fun returnToMain() {
        val main_intent = Intent(this, Home::class.java)
        startActivity(main_intent)
    }

    private fun revokeAccess() {
        // Firebase sign out
        auth.signOut()

        // Google revoke access
        googleSignInClient.revokeAccess().addOnCompleteListener(this) {
            updateUI(null)
        }
    }

    private fun updateUI(user: FirebaseUser?) {
        hideProgressDialog()
        if (user != null) {
            status.text = getString(R.string.google_status_fmt, user.email)

            signInButton.visibility = View.GONE
            signOutAndDisconnect.visibility = View.VISIBLE
        } else {
            status.setText(R.string.signed_out)

            signInButton.visibility = View.VISIBLE
            signOutAndDisconnect.visibility = View.GONE
        }
    }

    override fun onClick(v: View) {
        val i = v.id
        when (i) {
            R.id.signInButton -> signIn()
            R.id.signOutButton -> signOut()
        }
    }

    companion object {
        private const val TAG = "GoogleActivity"
        private const val RC_SIGN_IN = 9001
    }
}
