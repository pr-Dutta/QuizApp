package com.example.quizapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.quizapp.ui.theme.QuizAppTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment

@Composable
fun ParentComposable() {
    // Create the state variable in the parent composable
    var count by remember { mutableStateOf(0) }

    // Pass the state variable and a callback function to modify it to the child composable
    ChildComposable(count = count, onCountChange = { newCount ->
        count = newCount
    })

}

@Composable
// onCountChange is an lambda function
fun ChildComposable(count: Int, onCountChange: (Int) -> Unit) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Use the count state variable passed from the parent
        Text(text = "Count: $count")

        // Button to increment the count
        Button(onClick = { onCountChange(count + 1) }) {
            Text(text = "Increment")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun Practice2Preview() {
    QuizAppTheme {  // need to change
        ParentComposable()
    }
}