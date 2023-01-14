package com.umutcansahin.todoapp.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.umutcansahin.todoapp.utils.Constants
import com.umutcansahin.todoapp.utils.toFormat
import com.umutcansahin.todoapp.data.local.ToDoEntity
import com.umutcansahin.todoapp.databinding.ToDoListRecyclerBinding
import com.umutcansahin.todoapp.ui.home.HomeFragmentDirections

class ToDoListAdapter(val toDoList: ArrayList<ToDoEntity>) : RecyclerView.Adapter<ToDoListAdapter.ToDoListViewHolder>(){

    class ToDoListViewHolder(val binding: ToDoListRecyclerBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoListViewHolder {
        val binding = ToDoListRecyclerBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ToDoListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ToDoListViewHolder, position: Int) {

        holder.binding.recyclerNameTextView.text = toDoList[position].name
        holder.binding.recyclerTypeTextView.text = toDoList[position].type
        holder.binding.recyclerDateTextView.text = toDoList[position].timestamp.toFormat(Constants.CURRENT_DATE_FORMAT)

        holder.binding.recyclerUpdateButton.setOnClickListener {
            val action = HomeFragmentDirections.actionNavigationHomeToAddFragment(
                isInsert = false,
                id = toDoList[position].id
            )
            Navigation.findNavController(it).navigate(action)
        }

        holder.binding.recyclerDeleteButton.setOnClickListener {
            onItemClick?.invoke(toDoList[position])
        }

    }

    override fun getItemCount(): Int {
        return toDoList.size
    }

    fun updateList(newToDoList: List<ToDoEntity>) {
        toDoList.clear()
        toDoList.addAll(newToDoList)
        notifyDataSetChanged()
    }

    var onItemClick: ((ToDoEntity) -> Unit)? = null


}