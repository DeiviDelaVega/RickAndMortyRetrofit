package com.example.rickandmortyretrofit.net

import com.example.rickandmortyretrofit.model.CharacterDetails
import com.google.gson.annotations.SerializedName

class ListCharacterDetailsResponse(
    @SerializedName("character")
    val charactersDetails: List<CharacterDetails>
)