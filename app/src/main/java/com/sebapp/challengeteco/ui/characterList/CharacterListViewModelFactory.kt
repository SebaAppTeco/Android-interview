package com.sebapp.challengeteco.ui.characterList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sebapp.challengeteco.domain.character.CharacterListUseCase

class CharacterListViewModelFactory(
    private val getCharacterListUseCase: CharacterListUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(CharacterListUseCase::class.java)
            .newInstance(getCharacterListUseCase)
    }
}
