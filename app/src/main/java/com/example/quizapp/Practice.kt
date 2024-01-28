package com.example.quizapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.quizapp.ui.theme.QuizAppTheme

@Composable
fun RadioButtonScreen() {

//    // For single radio button    - (27-01-2024)
//    var radioState by remember { mutableStateOf(false) }
//
//    Box(
//        modifier = Modifier.fillMaxSize(),
//        contentAlignment = Center                           // new
//    ) {
//        RadioButton(selected = radioState, onClick = {
//                radioState = !radioState
//        })
//    }


    // For multiple radio button    - (27-01-2024)
    val gender = listOf("Male", "Female", "Other")
    var genderState by remember { mutableStateOf("Male") }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center                 // new
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            gender.forEach {
                /* it -> the each element of the gender list */
                RadioEachRow(
                    selected = genderState == it,
                    title = it) { data ->
                    genderState = data
                }
            }
        }
    }
}

// - (27-01-2024)
@Composable
fun RadioEachRow(
    selected: Boolean,
    title: String,
    /* This function will take String as argument and return Unit */
    onValueChange: (String) -> Unit             // lambda function
) {
    Row(
        verticalAlignment =Alignment.CenterVertically
    ) {
        RadioButton(selected = selected, onClick = {
            onValueChange(title)
        })
        Text(text = title)
    }
}

@Preview(showBackground = true)
@Composable
fun RadioButtonScreenPreview() {
    QuizAppTheme {  // need to change
        RadioButtonScreen()
    }
}
