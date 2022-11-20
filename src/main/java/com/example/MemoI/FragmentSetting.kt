package com.example.MemoI

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.MemoI.databinding.FragmentSettingBinding
import com.google.android.material.snackbar.Snackbar

// todo: make buttons work
class FragmentSetting : Fragment() {
    lateinit var parentActivity: SettingActivity
    lateinit var binding: FragmentSettingBinding

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

        fun soManyThingsToDo(view: View) = Snackbar.make(view, "구현중...", Snackbar.LENGTH_SHORT).show()

        with (binding) {
            btnSetDate.setOnClickListener { _ -> moveFragment(FragmentSetDate()) }

            btnSetLoc.setOnClickListener { _ -> moveFragment(FragmentSetLocation()) }

            btnTodo.setOnClickListener { view ->
                //TODO: pop-up for input
                soManyThingsToDo(view)
            }

            btnSetAppOpen.setOnClickListener { view ->
                //TODO: select the application, and then save it
                soManyThingsToDo(view)
            }

            btnSetUrl.setOnClickListener { view ->
                //TODO: input the url.
                soManyThingsToDo(view)
            }
        }

        return binding.root
    }

}