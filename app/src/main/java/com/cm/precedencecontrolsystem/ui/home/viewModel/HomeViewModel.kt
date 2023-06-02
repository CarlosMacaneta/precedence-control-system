package com.cm.precedencecontrolsystem.ui.home.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cm.precedencecontrolsystem.domain.models.Subscription
import com.cm.precedencecontrolsystem.domain.usecase.AppUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject constructor(
    private val useCases: AppUseCases
) : ViewModel() {

    private val _subscriptions = MutableLiveData<List<Subscription>>()
    val subscription: LiveData<List<Subscription>> = _subscriptions

    fun createSubscription(subscription: Subscription) {
        viewModelScope.launch {
            useCases.createSubscription(subscription)
        }
    }

    private fun getSubscriptions() {
        //_subscriptions.value = useCases.
    }
}