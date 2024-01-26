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
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import com.google.firebase.auth.FirebaseAuth


class LoginFragment : Fragment() {

    lateinit var text: TextView
    lateinit var btnlogin: Button
    var email: EditText? = null
    var password: EditText? = null
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPreferences = requireActivity().getSharedPreferences("loginData", Context.MODE_PRIVATE)

        text = view.findViewById(R.id.signuptxt)
        btnlogin = view.findViewById(R.id.btnlogin)
        email = view.findViewById(R.id.email_login)
        password = view.findViewById(R.id.etxt_password_login)
        text.setOnClickListener {
            openSignUpFragement()
        }
        btnlogin.setOnClickListener {
            val enteredEmail = email?.text?.toString()
            val enteredPassword = password?.text?.toString()
            if (!enteredEmail.isNullOrEmpty() && !enteredPassword.isNullOrEmpty()) {

//logic for login
                loginWithFirebase(enteredEmail, enteredPassword)


            } else {
                //toast msg
                Toast.makeText(context, "Enter both the fields", Toast.LENGTH_SHORT).show()

            }

        }


    }

    private fun openSignUpFragement() {
        val signUp = SignUpFragment()

        parentFragmentManager.beginTransaction()
            .replace(
                R.id.login_frame,
                signUp
            )
            .addToBackStack(null) // This line allows the user to go back to the previous fragment when pressing back
            .commit()
    }

    fun loginWithFirebase(userEmail: String, userPassword: String) {

        auth.signInWithEmailAndPassword(userEmail, userPassword)
            .addOnCompleteListener() { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user = auth.currentUser
                    Toast.makeText(context, "Login success", Toast.LENGTH_SHORT).show()
                    val editor = sharedPreferences.edit()
                    editor.putBoolean("isLoggedIn", true)
                    editor.apply()

                    startActivity(Intent(requireContext(), MainActivity::class.java).putExtra("roll",2))
                    activity?.finish()
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(
                        context,
                        "Authentication failed.",
                        Toast.LENGTH_SHORT,
                    ).show()
                }
            }

    }
}