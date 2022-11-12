package com.example.projectreminder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.projectreminder.databinding.ActivityMainBinding

// TODO: add button clickListners
class MainActivity : AppCompatActivity() {

    // TODO: path has to be fixed by editing project(application) name
    private val dirPath = "/storage/emulated/0/Android/data/com.example.projectreminder/files/"
    lateinit var todoList: ArrayList<Todo>
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