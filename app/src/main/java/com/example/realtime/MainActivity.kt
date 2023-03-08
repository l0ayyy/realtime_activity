package com.example.realtime

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.realtime.databinding.ActivityMainBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {

    var count:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val database = Firebase.database
        val myRef = database.getReference()
        binding.savebtn.setOnClickListener {
            val name = binding.PersonName.text.toString()
            val id = binding.PersonID.text.toString()
            val age = binding.PersonAge.text.toString()

            val person = hashMapOf(
                "name" to name,
                "id" to id,
                "age" to age
            )

            myRef.child("person").child("$count").setValue(person)
            count++
            Toast.makeText(applicationContext,"Success", Toast.LENGTH_SHORT).show()
        }

        binding.getbtn.setOnClickListener {
            myRef.addValueEventListener(object: ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val value = snapshot.getValue()
                    binding.textData.text = value.toString()
                    Toast.makeText(applicationContext,"Success", Toast.LENGTH_SHORT).show()

                }
                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(applicationContext,"Fail", Toast.LENGTH_SHORT).show()

                }

            })
        }



    }
}