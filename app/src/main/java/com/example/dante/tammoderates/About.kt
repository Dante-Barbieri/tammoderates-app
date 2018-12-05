package com.example.dante.tammoderates

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.view.View
import com.example.dante.tammodertates.R
import kotlinx.android.synthetic.main.activity_about.*

class About : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            about_body_label.justificationMode = Layout.JUSTIFICATION_MODE_INTER_WORD
        }
    }

    fun openApply(view: View) {
        val url = "https://docs.google.com/forms/d/e/1FAIpQLSdwo5WwTDw6Gn02YwNEHl3XRmzFyAQmKeNkEEyY6W4rJbLk0g/viewform"

        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(url)
        startActivity(i)
    }
}
