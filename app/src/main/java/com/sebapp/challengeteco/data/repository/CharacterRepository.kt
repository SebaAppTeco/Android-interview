package com.sebapp.challengeteco.data.repository

import com.sebapp.challengeteco.data.model.Resource
import com.sebapp.challengeteco.data.model.RickAndMortyList

interface CharacterRepository {
    suspend fun getListCharacter(page: Int): Resource<RickAndMortyList>
}
