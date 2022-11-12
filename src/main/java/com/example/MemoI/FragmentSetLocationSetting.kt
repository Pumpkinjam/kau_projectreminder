package com.example.MemoI

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.MemoI.databinding.FragmentSetLocationSettingBinding
import com.example.MemoI.databinding.FragmentSettingBinding


class FragmentSetLocationSetting : Fragment() {

    lateinit var binding: FragmentSetLocationSettingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSetLocationSettingBinding.inflate(inflater, container, false)

        binding.btnLocationExpire.setOnClickListener {
            // TODO
        }

        binding.btnSetNotification.setOnClickListener {
            // TODO
        }


        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentSetLocationSetting().apply {
                arguments = Bundle().apply {

                }
            }
    }
}