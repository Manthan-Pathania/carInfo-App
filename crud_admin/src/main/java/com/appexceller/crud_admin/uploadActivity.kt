package com.appexceller.crud_admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.appexceller.crud_admin.databinding.ActivityUploadBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class uploadActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUploadBinding

    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        binding = ActivityUploadBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnSave.setOnClickListener {
            val name = binding.etOwner.text.toString()
            val brand = binding.etBrand.text.toString()
            val rto = binding.etRto.text.toString()
            val number = binding.etNumber.text.toString()
            database = FirebaseDatabase.getInstance().getReference("Users")
            val data = vehicleData(name,brand,rto,number)
            database.child(number).setValue(data).addOnSuccessListener {
                binding.etOwner.text.clear()
                binding.etRto.text.clear()
                binding.etBrand.text.clear()
                binding.etNumber.text.clear()
                Toast.makeText(this,"Saved",Toast.LENGTH_SHORT).show()
                val intent = Intent(this@uploadActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }.addOnFailureListener{
                Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()
            }
        }
    }
}