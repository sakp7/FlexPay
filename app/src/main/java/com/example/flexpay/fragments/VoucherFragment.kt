package com.example.flexpay.fragments

import android.os.Bundle
import android.util.Log
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


class VoucherFragment : Fragment() {
    lateinit var backbtn: TextView
    lateinit var voucher_id1:EditText
    lateinit var voucher_id2:EditText
    lateinit var voucherbtn:Button
    private var database: FirebaseDatabase = FirebaseDatabase.getInstance()
    private var dbReference: DatabaseReference = database.reference.child("vouchers")
    private var dbReference2: DatabaseReference = database.reference.child("users")

    private var dbReference3: DatabaseReference = database.reference.child("vouchers")



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_voucher, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        backbtn=view.findViewById(R.id.arrow_voucher)
        voucher_id1=view.findViewById(R.id.vouchertxt1)
        voucher_id2=view.findViewById(R.id.vouchertxt2)
        voucherbtn=view.findViewById(R.id.voucherbtnsubmit)

        voucherbtn.setOnClickListener {
            val t1=voucher_id1.text.toString()
            val t2=voucher_id2.text.toString()
            if(t1!=t2){
                Toast.makeText(context, "voucher id's mismatch", Toast.LENGTH_LONG).show()

            }
            else{
                val auth = FirebaseAuth.getInstance()

                val userId = auth.currentUser?.uid

                dbReference3.child(t1).addListenerForSingleValueEvent(object :ValueEventListener{
                    override fun onDataChange(snapshot1: DataSnapshot) {
                        if (snapshot1.exists()){
                            val flag=snapshot1.child("F").value
                            Log.d("F", flag.toString())
                            if (flag=="null"){
                                Toast.makeText(context,"Voucher aldready redeemed",Toast.LENGTH_SHORT).show()
                            }
                            else{
                                dbReference.child(t1).addListenerForSingleValueEvent(object : ValueEventListener {
                                    override fun onDataChange(voucherSnapshot: DataSnapshot) {
                                        if (voucherSnapshot.exists()) {
                                            val voucherAmount = voucherSnapshot.child("amount").getValue(Int::class.java)
                                            Log.d("error",voucherAmount.toString())

                                            dbReference2.child(userId.toString()).child("Balance").addListenerForSingleValueEvent(object : ValueEventListener {
                                                override fun onDataChange(userSnapshot: DataSnapshot) {
                                                    Log.d("error",userId.toString())
                                                    if (userSnapshot.exists()) {
                                                        var currentBalance = userSnapshot.getValue(Int::class.java)
                                                        Log.d("error1",currentBalance.toString())
                                                        currentBalance = currentBalance?.plus(voucherAmount ?: 0)

                                                        dbReference2.child(userId.toString()).child("Balance").setValue(currentBalance)
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

                                                }
                                            })
                                        } else {
                                            Toast.makeText(context, "Invalid voucher ID", Toast.LENGTH_LONG).show()
                                        }
                                    }

                                    override fun onCancelled(error: DatabaseError) {


                                    }
                                })
                            }
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        TODO("Not yet implemented")
                    }


                })




                }
        }



        backbtn.setOnClickListener {
            fragmentManager?.popBackStack()

        }
    }
    }
