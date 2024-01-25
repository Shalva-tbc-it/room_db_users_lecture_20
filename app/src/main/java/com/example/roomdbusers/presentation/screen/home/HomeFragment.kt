package com.example.roomdbusers.presentation.screen.home

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdbusers.databinding.FragmentHomeBinding
import com.example.roomdbusers.presentation.common.base.BaseFragment
import com.example.roomdbusers.presentation.event.home.HomeEvent
import com.example.roomdbusers.presentation.screen.home.adapter.UsersRecyclerAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private lateinit var adapter: UsersRecyclerAdapter
    private val viewModel: HomeViewModel by viewModels()


    override fun bind() {
        bindAdapter()
        getAllUsers()
    }

    override fun bindViewActionListeners() {
        binding.btnAddUser.setOnClickListener {
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToAddFragment()
            )
        }
        adapterListener()
    }

    override fun bindObserves() {

    }

    private fun bindAdapter() {
        adapter = UsersRecyclerAdapter()
        binding.recyclerUsers.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerUsers.adapter = adapter
    }

    private fun adapterListener() {
        adapter.setOnItemClickListener(
            listener = {
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToUpdateFragment(it.id)
                )
            }
        )
    }


    private fun getAllUsers() {
        viewModel.onEvent(HomeEvent.GetAllUsers)
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.users.collect {
                    HomeEvent.GetAllUsers
                    adapter.submitList(it)
                }
            }
        }
    }


}