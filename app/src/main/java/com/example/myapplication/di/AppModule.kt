package com.example.myapplication.di

import com.example.myapplication.data.datasource.PhonebookDataSource
import com.example.myapplication.data.datasource.PhonebookDataSourceImpl
import com.example.myapplication.data.repository.PhonebookRepositoryImpl
import com.example.myapplication.domain.repository.PhonebookRepository
import com.example.myapplication.domain.usecase.DeleteContactUseCase
import com.example.myapplication.domain.usecase.GetPhonebookUseCase
import com.example.myapplication.domain.usecase.UpdateContactUseCase
import com.example.myapplication.presentation.ContactDetailsViewModel
import com.example.myapplication.presentation.PhonebookRouter
import com.example.myapplication.presentation.PhonebookViewModel
import com.example.myapplication.ui.router.PhonebookRouterImpl
import com.example.registration.data.database.PhonebookDatabase
import com.example.registration.data.database.PhonebookDatabaseBuilder
import com.example.myapplication.ui.buildCicerone
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
	single { buildCicerone() }
	single { get<Cicerone<Router>>().router }
	single { get<Cicerone<Router>>().getNavigatorHolder() }

	single { PhonebookDatabaseBuilder().build(androidContext()) }
	factory { get<PhonebookDatabase>().getPhonebookDao() }
	factory<PhonebookDataSource> { PhonebookDataSourceImpl(get()) }
	factory<PhonebookRepository> {
		PhonebookRepositoryImpl(
			phonebookDataSource = get(),
		)
	}

	factory { GetPhonebookUseCase(get()) }
	factory { DeleteContactUseCase(get()) }
	factory { UpdateContactUseCase(get()) }

	factory<PhonebookRouter> { PhonebookRouterImpl(get()) }
	viewModel {
		PhonebookViewModel(
			router = get(),
			getPhonebookUseCase = get(),
			updateContactUseCase = get(),
			deleteContactUseCase = get(),
		)
	}

	viewModel {
		ContactDetailsViewModel(
			router = get(),
			updateContactUseCase = get(),
		)
	}
}