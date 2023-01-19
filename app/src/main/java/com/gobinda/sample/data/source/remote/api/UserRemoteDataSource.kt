package com.gobinda.sample.data.source.remote.api

import com.gobinda.sample.data.User
import com.gobinda.sample.data.source.UserDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(api: RestApi, dispatcher: CoroutineDispatcher = Dispatchers.IO) : UserDataSource {

    override fun getUser(email: String): Flow<Result<User>> {
        TODO("Not yet implemented")
    }

    override suspend fun insertUser(user: User) : Long{
        TODO("Not yet implemented")
    }

    override suspend fun updateUser(user: User) {
        TODO("Not yet implemented")
    }
}