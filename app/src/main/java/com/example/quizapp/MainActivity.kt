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


            // - (16-02-2024)

            // Now I have to change the indexOfQuizList state
            // to change the quiz while clicking the next button
            var indexOfQuizList by remember { mutableStateOf(0) }
            var score by remember { mutableStateOf(0) }
            var selectedOption by remember { mutableStateOf("") }

            /* I need to modify the score mutable state
            * within the same composable to reflect it - (12-02-2024) */

            QuestionsAndOptions(
                quiz = quizList[indexOfQuizList],
                onSelectedOptionChange = { newSelectedOption ->   // (16-02-2024)
                    selectedOption = newSelectedOption
                },
                selectedOption
            )

            ResultAndButton(
                indexOfQuizList,
                mutableStateOf(selectedOption),
                score,
                quiz = quizList[indexOfQuizList],
                onIndexOfQuizListChange = { newIndex ->         // (16-02-2024)
                    indexOfQuizList = newIndex
                },
                onScoreChange = {
                    score = it                          // (16-02-2024)
                },
                onSelectedOptionChange = {
                    selectedOption = it
                }
            )
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun QuestionsAndOptions(
    quiz: Quiz,
    onSelectedOptionChange: (String) -> Unit,           // (16-02-2024)
    selectedOption: String                          // (16-02-2024)
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
        //var radioState by remember { mutableStateOf(quiz.optionOne) }



        
        // - (14-02-2024) ---------------------------- From here


        Box(
            modifier = Modifier
                .padding(32.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start,
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    RadioButton(selected = selectedOption == "a",
                        onClick = {
                            onSelectedOptionChange("a")         // (16-02-2024)
                        })
                    Text(text = "${quiz.optionOne}")
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    RadioButton(selected = selectedOption == "b",
                        onClick = {
                            onSelectedOptionChange("b")
                        })
                    Text(text = "${quiz.optionTwo}")
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    RadioButton(selected = selectedOption == "c",
                        onClick = {
                            onSelectedOptionChange("c")
                        })
                    Text(text = "${quiz.optionThree}")
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    RadioButton(selected = selectedOption == "d",
                        onClick = {
                            onSelectedOptionChange("d")          // (16-02-2024)
                        })
                    Text(text = "${quiz.optionFour}")
                }
            }
        }
    }
}


// - (02-02-2024)
@Composable
fun ResultAndButton(
    indexOfQuizList: Int,                           // (16-02-2024)
    selectedOption: MutableState<String>,
    score: Int,                                         // (16-02-2024)
    quiz: Quiz,
    onIndexOfQuizListChange: (Int) -> Unit,            // (16-02-2024)
    onScoreChange: (Int) -> Unit,                    // (16-02-2024)
    onSelectedOptionChange: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {                                         // (16-02-2024)

                onSelectedOptionChange(" ")
                
                if (indexOfQuizList < (quizList.size - 1)) {
                    onIndexOfQuizListChange(indexOfQuizList+1)

                    if (selectedOption.value == quiz.answer.toString()) {
                        onScoreChange(score+1)
                    }
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

        Text("Your score is ${score}/5",
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

/* While I press the next button after all of the quiz completed it
* still increments the score - (17-02-2024) - Done */

/* The selected state of radio button does not dis-appear
 * when clicking the next button - (17-02-2024) */