package com.example.projectreminder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.projectreminder.databinding.ActivitySettingBinding
import java.util.Stack

class SettingActivity : AppCompatActivity() {
    lateinit var binding : ActivitySettingBinding
    lateinit var viewStack : Stack<Fragment>   // for btn_back makes the screen return step-by-step

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        viewStack = Stack<Fragment>()
        setContentView(binding.root)

        val fragment = supportFragmentManager.beginTransaction().run {
            val fragment = FragmentSetting();
            replace(binding.frmFragmentSetting.id, fragment)
            commit()
            fragment
        }

        binding.btnBack.setOnClickListener { view ->
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

        // TODO: save the change before returning to main activity
        binding.btnConfirm.setOnClickListener { view ->
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

    }

    // fragment in "frmFragmentSetting" can be changed by this method
    public fun changeFragment(frg: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.frmFragmentSetting.id, frg)
            .commit()
    }
}