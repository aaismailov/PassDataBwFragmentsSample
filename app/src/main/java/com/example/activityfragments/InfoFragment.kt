package com.example.activityfragments

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.activityfragments.databinding.FragmentInfoBinding
import java.io.Serializable

class InfoFragment : Fragment() {
    private lateinit var binding: FragmentInfoBinding

    @Suppress("DEPRECATION")
    inline fun <reified T : Serializable> Bundle.customGetSerializable(key: String): T? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            getSerializable(key, T::class.java)
        } else {
            getSerializable(key) as? T
        }
    }

    private val user: User by lazy {
        arguments.let { it!!.customGetSerializable("user")!! }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentInfoBinding.inflate(layoutInflater, container, false)

        binding.userNameText.text = user.name
        binding.userAgeText.text = user.age
        binding.userAddressText.text = user.address
        binding.userEmailText.text = user.email

        binding.sendBtn.setOnClickListener {

            val sendMsg = "${user.name}\n${user.age}\n${user.address}\n${user.email}"
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, sendMsg)
                type = "text/plain"
            }

            try {
                startActivity(Intent.createChooser(sendIntent, null))
            } catch (e: Exception) {
                Toast.makeText(requireActivity(), "Повторите запрос", Toast.LENGTH_SHORT).show()
            }
        }
        return binding.root
    }

}