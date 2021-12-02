package com.example.myapplication.domain.usecase

import com.example.myapplication.domain.entity.Phonebook
import com.example.myapplication.domain.repository.PhonebookRepository

class UpdateContactUseCase(
	private var phonebookRepository: PhonebookRepository,
) {

	suspend operator fun invoke(phonebook: Phonebook) {
		phonebookRepository.insert(phonebook)
	}
}