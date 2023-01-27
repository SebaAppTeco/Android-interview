package com.sebapp.challengeteco.data.network

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sebapp.challengeteco.data.model.CharacterData
import com.sebapp.challengeteco.data.model.Resource
import com.sebapp.challengeteco.domain.character.CharacterListUseCase

class CharacterPagingSource(
    private val characterListUseCase: CharacterListUseCase
) : PagingSource<Int, CharacterData>() {

    override fun getRefreshKey(state: PagingState<Int, CharacterData>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterData> {
        return try {
            val nextPage: Int = params.key ?: FIRST_PAGE_INDEX
            var nextPageNumber: Int? = null
            var prevPageNumber: Int? = null

            val response = characterListUseCase.getListCharacter(nextPage)
            when (response.status) {
                Resource.Status.SUCCESS -> {
                    response.data?.let { listCharacter ->
                        if (listCharacter.info.next != null) {
                            val uri = Uri.parse(listCharacter.info.next)
                            val nextPageQuery = uri.getQueryParameter("page")
                            nextPageNumber = nextPageQuery?.toInt()
                        }

                        if (listCharacter.info.prev != null) {
                            val uri = Uri.parse(listCharacter.info.prev)
                            val prevPageQuery = uri.getQueryParameter("page")
                            prevPageNumber = prevPageQuery?.toInt()
                        }
                    }
                }
                Resource.Status.ERROR, Resource.Status.LOADING -> {}
            }
            LoadResult.Page(
                data = response.data?.results ?: emptyList(),
                prevKey = prevPageNumber,
                nextKey = nextPageNumber
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    companion object {
        private const val FIRST_PAGE_INDEX = 1
    }
}
