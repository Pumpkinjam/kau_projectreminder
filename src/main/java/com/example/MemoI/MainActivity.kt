package com.example.MemoI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import com.example.MemoI.databinding.ActivityMainBinding
import java.io.*
import java.nio.Buffer
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.*

// TODO: add button clickListners
class MainActivity : AppCompatActivity() {

    // TODO: todoList logic, and save the information to file(csv format)
    // TODO+: encryption?
    lateinit var todoList: Vector<Todo>
    lateinit var binding: ActivityMainBinding
    lateinit var savefile: File

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        todoList = Vector<Todo>()

        var dirpath = Environment.getExternalStorageDirectory().getAbsolutePath()

        println("Checking file directories...")
        for (dir in Todo.fileDirArray) {
            dirpath += dir
            val tmp_path = Paths.get(dirpath)
            try {
                if (!Files.exists(tmp_path)) throw IOException()
            } catch (e: IOException) {
                Files.createDirectory(tmp_path)
                System.err.println("$tmp_path created.")
            }
        }

        println("Checking file...")
        val filepath = Paths.get(dirpath + Todo.filename)
        try {
            if (!Files.exists(filepath)) throw IOException()
        } catch (e: IOException) {
            Files.createFile(filepath)
            System.err.println("$filepath created.")
        }

        savefile = File(Todo.filepath, Todo.filename)
        BufferedWriter(FileWriter(savefile)).append(" \n")  // In case...


        try {
            // val bw = BufferedWriter(FileWriter(savefile));
            val br = BufferedReader(FileReader(savefile));
            var str: String

            println("Loading <todoList> in progress...")
            while (true) {
                str = br.readLine()
                if (str.equals(" ")) break;

                todoList.add(Todo.of(br.readLine()))
                println("{$str} load complete.")
            }
            br.close()
            println("Todo List load complete.")
        }
        catch (e: IOException) {
            System.err.println("Todo List load failure.")
            e.printStackTrace()
        }


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addMore.setOnClickListener {
            todoList.add(Todo("title!", "Des!", ))
            System.out.println(todoList.get(0))
            val intent = Intent(this, SettingActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroy() {

        // TODO: imagine that what happens if the saving progress fails...
        // TODO: we need something more safe way
        try {
            val bw = BufferedWriter(FileWriter(savefile))

            // must save " " at the end of the file!!
            var str = ""
            for (td in todoList) str += td.toCsvFormat()
            str += " \n"

            bw.write(str)

            System.out.println("Todo List save completed.")
        }
        catch (e: IOException) {
            System.err.println("Todo List save failure.")
            e.printStackTrace()
        }

        super.onDestroy()
    }
}