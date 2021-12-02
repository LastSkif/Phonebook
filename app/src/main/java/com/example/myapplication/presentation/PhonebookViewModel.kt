package com.example.myapplication.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.domain.entity.Phonebook
import com.example.myapplication.domain.usecase.DeleteContactUseCase
import com.example.myapplication.domain.usecase.GetPhonebookUseCase
import com.example.myapplication.domain.usecase.UpdateContactUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PhonebookViewModel(
	private val router: PhonebookRouter,
	private val getPhonebookUseCase: GetPhonebookUseCase,
	private val updateContactUseCase: UpdateContactUseCase,
	private val deleteContactUseCase: DeleteContactUseCase,
) : ViewModel() {

	private companion object {

		private const val DEFAULT_NAME = "Владислак Стоянога"
		private const val DEFAULT_NUMBER = "+7-800-555-3535"
	}

	val phonebookLiveData = MutableLiveData<List<Phonebook>>()
	var size: Long = 0

	init {
		loadPhonebook()
	}

	private fun loadPhonebook() {
		viewModelScope.launch(Dispatchers.Main) {
			phonebookLiveData.value = getPhonebookUseCase()
		}
	}

	fun navigateToDetailsScreen(contact: Phonebook) {
		router.navigateToDetails(contact)
	}

	fun deleteContact(id: Long) {
		viewModelScope.launch(Dispatchers.IO) {
			deleteContactUseCase(id)
			loadPhonebook()
		}
	}

	fun createNewContact() {
		viewModelScope.launch(Dispatchers.IO) {
			updateContactUseCase(
				Phonebook(
					id = size.plus(1),
					name = DEFAULT_NAME,
					number = DEFAULT_NUMBER,
				)
			)
			loadPhonebook()
		}
	}

}