package com.example.flexpay.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.flexpay.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUpFragment : Fragment() {
    private lateinit var btnsignUp: Button
    private var name: EditText? = null
    private var email: EditText? = null
    private var rollNumber: EditText? = null
    private var password: EditText? = null
    private var confirmPassword: EditText? = null

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private var database: FirebaseDatabase = FirebaseDatabase.getInstance()
    private var dbReference: DatabaseReference = database.reference.child("users")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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
            val enteredName = name?.text?.toString()
            val enteredRollNumber = rollNumber?.text?.toString()
            val enteredEmail = email?.text?.toString()
            val enteredPassword = password?.text?.toString()
            val enteredConfirmPassword = confirmPassword?.text?.toString()

            if (enteredName.isNullOrEmpty() ||
                enteredEmail.isNullOrEmpty() ||
                enteredRollNumber.isNullOrEmpty() ||
                enteredPassword.isNullOrEmpty() ||
                enteredConfirmPassword.isNullOrEmpty()
            ) {
                // Raise a toast message
                Toast.makeText(context, "Enter all the fields", Toast.LENGTH_SHORT).show()
            } else if (enteredPassword != enteredConfirmPassword) {
                // Raise a toast for passwords not equal
                Toast.makeText(context, "Passwords are not equal", Toast.LENGTH_SHORT).show()
            } else {
                signUpWithFirebase(enteredEmail, enteredPassword, enteredName, enteredRollNumber)
            }
        }
    }

    private fun createdbforuser(user: FirebaseUser?, userName: String, roll_number: String) {
        val userBalance: Int = 0
        val userId = user?.uid
        if (userId != null) {
            val userMap = hashMapOf(
                "Name" to userName,
                "roll_number" to roll_number,
                "Balance" to userBalance,
                "Status" to -1,
                "transactions" to hashMapOf<String, Any>()
            )

            dbReference.child(userId).setValue(userMap)
                .addOnSuccessListener {
                }
                .addOnFailureListener {
                    Toast.makeText(requireContext(), "Account could not be created", Toast.LENGTH_SHORT).show()
                }

            requireActivity().supportFragmentManager.popBackStack()
        }
    }

    private fun signUpWithFirebase(
        userEmail: String,
        userPassword: String,
        userName: String,
        rollNumber: String
    ) {
        auth.createUserWithEmailAndPassword(userEmail, userPassword)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    if (user != null) {
                        createdbforuser(user, userName, rollNumber)
                        Toast.makeText(context, "Account has been created", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "User is null", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(context, "Account could not be created", Toast.LENGTH_SHORT).show()
                }
            }
    }
}
