package com.sebapp.challengeteco.data.repository

import com.sebapp.challengeteco.data.model.RickAndMortyList
import com.sebapp.challengeteco.data.network.RetroInstance
import com.sebapp.challengeteco.data.services.ApiService

class CharacterRepository {
    private var apiService: ApiService =
        RetroInstance.getRetroInstance().create(ApiService::class.java)

    suspend fun getListCharacter(
        values: Int,
        callBack: ((RickAndMortyList) -> Unit)
    ) {
        return callBack(apiService.getListCharacter(values))
    }
}
