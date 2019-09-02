package br.com.hussan.cubosmovies.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import br.com.hussan.cubosmovies.R
import br.com.hussan.cubosmovies.data.model.MovieView
import br.com.hussan.cubosmovies.databinding.ListItemMovieBinding

class MoviesAdapter(private val clickListenerItem: (MovieView) -> Unit) :
    RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    private var movies: List<MovieView> = listOf()

    inner class MovieViewHolder(val binding: ListItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val binding: ListItemMovieBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.list_item_movie, parent, false)
        return MovieViewHolder(binding)
    }

    fun setItems(items: List<MovieView>) {
        movies = items
        notifyDataSetChanged()
    }

    fun addItems(items: List<MovieView>) {
        movies += items
        notifyDataSetChanged()
    }

    fun getMovies() = movies

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.binding.movie = movie

        holder.binding.root.setOnClickListener {
            clickListenerItem.invoke(movie)
        }

    }

    override fun getItemCount() = movies.size

}
