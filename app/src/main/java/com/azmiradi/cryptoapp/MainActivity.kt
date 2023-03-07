package com.azmiradi.cryptoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import java.util.*

class MainActivity : AppCompatActivity() {
    var cipherText: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val cryptoData = CryptoData()

        val inputEditText = findViewById<EditText>(R.id.data)

        findViewById<Button>(R.id.encrypt)
            .setOnClickListener {

                val bytes = inputEditText.text.toString().toByteArray()

                cipherText= Base64.getEncoder().encodeToString(cryptoData.encrypt(bytes = bytes))

                inputEditText.setText(cipherText)
            }

        findViewById<Button>(R.id.decrypt)
            .setOnClickListener {
                val originalText = cryptoData.decrypt(Base64.getDecoder().decode(cipherText))?.decodeToString()

                inputEditText.setText(originalText)
            }
    }

}