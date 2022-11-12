package com.example.MemoI

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.MemoI.databinding.FragmentSettingBinding
import com.google.android.material.snackbar.Snackbar

class FragmentSetting : Fragment() {
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

        fun moveFragment(frg: Fragment) {
            parentActivity.viewStack.push(this)
            parentActivity.changeFragment(frg)
        }

        fun soManyThingsToDo(view: View) {Snackbar.make(view, "구현중...", Snackbar.LENGTH_SHORT).show()}

        binding.btnSetDate.setOnClickListener { _ -> moveFragment(FragmentSetDate()) }

        binding.btnSetLoc.setOnClickListener { _ -> moveFragment(FragmentSetLocation()) }

        binding.btnTodo.setOnClickListener { view ->
            //TODO: pop-up for input
            soManyThingsToDo(view)
        }

        binding.btnSetAppOpen.setOnClickListener { view ->
            //TODO: select the application, and then save it
            soManyThingsToDo(view)
        }

        binding.btnSetUrl.setOnClickListener { view ->
            //TODO: input the url.
            soManyThingsToDo(view)
        }

        return binding.root
    }

    companion object {
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentSetting().apply {
                arguments = Bundle().apply {
                    //putString(ARG_PARAM1, param1)
                    //putString(ARG_PARAM2, param2)
                }
            }
    }
}