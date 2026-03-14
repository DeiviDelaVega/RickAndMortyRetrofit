package com.example.rickandmortyretrofit.ui.details

import com.example.rickandmortyretrofit.model.Character
import com.example.rickandmortyretrofit.model.CharacterDetails

interface SplashView {
    fun onSuccess(characterdetail : CharacterDetails)

    fun onFailed(message : String)
}