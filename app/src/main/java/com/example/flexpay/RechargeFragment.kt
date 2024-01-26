package com.example.flexpay

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
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
        rechargebtn.setOnClickListener {

            val amt1= amount1.text?.toString()?.toInt()
            val amt2=amount2.text?.toString()?.toIntOrNull()

            val amount = amount1.text?.toString()?.toInt()
            if (amt1 != null) {
                if (amt1 == amt2) {
                    updateDatabase(amount)


                } else {
                    Toast.makeText(context, "Both amounts should match", Toast.LENGTH_LONG).show()
                }
            }


        }
    }

    fun updateDatabase(amount: Int?) {
        val userId = "Zv6R6Df1wLR1xXhfPLRuqRZoFL03"
        val dbref = dbReference.child(userId).child("balance")


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