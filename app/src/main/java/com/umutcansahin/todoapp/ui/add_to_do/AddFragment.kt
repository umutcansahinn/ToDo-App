package com.umutcansahin.todoapp.ui.add_to_do


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.datepicker.MaterialDatePicker
import com.umutcansahin.todoapp.R
import com.umutcansahin.todoapp.utils.Constants
import com.umutcansahin.todoapp.utils.toFormat
import com.umutcansahin.todoapp.data.local.ToDoEntity
import com.umutcansahin.todoapp.databinding.FragmentAddBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_add.*
import java.util.*

@AndroidEntryPoint
class AddFragment : Fragment(R.layout.fragment_add) {

    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!


    private val viewModel: AddViewModel by viewModels()

    private var isInsert = true
    private var id: Int? = null
    private var type: String? = null
    private var selectedDate = Date()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        observeEvent()
        arguments?.let {
            isInsert = AddFragmentArgs.fromBundle(it).isInsert
            id = AddFragmentArgs.fromBundle(it).id
        }

        viewModel.getById(id)


    }

    private fun observeEvent() {
        viewModel.singleToDo.observe(viewLifecycleOwner) {
            it?.let {
                setUIState(it)
            }
        }
    }

    private fun setUIState(entity: ToDoEntity) {

        binding.dateButton.text = entity.timestamp.toFormat(Constants.CURRENT_DATE_FORMAT)
        binding.nameEditText.setText(entity.name)
        binding.saveButton.text = "Update"
        binding.saveButton.setBackgroundColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.new_button
            )
        )
        binding.saveButton.icon =
            ContextCompat.getDrawable(requireContext(), R.drawable.ic_baseline_edit_24)

        when (entity.type) {

            "Business" -> binding.radioButtonBusiness.isChecked = true
            "School" -> binding.radioButtonSchool.isChecked = true
            "Shopping" -> binding.radioButtonShopping.isChecked = true
            "Sport" -> binding.radioButtonSports.isChecked = true

        }
    }

    private fun initViews() {
        binding.saveButton.setOnClickListener {
            viewModel.insertOrUpdate(
                name = binding.nameEditText.text.toString(),
                isInsert = isInsert,
                id = id,
                type = type,
                date = selectedDate

            )
            findNavController().popBackStack()
        }

        binding.dateButton.setOnClickListener {
            val datePicker =
                MaterialDatePicker.Builder.datePicker()
                    .setTitleText(getString(R.string.select_date))
                    .setSelection(selectedDate.time)
                    .build()
            datePicker.addOnPositiveButtonClickListener { timestamp ->
                selectedDate = Date(timestamp)
                dateButton.text = selectedDate.toFormat(Constants.CURRENT_DATE_FORMAT)
            }
            datePicker.show(parentFragmentManager, Constants.TAG_DATE_PICKER);
        }
        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->

            when (checkedId) {

                R.id.radioButtonBusiness -> type = radioButtonBusiness.text.toString()
                R.id.radioButtonSchool -> type = radioButtonSchool.text.toString()
                R.id.radioButtonShopping -> type = radioButtonShopping.text.toString()
                R.id.radioButtonSports -> type = radioButtonSports.text.toString()

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}