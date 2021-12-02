package com.example.myapplication.domain.repository

import com.example.myapplication.domain.entity.Phonebook

interface PhonebookRepository {

    suspend fun insert(phonebook: Phonebook)
    suspend fun delete(id: Long)
    suspend fun getPhonebook(): List<Phonebook>
}