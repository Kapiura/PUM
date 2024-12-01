package com.example.lista3

import androidx.recyclerview.widget.RecyclerView
import com.example.lista3.databinding.WordListItemBinding

class WordListViewHolder(
    private val binding: WordListItemBinding,
    onItemClick: (Int) -> Unit
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: String, s: String, s1: String, s2: String){
        binding.singleWord.text = item
    }
    init {
        itemView.setOnClickListener {
            onItemClick(adapterPosition)
        }
    }
}

