package com.example.rickandmortyretrofit.net

import com.example.rickandmortyretrofit.model.CharacterDetails
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiServiceCharacter {
    @GET("character/{id}")
    fun characterDetailsId(@Path("id") id: String): Call<CharacterDetails>

    @GET("character")
    fun getItems(@Query("name") name: String, @Query("page") page: Int): Call<ListCharacterResponse>

}