package com.example.lista3

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lista3.databinding.WordListItemBinding

class WordListAdapter(
    private val exerciseList: MutableList<ExerciseList>,
    private val onItemClick: (ExerciseList) -> Unit
) : RecyclerView.Adapter<WordListViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordListViewHolder {
        return WordListViewHolder(
            WordListItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        ){onItemClick(exerciseList[it])}
    }

    override fun getItemCount(): Int = exerciseList.size

    override fun onBindViewHolder(holder: WordListViewHolder, position: Int) {
        val currItem = exerciseList[position]
        val subjectName = currItem.subject.name
        val listNumber = exerciseList.subList(0, position).count { it.subject == currItem.subject } + 1
        val exercisesNumber = currItem.exercises.size
        val grade = currItem.grade
        holder.bind(subjectName,"Lista "+listNumber.toString(),"Liczba zadań: "+exercisesNumber.toString(),"Ocena: "+grade.toString())
    }

}