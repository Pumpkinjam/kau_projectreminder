package com.example.projectreminder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.projectreminder.databinding.ActivityDateBinding

class DateActivity : AppCompatActivity() {
    lateinit var binding : ActivityDateBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityDateBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.backBtnTosett.setOnClickListener {
            val intent = Intent(this,SettingActivity::class.java)
            startActivity(intent)


        }
    }
}