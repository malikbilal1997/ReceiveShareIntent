package com.thephoenixdevelopers.sharefile

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.thephoenixdevelopers.sharefile.databinding.ActivityReceiveBinding

class ReceiveActivity : AppCompatActivity() {

    private lateinit var binding: ActivityReceiveBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityReceiveBinding
            .inflate(layoutInflater)

        setContentView(binding.root)

        getReceivedIntent()
    }


    private fun getReceivedIntent() {

        intent.extras?.getParcelable<Uri>(Intent.EXTRA_STREAM)?.let { uri ->

            kotlin.runCatching {
                contentResolver.openInputStream(uri)
            }.onSuccess {

                Log.d(TAG, "Success -> $it")

            }.onFailure {
                Log.d(TAG, "Error -> $it")
            }


            Log.d(TAG, "Uri -> $uri")
            binding.imageView.setImageURI(uri)

        }

    }

    companion object {

        const val TAG = "ReceiveActivity"

    }
}