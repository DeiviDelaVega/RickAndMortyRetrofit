package com.example.rickandmortyretrofit.ui.details

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.rickandmortyretrofit.R
import com.example.rickandmortyretrofit.databinding.ActivityDetailsBinding
import com.example.rickandmortyretrofit.model.CharacterDetails

class DetailsActivity : AppCompatActivity(), SplashView {
    private lateinit var binding: ActivityDetailsBinding

    private val splashPresenter = SplashPresenter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        retrofitInit()

    }


    fun retrofitInit() {
        val id = intent.getStringExtra("id").toString()
        splashPresenter.retrofit(id)
    }


    override fun onSuccess(characterdetail: CharacterDetails) {

        val imageView = binding.imagenDetails
        val context = binding.root.context

        Glide.with(context).load(characterdetail.imagen).into(imageView)

        binding.textNombre.text = characterdetail.nombre
        binding.especie.text = characterdetail.especie
        binding.genero.text = characterdetail.genero
        binding.estatus.text = characterdetail.estatus
        binding.localizacion.text = characterdetail.locacion.name
    }

    override fun onFailed(message: String) {
        TODO("Not yet implemented")
    }


}