package com.example.lista3

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.lista3.databinding.WordListItemBinding

class WordListViewHolder(
    private val binding: WordListItemBinding,
    onItemClick: (Int) -> Unit
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: String){
        binding.singleWord.text = item
    }
    init {
        itemView.setOnClickListener {
            onItemClick(adapterPosition)
        }
    }
}

