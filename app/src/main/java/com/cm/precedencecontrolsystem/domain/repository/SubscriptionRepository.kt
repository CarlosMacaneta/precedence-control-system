package com.cm.precedencecontrolsystem.domain.repository

import com.cm.precedencecontrolsystem.domain.models.Subscription

interface SubscriptionRepository {

    suspend fun create(subscription: Subscription)

    fun getSubscriptionsByName(value: Long): List<Subscription>

    fun getAllSubscriptions(): List<Subscription>

    fun getAllSubscriptions(size: Int): List<Subscription>

    suspend fun destroy(subscription: Subscription)
}
