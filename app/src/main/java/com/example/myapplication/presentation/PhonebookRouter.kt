package com.example.myapplication.presentation

import com.example.myapplication.domain.entity.Phonebook

interface PhonebookRouter {

    fun navigateToDetails(contact: Phonebook)

    fun exit()
}