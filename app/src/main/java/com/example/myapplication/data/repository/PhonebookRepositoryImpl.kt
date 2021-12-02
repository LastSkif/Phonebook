package com.example.myapplication.data.repository

import com.example.myapplication.data.datasource.PhonebookDataSource
import com.example.myapplication.data.dto.PhonebookDto
import com.example.myapplication.data.mapper.toDto
import com.example.myapplication.data.mapper.toEntity
import com.example.myapplication.domain.entity.Phonebook
import com.example.myapplication.domain.repository.PhonebookRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PhonebookRepositoryImpl(
	private val phonebookDataSource: PhonebookDataSource,
) : PhonebookRepository {

	override suspend fun insert(phonebook: Phonebook) {
		withContext(Dispatchers.IO) {
			phonebookDataSource.insert(phonebook.toDto())
		}
	}

	override suspend fun delete(id: Long) {
		withContext(Dispatchers.IO) {
			phonebookDataSource.delete(id)
		}
	}

	override suspend fun getPhonebook(): List<Phonebook> =
		withContext(Dispatchers.IO) {
			phonebookDataSource.getAll().map(PhonebookDto::toEntity)
		}
}