package com.cm.precedencecontrolsystem.data.source

import androidx.room.*
import com.cm.precedencecontrolsystem.domain.models.Student
import kotlinx.coroutines.flow.Flow

@Dao
interface StudentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun create(student: Student)

    @Query("SELECT * FROM student WHERE id =:id")
    fun getStudentById(id: Int): Student?

    @Query("SELECT * FROM student WHERE tail =:tail")
    fun getStudentByTail(tail: Boolean): Student?

    @Query("SELECT * FROM student ORDER BY name DESC")
    fun getAllStudents(): Flow<List<Student>>

    @Query("SELECT * FROM student LIMIT :size")
    fun getAllStudents(size: Int): Flow<List<Student>>

    @Query("SELECT * FROM student WHERE name =:name")
    fun getStudentsByName(name: String): Flow<List<Student>>

    @Delete
    fun delete(student: Student)
}