package com.example.flexpay.activity

import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.flexpay.fragments.HomeFragment
import com.example.flexpay.fragments.ProfileFragment
import com.example.flexpay.R
import com.example.flexpay.fragments.RechargeFragment
import com.example.flexpay.fragments.TransactionsFragment

class ExpActivity : AppCompatActivity() {

    lateinit var btn1:ImageButton
    lateinit var btn2:ImageButton
    lateinit var btn3:ImageButton
    lateinit var btn4:ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_exp2)

        btn1=findViewById(R.id.home_exp2)
        btn2=findViewById(R.id.recharge_exp2)
        btn3=findViewById(R.id.list_exp2)
        btn4=findViewById(R.id.profile_exp2)
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame_exp2, HomeFragment())
            .addToBackStack("back")
            .commit()

        btn1.setOnClickListener {

            supportFragmentManager.beginTransaction().replace(R.id.frame_exp2, HomeFragment())
                .addToBackStack("back")
                .commit()

        }
btn2.setOnClickListener {
    supportFragmentManager.beginTransaction()
        .replace(R.id.frame_exp2, RechargeFragment())
        .addToBackStack("back")
        .commit()

}
        btn3.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.frame_exp2, TransactionsFragment())
                .addToBackStack("back")
                .commit()

        }
        btn4.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.frame_exp2, ProfileFragment())
                .addToBackStack("back")
                .commit()

        }


    }


}