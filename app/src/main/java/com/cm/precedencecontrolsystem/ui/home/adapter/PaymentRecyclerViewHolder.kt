package com.cm.precedencecontrolsystem.ui.home.adapter

import androidx.recyclerview.widget.RecyclerView
import com.cm.precedencecontrolsystem.databinding.ItemSubscriptionInfoBinding
import com.cm.precedencecontrolsystem.domain.models.Subscription

class PaymentRecyclerViewHolder(
    private val binding: ItemSubscriptionInfoBinding
): RecyclerView.ViewHolder(binding.root) {

    fun bind(subscription: Subscription) {


        binding.entity.text = subscription.entity.toString()
        binding.reference.text = subscription.reference.toString()
        binding.amount.text = subscription.amount.toString()
        binding.date.text = subscription.date
    }
}