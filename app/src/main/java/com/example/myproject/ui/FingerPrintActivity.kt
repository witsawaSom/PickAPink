package com.example.myproject.ui

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Rect
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import com.example.myproject.R


class FingerPrintActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finger_print)

//        val biometricManager = BiometricManager.from(applicationContext)
//        val biometricPrompt = instanceOfBiometricPrompt()
//        val promptInfo = getPromptInfo()
//        val canAuthenticate = biometricManager.canAuthenticate()
//
//        if (biometricManager.canAuthenticate() == BiometricManager.BIOMETRIC_SUCCESS) {
//            biometricPrompt.authenticate(promptInfo)
//        } else {
//            Log.d("TAG", "could not authenticate because: $canAuthenticate")
//        }
    }

    private fun instanceOfBiometricPrompt(): BiometricPrompt {
        val executor = ContextCompat.getMainExecutor(this)

        val callback = object : BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                super.onAuthenticationError(errorCode, errString)
                Toast.makeText(applicationContext, "$errorCode :: $errString", Toast.LENGTH_SHORT).show()
            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
                Toast.makeText(applicationContext, "Authentication failed", Toast.LENGTH_SHORT).show()
            }

            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
                Toast.makeText(applicationContext, "Authentication was successful", Toast.LENGTH_SHORT).show()
            }
        }

        return BiometricPrompt(this, executor, callback)
    }

    private fun getPromptInfo(): BiometricPrompt.PromptInfo {
        return BiometricPrompt.PromptInfo.Builder()
            .setTitle("My App's Authentication")
            .setSubtitle("Please login to get access")
            .setDescription("My App is using Android biometric authentication")
            .setDeviceCredentialAllowed(true)
            .build()
    }


}
