package com.example.flexpay

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity2 : AppCompatActivity() {

    private lateinit var bottom_navigation_view: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recy)

//        bottom_navigation_view = findViewById(R.id.bottomnavbar)
//
//        setInitialFragment()
//
//        bottom_navigation_view.setOnItemSelectedListener { item ->
//
//            when (item.itemId) {
//                R.id.page_1 -> {
//                    replaceFragment(HomeFragment())
//
//                }R.id.page_2 -> replaceFragment(RechargeFragment())
//                R.id.page_3 -> replaceFragment(TransactionsFragment())
//                R.id.page_4 -> replaceFragment(ProfileFragment())
//                else -> false
//            }
//        }
//
//
//    }
//
//    private fun replaceFragment(fragment: Fragment): Boolean {
//        supportFragmentManager.beginTransaction().apply {
//            replace(R.id.frame_main, fragment)
//            addToBackStack(null) // Add each fragment transaction to the back stack
//            commit()
//        }
//
//        return true
//    }
//    private fun setInitialFragment() {
//        replaceFragment(HomeFragment())
//        bottom_navigation_view.menu.findItem(R.id.page_1)?.isChecked = true
//    }
//    override fun onBackPressed() {
//        if (supportFragmentManager.backStackEntryCount > 0) {
//            // If there are fragments in the back stack, pop the stac
//            updateSelectedItem()
//        }else {
//            // If the back stack is empty, open HomeFragment directly
//            bottom_navigation_view.selectedItemId = R.id.page_1
//        }
//        super.onBackPressed()
//    }
//    private fun updateSelectedItem() {
//
//        val currentFragment = supportFragmentManager.findFragmentById(R.id.frame_main)
//        bottom_navigation_view.menu.findItem(when (currentFragment) {
//            is HomeFragment -> R.id.page_1
//            is RechargeFragment -> R.id.page_2
//            is TransactionsFragment -> R.id.page_3
//            is ProfileFragment -> R.id.page_4
//            else -> R.id.page_1
//        })?.isChecked = true
    }
}