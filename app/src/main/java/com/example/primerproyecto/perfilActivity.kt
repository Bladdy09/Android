package com.example.primerproyecto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.primerproyecto.databinding.ActivityLogin2Binding
import com.example.primerproyecto.databinding.ActivityPerfilBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class perfilActivity : AppCompatActivity() {

    private lateinit var binding:ActivityPerfilBinding
    private lateinit var  database : DatabaseReference
    private lateinit var login: ActivityLogin2Binding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityPerfilBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()


        //readData(mail)

        binding.button3.setOnClickListener {
            //val mail:String = intent.getStringExtra("BEmail").toString()
            val email :String = binding.editTextName.text.toString()

            if (email.isNotEmpty()) {
                readData(email)
            }
            else {
                Toast.makeText(this, "Please enter the  name", Toast.LENGTH_SHORT).show()
            }
        }


    }


    private fun readData(bName: String) {
        database = FirebaseDatabase.getInstance().getReference("Users")
        database.child(bName).get().addOnSuccessListener {
            if (it.exists()){
                val email = it.child("email").value
                val name = it.child("name").value
                val apellido = it.child("apellido").value
                val cell = it.child("cell").value
                val sexo = it.child("sexo").value
                val pais = it.child("pais").value
                val provincia = it.child("provincia").value
                val fecha = it.child("fechaNaci").value
                Toast.makeText(this, "Successfuly read", Toast.LENGTH_SHORT).show()
                binding.editTextName.text.clear()
                binding.textviewname.text = name.toString()
                binding.textViewemail.text = email.toString()
                binding.textViewapellido.text = apellido.toString()
                binding.textViewcell.text = cell.toString()
                binding.textViewsexo.text = sexo.toString()
                binding.textViewpais.text = pais.toString()
                binding.textViewprovincia.text = provincia.toString()
                binding.textViewfecha.text = fecha.toString()

            }
            else{
                Toast.makeText(this, "User doesnt exist", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener{
            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
        }

    }


}



