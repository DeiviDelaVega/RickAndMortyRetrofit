package com.example.rickandmortyretrofit.model

import com.google.gson.annotations.SerializedName

class CharacterDetails(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val nombre: String,
    @SerializedName("species")
    val especie: String,
    @SerializedName("gender")
    val genero: String,
    @SerializedName("image")
    val imagen: String,
    @SerializedName("location")
    val locacion : LocationInfo,
    @SerializedName("status")
    val estatus : String
)

data class LocationInfo(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)