package com.example.lista3

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lista3.databinding.WordListItemBinding

class WordListAdapter(
    private val wordList: MutableList<String>,
    private val onItemClick: (String) -> Unit
) : RecyclerView.Adapter<WordListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordListViewHolder {
        return WordListViewHolder(
            WordListItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        ){onItemClick(wordList[it])}
    }

    override fun getItemCount(): Int = wordList.size

    override fun onBindViewHolder(holder: WordListViewHolder, position: Int) {
        val currentItem = wordList[position]
        holder.bind(currentItem)
    }

}