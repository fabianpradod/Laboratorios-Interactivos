package com.laboratoriosinteractivos.lab10.di

import android.content.Context
import androidx.room.Room
import com.laboratoriosinteractivos.lab10.data.local.LabDb

object Dependencies {
    private var database: LabDb? = null

    private fun buildDatabase(context: Context): LabDb {
        return Room.databaseBuilder(
            context,
            LabDb::class.java,
            "uvg.db"
        ).build()
    }

    fun provideDatabase(context: Context): LabDb {
        return database ?: synchronized(this) {
            database ?: buildDatabase(context).also { database = it }
        }
    }
}