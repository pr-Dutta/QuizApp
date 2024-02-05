package com.example.quizapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.example.quizapp.ui.theme.QuizAppTheme

@Composable
fun MyComposable() {
    // Define a mutable state variable with an initial value
    val countState = remember { mutableStateOf(0) }

    // Access the current value of the mutable state
    val count = countState.value

    // Modify the mutable state
    countState.value += 1

    // Rest of the composable code...
}

@Preview(showBackground = true)
@Composable
fun Practice1Preview() {
    QuizAppTheme {  // need to change
        MyComposable()
    }
}