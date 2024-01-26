package com.example.flexpay

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
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

        bottom_navigation_view = findViewById(R.id.bottom_navigation)
        setInitialFragment()

        bottom_navigation_view.setOnItemSelectedListener { item ->

            when (item.itemId) {
                R.id.navigation_home -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame_main, HomeFragment())
                        .commit()
                    true
                }

                R.id.navigation_transactions -> replaceFragment(TransactionsFragment())
                R.id.navigation_recharge -> replaceFragment(RechargeFragment())
                R.id.navigation_profile -> replaceFragment(ProfileFragment())
                else -> false
            }
        }
    }
    private fun replaceFragment(fragment: Fragment): Boolean {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frame_main, fragment)
            addToBackStack(null) // Add each fragment transaction to the back stack
            commit()
        }
        return true
    }
    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            // If there are fragments in the back stack, pop the stac
            updateSelectedItem()
        }
        super.onBackPressed()
    }

    private fun setInitialFragment() {
        replaceFragment(HomeFragment())
        bottom_navigation_view.menu.findItem(R.id.navigation_home)?.isChecked = true
    }
    private fun updateSelectedItem() {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.frame_main)
        when (currentFragment) {
            is HomeFragment -> bottom_navigation_view.selectedItemId = R.id.navigation_home
            is TransactionsFragment -> bottom_navigation_view.selectedItemId = R.id.navigation_transactions
            is RechargeFragment -> bottom_navigation_view.selectedItemId = R.id.navigation_recharge
            is ProfileFragment -> bottom_navigation_view.selectedItemId = R.id.navigation_profile
        }
    }

}