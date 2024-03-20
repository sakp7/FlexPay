package com.example.flexpay.fragments
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.auth.FirebaseAuth
import android.view.GestureDetector
import android.view.MotionEvent
import androidx.core.content.ContextCompat
import com.example.flexpay.R
import com.example.flexpay.utils.NotificationUtils
import com.google.firebase.messaging.FirebaseMessaging

class HomeFragment : Fragment() {
    lateinit var balancetxt: TextView
    lateinit var name1: TextView
    lateinit var voucherbtn: ImageView
    lateinit var transactionsbtn: ImageView
    lateinit var rechargebtn: ImageView
    lateinit var profilebtn: ImageView
    private lateinit var swipeButton: Button
    private lateinit var gestureDetector: GestureDetector
    private var database: FirebaseDatabase = FirebaseDatabase.getInstance()
    val auth = FirebaseAuth.getInstance()

    val userId = auth.currentUser?.uid
    lateinit var secureCard: Button
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        balancetxt = view.findViewById(R.id.balancetxt)
        rechargebtn = view.findViewById(R.id.rechargebtnhome)
        profilebtn = view.findViewById(R.id.profilebtnhome)
        voucherbtn = view.findViewById(R.id.vouchersbtnhome)
        transactionsbtn = view.findViewById(R.id.transactionsbtnhome)
        secureCard = view.findViewById(R.id.btnsecure)


        val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
        Log.d("error", userId.toString())
        val dbReference: DatabaseReference =
            database.reference.child("users").child(userId.toString())
        dbReference.addValueEventListener(
            object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
//                        val userName = snapshot.child("name").getValue(String::class.java)
                        val userBalance = snapshot.child("Balance").getValue(Int::class.java)
                        if (userBalance != null) {
//                            balancetxt.setText(userBalance)
                            balancetxt.text = "₹ " + userBalance.toString()
                        }
//                        name1.text = "Card Holder Name: $userName"
                    }
                }


                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }


            }
        )

        rechargebtn.setOnClickListener {
            replaceFragment(RechargeFragment())
        }
        transactionsbtn.setOnClickListener {
            replaceFragment(TransactionsFragment())

        }
        voucherbtn.setOnClickListener {
            replaceFragment(VoucherFragment())

        }
        profilebtn.setOnClickListener {
            replaceFragment(ProfileFragment())

        }


        swipeButton = view.findViewById(R.id.btnsecure)
        gestureDetector = GestureDetector(requireContext(), SwipeGestureListener())

        val securecardReferance: DatabaseReference =
            database.reference.child("users").child(userId.toString()).child("secureStatus")
        securecardReferance.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot5: DataSnapshot) {
                val data = snapshot5.value.toString()
                if (data == "1") {
                    swipeButton.setBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.green
                        )
                    )
                    swipeButton.text = "Swipe right to block"
                } else {
                    swipeButton.setBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.red
                        )
                    )
                    swipeButton.text = "Swipe left to unblock"
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        }

        )


        swipeButton.setOnTouchListener { _, event ->
            gestureDetector.onTouchEvent(event)
        }


    }

    inner class SwipeGestureListener : GestureDetector.SimpleOnGestureListener() {
        private val SWIPE_THRESHOLD = 100

        override fun onFling(
            e1: MotionEvent?,
            e2: MotionEvent,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            val deltaX = e2.x?.minus(e1?.x ?: 0f) ?: 0f

            if (deltaX > SWIPE_THRESHOLD) {
                makeRed()
                return true
            }

            // Reset to initial position
            resetButtonState()
            return false
        }
    }

    private fun resetButtonState() {
        swipeButton.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.green))
        swipeButton.text = "Swipe right to block"
        val statusReference =
            database.reference.child("users").child(userId.toString()).child("secureStatus")
        statusReference.setValue("1").addOnSuccessListener {
            FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val token = task.result
                    // Use the FCM token as needed
                    println("FCM Token: $token")
                    NotificationUtils.sendFailureNotification(token)


                } else {
                    println("Failed to get FCM token: ${task.exception}")
                }
            }
        }
            .addOnFailureListener {

            }

    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction: FragmentTransaction? = fragmentManager?.beginTransaction()

        transaction?.replace(R.id.frame_main, fragment)
        transaction?.addToBackStack(null)
        transaction?.commit()
    }

    private fun makeGreen() {
        swipeButton.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.green))
        swipeButton.text = "Swipe right to block"

        val statusReference =
            database.reference.child("users").child(userId.toString()).child("secureStatus")
        statusReference.setValue("1").addOnSuccessListener {

        }
            .addOnFailureListener {

            }

    }

    private fun makeRed() {
        swipeButton.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.red))
        swipeButton.text = "Swipe left to unblock"


        val statusReference =
            database.reference.child("users").child(userId.toString()).child("secureStatus")
        statusReference.setValue("-1").addOnSuccessListener {
            FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val token = task.result
                    // Use the FCM token as needed
                    Log.d("token",token)
                    NotificationUtils.sendFailureNotification(token)

                } else {
                    Log.d("token","fail")
                }
            }
                .addOnFailureListener {

                }


        }
    }
}






