package com.sebapp.challengeteco.domain.character

import com.sebapp.challengeteco.data.model.Resource
import com.sebapp.challengeteco.data.model.RickAndMortyList

interface CharacterListUseCase {
    suspend fun getListCharacter(page: Int): Resource<RickAndMortyList>
}
