package com.example.lista3

import androidx.recyclerview.widget.RecyclerView
import com.example.lista3.databinding.WordListItemBinding

class WordListViewHolder(
    private val binding: WordListItemBinding,
    onItemClick: (Int) -> Unit
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(subject: String, listName: String, taskCount: String, grade: String){
        binding.subjectName.text = subject
        binding.listName.text = listName
        binding.taskCount.text = taskCount
        binding.grade.text = grade
    }
    init {
        itemView.setOnClickListener {
            onItemClick(adapterPosition)
        }
    }
}

