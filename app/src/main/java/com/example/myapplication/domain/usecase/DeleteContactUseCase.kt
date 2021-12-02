package com.example.myapplication.domain.usecase

import com.example.myapplication.domain.repository.PhonebookRepository

class DeleteContactUseCase(
	private var phonebookRepository: PhonebookRepository
) {

	suspend operator fun invoke(id: Long) {
		phonebookRepository.delete(id)
	}
}