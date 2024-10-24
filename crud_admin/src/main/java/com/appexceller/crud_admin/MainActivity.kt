package com.appexceller.crud_admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.view.View
import com.appexceller.crud_admin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnUpload.setOnClickListener {
            val intent = Intent(this@MainActivity, uploadActivity::class.java)
            startActivity(intent)
        }

        binding.btnUpdate.setOnClickListener {
            val intent = Intent(this@MainActivity, UpdateActivity::class.java)
            startActivity(intent)
        }

        binding.btnDelete.setOnClickListener {
            val intent = Intent(this@MainActivity, DeleteActivity::class.java)
            startActivity(intent)
        }

    }
}