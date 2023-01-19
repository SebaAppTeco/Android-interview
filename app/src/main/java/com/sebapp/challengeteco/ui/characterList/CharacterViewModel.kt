package com.sebapp.challengeteco.ui.characterList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.sebapp.challengeteco.data.model.CharacterData
import com.sebapp.challengeteco.data.network.CharacterPagingSource
import com.sebapp.challengeteco.domain.character.CharacterListUseCase
import kotlinx.coroutines.flow.Flow

class CharacterViewModel(
    private val characterListUseCase: CharacterListUseCase
) : ViewModel() {

    fun getListData(): Flow<PagingData<CharacterData>> {
        return Pager(config = PagingConfig(pageSize = 20, maxSize = 200),
            pagingSourceFactory = { CharacterPagingSource(characterListUseCase) }).flow.cachedIn(
            viewModelScope
        )
    }
}
