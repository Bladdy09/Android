package com.example.primerproyecto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LoginActivity2 : AppCompatActivity() {

    private lateinit var tvRedirectSignUp: TextView
    private lateinit var etEmail: EditText
    private lateinit var etPass: EditText
    private lateinit var btnLogin: Button
    // Creating firebaseAuth object

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login2)

        tvRedirectSignUp = findViewById(R.id.tvRedirectSignUp)

        btnLogin = findViewById(R.id.btnLogin)

        etEmail = findViewById(R.id.etEmailAddress)

        etPass = findViewById(R.id.etPassword)

        auth = FirebaseAuth.getInstance()



        btnLogin.setOnClickListener {
            login()

            }



        tvRedirectSignUp.setOnClickListener {

            val intent = Intent(this, MainActivity::class.java)

            startActivity(intent)

            finish()

        }


    }
    private fun login() {

        val email = etEmail.text.toString()

        val pass = etPass.text.toString()

        if (email.isBlank() || pass.isBlank()) {

            Toast.makeText(this, "Email and Password can't be blank", Toast.LENGTH_SHORT).show()
            return
        }
        auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(this) {


            if (it.isSuccessful) {
                val intent2 = Intent(this, perfilActivity::class.java).apply {
                    intent.putExtra("BEmail",email)
                }

                startActivity(intent2)

                Toast.makeText(this, "Successfully LoggedIn", Toast.LENGTH_SHORT).show()

            } else

                Toast.makeText(this, "Log In failed ", Toast.LENGTH_SHORT).show()

        }

    }

}