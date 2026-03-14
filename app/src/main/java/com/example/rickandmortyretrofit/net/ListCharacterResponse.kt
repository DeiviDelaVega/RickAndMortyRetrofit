package com.example.rickandmortyretrofit.net

import com.example.rickandmortyretrofit.model.Character
import com.google.gson.annotations.SerializedName

class ListCharacterResponse(
    @SerializedName("results")
    val characters: List<Character>
)