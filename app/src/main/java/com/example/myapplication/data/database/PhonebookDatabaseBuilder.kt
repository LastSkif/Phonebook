package com.example.registration.data.database

import android.content.Context
import androidx.room.Room

class PhonebookDatabaseBuilder {

    private companion object {

        const val USER_DATABASE_NAME = "phonebook"
    }

    fun build(context: Context): PhonebookDatabase =
        Room.databaseBuilder(
            context,
            PhonebookDatabase::class.java,
            USER_DATABASE_NAME,
        ).build()
}