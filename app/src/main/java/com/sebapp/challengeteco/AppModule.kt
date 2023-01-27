package com.sebapp.challengeteco

import com.sebapp.challengeteco.common.Constants
import com.sebapp.challengeteco.data.repository.CharacterRepository
import com.sebapp.challengeteco.data.repository.CharacterRepositoryImpl
import com.sebapp.challengeteco.data.services.ApiService
import com.sebapp.challengeteco.domain.character.CharacterListUseCase
import com.sebapp.challengeteco.domain.character.CharacterListUseCaseImpl
import com.sebapp.challengeteco.ui.characterList.CharacterViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single {
        Retrofit.Builder()
            .baseUrl(Constants.baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
            .create(ApiService::class.java)
    }
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }
    single<CharacterRepository> { CharacterRepositoryImpl(get()) }
    single<CharacterListUseCase> { CharacterListUseCaseImpl(get()) }
    viewModel { CharacterViewModel(get()) }
}
