package com.example.lista2

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.lista2.databinding.FragmentRegisterBinding

class MainActivity : AppCompatActivity() {
    private lateinit var manager: Manager

    override fun onCreate(savedInstanceState: Bundle?) {
        manager = Manager(this)


        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_main)

        manager.addUser("admin", "admin")
        manager.addUser("ziomek", "ziomek123")
        manager.addUser("ziutek", "123")
        manager.addUser("marcin", "marek")
        manager.addUser("kapi", "123")

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

    }
}