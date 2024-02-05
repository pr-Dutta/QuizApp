package com.example.quizapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quizapp.ui.theme.QuizAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuizAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    QuizUi()
                }
            }
        }
    }
}

@Composable
fun QuizUi(modifier: Modifier = Modifier) {
    val image = painterResource(R.drawable.quiz_app_background)

    Box {
        Image(
            painter = image,
            contentDescription = null,
            contentScale = ContentScale.Crop,       // new
        )

        QuizCard()
    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun QuizCard(modifier: Modifier = Modifier) {

    Column(modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,           // new
        horizontalAlignment = Alignment.CenterHorizontally  // new
    ) {
        Card(
            modifier  = Modifier
                .size(width = 310.dp, height = 600.dp)     // new
                .background(color = Color.Black)
                .padding(8.dp),         // unexpected Improvement   new
            shape = RoundedCornerShape(30.dp)       // new
            ) {

            var indexOfQuizList by remember { mutableStateOf(0) }


            // - (04-02-2024)
            var selectedOption by remember { mutableStateOf("") }
            var score by remember { mutableStateOf(0) }

            Questions(
                mutableStateOf(selectedOption),
                mutableStateOf(score),
                quiz = quizList[indexOfQuizList]
            )


            Spacer(modifier = Modifier.weight(1f))          // new learning



            Button(
                onClick = {
                    if (indexOfQuizList < (quizList.size - 1)) {

                        // TO - DO
//                        if (quizList[indexOfQuizList].answer.toString() == selectedOption) {
//                            score++
//                        }

                        println("Hi")
                        indexOfQuizList++
                    }
                },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 10.dp)
                    //.align(Alignment.End)                // new
            ) {
                Text(
                    text = "Next",
                    fontSize = 20.sp
                )
            }
            Result(mutableStateOf(score))

        }
    }
}

@Composable
fun Questions(
    selectedOption: MutableState<String>,
    score: MutableState<Int>,
    quiz: Quiz
) {

    Column(modifier = Modifier
        .padding(18.dp)
    ) {

        Text("Your Quiz", fontSize = 40.sp,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                // have to learn how to add hash code color
                .background(color = Color.LightGray)
        )

        Text(
            quiz.question,
            fontSize = 25.sp,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 32.dp),
            textAlign = TextAlign.Center
        )


        // - (29-01-2024)
        val options = listOf(
            "${quiz.optionOne}",
            "${quiz.optionTwo}",
            "${quiz.optionThree}",
            "${quiz.optionFour}",
        )
        var radioState by remember { mutableStateOf(quiz.optionOne) }

        Box(
            modifier = Modifier
                .padding(32.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start,              // new
            ) {
                options.forEach {                  // have to revise it
                    RowEachOption(
                        selectedOption,
                        score,                                // - (01-02-2024)
                        it,
                        quiz,
                        selected = radioState == it,
                        title = it) { data ->
                        radioState = data
                    }
                }
            }
        }

    }
}

@Composable
fun RowEachOption(
    selectedOption: MutableState<String>,
    score: MutableState<Int>,                                                       // - (01-02-2024)
    it: String,
    quiz: Quiz,
    selected: Boolean,
    title: String,
    onValueChange: (String) -> Unit,
) {

    // This will store the selected option - (01-02-2024)
    //var selectedOption by remember { mutableStateOf("") }
    var correctAnswer = "${quiz.answer}"
    //var score by remember { mutableIntStateOf(0) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        RadioButton(selected = selected, onClick = {
            onValueChange(title)

                                                             // (01-02-2024)
            if (it == quiz.optionOne) {
                selectedOption.value = "a"
                println("${quiz.optionOne}")
            }else if (it == quiz.optionTwo) {
                selectedOption.value = "b"
                println("${quiz.optionOne}")
            }else if (it == quiz.optionTwo) {
                selectedOption.value = "c"
                println("${quiz.optionOne}")
            }else if (it == quiz.optionThree) {
                selectedOption.value = "d"
                println("${quiz.optionFour}")
            }

            // TO-DO - (02-02-2024)
            if (selectedOption.value == correctAnswer) {
                score.value++
            }

        })
        Text(text = title)
    }
}

// - (02-02-2024)
@Composable
fun Result(
    score: MutableState<Int>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Your score is ${score.value}/10",
            fontSize = 20.sp,
            modifier = Modifier
                .padding(bottom = 20.dp),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(
    showBackground = true,
    name = "My Quiz App"
    )
@Composable
fun QuizUiPreview() {
    QuizAppTheme {
        QuizUi()
    }
}