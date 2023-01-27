package com.sebapp.challengeteco.data.repository

import com.sebapp.challengeteco.data.model.Resource
import com.sebapp.challengeteco.data.model.RickAndMortyList
import com.sebapp.challengeteco.data.services.ApiService
import java.io.IOException

class CharacterRepositoryImpl(
    private var apiService: ApiService
) : CharacterRepository {
    override suspend fun getListCharacter(page: Int): Resource<RickAndMortyList> {
        try {
            val response = apiService.getListCharacter(page)
            if (response.isSuccessful) {
                response.body()?.let {
                    return Resource.success(it)
                }
            }
            return Resource.error("error", null)
        } catch (e: IOException) {
            error("${e.message}")
        }
    }
}
