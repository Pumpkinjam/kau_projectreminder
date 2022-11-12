package com.example.projectreminder

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.projectreminder.databinding.FragmentSettingBinding

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"

class fragment_setting : Fragment() {
    //private var param1: String? = null
    //private var param2: String? = null

    lateinit var parentActivity: SettingActivity
    lateinit var binding: FragmentSettingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        parentActivity = activity as SettingActivity
        binding = FragmentSettingBinding.inflate(inflater, container, false);

        binding.btnBack.setOnClickListener { view ->
            val intent = Intent(activity,MainActivity::class.java)
            startActivity(intent)
        }

        binding.btnConfirm.setOnClickListener { view ->
            val intent = Intent(activity,MainActivity::class.java)
            startActivity(intent)
        }

        binding.btnSetDate.setOnClickListener { view ->
            parentActivity.changeFragment(fragment_set_date())
        }

        binding.btnSetLoc.setOnClickListener { view ->
            parentActivity.changeFragment(fragment_set_location())
        }

        return binding.root
    }

    companion object {
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            fragment_setting().apply {
                arguments = Bundle().apply {
                    //putString(ARG_PARAM1, param1)
                    //putString(ARG_PARAM2, param2)
                }
            }
    }
}