package com.umutcansahin.todoapp.ui.detail_pie_chart.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.umutcansahin.todoapp.utils.Constants
import com.umutcansahin.todoapp.utils.toFormat
import com.umutcansahin.todoapp.databinding.DetailChartRecyclerBinding
import com.umutcansahin.todoapp.domain.uimodel.ToDoUIModel

class DetailChartAdapter(val toDoList: ArrayList<ToDoUIModel>) : RecyclerView.Adapter<DetailChartAdapter.DetailChartViewHolder>(){

    class DetailChartViewHolder(val binding: DetailChartRecyclerBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailChartViewHolder {
        val binding = DetailChartRecyclerBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return DetailChartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DetailChartViewHolder, position: Int) {
        holder.binding.recyclerNameTextView.text = toDoList[position].name
        holder.binding.recyclerTypeTextView.text = toDoList[position].type
        holder.binding.recyclerDateTextView.text = toDoList[position].timestamp.toFormat(
            Constants.CURRENT_DATE_FORMAT
        )

        holder.binding.cardView.setCardBackgroundColor(Color.parseColor(toDoList[position].typeColor))
    }

    override fun getItemCount(): Int {
        return toDoList.size
    }

    fun updateList(newToDoList: List<ToDoUIModel>) {
        toDoList.clear()
        toDoList.addAll(newToDoList)
        notifyDataSetChanged()
    }

}