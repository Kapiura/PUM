package com.example.lista2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.lista2.databinding.FragmentHomePageBinding
import com.example.lista2.databinding.FragmentLoginPageBinding

class LoginPage : Fragment() {
    private lateinit var binding: FragmentLoginPageBinding
    private lateinit var manager: Manager;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginPageBinding.inflate(inflater)
        manager = Manager(requireContext())

        val loginBtn: Button = binding.loginBtnLogin

        loginBtn.setOnClickListener {
            val loginStr = binding.loginPlaceholderLogin.text.toString()
            val passStr = binding.passPlaceholderLogin.text.toString()

            if (loginStr.isBlank() || passStr.isBlank()) {
                Toast.makeText(requireContext(), "Login and password cannot be empty", Toast.LENGTH_SHORT).show()
            }
            if (manager.getUser(loginStr, passStr)) {
                val action = LoginPageDirections.actionLoginPageToWelcomePage(loginStr)
                Navigation.findNavController(requireView()).navigate(action)
            } else {
                Toast.makeText(requireContext(), "Wrong username or password", Toast.LENGTH_SHORT).show()
                binding.loginPlaceholderLogin.text.clear()
                binding.passPlaceholderLogin.text.clear()
            }
        }
        return binding.root
    }
}