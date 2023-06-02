package com.cm.precedencecontrolsystem.domain.usecase

import com.cm.precedencecontrolsystem.domain.models.Subscription
import com.cm.precedencecontrolsystem.domain.repository.SubscriptionRepository

class CreateSubscription(
    private val repository: SubscriptionRepository
) {
    suspend operator fun invoke(subscription: Subscription) {
        repository.create(subscription)
    }
}