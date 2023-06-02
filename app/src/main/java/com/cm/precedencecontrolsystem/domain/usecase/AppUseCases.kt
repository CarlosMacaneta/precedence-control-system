package com.cm.precedencecontrolsystem.domain.usecase

data class AppUseCases(
    val createStudent: CreateStudent,
    val createSubscription: CreateSubscription,
    val getStudentById: GetStudentById
)