package com.example.lista3

data class Exercise(
    val content: String,
    val points: Int
)
{
    companion object
    {
        fun generateExercise(): Exercise
        {
            val loremIpsum = """ Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum. """.trimIndent()
            val loremWords = loremIpsum.split("\\s+".toRegex()).toTypedArray()
            val randomContent = loremWords.take((10..loremWords.size).random()).joinToString(" ")
            val randomPoints = (1..10).random()
            return Exercise(randomContent, randomPoints)
        }
    }
}