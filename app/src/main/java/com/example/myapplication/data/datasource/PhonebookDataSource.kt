package com.example.myapplication.data.datasource

import com.example.myapplication.data.dao.PhonebookDao
import com.example.myapplication.data.dto.PhonebookDto

interface PhonebookDataSource {

	suspend fun insert(userDto: PhonebookDto)
	fun getAll(): List<PhonebookDto>
	suspend fun delete(id: Long)
}

class PhonebookDataSourceImpl(
    private val dao: PhonebookDao,
) : PhonebookDataSource {

	override suspend fun insert(phonebookDto: PhonebookDto) {
		dao.insert(phonebookDto)
	}

	override fun getAll(): List<PhonebookDto> = dao.getNames()

	override suspend fun delete(id: Long){
		dao.delete(id)
	}

}