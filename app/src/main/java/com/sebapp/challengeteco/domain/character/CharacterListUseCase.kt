package com.sebapp.challengeteco.domain.character

import com.sebapp.challengeteco.data.model.RickAndMortyList
import retrofit2.Response

interface CharacterListUseCase {
    suspend fun getListCharacter(value: Int, callBack: (Response<RickAndMortyList>) -> Unit)
}
