package com.umutcansahin.todoapp.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.umutcansahin.todoapp.R
import com.umutcansahin.todoapp.databinding.FragmentHomeBinding
import com.umutcansahin.todoapp.ui.home.adapter.ToDoListAdapter
import com.umutcansahin.todoapp.utils.BaseFragment
import com.umutcansahin.todoapp.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment(R.layout.fragment_home) {

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
            val action =
                HomeFragmentDirections.actionNavigationHomeToAddFragment(isInsert = true, id = 0)
            Navigation.findNavController(it).navigate(action)
        }
        toDoListAdapter.onItemClick = {

            val alert = MaterialAlertDialogBuilder(requireContext())
            alert.setTitle(R.string.alert)
            alert.setIcon(R.drawable.ic_baseline_warning_amber_24)
            alert.setMessage(R.string.alert_mesaj)
            alert.setPositiveButton(R.string.yes) { _, _ ->
                viewModel.deleteToDo(it)
            }
            alert.setNegativeButton(R.string.no) { _, _ ->

            }
            alert.show()
        }

        toDoListAdapter.onItemClickCheckBox = {
            viewModel.updateIsDone(toDoUIModel = it)
        }
        bottomNavigationViewVisibility = View.VISIBLE
        toolbarVisibility = true
    }

    private fun observeEvent() {
        viewModel.uiState.observe(viewLifecycleOwner, Observer { todo ->
            todo?.let {
                toDoListAdapter.updateList(it)
            }
        })
    }
}