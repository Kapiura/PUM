package com.example.lista3

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lista3.databinding.ExerciseListItemBinding

class ExerciseListAdapter(private var exerciseLists: List<ExerciseList>) : RecyclerView.Adapter<ExerciseListViewHolder>() {

    private var filteredExerciseLists: List<ExerciseList> = exerciseLists

    fun filter(subjectName: String? = null, taskNumber: Int? = null) {
        filteredExerciseLists = exerciseLists.filter { exerciseList ->
            val isSubjectMatching = subjectName?.let { exerciseList.subject.name.contains(it, ignoreCase = true) } ?: true
            val isTaskNumberMatching = taskNumber?.let { taskNumber <= exerciseList.exercises.size } ?: true
            isSubjectMatching && isTaskNumberMatching
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseListViewHolder {
        val binding = ExerciseListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ExerciseListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ExerciseListViewHolder, position: Int) {
        val exerciseList = filteredExerciseLists[position]
        val exercise = exerciseList.exercises[position % exerciseList.exercises.size]

        holder.bind(
            subject = "Zadanie ${position + 1}",
            grade = "pkt: ${exercise.points}",
            content = exercise.content
        )
    }

    override fun getItemCount(): Int {
        return filteredExerciseLists.sumOf { it.exercises.size }
    }
}


