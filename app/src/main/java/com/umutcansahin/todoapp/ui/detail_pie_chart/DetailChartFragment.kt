package com.umutcansahin.todoapp.ui.detail_pie_chart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.umutcansahin.todoapp.R
import com.umutcansahin.todoapp.databinding.FragmentDetailChartBinding
import com.umutcansahin.todoapp.ui.detail_pie_chart.adapter.DetailChartAdapter
import com.umutcansahin.todoapp.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailChartFragment : Fragment(R.layout.fragment_detail_chart) {

    private val binding by viewBinding(FragmentDetailChartBinding::bind)

    private val viewModel: DetailChartViewModel by viewModels()

    private val detailChartAdapter = DetailChartAdapter(arrayListOf())
    private var type: String? = null

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
}