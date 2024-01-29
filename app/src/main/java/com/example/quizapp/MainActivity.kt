package com.example.quizapp

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
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
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
            Questions(quiz = quizList[indexOfQuizList])     // We have extra function for it


            Spacer(modifier = Modifier.weight(1f))          // new learning

            Button(
                onClick = {
                    if (indexOfQuizList < (quizList.size - 1)) {
                        indexOfQuizList++
                    }
                },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 48.dp)
                    .align(Alignment.End)                // new
            ) {
                Text(
                    text = "Next",
                    fontSize = 20.sp
                )
            }
        }
    }
}

@Composable
fun Questions(modifier: Modifier = Modifier, quiz: Quiz) {

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
            "${quiz.optionFour}"
        )
        var radioState by remember { mutableStateOf(quiz.optionOne) }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                options.forEach {
                    ColumnEachOption(selected = radioState == it,
                        title = it) { data ->
                        radioState = data
                    }
                }
            }
        }
    }
}

@Composable
fun ColumnEachOption(
    selected: Boolean,
    title: String,
    onValueChange: (String) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(selected = selected, onClick = {
            onValueChange(title)
        })
        Text(text = title)
    }
}

@Preview(showBackground = true)
@Composable
fun QuizUiPreview() {
    QuizAppTheme {
        QuizUi()
    }
}