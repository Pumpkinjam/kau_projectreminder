package com.example.MemoI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.MemoI.databinding.ActivityMainBinding
import java.util.*

// TODO: add button clickListners
class MainActivity : AppCompatActivity() {

    // TODO: todoList logic, and save the information to file(csv format)
    private val dirPath = "/storage/emulated/0/Android/data/com.example.MemoI/files/"
    lateinit var todoList: Vector<Todo>
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addMore.setOnClickListener {
            val intent = Intent(this, SettingActivity::class.java)
            startActivity(intent)
        }
    }

}