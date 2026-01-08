package com.example.plumbingstore.data.repository

import com.example.plumbingstore.data.local.SharedCartDao
import com.example.plumbingstore.data.model.SharedCart
import com.example.plumbingstore.data.model.SharedCartStatus
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedCartRepository @Inject constructor(
    private val sharedCartDao: SharedCartDao
) {
    fun getUserSharedCarts(userId: String): Flow<List<SharedCart>> =
        sharedCartDao.getUserSharedCarts(userId)

    fun getSharedCart(cartId: String): Flow<SharedCart?> =
        sharedCartDao.getSharedCart(cartId)

    suspend fun createSharedCart(sharedCart: SharedCart) =
        sharedCartDao.insertSharedCart(sharedCart)

    suspend fun updateSharedCart(sharedCart: SharedCart) =
        sharedCartDao.updateSharedCart(sharedCart)

    suspend fun deleteSharedCart(sharedCart: SharedCart) =
        sharedCartDao.deleteSharedCart(sharedCart)

    suspend fun updateSharedCartStatus(cartId: String, status: SharedCartStatus) =
        sharedCartDao.updateSharedCartStatus(cartId, status.name)

    suspend fun updateSharedCartAmount(cartId: String, amount: Double) =
        sharedCartDao.updateSharedCartAmount(cartId, amount)

    suspend fun updateSharedCartParticipants(cartId: String, participants: List<String>) =
        sharedCartDao.updateSharedCartParticipants(cartId, participants)
} 