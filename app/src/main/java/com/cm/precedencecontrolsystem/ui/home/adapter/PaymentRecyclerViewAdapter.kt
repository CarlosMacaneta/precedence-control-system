package com.cm.precedencecontrolsystem.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cm.precedencecontrolsystem.databinding.ItemSubscriptionInfoBinding
import com.cm.precedencecontrolsystem.domain.models.Subscription

class PaymentRecyclerViewAdapter(
    private val subscriptions: List<Subscription>
): RecyclerView.Adapter<PaymentRecyclerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        PaymentRecyclerViewHolder(
            ItemSubscriptionInfoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )

    override fun onBindViewHolder(holder: PaymentRecyclerViewHolder, position: Int) {
        holder.bind(subscriptions[position])
    }

    override fun getItemCount() = subscriptions.size
}