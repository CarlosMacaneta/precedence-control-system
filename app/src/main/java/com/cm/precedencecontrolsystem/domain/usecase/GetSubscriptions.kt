package com.cm.precedencecontrolsystem.domain.usecase

import com.cm.precedencecontrolsystem.domain.repository.SubscriptionRepository

class GetSubscriptions(
    private val repository: SubscriptionRepository
) {
    operator fun invoke() = repository.getAllSubscriptions()
}