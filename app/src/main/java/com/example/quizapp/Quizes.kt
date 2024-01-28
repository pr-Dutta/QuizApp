package com.example.quizapp

data class Quiz(
    val question: String,
    val optionOne: String,
    val optionTwo: String,
    val optionThree: String,
    val optionFour: String,
    val answer: Char
)

val quizOne = Quiz(
    "Q. 1  What is the capital city of France?",
    "a) Berlin",
    "b) Paris",
    "c) Rome",
    "d) Madrid",
    'b'
)

val quizTwo = Quiz(
    "Q. 2 Grand Central Terminal, Park Avenue, New York is the world's?",
    "a) largest railway station",
    "b) highest railway station",
    "c) longest railway station",
    "d) None of the above",
    'a'
)

val quizThree = Quiz(
    "Q. 3 Entomology is the science that studies?",
    "a) Behavior of human beings",
    "b) Insects",
    "c) The origin and history of technical and scientific terms",
    "d) The formation of rocks",
    'b'
)

val quizFour = Quiz(
    "Q. 4 Garampani sanctuary is located at?",
    "a) Junagarh, Gujarat",
    "b) Diphu, Assam",
    "c) Kohima, Nagaland",
    "d) Gangtok, Sikkim",
    'b'
)

val quizFive = Quiz(
    "Q. 5 For which of the following disciplines is Nobel Prize awarded?",
    "a) Physics and Chemistry",
    "b) Physiology or Medicine",
    "c) Literature, Peace and Economics",
    "d) All of the above",
    'd'
)

val quizList = listOf(quizOne, quizTwo, quizThree, quizFour, quizFive)


// TO - DO

/*
* The text should not go out of the card */




