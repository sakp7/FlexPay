package com.example.flexpay
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class HomeFragment : Fragment() {

    lateinit var balancetxt: TextView
    lateinit var name1: TextView

    private var database: FirebaseDatabase = FirebaseDatabase.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        balancetxt = view.findViewById(R.id.balancetxt)
        name1 = view.findViewById(R.id.card_holder_name)
        val userId = "Zv6R6Df1wLR1xXhfPLRuqRZoFL03"

        val dbReference: DatabaseReference = database.reference.child("users").child(userId)
        dbReference.addValueEventListener(
            object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        val userName = snapshot.child("name").getValue(String::class.java)
                        val userBalance = snapshot.child("balance").getValue(Int::class.java)
                        if (userBalance != null) {
//                            balancetxt.setText(userBalance)
                            balancetxt.text = userBalance.toString()

                        }
                        name1.text = "Card Holder Name: $userName"
                    }
                }


                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }


            }
        )


    }
}
