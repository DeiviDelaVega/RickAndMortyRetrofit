package com.example.rickandmortyretrofit.ui.details

import android.util.Log
import com.bumptech.glide.Glide
import com.example.rickandmortyretrofit.model.Character
import com.example.rickandmortyretrofit.model.CharacterDetails
import com.example.rickandmortyretrofit.net.ApiServiceCharacter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SplashPresenter(private val view: SplashView) {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://rickandmortyapi.com/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiServiceCharacter::class.java)

    fun retrofit(id: String) {


        retrofit.characterDetailsId(id).enqueue(object : Callback<CharacterDetails> {
            override fun onResponse(
                call: Call<CharacterDetails?>,
                response: Response<CharacterDetails?>

            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    responseBody?.let {
                        val characterdetail = responseBody

                        view.onSuccess(characterdetail)

                    }
                } else {
                    Log.i("CHECK_RESPONSE", "onFailure: ${response.errorBody()}")

                }
            }

            override fun onFailure(
                call: Call<CharacterDetails?>,
                t: Throwable
            ) {
                Log.i("CHECK_RESPONSE", "onFailure: ${t.message}")
            }


        })
    }
}