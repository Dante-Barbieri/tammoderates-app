package com.example.dante.tammodertates

import android.annotation.TargetApi
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.text.Layout.JUSTIFICATION_MODE_INTER_WORD
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_meet__the__officers.*
import kotlinx.android.synthetic.main.content_meet__the__officers.*

class Meet_The_Officers : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meet__the__officers)
        setSupportActionBar(toolbar)
        fab.setOnClickListener { view ->
            val url = "mailto:tammoderates@gmail.com"

            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }
        val bios = listOf(president_bio, vice_president_bio, treasurer_bio, director_internal_affairs1_bio,
            director_internal_affairs2_bio, director_public_relations_bio)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            justifyBios(bios)
        }
    }

    @TargetApi(Build.VERSION_CODES.O)
    @RequiresApi(Build.VERSION_CODES.O)
    fun justifyBios(bios : List<TextView>) {
        for (bio in bios){
            bio.justificationMode = JUSTIFICATION_MODE_INTER_WORD
        }
    }
}
