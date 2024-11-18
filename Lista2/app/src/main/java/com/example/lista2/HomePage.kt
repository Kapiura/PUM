package com.example.lista2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.ContentProviderCompat
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.lista2.databinding.FragmentHomePageBinding

class HomePage : Fragment()
{
    private lateinit var binding: FragmentHomePageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomePageBinding.inflate(inflater)



        binding.loginBtn.setOnClickListener {
            val action = HomePageDirections.actionHomePageToLoginPage()
            Navigation.findNavController(requireView()).navigate(action)
        }

        binding.registerBtn.setOnClickListener {
            val action = HomePageDirections.actionHomePageToRegister()
            Navigation.findNavController(requireView()).navigate(action)
        }

        return binding.root
    }

}