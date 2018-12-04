package com.example.dante.tammoderates

import android.app.Activity
import android.content.Intent
import android.content.pm.ActivityInfo
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Toast
import com.example.dante.tammodertates.R

class Home : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_home)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT
    }

    fun toastApp(view: View) {
        val tst = Toast.makeText(this, "That not supported yet!", Toast.LENGTH_SHORT)
        tst.show()
    }

    fun openSignIn(view: View) {
        val login_intent = Intent(this, Login::class.java)
        startActivity(login_intent)
//        val url = "https://goo.gl/forms/z9J9CWtFwHciRRGl2"
//
//        val i = Intent(Intent.ACTION_VIEW)
//        i.data = Uri.parse(url)
//        startActivity(i)
    }

    fun openSocialMediaActivity(view: View) {
        val social_intent = Intent(this, Social_Media::class.java)
        startActivity(social_intent)
    }

    fun openMeetTheOfficersActivity(view: View) {
        val meet_intent = Intent(this, Meet_The_Officers::class.java)
        startActivity(meet_intent)
    }

    fun openAboutActivity(view: View) {
        val about_intent = Intent(this, About::class.java)
        startActivity(about_intent)
    }

    fun openImportantDatesActivity(view: View) {
        val important_intent = Intent(this, Important_Dates::class.java)
        startActivity(important_intent)
    }
}
