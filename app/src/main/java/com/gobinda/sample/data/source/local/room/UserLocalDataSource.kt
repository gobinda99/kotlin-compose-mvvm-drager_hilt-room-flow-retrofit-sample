package com.gobinda.sample.data.source.local.room

import com.gobinda.sample.data.User
import com.gobinda.sample.data.source.UserDataSource
import com.gobinda.sample.di.LocalTasksDataSource
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

@InstallIn(SingletonComponent::class)
@LocalTasksDataSource
@Module
class UserLocalDataSource @Inject constructor(db: SampleDatabase, val dispatcher : CoroutineDispatcher = Dispatchers.IO) : UserDataSource {

   val dao = db.userDao()

    override fun getUser(email: String): Flow<Result<User>> {

       return dao.loadUserByEmail(email).map {
            Result.success(it)
        }
    }

    override suspend fun insertUser(user: User) : Long {
        return withContext(dispatcher){
            dao.insertUser(user)
        }
    }

    override suspend fun updateUser(user: User) {
        TODO("Not yet implemented")
    }

}