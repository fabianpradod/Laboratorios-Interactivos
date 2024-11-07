package com.lab12.di

import android.content.Context
import com.lab12.data.local.AppDatabase
import com.lab12.data.local.AppDatabaseFactory

object AppDependencies {
    private var database: AppDatabase? = null

    fun provideDatabase(context: Context): AppDatabase {
        return database ?: synchronized(this) {
            database ?: AppDatabaseFactory.create(context).also { database = it }
        }
    }
}