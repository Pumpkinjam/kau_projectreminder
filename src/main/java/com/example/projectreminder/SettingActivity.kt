package com.example.projectreminder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.projectreminder.databinding.ActivitySettingBinding

class SettingActivity : AppCompatActivity() {
    lateinit var binding : ActivitySettingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.backBtn.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
        binding.dateBtn.setOnClickListener {
            val intent = Intent(this,DateActivity::class.java)
            startActivity(intent)
        }
    }
}