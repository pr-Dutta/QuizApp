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

            var score by remember { mutableStateOf(1) }

            /* I need to modify the score mutable state
            * within the same composable to reflect it - (12-02-2024) */

            QuestionsAndOptions(
                mutableStateOf(score),
                quiz = quizList[indexOfQuizList],
                mutableStateOf(indexOfQuizList)
            )
        }
    }
}

@Composable
fun QuestionsAndOptions(
    score: MutableState<Int>,
    quiz: Quiz,
    indexOfQuizList: MutableState<Int>
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



        
        // - (14-02-2024) ---------------------------- From here
        var selectedOption by remember { mutableStateOf(false) }

        Box(
            modifier = Modifier
                .padding(32.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    RadioButton(selected = selectedOption,
                        onClick = {
                            selectedOption = !selectedOption
                        })
                    Text(text = options[0])
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    RadioButton(selected = selectedOption,
                        onClick = {
                            selectedOption = !selectedOption
                        })
                    Text(text = options[1])
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    RadioButton(selected = selectedOption,
                        onClick = {
                            selectedOption = !selectedOption
                        })
                    Text(text = options[2])
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    RadioButton(selected = selectedOption,
                        onClick = {
                            selectedOption = !selectedOption
                        })
                    Text(text = options[3])
                }
            }
        }
    }

    ResultAndButton(
        indexOfQuizList,
        score = score
    )
}


// - (02-02-2024)
@Composable
fun ResultAndButton(
    indexOfQuizList: MutableState<Int>,
    score: MutableState<Int>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {
                if (indexOfQuizList.value < (quizList.size - 1)) {
                    indexOfQuizList.value++
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

        Text("Your score is ${score.value}/5",
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

/* TO-DO
* First I have to make my app show the increased number - learn with a
* simple app - started

*  The score isn't displayed, But it wasn't displayed on the UI
* And The score isn't staying while using mutableState of with remember */