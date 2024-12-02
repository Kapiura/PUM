package com.example.lista3

import androidx.recyclerview.widget.RecyclerView
import com.example.lista3.databinding.OcenaListItemBinding

class OcenaListViewHolder(
    private val binding: OcenaListItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(subject: String, listCount: String, grade: String) {
        binding.subjectName.text = subject
        binding.listCount.text = listCount.toString()
        binding.gradeAvg.text = grade.toString()
    }
}

