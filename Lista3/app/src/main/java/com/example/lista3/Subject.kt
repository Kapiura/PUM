package com.example.lista3

data class Subject(
    val name: String
)

object Subjects
{
    val subjectList = mutableListOf(
        Subject("PUM"),
        Subject("Matematyka"),
        Subject("Bazy danych"),
        Subject("Elektronika"),
        Subject("Programowanie w C++"),
        Subject("Fizyka"),
    )
}