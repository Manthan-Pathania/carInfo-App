package com.appexceller.crud_admin

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.appexceller.crud_admin.databinding.ActivityMainBinding
import com.appexceller.crud_admin.databinding.ActivityUpdateBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UpdateActivity : AppCompatActivity() {

    private lateinit var databaseReference : DatabaseReference
    private lateinit var binding : ActivityUpdateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnUpdate.setOnClickListener{
            val vehicleNumber = binding.etNumberReference.text.toString()
            val ownerName = binding.etOwnerUpdate.text.toString()
            val vehicleBrand = binding.etBrandUpdate.text.toString()
            val vehicleRto = binding.etRtoUpdate.text.toString()

            if (vehicleNumber.isNotEmpty()){
                udpate(vehicleNumber , ownerName , vehicleBrand , vehicleRto)
            }else{
                Toast.makeText(this , "Please Enter Vehicle Number" , Toast.LENGTH_SHORT).show()
            }

        }


    }

    private fun udpate(vehicleNumber : String , ownerName : String , vehicleBrand : String , vehicleRto : String){
        databaseReference = FirebaseDatabase.getInstance().getReference("Users")
        val vehicleData = mapOf<String,String>("ownerName" to ownerName , "vehicleBrand" to vehicleBrand , "vehicleRto" to vehicleRto)
        databaseReference.child(vehicleNumber).updateChildren(vehicleData).addOnSuccessListener {
            binding.etOwnerUpdate.text.clear()
            binding.etBrandUpdate.text.clear()
            binding.etRtoUpdate.text.clear()
            binding.etNumberReference.text.clear()
            Toast.makeText(this , "Updated Successfully" , Toast.LENGTH_SHORT).show()
            val intent = Intent(this@UpdateActivity , MainActivity::class.java)
            startActivity(intent)
            finish()
        }.addOnFailureListener{
            Toast.makeText(this , "Failed to Update" , Toast.LENGTH_SHORT).show()
        }
    }
}