package com.example.myapplication.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.domain.entity.Phonebook

class PhonebookAdapter(
	private val onClick: (Phonebook) -> Unit,
	private val onLongClick: (Long) -> Unit
) :
	RecyclerView.Adapter<PhonebookAdapter.PhonebookHolder>() {

	var listPhonebook: List<Phonebook> = emptyList()
		set(value) {
			field = value
			notifyDataSetChanged()
		}

	class PhonebookHolder(itemView: View, onClick: (Phonebook) -> Unit) :
		RecyclerView.ViewHolder(itemView) {

		val nameText: TextView = itemView.findViewById(R.id.name)
		val phoneText: TextView = itemView.findViewById(R.id.phone)
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhonebookHolder {
		val view =
			LayoutInflater.from(parent.context).inflate(R.layout.item_contact, parent, false)
		return PhonebookHolder(view, onClick)
	}

	override fun onBindViewHolder(holder: PhonebookHolder, position: Int) {
		holder.nameText.text = listPhonebook[position].name
		holder.phoneText.text = listPhonebook[position].number
		holder.itemView.setOnClickListener {
			onClick(listPhonebook[position])
		}
		holder.itemView.setOnLongClickListener {
			onLongClick(listPhonebook[position].id)
			true
		}
	}

	override fun getItemCount(): Int = listPhonebook.size
}