package com.sebapp.challengeteco.data.network

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sebapp.challengeteco.data.model.CharacterData
import com.sebapp.challengeteco.data.model.RickAndMortyList
import com.sebapp.challengeteco.domain.character.CharacterListUseCase

class CharacterPagingSource(
    private val characterListUseCase: CharacterListUseCase
//    private val apiService: ApiService
) : PagingSource<Int, CharacterData>() {

    override fun getRefreshKey(state: PagingState<Int, CharacterData>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterData> {
        return try {
            val nextPage: Int = params.key ?: FIRST_PAGE_INDEX
            var nextPageNumber: Int? = null
            var prevPageNumber: Int? = null
            var dataResult: RickAndMortyList? = null

            characterListUseCase.getListCharacter(nextPage) { response ->

                if (response.info.next != null) {
                    val uri = Uri.parse(response.info.next)
                    val nextPageQuery = uri.getQueryParameter("page")
                    nextPageNumber = nextPageQuery?.toInt()
                }

                if (response.info.prev != null) {
                    val uri = Uri.parse(response.info.prev)
                    val prevPageQuery = uri.getQueryParameter("page")
                    prevPageNumber = prevPageQuery?.toInt()
                }
                dataResult = response
            }
            LoadResult.Page(data = dataResult?.results ?: emptyList(),
                prevKey = prevPageNumber,
                nextKey = nextPageNumber)
//            val response = apiService.getListCharacter(nextPage)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
    companion object {
        private const val FIRST_PAGE_INDEX = 1
    }
}
