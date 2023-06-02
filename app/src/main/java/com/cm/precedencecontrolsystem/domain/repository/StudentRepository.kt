package com.cm.precedencecontrolsystem.domain.repository

import com.cm.precedencecontrolsystem.domain.models.Student
import kotlinx.coroutines.flow.Flow

interface StudentRepository {

    suspend fun create(student: Student)

    fun getStudentById(id: Int): Student?

    fun getStudentsByName(value: String): Flow<List<Student>>

    fun getAllStudents(): Flow<List<Student>>

    fun getAllStudents(size: Int): Flow<List<Student>>

    suspend fun destroy(student: Student)
}