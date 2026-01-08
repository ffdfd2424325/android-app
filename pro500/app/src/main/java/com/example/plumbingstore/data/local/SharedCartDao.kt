package com.example.plumbingstore.data.local

import androidx.room.*
import com.example.plumbingstore.data.model.SharedCart
import kotlinx.coroutines.flow.Flow

@Dao
interface SharedCartDao {
    @Query("SELECT * FROM shared_carts WHERE ownerId = :userId OR :userId IN (participants)")
    fun getUserSharedCarts(userId: String): Flow<List<SharedCart>>

    @Query("SELECT * FROM shared_carts WHERE id = :cartId")
    fun getSharedCart(cartId: String): Flow<SharedCart?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSharedCart(sharedCart: SharedCart)

    @Update
    suspend fun updateSharedCart(sharedCart: SharedCart)

    @Delete
    suspend fun deleteSharedCart(sharedCart: SharedCart)

    @Query("UPDATE shared_carts SET status = :status WHERE id = :cartId")
    suspend fun updateSharedCartStatus(cartId: String, status: String)

    @Query("UPDATE shared_carts SET totalAmount = :amount WHERE id = :cartId")
    suspend fun updateSharedCartAmount(cartId: String, amount: Double)

    @Query("UPDATE shared_carts SET participants = :participants WHERE id = :cartId")
    suspend fun updateSharedCartParticipants(cartId: String, participants: List<String>)
} 