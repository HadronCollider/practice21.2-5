package com.makentoshe.androidgithubcitemplate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bCharts = findViewById<ImageButton>(R.id.buttonCharts)

        bCharts.setOnClickListener{
            val intent = Intent(this, ChartsActivity::class.java)
            startActivity(intent)
        }

        val bArchive = findViewById<ImageButton>(R.id.buttonArchive)

        bArchive.setOnClickListener{
            val intent = Intent(this, ArchiveActivity::class.java)
            startActivity(intent)
        }



    }
}
