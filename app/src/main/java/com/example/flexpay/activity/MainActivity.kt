package com.example.flexpay.activity

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.flexpay.R
import com.example.flexpay.fragments.HomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class MainActivity : AppCompatActivity() {
    lateinit var btn: Button
    lateinit var text: TextView
    private lateinit var sharedPreferences: SharedPreferences
    var db = Firebase.firestore
    lateinit var bottom_navigation_view: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frame_main, HomeFragment())
            addToBackStack("back") // Add each fragment transaction to the back stack
            commit()
        }



    }
}