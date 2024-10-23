package com.appexceller.crud_admin

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.appexceller.crud_admin.databinding.ActivityDeleteBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class DeleteActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDeleteBinding
    private lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDeleteBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)



        binding.deleteBtn.setOnClickListener{
            val vehicleNumber = binding.etDeleteNumber.text.toString()
            if (vehicleNumber.isNotEmpty()){
                delete(vehicleNumber)
            }else{
                Toast.makeText(this@DeleteActivity, "Please Enter Vehicle Number", Toast.LENGTH_SHORT).show()
            }
        }




    }

    private fun delete(vehicleNumber : String){
        databaseReference = FirebaseDatabase.getInstance().getReference("Users")
        databaseReference.child(vehicleNumber).removeValue().addOnSuccessListener {
            binding.etDeleteNumber.text.clear()
            Toast.makeText(this@DeleteActivity, "Deleted Successfully", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@DeleteActivity , MainActivity::class.java)
            startActivity(intent)
            finish()
        }.addOnFailureListener {
            Toast.makeText(this@DeleteActivity, "Failed to Delete", Toast.LENGTH_SHORT).show()
        }

    }
}