package com.example.rickandmortyretrofit.ui

import android.os.Bundle
import android.transition.Explode
import android.util.Log

import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.example.rickandmortyretrofit.R
import com.example.rickandmortyretrofit.adapter.MyAdapterCharacters
import com.example.rickandmortyretrofit.databinding.ActivityMainBinding
import com.example.rickandmortyretrofit.net.ApiServiceCharacter
import com.example.rickandmortyretrofit.net.ListCharacterResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val adapter = MyAdapterCharacters()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        enableEdgeToEdge()
        setContentView(view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setActivityAnimation()
        RecyclerView()
        retrofit()
    }

    fun setActivityAnimation() {
        val fadeIn = Explode().apply {
            duration = 4000
        }

        window.enterTransition = fadeIn
    }

    fun retrofit() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiServiceCharacter::class.java)

        retrofit.listCharacter().enqueue(object : Callback<ListCharacterResponse> {
            override fun onResponse(
                call: Call<ListCharacterResponse?>,
                response: Response<ListCharacterResponse>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    Log.i("CHECK_RESPONSE", "onResponse: $responseBody")
                    responseBody?.let { body ->
                        val characters = body.characters

                        characters.forEach { character ->
                            adapter.addCharacter(character)

                        }

                    }
                } else {
                    Log.i("CHECK_RESPONSE", "onFailure: ${response.errorBody()}")
                }
            }

            override fun onFailure(call: Call<ListCharacterResponse?>, t: Throwable) {
                Log.i("CHECK_RESPONSE", "onFailure: ${t.message}")
            }

        })


    }

    fun RecyclerView() {
        binding.recyclerViewCharacter.adapter = adapter
        binding.recyclerViewCharacter.layoutManager =
            GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)
    }
}