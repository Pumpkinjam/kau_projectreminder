package com.example.MemoI

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.example.MemoI.databinding.FragmentSetLocationBinding
import com.example.MemoI.databinding.FragmentSetLocationMapBinding

class FragmentSetLocationMap : Fragment() {

    lateinit var binding: FragmentSetLocationMapBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSetLocationMapBinding.inflate(inflater, container, false);

        binding.btnMapConfirm.setOnClickListener {
            // TODO: set&save the location, change the text in "parentFragment.btn_open_map"
            (parentFragment as FragmentSetLocation).changeFragment(FragmentSetLocationSetting())
        }
        // TODO: Map UI Setting

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentSetLocationMap().apply {
                arguments = Bundle().apply {
                }
            }
    }
}