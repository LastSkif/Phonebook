package com.example.myapplication.data.mapper

import com.example.myapplication.data.dto.PhonebookDto
import com.example.myapplication.domain.entity.Phonebook

fun Phonebook.toDto(): PhonebookDto = PhonebookDto(
	id = id,
	name = name,
	number = number,
)

fun PhonebookDto.toEntity(): Phonebook = Phonebook(
	id = id,
	name = name,
	number = number,
)