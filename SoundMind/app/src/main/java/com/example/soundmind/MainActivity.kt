package com.example.soundmind

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.soundmind.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.loginButton.setOnClickListener {
            val sm = Intent(this, loading::class.java)
            startActivity(sm)
        }
        binding.signButton.setOnClickListener {
            val email = binding.emailEdit.text.toString()
            val pword = binding.passEdit.text.toString()
            val confirmpword = binding.confirmpass.text.toString()

            if (email.isNotEmpty() && pword.isNotEmpty() && confirmpword.isNotEmpty()) {
                if (pword == confirmpword) {

                    firebaseAuth.createUserWithEmailAndPassword(email, pword)
                        .addOnCompleteListener {
                            if (it.isSuccessful) {
                                val sm = Intent(this, Login::class.java)
                                startActivity(sm)

                            } else {
                                Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }
                } else {
                    Toast.makeText(this, "Password is Incorrect", Toast.LENGTH_SHORT).show()

                }
            } else {
                Toast.makeText(this, "Empty Fields is not Allowed!", Toast.LENGTH_SHORT).show()
            }


        }


    }
}