package com.example.rickandmortyretrofit.ui.main

import com.example.rickandmortyretrofit.model.Character

interface MainView {

    fun onSuccess(list: List<Character>)

    fun onFailed(message : String)

    fun showProgress()

    fun hideProgress()
}