package com.cm.precedencecontrolsystem.domain.usecase

import com.cm.precedencecontrolsystem.domain.repository.StudentRepository

class GetStudentById(
    private val repository: StudentRepository
) {
    operator fun invoke(id: Int) = repository.getStudentById(id)
}