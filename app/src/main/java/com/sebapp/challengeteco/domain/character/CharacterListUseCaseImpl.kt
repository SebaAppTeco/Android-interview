package com.sebapp.challengeteco.domain.character

import com.sebapp.challengeteco.data.model.Resource
import com.sebapp.challengeteco.data.model.RickAndMortyList
import com.sebapp.challengeteco.data.repository.CharacterRepository

class CharacterListUseCaseImpl(
    private val characterRepository: CharacterRepository
) : CharacterListUseCase {

    override suspend fun getListCharacter(page: Int): Resource<RickAndMortyList> {
        return characterRepository.getListCharacter(page)
    }
}
