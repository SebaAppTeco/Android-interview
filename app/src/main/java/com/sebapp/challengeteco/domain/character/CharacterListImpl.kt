package com.sebapp.challengeteco.domain.character

import com.sebapp.challengeteco.data.model.RickAndMortyList
import com.sebapp.challengeteco.data.repository.CharacterRepository
import retrofit2.Response

class CharacterListImpl : CharacterListUseCase {

    private val characterRepository = CharacterRepository()

    override suspend fun getListCharacter(value: Int, callBack: ((Response<RickAndMortyList>) -> Unit)) {
        characterRepository.getListCharacter(value, callBack)
    }
}
