package com.sebapp.challengeteco.data.services

import com.sebapp.challengeteco.data.model.RickAndMortyList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("character")
    suspend fun getListCharacter(@Query("page") query: Int): Response<RickAndMortyList>
}
