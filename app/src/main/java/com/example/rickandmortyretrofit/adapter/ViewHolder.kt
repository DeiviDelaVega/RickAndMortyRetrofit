package com.example.rickandmortyretrofit.adapter

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rickandmortyretrofit.databinding.ItemCharacterBinding
import com.example.rickandmortyretrofit.model.Character
import com.example.rickandmortyretrofit.ui.details.DetailsActivity


class ViewHolder(private val binding: ItemCharacterBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(character: Character) {

        val imageView = binding.imagen

        val context = binding.root.context

        Glide.with(context).load(character.imagen).into(imageView)

        binding.nombre.text = character.nombre
        binding.speciesNombre.text = character.especie
        binding.genderNombre.text = character.genero

        binding.buttonDetalle.setOnClickListener {

            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra("id", character.id.toString())
            context.startActivity(intent)
        }

    }
}