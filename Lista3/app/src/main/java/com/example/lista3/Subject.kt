package com.example.lista3

data class Subject(
    val name: String
)

object Subjects
{
    val subjectList = mutableListOf(
        Subject("PUM"),
        Subject("Matematyka"),
        Subject("Fizyka"),
        Subject("Elektronika"),
        Subject("Algorytmy"),
    )
}