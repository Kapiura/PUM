package com.example.lista4

data class Quiz(
    val question: String,
    val ans: List<String>,
    val correctAnsIndex: Int
)

fun generateQuiz() : List<Quiz>
{
    return listOf(
        Quiz(
            "Jaki kraj jest największy pod względem liczby ludności?",
            listOf("Indie", "Chiny", "Stany Zjednoczone", "Brazylia"),
            1
        ),
        Quiz(
            "Jakie kolory składają się na flagę Niemiec?",
            listOf("Czerwony, biały, niebieski", "Żółty, zielony, czerwony", "Czarny, czerwony, żółty", "Biały, niebieski, czerwony"),
            2
        ),
        Quiz(
            "Z jakiego kraju pochodzi paella?",
            listOf("Włochy", "Hiszpania", "Portugalia", "Francja"),
            1
        ),
        Quiz(
            "Który kraj jest największym producentem wina?",
            listOf("Francja", "Hiszpania", "Włochy", "Australia"),
            2
        ),
        Quiz(
            "W którym roku odbył się chrzest Polski?",
            listOf("966", "1025", "1410", "1918"),
            0
        ),
        Quiz(
            "Kto był dowódcą wojsk polskich w bitwie pod Grunwaldem?",
            listOf("Mieszko I", "Władysław Jagiełło", "Kazimierz Wielki", "Bolesław Chrobry"),
            1
        ),
        Quiz(
            "Jakie umaszczenie mają konie rasy friesian?",
            listOf("Czarne", "Białe", "Srokate", "Gniade"),
            0
        ),
        Quiz(
            "Jak nazywa się miejsce, gdzie konie są trzymane i karmione?",
            listOf("Stodoła", "Stajnia", "Obora", "Spichlerz"),
            1
        ),
        Quiz(
            "Który kolor uzyskasz, mieszając niebieski i czerwony?",
            listOf("Zielony", "Fioletowy", "Pomarańczowy", "Brązowy"),
            1
        ),
        Quiz(
            "Który napój alkoholowy jest tradycyjnie produkowany z jęczmienia?",
            listOf("Wino", "Tequila", "Whisky", "Gin"),
            2
        )
    )
}
