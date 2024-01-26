package com.example.flexpay

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button


class SplashFragment2 : Fragment() {

    lateinit var btn:Button
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn=view.findViewById(R.id.btn_2)
        btn.setOnClickListener {
            val splash3=SplashFragment3()

            parentFragmentManager.beginTransaction()
                .replace(R.id.frame, splash3) // R.id.fragment_container should be the ID of the container in your activity
                .addToBackStack(null) // This line allows the user to go back to the previous fragment when pressing back
                .commit()


        }


    }


}