package com.cm.precedencecontrolsystem.data.repository

import com.cm.precedencecontrolsystem.data.source.StudentDao
import com.cm.precedencecontrolsystem.domain.models.Student
import com.cm.precedencecontrolsystem.domain.repository.StudentRepository


class StudentRepositoryImpl(
    private val dao: StudentDao
): StudentRepository {
    override suspend fun create(student: Student) {
        dao.create(student)
    }

    override fun getStudentById(id: Int) = dao.getStudentById(id)

    override fun getStudentsByName(value: String) = dao.getStudentsByName(value)

    override fun getAllStudents() = dao.getAllStudents()

    override fun getAllStudents(size: Int) = dao.getAllStudents(size)

    override suspend fun destroy(student: Student) {
        dao.delete(student)
    }

}