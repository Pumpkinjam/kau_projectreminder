package com.example.MemoI

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.io.IOException
import kotlin.collections.ArrayList

class AppViewModel(application: Application): AndroidViewModel(application) {

    private val context = getApplication<Application>().applicationContext

    // list of saved Todos
    private val _todoList = MutableLiveData<ArrayList<Todo>>(ArrayList<Todo>())
    private val _tempTodo = MutableLiveData<Todo>(Todo("If you are watching this, ", "there must be an unexpected action in application"))

    // current-adding todo_ instance
    val todoList: LiveData<ArrayList<Todo>> get() = _todoList
    val tempTodo: LiveData<Todo> get() = _tempTodo

    override fun onCleared() {
        println("***\n\n\n\n\nExit\n\n\n\n\n***")

        // TODO: imagine that what happens if the saving progress fails...
        // TODO: we need something more safe way
        try {
            // must save " " at the end of the file!!
            var str = ""
            for (td in _todoList.value!!) str += td.toCsvFormat()
            str += " \n"

            context.openFileOutput(Todo.filename, Context.MODE_PRIVATE).write(str.toByteArray())

            System.out.println("Todo List save completed.")
        }
        catch (e: IOException) {
            System.err.println("Todo List save failure.")
            e.printStackTrace()
        }
        super.onCleared()
    }

}