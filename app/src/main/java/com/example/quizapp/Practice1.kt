package com.example.quizapp

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quizapp.ui.theme.QuizAppTheme

@SuppressLint("UnrememberedMutableState")
@Composable
fun MyComposable() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Define a mutable state variable with an initial value
        var countState by remember { mutableStateOf(0) }


        Button(mutableStateOf(countState))

        Result1(mutableStateOf(countState))
    }
}


@Composable
fun Button(
    score: MutableState<Int>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        androidx.compose.material3
            .Button(onClick = {
                score.value++
            }) {
                Text("Click Me")
        }

    }
}




@Composable
fun Result1(
    score: MutableState<Int>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Your score is ${score.value}/5",
            fontSize = 20.sp,
            modifier = Modifier
                .padding(bottom = 20.dp),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
    }
    println("Inside Result - ${score.value}")
}

@Preview(showBackground = true)
@Composable
fun Practice1Preview() {
    QuizAppTheme {  // need to change
        MyComposable()
    }
}