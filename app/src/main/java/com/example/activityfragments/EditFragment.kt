package com.example.activityfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.activityfragments.databinding.FragmentEditBinding

class EditFragment : Fragment() {

    private lateinit var binding: FragmentEditBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentEditBinding.inflate(layoutInflater, container, false)
        binding.saveBtn.setOnClickListener {
            if (binding.textName.text.isNullOrBlank()) {
                binding.textName.error = "Введите имя"
            } else if (binding.textAge.text.isNullOrBlank()) {
                binding.textAge.error = "Введите возраст"
            } else if (binding.textAddress.text.isNullOrBlank()) {
                binding.textAge.error = "Введите адрес"
            } else if (binding.textEmail.text.isNullOrBlank()) {
                binding.textEmail.error = "Введите почту"
            } else {
                findNavController().navigate(
                    R.id.nav_info,
                    bundleOf(
                        "user" to User(
                            binding.textName.text.toString(),
                            binding.textAge.text.toString(),
                            binding.textAddress.text.toString(),
                            binding.textEmail.text.toString(),
                        )
                    )
                )
            }
        }
        return binding.root
    }
}