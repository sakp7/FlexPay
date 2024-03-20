package com.example.flexpay.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.flexpay.R


class SplashFragement : Fragment() {
    lateinit var button: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash_fragement, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button = view.findViewById(R.id.btn1_1)

        button.setOnClickListener {
            val splash2= SplashFragment2()

            parentFragmentManager.beginTransaction()
                .replace(R.id.frame, splash2) // R.id.fragment_container should be the ID of the container in your activity
                .addToBackStack(null) // This line allows the user to go back to the previous fragment when pressing back
                .commit()

        }


    }


}