package com.example.registration.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.data.dao.PhonebookDao
import com.example.myapplication.data.dto.PhonebookDto

@Database(
	entities = [
		PhonebookDto::class
	],
	version = 1,
	exportSchema = false
)
abstract class PhonebookDatabase : RoomDatabase() {

	abstract fun getPhonebookDao(): PhonebookDao
}