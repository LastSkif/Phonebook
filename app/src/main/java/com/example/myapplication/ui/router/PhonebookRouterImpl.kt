package com.example.myapplication.ui.router

import com.example.myapplication.domain.entity.Phonebook
import com.example.myapplication.presentation.PhonebookRouter
import com.example.myapplication.ui.getContactDetailsScreen
import com.github.terrakok.cicerone.Router

class PhonebookRouterImpl(
    private val router: Router
) : PhonebookRouter {

    override fun navigateToDetails(contact: Phonebook) {
        router.navigateTo(getContactDetailsScreen(contact))
    }

    override fun exit() {
        router.exit()
    }
}