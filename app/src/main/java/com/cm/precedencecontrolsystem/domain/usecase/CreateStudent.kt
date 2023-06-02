package com.cm.precedencecontrolsystem.domain.usecase

import com.cm.precedencecontrolsystem.domain.models.Student
import com.cm.precedencecontrolsystem.domain.repository.StudentRepository

class CreateStudent(
    private val repository: StudentRepository
) {
    suspend operator fun invoke(student: Student) {
        repository.create(student)
    }
}