package com.example.MemoI

import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.io.IOException
import kotlin.collections.ArrayList

class SettingViewModel(application: Application): AndroidViewModel(application) {

    private val context = getApplication<Application>().applicationContext

    // current-adding todo_ instance
    private val _tempTodo = MutableLiveData<TodoBuilder>(TodoBuilder("If you are watching this, ", "there must be an unexpected action in application"))
    val tempTodo: LiveData<TodoBuilder> get() = _tempTodo

    // if false, Todo_ is not saved when onCleared()
    private val _toSave = MutableLiveData<Boolean>()
    val toSave: LiveData<Boolean> get() = _toSave

    val tmp = System.err.println("If this message was shown more than once, there must be a problem.")

    override fun onCleared() {
        if (_toSave.value!!) {
            _tempTodo.value!!.build()
        }
        super.onCleared()
    }

    private fun hasPermission(): Boolean =
        (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
            == PackageManager.PERMISSION_GRANTED)

    // single AppViewModel on a single application
    companion object {
        private var instance: SettingViewModel? = null
        fun getInstance(appContext: Application) = instance ?: synchronized(SettingViewModel::class.java) {
            instance ?: SettingViewModel(appContext).also {instance = it}
        }
    }


}