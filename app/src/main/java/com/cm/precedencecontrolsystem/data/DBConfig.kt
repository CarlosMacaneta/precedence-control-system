package com.cm.precedencecontrolsystem.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cm.precedencecontrolsystem.data.source.StudentDao
import com.cm.precedencecontrolsystem.data.source.SubscriptionDataSource
import com.cm.precedencecontrolsystem.domain.models.Student
import com.cm.precedencecontrolsystem.domain.models.Subject
import com.cm.precedencecontrolsystem.domain.models.Subscription

@Database(
    entities = [
        Student::class,
        Subject::class,
        Subscription::class
    ],
    version = 1,
    exportSchema = false
)
abstract class DBConfig: RoomDatabase() {
    abstract fun studentDao(): StudentDao
    abstract fun subscriptionDao(): SubscriptionDataSource

    companion object {
        const val DB_NAME = "scp_db"
    }
}