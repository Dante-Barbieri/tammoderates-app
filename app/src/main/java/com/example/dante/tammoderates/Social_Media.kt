package com.example.dante.tammoderates

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.dante.tammodertates.R

class Social_Media : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_social_media)
    }

    fun openTwitter(view: View) {
        val url = "https://twitter.com/tammoderates?lang=en"

        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(url)
        startActivity(i)
    }

    fun openInstagram(view: View) {
        val url = "http://instagram.com/_u/tammoderates"

        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(url)
        startActivity(i)
    }

    fun openFacebook(view: View) {
        val url = "https://m.facebook.com/tammoderates/"

        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(url)
        startActivity(i)
    }

    fun openWebsite(view: View) {
        val url = "https://tammoderates.wixsite.com/tamm"

        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(url)
        startActivity(i)
    }
}
