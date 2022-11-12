package com.example.MemoI

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.MemoI.databinding.FragmentSetDateBinding

// THIS CLASS IS EMPTY.
// TODO: make buttons work.
class FragmentSetDate : Fragment() {

    lateinit var binding: FragmentSetDateBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSetDateBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentSetDate().apply {
                arguments = Bundle().apply {
                }
            }
    }
}