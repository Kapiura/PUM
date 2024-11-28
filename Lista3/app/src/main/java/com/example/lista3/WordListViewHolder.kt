package com.example.lista3

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.lista3.databinding.WordListItemBinding

class WordListViewHolder(
    private val binding: WordListItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: String){
        binding.singleWord.text = item
    }
}

