package com.example.flexpay

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import kotlin.math.log


class ProfileFragment : Fragment() {

    private lateinit var logoutBtn: Button
    private lateinit var resetpassword: Button
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        resetpassword=view.findViewById(R.id.btn_reset_password)
        logoutBtn=view.findViewById(R.id.btnlogout)
        sharedPreferences =
            requireActivity().getSharedPreferences("loginData", Context.MODE_PRIVATE)
        logoutBtn.setOnClickListener {
            val editor = sharedPreferences.edit()
            editor.putBoolean("isLoggedIn", false)
            editor.apply()
            startActivity(Intent(context, LoginActivity::class.java))
            requireActivity().finish()
        }

    }


}


