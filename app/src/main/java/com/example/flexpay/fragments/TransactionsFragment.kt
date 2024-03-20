package com.example.flexpay.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flexpay.R
import com.example.flexpay.Transaction
import com.example.flexpay.utils.TransactionAdapter
import com.example.flexpay.utils.User
import com.example.flexpay.utils.MyAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class TransactionsFragment : Fragment() {

    private lateinit var dbref: DatabaseReference
    private lateinit var userrecyclerView: RecyclerView
    private lateinit var userArrayList: ArrayList<User>
    lateinit var backbtn: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TransactionAdapter
    private val databaseReference = FirebaseDatabase.getInstance().getReference("users")
    private val transactions: MutableList<Transaction> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_transactions, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        backbtn = view.findViewById(R.id.arrow_transactions)

        backbtn.setOnClickListener {
            fragmentManager?.popBackStack()

        }


        userrecyclerView = view.findViewById(R.id.recyclerview_transactions)
        userrecyclerView.layoutManager = LinearLayoutManager(context)
        userrecyclerView.setHasFixedSize(true)
        userArrayList = arrayListOf<User>()
        getUserData()


    }

    private fun getUserData() {
            val auth = FirebaseAuth.getInstance()
            val userId = auth.currentUser?.uid
            dbref = FirebaseDatabase.getInstance().getReference("users").child(userId.toString()).child("transactions")

            dbref.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        // Clear the existing data
                        userArrayList.clear()
                        for (userSnapshot in snapshot.children) {
                            val user = userSnapshot.getValue(User::class.java)
                            userArrayList.add(user!!)
                        }
                        // Create and set the adapter outside the loop to avoid unnecessary adapter creations
                        val adapter = MyAdapter(userArrayList)
                        userrecyclerView.adapter = adapter
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    // Handle error if needed
                }
            })
        }


    }


