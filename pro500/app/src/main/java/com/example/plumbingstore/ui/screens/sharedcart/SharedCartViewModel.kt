package com.example.plumbingstore.ui.screens.sharedcart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.plumbingstore.data.model.SharedCart
import com.example.plumbingstore.data.model.SharedCartStatus
import com.example.plumbingstore.data.repository.SharedCartRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class SharedCartViewModel @Inject constructor(
    private val sharedCartRepository: SharedCartRepository
) : ViewModel() {

    private val _userId = MutableStateFlow("") // TODO: Получать из AuthRepository
    val userId: StateFlow<String> = _userId.asStateFlow()

    val sharedCarts: StateFlow<List<SharedCart>> = userId
        .flatMapLatest { userId ->
            sharedCartRepository.getUserSharedCarts(userId)
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun createSharedCart(name: String) {
        viewModelScope.launch {
            val newCart = SharedCart(
                id = UUID.randomUUID().toString(),
                name = name,
                ownerId = userId.value,
                totalAmount = 0.0,
                participants = listOf(userId.value),
                status = SharedCartStatus.ACTIVE
            )
            sharedCartRepository.createSharedCart(newCart)
        }
    }

    fun updateSharedCartStatus(cartId: String, status: SharedCartStatus) {
        viewModelScope.launch {
            sharedCartRepository.updateSharedCartStatus(cartId, status)
        }
    }

    fun updateSharedCartAmount(cartId: String, amount: Double) {
        viewModelScope.launch {
            sharedCartRepository.updateSharedCartAmount(cartId, amount)
        }
    }

    fun addParticipant(cartId: String, participantId: String) {
        viewModelScope.launch {
            val cart = sharedCartRepository.getSharedCart(cartId).first()
            cart?.let {
                val updatedParticipants = it.participants + participantId
                sharedCartRepository.updateSharedCartParticipants(cartId, updatedParticipants)
            }
        }
    }

    fun removeParticipant(cartId: String, participantId: String) {
        viewModelScope.launch {
            val cart = sharedCartRepository.getSharedCart(cartId).first()
            cart?.let {
                val updatedParticipants = it.participants - participantId
                sharedCartRepository.updateSharedCartParticipants(cartId, updatedParticipants)
            }
        }
    }
} 