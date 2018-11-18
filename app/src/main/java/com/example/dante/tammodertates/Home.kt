package com.example.dante.tammodertates

import android.app.Activity
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.pm.ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Toast

class Home : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_home)
        requestedOrientation = SCREEN_ORIENTATION_SENSOR_PORTRAIT
    }

    fun toastApp(view: View) {
        val tst = Toast.makeText(this, "That not supported yet!", Toast.LENGTH_SHORT)
        tst.show()
    }

    fun openSignIn(view: View) {
        val url = "https://goo.gl/forms/z9J9CWtFwHciRRGl2"

        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(url)
        startActivity(i)
    }

    fun openSocialMediaActivity(view: View) {
        val social_intent = Intent(this, Social_Media::class.java)
        startActivity(social_intent)
    }
}
