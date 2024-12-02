package com.example.lista3

import kotlin.math.roundToInt

class ExerciseListStatistics(private val exerciseLists: List<ExerciseList>)
{
    fun groupBySubject(): Map<Subject, List<ExerciseList>>
    {
        return exerciseLists.groupBy { it.subject }
    }
    fun calculateAverageGradeBySubject(): Map<Subject, Float> {
        return exerciseLists
            .groupBy { it.subject }
            .mapValues { (_, lists) ->
                val average = lists.map { it.grade }.average().toFloat()
                (average * 100).roundToInt() / 100f
            }
    }

    fun countUniqueSubjects(): Int
    {
        return exerciseLists.map { it.subject }.distinct().count()
    }
}