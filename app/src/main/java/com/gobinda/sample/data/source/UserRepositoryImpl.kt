package com.gobinda.sample.data.source

import com.gobinda.sample.data.User
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow

class UserRepositoryImpl(
     val localData: UserDataSource,
     val remoteData: UserDataSource,
    val  dispatcher: CoroutineDispatcher = Dispatchers.IO
) : UserRepository {

    override fun getUser(email : String): Flow<Result<User>> {
        return localData.getUser(email)
    }

    override suspend fun insertUser(user: User) : Long {
       return localData.insertUser(user)
    }

    override suspend fun updateUser(user: User) {
        TODO("Not yet implemented")
    }
}