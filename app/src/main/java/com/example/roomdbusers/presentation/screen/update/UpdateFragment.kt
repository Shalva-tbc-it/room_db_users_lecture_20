package com.example.roomdbusers.presentation.screen.update

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.roomdbusers.databinding.FragmentUpdateBinding
import com.example.roomdbusers.presentation.common.base.BaseFragment
import com.example.roomdbusers.presentation.event.update.UpdateEvent
import com.example.roomdbusers.presentation.model.Users
import com.example.roomdbusers.presentation.screen.state.RoomState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UpdateFragment : BaseFragment<FragmentUpdateBinding>(FragmentUpdateBinding::inflate) {

    private val viewModel: UpdateViewModel by viewModels()
    private val args: UpdateFragmentArgs by navArgs()

    override fun bind() {

    }

    override fun bindViewActionListeners() {
        listener()
    }

    override fun bindObserves() {
        roomHandle()
    }

    private fun listener() = with(binding) {
        btnSendUser.setOnClickListener {
            val user = Users (
                id = args.id,
                firstName = edFirstName.text.toString(),
                lastName = edLastName.text.toString(),
                age = edAge.text.toString(),
                email = edEmail.text.toString()

            )
            updateUser(user)
        }
    }

    private fun updateUser(users: Users) {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.onEvent(
                    UpdateEvent.Update(
                        users = users
                    )
                )
            }
        }
    }

    private fun roomHandle() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.roomStateFlow.collect {
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
            UpdateFragmentDirections.actionUpdateFragmentToHomeFragment()
        )
    }

}