package com.sebapp.challengeteco.presentation.characterList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.sebapp.challengeteco.data.model.CharacterData
import com.sebapp.challengeteco.data.network.CharacterPagingSource
import com.sebapp.challengeteco.usecase.CharacterListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.plus

class CharacterViewModel(
    private val characterListUseCase: CharacterListUseCase
) : ViewModel() {

    var listCharacter: Flow<PagingData<CharacterData>>? = null

    fun getMainListData() {
        listCharacter = Pager(config = PagingConfig(pageSize = 20, maxSize = 200),
            pagingSourceFactory = {
                CharacterPagingSource(characterListUseCase)
            }).flow.cachedIn(viewModelScope + Dispatchers.IO)
    }
}
