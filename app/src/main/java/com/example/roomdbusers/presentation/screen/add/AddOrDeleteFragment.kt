package com.example.roomdbusers.presentation.screen.add

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.roomdbusers.databinding.FragmentAddBinding
import com.example.roomdbusers.presentation.common.base.BaseFragment
import com.example.roomdbusers.presentation.event.add_or_delete.AddOrDeleteEvent
import com.example.roomdbusers.presentation.model.Users
import com.example.roomdbusers.presentation.screen.state.RoomState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AddOrDeleteFragment : BaseFragment<FragmentAddBinding>(FragmentAddBinding::inflate) {

    private val viewModel: AddOrDeleteViewModel by viewModels()

    override fun bind() {

    }

    override fun bindViewActionListeners() {
        listener()
    }

    override fun bindObserves() {
        roomUserHandle()
    }


    private fun listener() = with(binding) {
        btnSendUser.setOnClickListener {
            val user = Users(
                id = 0,
                firstName = edFirstName.text.toString(),
                lastName = edLastName.text.toString(),
                age = edAge.text.toString(),
                email = edEmail.text.toString()

            )
            addUsers(
                users = user
            )
        }

        btnDeleteUser.setOnClickListener {
            deleteUserWithEmail(edEmailDelete.text.toString())
        }

    }


    private fun addUsers(users: Users) {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.onEvent(
                    AddOrDeleteEvent.AddUser(
                        users = users
                    )
                )
            }
        }
    }

    private fun deleteUserWithEmail(email: String) {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.onEvent(
                    AddOrDeleteEvent.Delete(
                        email = email
                    )
                )
            }
        }
    }

    private fun roomUserHandle() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.roomStateFlow.collectLatest {
                    when (it) {
                        is RoomState.Success -> {
                            Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                            navigateToHome()
                        }
                        is RoomState.Error -> Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    private fun navigateToHome() {
        findNavController().navigate(
            AddOrDeleteFragmentDirections.actionAddFragmentToHomeFragment()
        )
    }



}