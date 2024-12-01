package com.example.lista3

data class ExerciseList(
    val exercises: MutableList<Exercise> = mutableListOf(),
    val subject: Subject,
    val grade: Float
)
{
    companion object {
        fun generateExercisesList(): ExerciseList
        {
            val exList = mutableListOf<Exercise>()
            val randomGrade = (4..10).random().toFloat() / 2
            val subject = Subjects.subjectList[(0..5).random()]
            for(j in 1..10)
            {
                exList.add(Exercise.generateExercise())
            }
            return ExerciseList(exList, subject, randomGrade)
        }
        object ExerciseListProvider {
            val allExerciseLists: MutableList<ExerciseList> by lazy {
                MutableList(20) { ExerciseList.generateExercisesList() }
            }
        }
    }
}