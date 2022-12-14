package com.example.MemoI

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.MemoI.databinding.FragmentSetDateBinding
import java.util.*

class FragmentSetDate : Fragment() {

    //lateinit var vm: AppViewModel
    val vm: SettingViewModel by activityViewModels()
    lateinit var binding: FragmentSetDateBinding

    private var calendar = Calendar.getInstance()
    private var year = calendar.get(Calendar.YEAR)
    private var month = calendar.get(Calendar.MONTH)
    private var day = calendar.get(Calendar.DAY_OF_MONTH)
    private var hour =calendar.get(Calendar.HOUR_OF_DAY)
    private var minute = calendar.get(Calendar.MINUTE)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSetDateBinding.inflate(inflater, container, false)
        with (binding) {
            // todo?: click txtStartDate to change format "yyyy년 mm월 dd일"
            btnSetDate.setOnClickListener {
                val datePickerDialog =
                    DatePickerDialog.OnDateSetListener { datepicker, year, month, day ->
                        txtStartDateCheck.text = "$year/${(month + 1)}/$day"
                        (activity as SettingActivity).tempTodo.setDate(year, month+1, day);
                    }
                var date = DatePickerDialog(activity as SettingActivity, datePickerDialog, year, month, day)
                date.show()
            }

            btnSetTime.setOnClickListener {
                val timePickerDialog =
                    TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                        txtStartTimeCheck.text = "${hour}시 ${minute}분"
                        (activity as SettingActivity).tempTodo.setTime(hour, minute);
                    }
                var time = TimePickerDialog(activity as SettingActivity, timePickerDialog, hour, minute, false)
                time.show()
            }
        }

        return binding.root;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}