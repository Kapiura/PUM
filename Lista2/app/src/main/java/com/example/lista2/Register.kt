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
import com.example.lista2.databinding.FragmentRegisterBinding

class Register : Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    private lateinit var manager: Manager


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(inflater)
        manager = Manager(requireContext())

        val regBtn: Button = binding.registerBtnReg
        val login: EditText = binding.loginPlaceholderReg
        val pass: EditText = binding.passPlaceholderReg
        val rpass: EditText = binding.rPassReg

        regBtn.setOnClickListener {
            val loginStr = login.text.toString()
            val passStr = pass.text.toString()
            val rpassStr = rpass.text.toString()

            val user = User(loginStr, passStr);

            if(rpassStr == passStr)
            {
                val match = manager.addUser(user)
                if(match){
                    Toast.makeText(requireContext(), "New user added", Toast.LENGTH_SHORT).show()
                    val action = RegisterDirections.actionRegisterToHomePage()
                    Navigation.findNavController(requireView()).navigate(action)
                }
                else{
                    Toast.makeText(requireContext(), "User already exists", Toast.LENGTH_SHORT).show()
                }
            }
            else{
                Toast.makeText(requireContext(), "Password do not match", Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }
}