package com.sebapp.challengeteco.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CharacterData(
    val name: String?,
    val status: String?,
    val species: String?,
    val gender: String?,
    val image: String?,
    val origin: Origin?
) : Parcelable
