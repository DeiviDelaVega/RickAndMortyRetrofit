package com.example.rickandmortyretrofit.net

import com.example.rickandmortyretrofit.model.CharacterDetails
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface ApiServiceCharacter {
    @GET("character")
    fun listCharacter(): Call<ListCharacterResponse>

    @GET("character/{id}")
    fun getCharacterbyId(@Path("id") id: String):  Call<ListCharacterDetailsResponse>
}