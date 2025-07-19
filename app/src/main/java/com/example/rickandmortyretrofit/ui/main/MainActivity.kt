package com.example.rickandmortyretrofit.ui.main

import android.os.Bundle
import android.transition.Explode
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.example.rickandmortyretrofit.R
import com.example.rickandmortyretrofit.adapter.EndlessScrollListener
import com.example.rickandmortyretrofit.adapter.MyAdapterCharacters
import com.example.rickandmortyretrofit.databinding.ActivityMainBinding
import com.example.rickandmortyretrofit.model.Character
import com.example.rickandmortyretrofit.ui.util.EMPTY
import com.example.rickandmortyretrofit.ui.util.hide
import com.example.rickandmortyretrofit.ui.util.show


class MainActivity : AppCompatActivity(), MainView {

    private lateinit var binding: ActivityMainBinding

    private val adapter = MyAdapterCharacters()

    private var currentPage = 1

    private var currentTextSearch = EMPTY

    private val mainPresenter = MainPresenter(this)

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
        EndlessScroll()
        filterChain()
        fetchItems(currentPage)
    }

    fun setActivityAnimation() {
        val fadeIn = Explode().apply {
            duration = 4000
        }

        window.enterTransition = fadeIn
    }


    fun RecyclerView() {
        binding.recyclerViewCharacter.adapter = adapter
        binding.recyclerViewCharacter.layoutManager =
            GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)
    }

    fun EndlessScroll() {
        binding.recyclerViewCharacter.addOnScrollListener(object : EndlessScrollListener() {
            override fun onLoadMore() {
                currentPage++
                mainPresenter.fetchItems(name = currentTextSearch, page = currentPage)
            }
        })
    }

    private fun fetchItems(page: Int) {
        mainPresenter.fetchItems(page = page)

    }


    private fun filterChain() {
        val searchView = binding.search
        searchView.setOnQueryTextListener(object  : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                if (query.isNotBlank()) {
                    currentPage = 1
                    currentTextSearch = query
                    adapter.clear()
                    mainPresenter.fetchItems(name = query, page = currentPage)
                    searchView.clearFocus()
                }
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return true
            }
        })
        val closeButton  = searchView.findViewById<ImageView>(androidx.appcompat.R.id.search_close_btn)

        closeButton.setOnClickListener {
            searchView.setQuery(EMPTY, false)
            searchView.clearFocus()
            Log.i("CHECK_RESPONSE", "setOnCloseListener")
            currentPage = 1
            currentTextSearch = EMPTY
            adapter.clear()
            mainPresenter.fetchItems(name = currentTextSearch, page = currentPage)

            true
        }
    }


    override fun onSuccess(list: List<Character>) {
        list.forEach { character ->
            adapter.addCharacter(character)
        }
    }

    override fun onFailed(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun showProgress() {
        binding.progressBar.show()
    }

    override fun hideProgress() {
        binding.progressBar.hide()
    }

}