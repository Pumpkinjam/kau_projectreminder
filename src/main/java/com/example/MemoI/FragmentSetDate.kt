package com.example.MemoI

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.MemoI.databinding.FragmentSetDateBinding
import java.util.*

// THIS CLASS IS EMPTY.
// TODO: make buttons work.
class FragmentSetDate : Fragment() {
    private var calendar = Calendar.getInstance()
    private var year = calendar.get(Calendar.YEAR)
    private var month = calendar.get(Calendar.MONTH)
    private var day = calendar.get(Calendar.DAY_OF_MONTH)
    private var hour = calendar.get(Calendar.HOUR)
    private var minute = calendar.get(Calendar.MINUTE)
    private var am = calendar.get(Calendar.AM_PM)
    lateinit var binding: FragmentSetDateBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.btnSetDate.setOnClickListener {

            val datePickerDialog = DatePickerDialog(requireActivity().applicationContext, { _, year, month, day ->
                binding.startDateText.text =
                    year.toString() + "/" + (month + 1).toString() + "/" + day.toString()
            }, year, month, day)
            datePickerDialog.show()
        }
        binding.btnSetTime.setOnClickListener {
            val timePickerDialog = TimePickerDialog.OnTimeSetListener{ timePicker, hour, minute ->
                binding.btnSetTime.text =
                    hour.toString() + "/" + minute.toString()
            }
            var listener = TimePickerDialog(requireActivity().applicationContext, timePickerDialog, hour, minute, false)
            listener.show()
        }
        binding.btnCompletion.setOnClickListener {


        }
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