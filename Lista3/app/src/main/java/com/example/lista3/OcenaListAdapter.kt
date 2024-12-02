package com.example.lista3

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lista3.databinding.OcenaListItemBinding

class OcenaListAdapter(
    private val statistics: ExerciseListStatistics
) : RecyclerView.Adapter<OcenaListViewHolder>() {

    private val groupedBySubject = statistics.groupBySubject()
    private val averageGradeBySubject = statistics.calculateAverageGradeBySubject()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OcenaListViewHolder {
        val binding = OcenaListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return OcenaListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OcenaListViewHolder, position: Int) {
        val subject = groupedBySubject.keys.elementAt(position)
        val listCount = groupedBySubject[subject]?.size ?: 0
        val grade = averageGradeBySubject[subject] ?: 0f
        holder.bind(
            subject.name,
            "Liczba list: $listCount",
            "Średnia: $grade")
    }

    override fun getItemCount(): Int = groupedBySubject.size
}
