package com.example.MemoI

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.MemoI.databinding.ActivityMainBinding
import java.io.IOException
import java.time.LocalDateTime
import kotlin.collections.ArrayList

// TODO: add button clickListners
class MainActivity : AppCompatActivity() {

    // TODO: todoList logic, and save the information to file(csv format)
    // TODO+: encryption?

    lateinit var binding: ActivityMainBinding
    lateinit var todoList: ArrayList<Todo>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        todoList = ArrayList<Todo>()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (reqPermission()) loadFile()

        // if there's new Todo_
        val received = intent.getBundleExtra("new_todo_bundle")?.getSerializable("new_todo")
        val tdp = if (received == null) null else (received as SettingActivity.TodoPack)

        if (tdp != null) {
            todoList.add(tdp.todo)
            println(todoList)
        }

        if (false) {
            this.openFileOutput(Todo.filename, Context.MODE_PRIVATE).write("".toByteArray())
            println("todos.csv reset completed.")
        }

        // todoList initialize
        binding.recTodo.layoutManager = LinearLayoutManager(this)
        binding.recTodo.adapter = TodoAdapter(todoList)

        val thisAct = this
        with (binding) {
            addMore.setOnClickListener {
                val intent = Intent(thisAct, SettingActivity::class.java)
                startActivity(intent)
            }

            testBtn.setOnClickListener {
                val thisLocalTime: LocalDateTime = LocalDateTime.now()
                val builder = NotificationCompat.Builder(thisAct, "test_channel")
                    .setSmallIcon(R.drawable.ic_launcher_background)
                    .setContentTitle("?????? ??????")
                    .setContentText("?????? ?????? $thisLocalTime")

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) { // ????????? ?????? ???????????? ????????? ?????? ??? ????????? ??????
                    // gradle?????? SDK 26 ????????? ??????????????? ??? ????????? ??????????????? ??????. ????????? ????????????.
                    val channel_id = "test_channel" // ????????? ?????? ?????? id ??????
                    val channel_name = "????????????" // ?????? ?????? ??????
                    val descriptionText = "?????????" // ?????? ????????? ??????
                    val importance = NotificationManager.IMPORTANCE_DEFAULT // ?????? ???????????? ??????
                    val channel = NotificationChannel(channel_id, channel_name, importance).apply {
                        description = descriptionText
                    }

                    // ?????? ?????? ????????? ???????????? ??????
                    val notificationManager: NotificationManager =
                        getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                    notificationManager.createNotificationChannel(channel)

                    // ?????? ??????: ????????? ?????? ID(ex: 1002), ?????? ??????
                    notificationManager.notify(1002, builder.build())
                }
            }
        }

    }



    override fun onBackPressed() {
        val a = Intent(Intent.ACTION_MAIN)
        a.addCategory(Intent.CATEGORY_HOME)
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(a)
        println("\n\n\n==========Exiting...===========\n\n\n")
        this.finish()
        // exitProcess(0) << Do not call this code. AppViewModel.onCleared() will not be called.
    }



    override fun onPause() {
        saveFile()
        super.onPause()
    }

    private fun saveFile() {
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
    }

    private fun loadFile() {

        val files: Array<String> = this.fileList()
        println("=================")
        for (s in files) {
            println(s)
        }

        if (Todo.filename in files) println("${Todo.filename} found!")
        else this.openFileOutput(Todo.filename, Context.MODE_PRIVATE).use {
            println("Creating new file...")
            it.write(" ".toByteArray())
        }

        val lines = this.openFileInput(Todo.filename).bufferedReader().readLines()
        for (line in lines) {
            println(line)
            if (line == " ") break
            this.todoList.add(TodoBuilder.of(line).build())
        }
        println("=================\nload complete.")


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
                ), 1)
        }

        return (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED)
    }

}