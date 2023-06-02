package com.cm.precedencecontrolsystem.data.source

import androidx.room.*
import com.cm.precedencecontrolsystem.domain.models.Subscription

@Dao
interface SubscriptionDataSource {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun create(subscription: Subscription)

    @Query("SELECT * FROM subscription ORDER BY reference DESC")
    fun getAllSubscriptions(): List<Subscription>

    @Query("SELECT * FROM subscription LIMIT :size")
    fun getAllSubscriptions(size: Int): List<Subscription>

    @Query("SELECT * FROM subscription WHERE reference =:reference")
    fun getSubscriptionsByName(reference: Long): List<Subscription>

    @Delete
    fun delete(subscription: Subscription)
}