package com.example.flexpay.fragments

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.flexpay.R
import com.example.flexpay.activity.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class ProfileFragment : Fragment() {
    lateinit var userName:TextView
    private lateinit var userRoll:TextView
    private lateinit var logoutBtn: Button
    private lateinit var resetpassword: Button
    private lateinit var sharedPreferences: SharedPreferences
    lateinit var backbtn: TextView
    private var database: FirebaseDatabase = FirebaseDatabase.getInstance()
    private var dbReference: DatabaseReference = database.reference.child("users")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        backbtn=view.findViewById(R.id.arrow_profile)
        userName=view.findViewById(R.id.userName)
        userRoll=view.findViewById(R.id.userRoll)

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
        backbtn.setOnClickListener {
            fragmentManager?.popBackStack()

        }
        val auth = FirebaseAuth.getInstance()

        val userId = auth.currentUser?.uid

        dbReference.child(userId.toString()).addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    val username=snapshot.child("name").value
                    val userroll=snapshot.child("roll").value
                    userName.text=username.toString()
                    userRoll.text=userroll.toString()

                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        })



    }


}


