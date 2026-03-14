package com.example.rickandmortyretrofit.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortyretrofit.databinding.ItemCharacterBinding
import com.example.rickandmortyretrofit.model.Character
import java.util.Locale


class MyAdapterCharacters() : RecyclerView.Adapter<ViewHolder>() {

    private var characters: MutableList<Character> = mutableListOf()

    fun addCharacter(character: Character) {
        characters.add(character)
        notifyItemInserted(characters.size.minus(1))
    }

    fun clear() {
        val size = characters.size
        if (size > 0) {
            notifyItemRangeRemoved(0, size)
            characters.clear()
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCharacterBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ViewHolder((binding))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(characters[position])
    }

    override fun getItemCount() = characters.size


}