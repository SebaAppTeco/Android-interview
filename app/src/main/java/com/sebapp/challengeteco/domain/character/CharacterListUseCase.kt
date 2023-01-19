package com.sebapp.challengeteco.domain.character

import com.sebapp.challengeteco.data.model.RickAndMortyList

interface CharacterListUseCase {
    suspend fun getListCharacter(value: Int, callBack: (RickAndMortyList) -> Unit)
}
