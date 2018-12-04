package com.example.dante.tammoderates

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.dante.tammodertates.R

class About : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
    }

    fun openApply(view: View) {
        val url = "https://docs.google.com/forms/d/e/1FAIpQLSdwo5WwTDw6Gn02YwNEHl3XRmzFyAQmKeNkEEyY6W4rJbLk0g/viewform"

        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(url)
        startActivity(i)
    }
}
