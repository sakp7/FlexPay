package com.example.flexpay

import android.os.Bundle
import android.widget.ImageButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.flexpay.databinding.ActivityExp2Binding

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
            .replace(R.id.frame_exp2,HomeFragment())
            .addToBackStack("back")
            .commit()

        btn1.setOnClickListener {

            supportFragmentManager.beginTransaction().replace(R.id.frame_exp2,HomeFragment())
                .addToBackStack("back")
                .commit()

        }
btn2.setOnClickListener {
    supportFragmentManager.beginTransaction()
        .replace(R.id.frame_exp2,RechargeFragment())
        .addToBackStack("back")
        .commit()

}
        btn3.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.frame_exp2,TransactionsFragment())
                .addToBackStack("back")
                .commit()

        }
        btn4.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.frame_exp2,ProfileFragment())
                .addToBackStack("back")
                .commit()

        }


    }


}