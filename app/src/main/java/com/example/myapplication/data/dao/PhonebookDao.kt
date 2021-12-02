package com.example.myapplication.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.data.dto.PhonebookDto

@Dao
interface PhonebookDao {

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insert(phonebookDto: PhonebookDto)

	@Query("SELECT * FROM phonebook")
	fun getNames(): List<PhonebookDto>

	@Query("DELETE FROM phonebook WHERE :id == id")
	suspend fun delete(id: Long)
}