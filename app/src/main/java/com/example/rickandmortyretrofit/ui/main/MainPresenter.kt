package com.example.rickandmortyretrofit.ui.main

import android.os.Handler
import android.os.Looper
import android.util.Log
import com.example.rickandmortyretrofit.net.ApiServiceCharacter
import com.example.rickandmortyretrofit.net.ListCharacterResponse
import com.example.rickandmortyretrofit.ui.util.EMPTY
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainPresenter(private val view: MainView) {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://rickandmortyapi.com/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiServiceCharacter::class.java)

    fun fetchItems(name: String = EMPTY, page: Int) {

        view.showProgress()

        retrofit.getItems(name, page).enqueue(object : Callback<ListCharacterResponse> {
            override fun onResponse(
                call: Call<ListCharacterResponse?>,
                response: Response<ListCharacterResponse?>
            ) {
                view.hideProgress()
                if (response.isSuccessful) {
                    Handler(Looper.getMainLooper()).postDelayed({
                        val responseBody = response.body()
                        Log.i("CHECK_RESPONSE", "onResponse: $responseBody")
                        responseBody?.let { body ->
                            val characters = body.characters
                            view.onSuccess(list = characters)
                        }
                    }, 1000)
                } else {
                    Log.i("CHECK_RESPONSE", "onFailure: ${response.errorBody()}")
                    view.onFailed(message = response.errorBody().toString())
                }
            }

            override fun onFailure(
                call: Call<ListCharacterResponse?>,
                t: Throwable
            ) {
                view.hideProgress()
                Log.i("CHECK_RESPONSE", "onFailure: ${t.message}")
                view.onFailed(message = t.message.toString())
            }

        })
    }


}