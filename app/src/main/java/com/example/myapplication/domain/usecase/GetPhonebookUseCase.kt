package com.example.myapplication.domain.usecase

import com.example.myapplication.domain.entity.Phonebook
import com.example.myapplication.domain.repository.PhonebookRepository

class GetPhonebookUseCase(
	private val phonebookRepository: PhonebookRepository
) {

	suspend operator fun invoke(): List<Phonebook> = phonebookRepository.getPhonebook()
}