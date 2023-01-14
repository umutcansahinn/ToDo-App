package com.umutcansahin.todoapp.ui.chart

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.github.mikephil.charting.utils.ColorTemplate
import com.umutcansahin.todoapp.R
import com.umutcansahin.todoapp.data.local.ToDoEntity
import com.umutcansahin.todoapp.databinding.FragmentChartBinding
import com.umutcansahin.todoapp.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_chart.*

@AndroidEntryPoint
class ChartFragment : Fragment(R.layout.fragment_chart) {

    private val binding by viewBinding(FragmentChartBinding::bind)

    private val viewModel: ChartViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAllToDo()
        observeEvent()
        initViews()
    }

    private fun observeEvent() {

        viewModel.entity.observe(viewLifecycleOwner, Observer {
            setChartData(it)
        })
    }

    private fun initViews() {
        binding.pieChart.setOnChartValueSelectedListener(object :OnChartValueSelectedListener {
            override fun onValueSelected(e: Entry?, h: Highlight?) {
                Log.d("pie_e","${e}")
                Log.d("pie_e","${e?.data}")
                Log.d("pie_h","${h}")
                e?.let {

                    val action = ChartFragmentDirections
                        .actionNavigationNotificationsToDetailChartFragment(it.data.toString())
                    Navigation.findNavController(requireView()).navigate(action)
                }
            }
            override fun onNothingSelected() {}
        })
    }

    private fun setChartData(list: List<ToDoEntity>) {

        var school = 0f
        var shopping = 0f
        var sport = 0f
        var business = 0f

        list.forEach {
            when (it.type) {
                "School" -> school ++
                "Business" -> business ++
                "Shopping" -> shopping ++
                "Sport" -> sport ++
            }

            val pieEntry = mutableListOf<PieEntry>()
            pieEntry.add(PieEntry(school, "School","School"))
            pieEntry.add(PieEntry(business, "Business","Business"))
            pieEntry.add(PieEntry(sport, "Sport","Sport"))
            pieEntry.add(PieEntry(shopping, "Shopping","Shopping"))

            val pieDataSet = PieDataSet(pieEntry, "")
            pieDataSet.setColors(*ColorTemplate.COLORFUL_COLORS)
            pieDataSet.valueTextSize = 30f
            pieDataSet.valueTextColor = Color.BLACK
            pieDataSet.formSize = 25f
            val data = PieData(pieDataSet)
            pieChart.data = data
            pieChart.animateY(2000)
        }
    }
}