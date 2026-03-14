package com.example.rickandmortyretrofit.model

import com.google.gson.annotations.SerializedName

class Character(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val nombre: String,
    @SerializedName("species")
    val especie: String,
    @SerializedName("gender")
    val genero: String,
    @SerializedName("image")
    val imagen: String
)