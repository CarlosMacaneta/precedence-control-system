package com.cm.precedencecontrolsystem.data.repository

import com.cm.precedencecontrolsystem.data.source.SubscriptionDataSource
import com.cm.precedencecontrolsystem.domain.models.Subscription
import com.cm.precedencecontrolsystem.domain.repository.SubscriptionRepository
import javax.inject.Inject

class SubscriptionRepositoryImpl
@Inject constructor(
    private val dao: SubscriptionDataSource
): SubscriptionRepository {

    override suspend fun create(subscription: Subscription) {
        dao.create(subscription)
    }

    override fun getSubscriptionsByName(value: Long) = dao.getSubscriptionsByName(value)

    override fun getAllSubscriptions() = dao.getAllSubscriptions()

    override fun getAllSubscriptions(size: Int) = dao.getAllSubscriptions(size)

    override suspend fun destroy(subscription: Subscription) {
        dao.delete(subscription)
    }

}