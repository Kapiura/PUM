package com.example.lista3

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lista3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val wordList by lazy { MutableList(50) { "word $it" } }

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

        override fun onCreate(savedInstanceState: Bundle?) {
            binding.recyclerView.apply {
                adapter = WordListAdapter(wordList)
                layoutManager = LinearLayoutManager(this@MainActivity)
            }
            super.onCreate(savedInstanceState)
            enableEdgeToEdge()
            setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}