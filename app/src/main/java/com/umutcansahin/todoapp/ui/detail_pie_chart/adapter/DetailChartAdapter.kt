package com.umutcansahin.todoapp.ui.detail_pie_chart.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.umutcansahin.todoapp.utils.Constants
import com.umutcansahin.todoapp.utils.toFormat
import com.umutcansahin.todoapp.data.local.ToDoEntity
import com.umutcansahin.todoapp.databinding.DetailChartRecyclerBinding

class DetailChartAdapter(val todo: ArrayList<ToDoEntity>) : RecyclerView.Adapter<DetailChartAdapter.DetailChartViewHolder>(){

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
        holder.binding.recyclerNameTextView.text = todo[position].name
        holder.binding.recyclerTypeTextView.text = todo[position].type
        holder.binding.recyclerDateTextView.text = todo[position].timestamp.toFormat(
            Constants.CURRENT_DATE_FORMAT
        )
    }

    override fun getItemCount(): Int {
        return todo.size
    }

    fun updateList(newToDoList: List<ToDoEntity>) {
        todo.clear()
        todo.addAll(newToDoList)
        notifyDataSetChanged()
    }

}