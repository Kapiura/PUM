package com.example.lista4

import android.os.Bundle
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val questions = generateQuiz()
            mainQuiz(questions)
        }
    }
}

@Composable
fun QuizEnd(result: Int, questionsSize: Int) {
    Column(
        modifier = Modifier.fillMaxSize().padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally)
    {
        Text(text = "Your score is: $result/$questionsSize")
    }
}


@Composable
fun mainQuiz(questions: List<Quiz>){

var currQuestionId by remember { mutableStateOf(0) }
var ans by remember { mutableStateOf<Int?>(null) }
var result by remember { mutableStateOf(0) }
var isEnd by remember { mutableStateOf(false) }

if (isEnd)
    QuizEnd(result = result, questionsSize = questions.size)
else
{
    val currentQuestion = questions[currQuestionId]

    main(
        questionText = currentQuestion.question,
        progressbarNumber = (currQuestionId + 1) / questions.size.toFloat(),
        answersList = currentQuestion.ans,
        selectedAnswerId = ans,
        onSelectedAnswer = { index -> ans = index },
        onClickedNext = {
            if (ans == currentQuestion.correctAnsIndex)
                result++
            ans = null
            if (currQuestionId < questions.size - 1)
                currQuestionId++
            else
                isEnd = true
        })
}
}



@Composable
fun main(
    questionText: String,
    progressbarNumber: Float,
    answersList: List<String>,
    selectedAnswerId: Int?,
    onSelectedAnswer: (Int) -> Unit,
    onClickedNext: () -> Unit)
{
    Column(
        modifier = Modifier.fillMaxSize().padding(10.dp),
        verticalArrangement = Arrangement.SpaceBetween) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Question ${(progressbarNumber * 10).toInt()}/10",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = 8.dp))
            LinearProgressIndicator(
                progress = progressbarNumber,
                modifier = Modifier.fillMaxWidth())
        }

        Card(
            modifier = Modifier.fillMaxWidth().padding(vertical = 20.dp),
            elevation = CardDefaults.cardElevation(4.dp),
            shape = RectangleShape,
            colors = CardDefaults.cardColors(containerColor = Color(0xFFE0E0E0)))
        {
            Text(
                text = questionText,
                modifier = Modifier.padding(10.dp),
                style = MaterialTheme.typography.bodyLarge)
        }

        Column {
            Card()
            {
                Column(
                    modifier = Modifier.padding(20.dp))
                {
                    answersList.forEachIndexed { index, answer ->
                        Card(
                            shape = RoundedCornerShape(10.dp),
                            colors = CardDefaults.cardColors(containerColor = Color(0xFFE0E0E0)),
                            modifier = Modifier.padding(bottom = 10.dp))
                        {
                            Row(
                                modifier = Modifier.fillMaxWidth().clickable { onSelectedAnswer(index) },
                                verticalAlignment = Alignment.CenterVertically)
                            {
                                RadioButton(
                                    selected = selectedAnswerId == index,
                                    onClick = { onSelectedAnswer(index) })
                                Text(
                                    text = answer,
                                    modifier = Modifier.padding(start = 10.dp))
                            }
                        }

                    }
                }

            }

        }

        Button(
            onClick = onClickedNext,
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
        )
        {
            Text("Next")
        }
    }
}