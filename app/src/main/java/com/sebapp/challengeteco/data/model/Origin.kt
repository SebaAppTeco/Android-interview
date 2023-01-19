package com.sebapp.challengeteco.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Origin(
    val name: String?
) : Parcelable
