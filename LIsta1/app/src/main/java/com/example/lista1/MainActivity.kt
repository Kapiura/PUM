package com.example.lista1

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity()
{
    private lateinit var question: TextView
    private lateinit var questionNumber: TextView
    private lateinit var answer1: RadioButton
    private lateinit var answer2: RadioButton
    private lateinit var answer3: RadioButton
    private lateinit var answer4: RadioButton
    private lateinit var radioGroup: RadioGroup
    private lateinit var buttonNext: Button
    private lateinit var progress: ProgressBar
    private lateinit var card : CardView

    private var questionList: ArrayList<Question> = ArrayList()
    private var number = 0
    private var result = 0

    private fun init()
    {
        question = findViewById(R.id.questionText)
        questionNumber = findViewById(R.id.questionNumberText)
        answer1 = findViewById(R.id.radioButton1)
        answer2 = findViewById(R.id.radioButton2)
        answer3 = findViewById(R.id.radioButton3)
        answer4 = findViewById(R.id.radioButton4)
        radioGroup = findViewById(R.id.radioGroup)
        buttonNext = findViewById(R.id.nextButton)
        progress = findViewById(R.id.progressBar)
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init();
        createQuestionDataBase();
        displayQuestion();
        listenNextButton();
    }

    private fun listenNextButton()
    {
        buttonNext.setOnClickListener {
            checkAnswer()
            if (++number < questionList.size)
                displayQuestion()
            else
                endQuiz()
        }
    }

    private fun endQuiz()
    {
        questionNumber.text = "Koniec Quizu"
        radioGroup.clearCheck()
        radioGroup.visibility = View.GONE
        answer1.visibility = View.GONE
        answer2.visibility = View.GONE
        answer3.visibility = View.GONE
        answer4.visibility = View.GONE
        question.text = "Twój wynik: $result/${questionList.size}"
        buttonNext.visibility = View.GONE
        progress.visibility = View.GONE
    }

    private fun createQuestionDataBase()
    {
        questionList.add(
            Question(
                "Jaki kraj jest największy pod względem liczby ludności?",
                listOf("Indie", "Chiny", "Stany Zjednoczone", "Brazylia"),
                1 // Prawidłowa odpowiedź: Chiny
            )
        )

        questionList.add(
            Question(
                "Jakie kolory składają się na flagę Niemiec?",
                listOf("Czerwony, biały, niebieski", "Żółty, zielony, czerwony", "Czarny, czerwony, żółty", "Biały, niebieski, czerwony"),
                2 // Prawidłowa odpowiedź: Czarny, czerwony, żółty
            )
        )

        questionList.add(
            Question(
                "Z jakiego kraju pochodzi paella?",
                listOf("Włochy", "Hiszpania", "Portugalia", "Francja"),
                1 // Prawidłowa odpowiedź: Hiszpania
            )
        )

        questionList.add(
            Question(
                "Który kraj jest największym producentem wina?",
                listOf("Francja", "Hiszpania", "Włochy", "Australia"),
                2 // Prawidłowa odpowiedź: Włochy
            )
        )

        questionList.add(
            Question(
                "W którym roku odbył się chrzest Polski?",
                listOf("966", "1025", "1410", "1918"),
                0 // Prawidłowa odpowiedź: 966
            )
        )

        questionList.add(
            Question(
                "Kto był dowódcą wojsk polskich w bitwie pod Grunwaldem?",
                listOf("Mieszko I", "Władysław Jagiełło", "Kazimierz Wielki", "Bolesław Chrobry"),
                1 // Prawidłowa odpowiedź: Władysław Jagiełło
            )
        )

        questionList.add(
            Question(
                "Jakie umaszczenie mają konie rasy friesian?",
                listOf("Czarne", "Białe", "Srokate", "Gniade"),
                0 // Prawidłowa odpowiedź: Czarne
            )
        )

        questionList.add(
            Question(
                "Jak nazywa się miejsce, gdzie konie są trzymane i karmione?",
                listOf("Stodoła", "Stajnia", "Obora", "Spichlerz"),
                1 // Prawidłowa odpowiedź: Stajnia
            )
        )

        questionList.add(
            Question(
                "Który kolor uzyskasz, mieszając niebieski i czerwony?",
                listOf("Zielony", "Fioletowy", "Pomarańczowy", "Brązowy"),
                1 // Prawidłowa odpowiedź: Fioletowy
            )
        )

        questionList.add(
            Question(
                "Który napój alkoholowy jest tradycyjnie produkowany z jęczmienia?",
                listOf("Wino", "Tequila", "Whisky", "Gin"),
                2 // Prawidłowa odpowiedź: Whisky
            )
        )
    }

    private fun displayQuestion()
    {
        questionNumber.text = "Pytanie ${number + 1}/${questionList.size}"
        progress.progress = ((number + 1) * 100 / questionList.size)

        val currentQuesttion = questionList[number]

        question.text = currentQuesttion.question
        answer1.text = currentQuesttion.answers[0]
        answer2.text = currentQuesttion.answers[1]
        answer3.text = currentQuesttion.answers[2]
        answer4.text = currentQuesttion.answers[3]

        radioGroup.clearCheck()
    }

    private fun checkAnswer()
    {
        val selectedAnswerIndex = when (radioGroup.checkedRadioButtonId) {
            R.id.radioButton1 -> 0
            R.id.radioButton2 -> 1
            R.id.radioButton3 -> 2
            R.id.radioButton4 -> 3
            else -> -1
        }
        if (selectedAnswerIndex == questionList[number].target) {
            result++
        }
    }


}