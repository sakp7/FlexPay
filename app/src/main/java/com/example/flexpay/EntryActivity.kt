package com.example.flexpay

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class EntryActivity : AppCompatActivity() {
    private lateinit var btn1 :Button
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        btn1=findViewById(R.id.btn1)
        btn1.setOnClickListener {


            val intent =Intent(this@EntryActivity,MainActivity::class.java)
            startActivity(intent)

        }



    }
}