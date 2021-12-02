package com.example.myapplication.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.domain.entity.Phonebook
import com.example.myapplication.domain.usecase.UpdateContactUseCase
import kotlinx.coroutines.launch

class ContactDetailsViewModel(
	private val router: PhonebookRouter,
	private val updateContactUseCase: UpdateContactUseCase,
) : ViewModel() {

	val nameLiveData = MutableLiveData<String>()
	val phoneLiveData = MutableLiveData<String>()

	fun update(id: Long, name: String, number: String) {
		val contact = Phonebook(id, name, number)
		viewModelScope.launch {
			updateContactUseCase.invoke(contact)
		}
	}

	fun exit() {
		router.exit()
	}
}