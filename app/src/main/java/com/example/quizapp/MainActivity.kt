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
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
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
            modifier = Modifier.fillMaxSize()
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
        val customColorForCardBackground = Color(0xffa8dadc)
        Card(
            modifier  = Modifier
                .size(width = 310.dp, height = 600.dp)     // new
                .background(color = customColorForCardBackground)
                .padding(8.dp),         // unexpected Improvement   new
            shape = RoundedCornerShape(30.dp),       // new
            colors = CardDefaults.cardColors(
                containerColor = Color(0xffa8dadc),
                //contentColor = Color(0xffa8dadc),
            )
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

        Text(
            "Latest Quiz", fontSize = 32.sp,
            style = TextStyle(
                fontFamily = FontFamily.Monospace
            ),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                // have to learn how to add hash code color
                .background(
                    color = Color(0xffff8800),
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(8.dp),
            fontWeight = FontWeight.Bold
        )

        Text(
            quiz.question,
            fontSize = 25.sp,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 32.dp),
            textAlign = TextAlign.Center,
            //fontStyle = FontStyle.Italic,
            style = TextStyle(
                fontFamily = FontFamily.Monospace
            )
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
                    Text(
                        text = "${quiz.optionOne}",
                        style = TextStyle(
                            fontFamily = FontFamily.Monospace
                        ),
                        fontSize = 18.sp
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    RadioButton(selected = selectedOption == "b",
                        onClick = {
                            onSelectedOptionChange("b")
                        })
                    Text(
                        text = "${quiz.optionTwo}",
                        style = TextStyle(
                            fontFamily = FontFamily.Monospace
                        ),
                        fontSize = 18.sp
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    RadioButton(selected = selectedOption == "c",
                        onClick = {
                            onSelectedOptionChange("c")
                        })
                    Text(
                        text = "${quiz.optionThree}",
                        style = TextStyle(
                            fontFamily = FontFamily.Monospace
                        ),
                        fontSize = 18.sp
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    RadioButton(selected = selectedOption == "d",
                        onClick = {
                            onSelectedOptionChange("d")          // (16-02-2024)
                        })
                    Text(
                        text = "${quiz.optionFour}",
                        style = TextStyle(
                            fontFamily = FontFamily.Monospace
                        ),
                        fontSize = 18.sp
                    )
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
                }else if (indexOfQuizList < quizList.size) {
                    if (selectedOption.value == quiz.answer.toString()) {
                        onScoreChange(score+1)
                        selectedOption.value = " "          // new
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
                style = TextStyle(
                    fontFamily = FontFamily.Monospace
                ),
                fontSize = 20.sp
            )
        }

        Text(
            "Your score is ${score}/5",
            style = TextStyle(
                fontFamily = FontFamily.Monospace
            ),
            fontSize = 24.sp,
            modifier = Modifier
                .padding(bottom = 20.dp)
                .background(
                    color = Color(0xffff8800),
                    shape = RoundedCornerShape(8.dp)
                ),
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

/* One new improvement should be done ->
* the score is increasing after the last question arrives,
* which should not be happen because we submit it already - 20-02-2024 */