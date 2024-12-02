package com.example.lista3

import androidx.recyclerview.widget.RecyclerView
import com.example.lista3.databinding.ExerciseListItemBinding

class ExerciseListViewHolder(
    private val binding: ExerciseListItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(subject: String, grade: String, content: String) {
        binding.exNumber.text = subject
        binding.gradeAvg.text = grade
        binding.content.text = content
    }
}
