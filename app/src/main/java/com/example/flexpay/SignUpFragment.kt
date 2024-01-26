package com.example.flexpay

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUpFragment : Fragment() {
    lateinit var text: TextView
    private lateinit var btnsignUp: Button
    private var name: EditText? = null
    private var email: EditText? = null
    private var rollNumber: EditText? = null
    private var password: EditText? = null
    private var confirmPassword: EditText? = null


    val db = Firebase.firestore
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private var database: FirebaseDatabase = FirebaseDatabase.getInstance()
    private var dbReference: DatabaseReference = database.reference.child("users")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        name = view.findViewById(R.id.etxtsignup)
        rollNumber = view.findViewById(R.id.etxtroll)
        email = view.findViewById(R.id.signup_email)
        password = view.findViewById(R.id.signpass_1)
        confirmPassword = view.findViewById(R.id.signpass_2)

        btnsignUp = view.findViewById(R.id.btnsignup)
        btnsignUp.setOnClickListener {
            val enteredname = name?.text?.toString()
            val enteredRollNumber = rollNumber?.text?.toString()
            val enteredEmail = email?.text?.toString()
            val enteredPassword = password?.text?.toString()
            val enteredConfirmPassword = confirmPassword?.text?.toString()
            if (enteredname.isNullOrEmpty() ||
                enteredEmail.isNullOrEmpty() ||
                enteredRollNumber.isNullOrEmpty()
                || enteredPassword.isNullOrEmpty()
                || enteredConfirmPassword.isNullOrEmpty()
            ) {
                //raise a toast msg
                Toast.makeText(context, "Enter all the fields", Toast.LENGTH_SHORT).show()

            } else if (!enteredPassword.equals(enteredConfirmPassword)) {
                //raise a toast for passwords not equal
                Toast.makeText(context, "Passwords are not equal", Toast.LENGTH_SHORT).show()

            } else {

                signUpWithFirebase(enteredEmail, enteredPassword)
                createdbforuser(enteredname, enteredRollNumber, enteredEmail)

            }
        }
    }

    private fun createdbforuser(userName: String, roll_number: String, userEmail: String) {
        val userBalance: Int = 0
        val user = hashMapOf(
            "Name" to userName,
            "Email" to userEmail,
            "roll_number" to roll_number,
            "Balance" to userBalance,
            "Status" to -1
        )
        val mAuth = FirebaseAuth.getInstance()
        val myContext = context
        val userId = mAuth.currentUser.toString()
        if (userId != null) {
            dbReference.child(userId).setValue(user)
                .addOnSuccessListener {

                    Toast.makeText(myContext, "Account created", Toast.LENGTH_SHORT)
                        .show()
                }
                .addOnFailureListener {
                    Toast.makeText(myContext, "Account could not be created", Toast.LENGTH_SHORT)
                        .show()
                }
            requireActivity().supportFragmentManager.popBackStack()

        }
    }


    private fun signUpWithFirebase(userEmail: String, userPassword: String) {
        val myContext1 = context
        auth.createUserWithEmailAndPassword(
            userEmail, userPassword

        ).addOnCompleteListener { task ->
            if (task.isSuccessful) {

                Toast.makeText(myContext1, "Account has been created", Toast.LENGTH_SHORT).show()

//                supportFragmentManager.popBackStack()

            } else {
                Toast.makeText(myContext1, "Account could not be created", Toast.LENGTH_SHORT)
                    .show()
            }
        }


    }


}