package com.umutcansahin.todoapp.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.umutcansahin.todoapp.databinding.FragmentHomeBinding
import com.umutcansahin.todoapp.ui.add_to_do.AddViewModel
import com.umutcansahin.todoapp.ui.home.adapter.ToDoListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()

    private val toDoListAdapter = ToDoListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}