package com.example.soundmind

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import com.example.soundmind.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signupButton.setOnClickListener {
            val sm = Intent(this, MainActivity::class.java)
            startActivity(sm)
        }

        binding.logButton.setOnClickListener {
            val email = binding.emailEdit.text.toString()
            val pword = binding.passEdit.text.toString()

            if (email.isNotEmpty() && pword.isNotEmpty()) {


                firebaseAuth.signInWithEmailAndPassword(email, pword)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            val sm = Intent(this, SMHome::class.java)
                            startActivity(sm)

                        } else {
                            Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT)
                                .show()
                        }
                    }

            } else {
                Toast.makeText(this, "Empty Fields is not Allowed!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}