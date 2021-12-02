package com.example.myapplication.ui

import com.example.myapplication.domain.entity.Phonebook
import com.github.terrakok.cicerone.androidx.FragmentScreen

fun getContactDetailsScreen(contact: Phonebook) = FragmentScreen { ContactDetailsFragment.newInstance(contact) }

fun getPhonebookScreen() = FragmentScreen { PhonebookFragment() }