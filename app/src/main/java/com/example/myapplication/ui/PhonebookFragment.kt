package com.example.myapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.databinding.PhonebookFragmentBinding
import com.example.myapplication.domain.entity.Phonebook
import com.example.myapplication.presentation.PhonebookViewModel
import com.example.myapplication.ui.adapter.PhonebookAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class PhonebookFragment : Fragment(R.layout.phonebook_fragment) {

	private val viewModel: PhonebookViewModel by viewModel()
	private var _binding: PhonebookFragmentBinding? = null
	private val binding: PhonebookFragmentBinding
		get() = requireNotNull(_binding) { "binding can't be null" }

	private val adapter = PhonebookAdapter(
		::navigateDetails,
		::delete
	)

	fun navigateDetails(details: Phonebook) {
		viewModel.navigateToDetailsScreen(details)
	}

	fun delete(id: Long) {
		viewModel.deleteContact(id)
	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
		_binding = PhonebookFragmentBinding.inflate(inflater, container, false)
		viewModel.phonebookLiveData.observe(viewLifecycleOwner) {
			adapter.listPhonebook = it
			viewModel.size = it.size.toLong()
		}

		binding.recyclerView.adapter = adapter

		binding.addContactButton.setOnClickListener {
			viewModel.createNewContact()
		}

		return binding.root
	}

	override fun onDestroyView() {
		_binding = null
		super.onDestroyView()
	}
}