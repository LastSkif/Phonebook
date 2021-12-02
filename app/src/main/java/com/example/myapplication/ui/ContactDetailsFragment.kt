package com.example.myapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.databinding.ContactDetailsFragmentBinding
import com.example.myapplication.domain.entity.Phonebook
import com.example.myapplication.presentation.ContactDetailsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val KEY = "Contact"

var Bundle.requestData
	get() = getSerializable(KEY) as Phonebook
	set(value) = putSerializable(KEY, value)

class ContactDetailsFragment : Fragment(R.layout.contact_details_fragment) {

	companion object {

		fun newInstance(contact: Phonebook) =
			ContactDetailsFragment().apply {
				val bundle = Bundle()
				bundle.requestData = contact
				arguments = bundle
			}
	}

	private val viewModel: ContactDetailsViewModel by viewModel()
	private var _binding: ContactDetailsFragmentBinding? = null
	private val binding: ContactDetailsFragmentBinding
		get() = requireNotNull(_binding) { "binding can't be null" }

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?,
	): View {
		_binding = ContactDetailsFragmentBinding.inflate(inflater, container, false)

		setObserver()
		setListeners()

		return binding.root
	}

	private fun setListeners() {
		with(binding) {
			nameField.doOnTextChanged { text, _, _, _ ->
				viewModel.nameLiveData.value = text.toString()
			}
			phoneField.doOnTextChanged { text, _, _, _ ->
				viewModel.phoneLiveData.value = text.toString()
			}
			saveButton.setOnClickListener {
				viewModel.update(
					requireArguments().requestData.id,
					viewModel.nameLiveData.value.toString(),
					viewModel.phoneLiveData.value.toString()
				)
				viewModel.exit()
			}
		}
	}

	private fun setObserver() {
		with(viewModel) {
			nameLiveData.observe(viewLifecycleOwner) {
				if (it != binding.nameField.text.toString())
					binding.nameField.setText(it)
			}
			phoneLiveData.observe(viewLifecycleOwner) {
				if (it != binding.phoneField.text.toString())
					binding.phoneField.setText(it)
			}
		}
	}

	override fun onDestroyView() {
		_binding = null
		super.onDestroyView()
	}
}