package com.thephoenixdevelopers.sharefile

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.thephoenixdevelopers.sharefile.databinding.ActivityShareBinding

class ShareActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShareBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityShareBinding
            .inflate(layoutInflater)

        setContentView(binding.root)

        initClickListener()

    }

    private fun initClickListener() {

        binding.materialButton.setOnClickListener {
            passReceivedIntent()
        }
    }

    private fun passReceivedIntent() {

        intent.extras?.getParcelable<Uri>(Intent.EXTRA_STREAM)?.let { uri ->

            Log.d(MainActivity.TAG, "Uri -> $uri")

            Intent(this, ReceiveActivity::class.java).apply {
                type = intent.type
                action = intent.action
                putExtra(Intent.EXTRA_STREAM, uri)
                startActivity(this)
            }

        }

    }
}