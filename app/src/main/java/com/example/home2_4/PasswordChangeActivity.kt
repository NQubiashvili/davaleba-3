package com.example.home2_4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class PasswordChangeActivity : AppCompatActivity() {

    private lateinit var buttonPasswordChange: Button
    private lateinit var editTextTextPassword2: TextView
    private lateinit var editTextTextPassword: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password_change)

        init()
        registerListeners()
    }


    private fun init() {
        editTextTextPassword = findViewById(R.id.editTextPassword)
        editTextTextPassword2 = findViewById(R.id.editTextTextPassword2)
        buttonPasswordChange = findViewById(R.id.buttonPasswordChange)


    }
    private fun registerListeners() {
        buttonPasswordChange.setOnClickListener {
            val newPassword = editTextTextPassword.text.toString()

            if (newPassword.isEmpty() || newPassword.length <9) {
                Toast.makeText(this, "Error!, incorect password!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }else if (newPassword != editTextTextPassword.text.toString()) {
                Toast.makeText(this, "incorect password", Toast.LENGTH_SHORT).show()
            }
            FirebaseAuth.getInstance().currentUser?.updatePassword(newPassword)
                ?.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "YaY!", Toast.LENGTH_SHORT).show()
                    }else {
                        Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show()
                    }
                }


        }

    }


}