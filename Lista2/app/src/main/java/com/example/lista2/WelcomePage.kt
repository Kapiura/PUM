package com.example.lista2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.lista2.databinding.FragmentHomePageBinding
import com.example.lista2.databinding.FragmentWelcomePageBinding

class WelcomePage : Fragment() {
    private lateinit var binding: FragmentWelcomePageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWelcomePageBinding.inflate(inflater)

        val username = arguments?.let { WelcomePageArgs.fromBundle(it).username }
        binding.usernameWelcomeText.text = "Welcome, $username!"

        binding.logoutBtn.setOnClickListener {
            val action = WelcomePageDirections.actionWelcomePageToHomePage()
            Navigation.findNavController(requireView()).navigate(action)
        }
        return binding.root
    }
}