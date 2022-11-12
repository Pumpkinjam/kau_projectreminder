package com.example.MemoI

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.MemoI.databinding.FragmentSetLocationBinding
import java.util.*

// TODO: make map work.
class FragmentSetLocation : Fragment() {

    lateinit var binding: FragmentSetLocationBinding
    lateinit var parentActivity: SettingActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSetLocationBinding.inflate(inflater, container, false)

        binding.btnOpenMap.setOnClickListener {
            childFragmentManager.beginTransaction().run {
                replace(binding.frmLocationSetting.id, FragmentSetLocationMap())
                commit()
            }
        }

        return binding.root
    }

    public fun changeFragment(frg: Fragment) {
        childFragmentManager.beginTransaction()
            .replace(binding.frmLocationSetting.id, frg)
            .commit()
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentSetLocation().apply {
                arguments = Bundle().apply {
                }
            }
    }
}