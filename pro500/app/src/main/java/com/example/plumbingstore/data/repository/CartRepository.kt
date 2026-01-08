package com.example.plumbingstore.data.repository

import com.example.plumbingstore.data.local.CartDao
import com.example.plumbingstore.data.model.CartItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CartRepository @Inject constructor(
    private val cartDao: CartDao
) {
    fun getAllCartItems(): Flow<List<CartItem>> = cartDao.getAllCartItems()

    fun getTotalPrice(): Flow<Double?> = cartDao.getTotalPrice()

    suspend fun addToCart(cartItem: CartItem) = cartDao.insertCartItem(cartItem)

    suspend fun updateCartItem(cartItem: CartItem) = cartDao.updateCartItem(cartItem)

    suspend fun removeFromCart(cartItem: CartItem) = cartDao.deleteCartItem(cartItem)

    suspend fun clearCart() = cartDao.clearCart()
} 