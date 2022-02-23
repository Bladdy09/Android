package com.example.primerproyecto

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.primerproyecto.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class MainActivity : AppCompatActivity() {

    private lateinit var editname : EditText
    private lateinit var editemail: EditText
    private lateinit var editpassword :EditText
    private lateinit var editpassconfir :EditText
    lateinit var tvRedirectLogin: TextView
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)




        auth= FirebaseAuth.getInstance()
        //database = FirebaseDatabase.getInstance().getReference()

        editname =findViewById<EditText>(R.id.editTextName)
        editemail = findViewById<EditText>(R.id.editTextEmail)
        editpassword = findViewById<EditText>(R.id.editTextPassword)
        editpassconfir = findViewById<EditText>(R.id.editpassconfirm)
        val registrar = findViewById<Button>(R.id.button)
        val btnLogin = findViewById<Button>(R.id.button2)

        binding.button.setOnClickListener{
            val name = binding.editTextName.text.toString()
            val email = binding.editTextEmail.text.toString()
            val apellido = binding.editTextapellido.text.toString()
            val cell = binding.editTextcell.text.toString()
            val sexo = binding.editTextsexo.text.toString()
            val fecha = binding.editTextDate2.text.toString()
            val pais = binding.editTextpais.text.toString()
            val provincia = binding.editTextprovincia.text.toString()

            database = FirebaseDatabase.getInstance().getReference("Users")
            val User = Users(name,apellido,cell,email,sexo,fecha,pais,provincia)
            database.child(name).setValue(User).addOnCompleteListener{
                binding.editTextName.text.clear()
                binding.editTextEmail.text.clear()
                binding.editTextPassword.text.clear()
                binding.editpassconfirm.text.clear()
                binding.editTextapellido.text.clear()
                binding.editTextcell.text.clear()
                binding.editTextsexo.text.clear()
                binding.editTextDate2.text.clear()
                binding.editTextpais.text.clear()
                binding.editTextprovincia.text.clear()

                Toast.makeText(this, "Successfully saved", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener{
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
            }
            signUpUser()
        }

        //registrar.setOnClickListener {
          //  signUpUser()


        //}

        btnLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity2::class.java)

            startActivity(intent)

        }






        //tvRedirectLogin.setOnClickListener {
          //  val intent2 = Intent(this, LoginActivity2::class.java)
            //startActivity(intent2)
        //}


    }


    private fun signUpUser() {

        val email = editemail.text.toString()

        val pass = editpassword.text.toString()

        val confirmPassword = editpassconfir.text.toString()

        if (email.isBlank() || pass.isBlank()) {

            Toast.makeText(this, "Email and Password can't be blank", Toast.LENGTH_SHORT).show()

            return

        }



        if (pass != confirmPassword) {

            Toast.makeText(this, "Password and Confirm Password do not match", Toast.LENGTH_SHORT).show()

            return

        }



        auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(this) {

            if (it.isSuccessful) {

                Toast.makeText(this, "Successfully Singed Up", Toast.LENGTH_SHORT).show()
                val intent2 = Intent(this, LoginActivity2::class.java)

                startActivity(intent2)



            } else {

                Toast.makeText(this, "Singed Up Failed!", Toast.LENGTH_SHORT).show()

            }


        }
    }
}

