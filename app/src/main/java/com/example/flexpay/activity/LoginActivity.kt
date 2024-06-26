package com.example.flexpay.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.flexpay.fragments.LoginFragment
import com.example.flexpay.R

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)



        val fragmentManager: FragmentManager =supportFragmentManager
        val fragmentTransaction: FragmentTransaction =fragmentManager.beginTransaction()

        val login= LoginFragment()

        fragmentTransaction.add(R.id.login_frame,login)
        fragmentTransaction.addToBackStack(null)

        fragmentTransaction.commit()

    }
}