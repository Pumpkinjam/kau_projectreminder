package com.example.MemoI

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import com.example.MemoI.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import java.io.*
import java.nio.Buffer
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.*

// TODO: add button clickListners
// TODO: don't let this activity create/destroy while using app
class MainActivity : AppCompatActivity() {

    // DO-ing: todoList logic, and save the information to file(csv format)
    // TODO+: encryption?
    lateinit var todoList: Vector<Todo>
    lateinit var binding: ActivityMainBinding

    private fun loadFile() {

        var files: Array<String> = this.fileList()
        if (Todo.filename in files) println("${Todo.filename} found!")
        else this.openFileOutput(Todo.filename, Context.MODE_PRIVATE).use {
            println("Creating new file...")
            it.write(" ".toByteArray())
        }

        val lines = this.openFileInput(Todo.filename).bufferedReader().readLines()
        for (line in lines) {
            if (line == " ") break
            todoList.add(Todo.of(line))
        }
        println("load complete.")
    }

    private fun reqPermission(): Boolean {

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
            == PackageManager.PERMISSION_GRANTED)
        {
            println("Permission already granted.")
            return true
        }
        else {
            println("requesting permission")
            ActivityCompat.requestPermissions(this,
                arrayOf<String>(
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    android.Manifest.permission.MANAGE_EXTERNAL_STORAGE
                ), 1);
        }

        return (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
            == PackageManager.PERMISSION_GRANTED)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        todoList = Vector<Todo>()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (reqPermission()) loadFile()

/*
    This code not works...
        val requestPermissionLauncher =
            registerForActivityResult(ActivityResultContracts.RequestPermission()
            ) {
                isGranted: Boolean ->
                if (isGranted) {
                    println("===============wow")
                    loadFile()
                } else {
                   // Snackbar.make(binding.root, "sure?", Snackbar.LENGTH_SHORT).show()
                    println("=================nope")
                }
            }

*/

        binding.addMore.setOnClickListener {
            //todoList.add(Todo("title!", "Des!", ))
            //System.out.println(todoList.get(0))
            val intent = Intent(this, SettingActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroy() {

        // TODO: imagine that what happens if the saving progress fails...
        // TODO: we need something more safe way
        try {
            // must save " " at the end of the file!!
            var str = ""
            for (td in todoList) str += td.toCsvFormat()
            str += " \n"

            this.openFileOutput(Todo.filename, Context.MODE_PRIVATE).write(str.toByteArray())

            System.out.println("Todo List save completed.")
        }
        catch (e: IOException) {
            System.err.println("Todo List save failure.")
            e.printStackTrace()
        }

        super.onDestroy()
    }
}