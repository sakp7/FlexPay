package com.example.flexpay.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.flexpay.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class RechargeFragment : Fragment() {
    private lateinit var amount1: EditText
    private lateinit var amount2: EditText
    private lateinit var rechargebtn: Button
    private var database: FirebaseDatabase = FirebaseDatabase.getInstance()
    private var dbReference: DatabaseReference = database.reference.child("users")

lateinit var backbtn:TextView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recharge, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        amount1 = view.findViewById(R.id.recharge_amount)
        amount2 = view.findViewById(R.id.recharge_amount2)
        rechargebtn = view.findViewById(R.id.rechargebtn)
        backbtn=view.findViewById(R.id.arrow_recharge)

        rechargebtn.setOnClickListener {

            val amt1= amount1.text?.toString()
            val amt2=amount2.text?.toString()
            if (amt1 == null || amt2 == null) {
                Toast.makeText(context, "Please enter valid amounts", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }



            if (amt1 != null && amt1!="") {
                if (amt1 == amt2) {
                    val amount = amount1.text?.toString()?.toInt()

                    updateDatabase(amount)


                } else {
                    Toast.makeText(context, "Both amounts should match", Toast.LENGTH_LONG).show()
                }
            }
            else{
                Toast.makeText(context, "Enter some amount", Toast.LENGTH_LONG).show()

            }

        }
        backbtn.setOnClickListener {
            fragmentManager?.popBackStack()

        }

    }

    fun updateDatabase(amount: Int?) {
        val auth = FirebaseAuth.getInstance()

        val userId = auth.currentUser?.uid
        val dbref = dbReference.child(userId.toString()).child("Balance")


//
//        dbref.setValue(amount).addOnSuccessListener {
//            Toast.makeText(context, "Balance has been updated", Toast.LENGTH_LONG).show()
//            requireActivity().supportFragmentManager.popBackStack()
//
//        }
//            .addOnFailureListener {
//                Toast.makeText(context, "Failed to update the balance", Toast.LENGTH_LONG).show()
//            }

        dbref.addListenerForSingleValueEvent(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    var currentBalance=snapshot.getValue(Int::class.java)

                   currentBalance= amount?.let { currentBalance?.plus(it) }
                    dbref.setValue(currentBalance)
                        .addOnSuccessListener {
                            Toast.makeText(context, "Balance has been updated", Toast.LENGTH_LONG).show()
            requireActivity().supportFragmentManager.popBackStack()

                        }
                        .addOnFailureListener {
                            Toast.makeText(context, "Failed to update the balance", Toast.LENGTH_LONG).show()

                        }

                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })


    }

}