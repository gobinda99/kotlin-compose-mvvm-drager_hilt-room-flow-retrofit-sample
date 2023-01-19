package com.gobinda.sample.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gobinda.sample.data.User


@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class SampleDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {

        @Volatile
        private var INSTANCE: SampleDatabase? = null

        fun getInstance(context: Context): SampleDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: bindDatabase(context).also { INSTANCE = it }
            }

        private fun bindDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext, SampleDatabase::class.java, "sample.db")
                .build()
    }
}