package com.example.MemoI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.MemoI.databinding.ActivitySettingBinding
import com.google.android.material.snackbar.Snackbar
import java.io.Serializable
import java.util.Stack

class SettingActivity : AppCompatActivity() {

//    lateinit var vm: SettingViewModel
    lateinit var binding: ActivitySettingBinding
    lateinit var viewStack: Stack<Fragment>   // for btn_back makes the screen return step-by-step
    lateinit var tempTodo: TodoBuilder

    data class TodoPack(val todo: Todo) : Serializable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // argument "title" must be removed
        tempTodo = TodoBuilder("title")

        binding = ActivitySettingBinding.inflate(layoutInflater)
        viewStack = Stack<Fragment>()
        setContentView(binding.root)

        val fragment = supportFragmentManager.beginTransaction().run {
            val fragment = FragmentSetting();
            replace(binding.frmFragmentSetting.id, fragment)
            commit()
            fragment
        }

        // returning to Main Activity through back button doesn't add new Todo_
        binding.btnBack.setOnClickListener { _ ->
            this.onBackPressed()
            /*if (viewStack.empty()) {
                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
            }
            else {
                supportFragmentManager.beginTransaction().run{
                    replace(binding.frmFragmentSetting.id,viewStack.pop())
                    commit()
                }
            }*/
        }

        // returning to Main Activity through confirm button adds new Todo_
        binding.btnConfirm.setOnClickListener { _ ->
            val intent = Intent(this,MainActivity::class.java)
            try {
                val bundle = Bundle()
                bundle.putSerializable("new_todo", TodoPack(tempTodo.build()))
                intent.putExtra("new_todo_bundle", bundle)
            }
            catch (e: TodoBuilder.NullIntegrityException) {
                Snackbar.make(binding.root, "타이틀을 설정해야 합니다.", Snackbar.LENGTH_SHORT)
            }

            startActivity(intent)
        }

    }

    override fun onBackPressed() {
        if (viewStack.empty()) {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
        else {
            supportFragmentManager.beginTransaction().run{
                replace(binding.frmFragmentSetting.id,viewStack.pop())
                commit()
            }
        }
    }

    // fragment in "frmFragmentSetting" can be changed by this method
    fun changeFragment(frg: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.frmFragmentSetting.id, frg)
            .commit()
    }
}