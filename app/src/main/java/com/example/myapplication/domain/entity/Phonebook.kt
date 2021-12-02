package com.example.myapplication.domain.entity

import java.io.Serializable

data class Phonebook(
    val id: Long,
    val name: String,
    val number: String
) : Serializable