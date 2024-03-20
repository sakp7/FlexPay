package com.example.flexpay.activity

import android.app.KeyguardManager
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.hardware.biometrics.BiometricPrompt
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CancellationSignal
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.example.flexpay.R

class BioActivity : AppCompatActivity() {
    lateinit var btnauth:Button
    private var cancellationSignal:CancellationSignal?=null
    private val authenticationCallback:BiometricPrompt.AuthenticationCallback
        get() =
            object :BiometricPrompt.AuthenticationCallback(){
                override fun onAuthenticationError(errorCode: Int, errString: CharSequence?) {
                    super.onAuthenticationError(errorCode, errString)
               notifyUser("Auth error:$errString")
                }

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult?) {
                    super.onAuthenticationSucceeded(result)
                notifyUser("Auth success")
                    startActivity(Intent(this@BioActivity, MainActivity::class.java))
                }
            }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bio)
//    btnauth=findViewById(R.id.buttonauth)



        checkBiometricSupport()
    btnauth.setOnClickListener {

        val biometricPrompt=BiometricPrompt.Builder(this)
            .setTitle("Biometric")
            .setSubtitle("Continue after biometric")
            .setDescription("As flex pay deals with financial details biometric is mandatory")
            .setNegativeButton("Cancel",this.mainExecutor,DialogInterface.OnClickListener{ dialog,which->
                notifyUser("Auth cancelled")

            }).build()
        biometricPrompt.authenticate(getCancellationSignal(),mainExecutor,authenticationCallback)
    }


    }

    private fun notifyUser(msg:String){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
    }


    private fun getCancellationSignal():CancellationSignal{
        cancellationSignal= CancellationSignal()
        cancellationSignal?.setOnCancelListener {
            notifyUser("Auth was cancelled by user")

        }
        return cancellationSignal as CancellationSignal

    }


    private fun checkBiometricSupport():Boolean{


        val keyguardManager=getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager

        if(!keyguardManager.isKeyguardSecure){
            notifyUser("Fingerprint service is not enabled in settings")
            return false
        }
        if(ActivityCompat.checkSelfPermission(this,android.Manifest.permission.USE_BIOMETRIC)!=PackageManager.PERMISSION_GRANTED){
            notifyUser("Fingerprint service permission is not enabled in settings")

            return false
        }
        return if (packageManager.hasSystemFeature(PackageManager.FEATURE_FINGERPRINT)){
            true
        } else true






    }
}