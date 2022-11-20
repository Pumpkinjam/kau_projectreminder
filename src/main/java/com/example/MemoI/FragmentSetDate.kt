package com.example.MemoI

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.MemoI.databinding.FragmentSetDateBinding
import java.util.*

class FragmentSetDate : Fragment() {
    private var calendar = Calendar.getInstance()
    private var year = calendar.get(Calendar.YEAR)
    private var month = calendar.get(Calendar.MONTH)
    private var day = calendar.get(Calendar.DAY_OF_MONTH)
    private var hour =calendar.get(Calendar.HOUR_OF_DAY)
    private var minute = calendar.get(Calendar.MINUTE)

    lateinit var binding: FragmentSetDateBinding

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
                        txtStartDate.text = "$year/${(month + 1)}/$day"
                    }
                var date = DatePickerDialog(activity as SettingActivity, datePickerDialog, year, month, day)
                date.show()
            }

            btnSetTime.setOnClickListener {
                val timePickerDialog =
                    TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                        btnSetTime.text = "${hour}시 ${minute}분"
                    }
                var time = TimePickerDialog(activity as SettingActivity, timePickerDialog, hour, minute, false)
                time.show()
            }
        }

        return binding.root;
    }
}