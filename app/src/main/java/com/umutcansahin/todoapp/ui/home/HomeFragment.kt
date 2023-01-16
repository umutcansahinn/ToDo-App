package com.umutcansahin.todoapp.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.umutcansahin.todoapp.R
import com.umutcansahin.todoapp.databinding.FragmentHomeBinding
import com.umutcansahin.todoapp.ui.home.adapter.ToDoListAdapter
import com.umutcansahin.todoapp.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding by viewBinding(FragmentHomeBinding::bind)

    private val viewModel: HomeViewModel by viewModels()

    private val toDoListAdapter = ToDoListAdapter(arrayListOf())

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getAllToDo()

        observeEvent()
        initViews()

    }

    private fun initViews() {

        binding.recyclerView.adapter = toDoListAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        binding.floatingActionButton.setOnClickListener {
            val action = HomeFragmentDirections.actionNavigationHomeToAddFragment(isInsert = true,id = 0)
            Navigation.findNavController(it).navigate(action)
        }
        toDoListAdapter.onItemClick = {
            viewModel.deleteToDo(it)
        }
    }

    private fun observeEvent() {
        viewModel.uiState.observe(viewLifecycleOwner, Observer { todo->
            todo?.let {
                toDoListAdapter.updateList(it)
            }
        })
    }
}