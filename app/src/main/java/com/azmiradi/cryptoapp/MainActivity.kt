package com.azmiradi.cryptoapp

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*


const val KEY_NAME= "key_name"
class MainActivity : AppCompatActivity() {
    lateinit var cryptoData: CryptoData

    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val textData = findViewById<TextView>(R.id.text)
        val inputEditText = findViewById<EditText>(R.id.data)
        val saveBu = findViewById<Button>(R.id.save)
        val readBu = findViewById<Button>(R.id.read)
        cryptoData = CryptoData()

        sharedPreferences = getSharedPreferences("setting", MODE_PRIVATE)

        saveBu.setOnClickListener {
            saveName(inputEditText.text.toString())
        }

        readBu.setOnClickListener {
            textData.text = readName()
        }
    }

    private fun saveName(name: String) {
        val bytes = name.toByteArray()
        val encryptedText =
            Base64.getEncoder().encodeToString(cryptoData.encrypt(bytes = bytes))

        val editor = sharedPreferences.edit()
        editor.putString(KEY_NAME,encryptedText)
        editor.apply()
    }

    private fun readName(): String {
        val name = sharedPreferences.getString(KEY_NAME, "")
        val originalText =
            cryptoData.decrypt(Base64.getDecoder().decode(name))?.decodeToString()
        return originalText ?: ""
    }

}