package com.example.lista3

data class ExerciseList(
    val exercises: MutableList<Exercise> = mutableListOf(),
    val subject: Subject,
    val grade: Float
)
{
    companion object {
        fun generateExercisesList(): ExerciseList {
            val exList = mutableListOf<Exercise>()
            val randomGrade = (4..10).random().toFloat() / 2
            val subject = Subjects.subjectList[(0..4).random()]
            val numberOfExercises = (1..10).random()
            for (j in 0 until numberOfExercises) {
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