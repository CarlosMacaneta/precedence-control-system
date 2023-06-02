package com.cm.precedencecontrolsystem.di

import android.app.Application
import androidx.room.Room
import com.cm.precedencecontrolsystem.data.DBConfig
import com.cm.precedencecontrolsystem.data.repository.StudentRepositoryImpl
import com.cm.precedencecontrolsystem.data.repository.SubscriptionRepositoryImpl
import com.cm.precedencecontrolsystem.domain.repository.StudentRepository
import com.cm.precedencecontrolsystem.domain.repository.SubscriptionRepository
import com.cm.precedencecontrolsystem.domain.usecase.AppUseCases
import com.cm.precedencecontrolsystem.domain.usecase.CreateStudent
import com.cm.precedencecontrolsystem.domain.usecase.CreateSubscription
import com.cm.precedencecontrolsystem.domain.usecase.GetStudentById
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton fun providesDatabaseConfig(app: Application): DBConfig =
        Room.databaseBuilder(
            app,
            DBConfig::class.java,
            DBConfig.DB_NAME
        ).build()

    @Provides
    @Singleton fun provideAppUseCases(
        studentRepository: StudentRepository,
        subscriptionRepository: SubscriptionRepository
    ): AppUseCases = AppUseCases(
        CreateStudent(studentRepository),
        CreateSubscription(subscriptionRepository),
        GetStudentById(studentRepository)
    )

    @Singleton
    @Provides fun providesStudentRepository(
        dsStudent: DBConfig
    ): StudentRepository = StudentRepositoryImpl(dsStudent.studentDao())

    @Singleton
    @Provides fun providesSubscriptionRepository(
        dsStudent: DBConfig
    ): SubscriptionRepository = SubscriptionRepositoryImpl(dsStudent.subscriptionDao())
}