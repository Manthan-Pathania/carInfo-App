package com.appexceller.carinformation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.appexceller.carinformation.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var database : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnSearch.setOnClickListener {
            val searchNumber : String = binding.etSearch.text.toString()
            if  (searchNumber.isNotEmpty()){
                readData(searchNumber)
            }else{
                Toast.makeText(this,"PLease enter the vehicle number",Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun readData(vehicleNumber: String) {
        database = FirebaseDatabase.getInstance().getReference("Users")
        database.child(vehicleNumber).get().addOnSuccessListener {
            if (it.exists()){
                val owner = it.child("ownerName").value
                val brand = it.child("vehicleBrand").value
                val RTO = it.child("vehicleRto").value
                Toast.makeText(this,"Results Found",Toast.LENGTH_SHORT).show()
                binding.etSearch.text.clear()
                binding.tvOwner.text = owner.toString()
                binding.tvBrand.text = brand.toString()
                binding.tvRTO.text = RTO.toString()
            }else{
                Toast.makeText(this,"Vehicle number does not exist",Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener{
            Toast.makeText(this,"Something went wrong",Toast.LENGTH_SHORT).show()
        }
    }
}