package com.example.projectreminder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.projectreminder.databinding.ActivitySettingBinding

class SettingActivity : AppCompatActivity() {
    lateinit var binding : ActivitySettingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragment = supportFragmentManager.beginTransaction().run {
            val fragment = fragment_setting();
            replace(binding.frmFragmentSetting.id, fragment)
            commit()
            fragment
        }

    }

    // fragment in "frmFragmentSetting" can be changed by this method
    public fun changeFragment(frg: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.frmFragmentSetting.id, frg)
            .commit()
    }
}