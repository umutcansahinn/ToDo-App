package com.umutcansahin.todoapp.ui.detail_pie_chart

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.umutcansahin.todoapp.R
import com.umutcansahin.todoapp.databinding.DetailChartRecyclerBinding
import com.umutcansahin.todoapp.databinding.FragmentAddBinding
import com.umutcansahin.todoapp.databinding.FragmentDetailChartBinding
import com.umutcansahin.todoapp.ui.detail_pie_chart.adapter.DetailChartAdapter
import com.umutcansahin.todoapp.ui.home.adapter.ToDoListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailChartFragment : Fragment() {

    private var _binding: FragmentDetailChartBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DetailChartViewModel by viewModels()

    private val detailChartAdapter = DetailChartAdapter(arrayListOf())
    private var type: String? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailChartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            type = DetailChartFragmentArgs.fromBundle(it).type
        }
        viewModel.getToDoByType(type!!)
        observeEvents()
        initViews()
    }

    private fun observeEvents() {
        viewModel.uiState.observe(viewLifecycleOwner) { todo->
            todo?.let {
                detailChartAdapter.updateList(it)
            }
        }
    }

    private fun initViews() {
        binding.recyclerView.adapter = detailChartAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}