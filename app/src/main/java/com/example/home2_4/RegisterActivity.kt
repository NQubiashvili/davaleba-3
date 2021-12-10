package com.example.home2_4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {

    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var buttonRegistration: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        init()
        registerListeners()
    }

    private fun init() {

        editTextEmail = findViewById(R.id.editTextEmail)
        editTextPassword = findViewById(R.id.editTextPassword)
        buttonRegistration = findViewById(R.id.buttonRegistration)


    }

    private fun registerListeners() {
        buttonRegistration.setOnClickListener {
            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "empty!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(!password.matches(".*[0-9].*".toRegex())){
                Toast.makeText(this, "put min 1 number", Toast.LENGTH_SHORT).show()
            }
            if (!password.matches(".*[a-z].*".toRegex())){
                Toast.makeText(this, "put min 1 LowerCase char!", Toast.LENGTH_SHORT).show()
        }
            if (!password.matches(".*[A-Z].*".toRegex())){
                Toast.makeText(this, "put min 1 UpperCase char!", Toast.LENGTH_SHORT).show()
            }

            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        startActivity(Intent(this, LoginActivity::class.java))
                        finish()
                }else{
                        Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show()
                    }

        }

        }
    }
}

