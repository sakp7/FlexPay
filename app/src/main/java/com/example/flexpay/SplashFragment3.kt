package com.example.flexpay

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button


class SplashFragment3 : Fragment() {
        lateinit var btn:Button
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash3, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn=view.findViewById(R.id.btn_3)
        btn.setOnClickListener {
            val intent=Intent(requireContext(),LoginActivity::class.java)
            startActivity(intent)
            activity?.finish()

        }


    }

}