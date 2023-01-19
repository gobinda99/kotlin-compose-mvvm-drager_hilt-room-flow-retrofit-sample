package com.gobinda.sample.data.source.local.room

import androidx.room.*
import com.gobinda.sample.data.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM users")
    fun observeUser(): Flow<List<User>>

    @Query("SELECT * FROM users")
    suspend fun getUsers(): List<User>

    @Query("SELECT * from users where email = :email LIMIT 1")
    fun loadUserByEmail(email: String): Flow<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(flights: List<User>)

    @Query("DELETE FROM users")
    suspend fun deleteUser()

    @Insert
    suspend fun insertUser(user: User): Long
}


