package br.com.hussan.cubosmovies.ui.main

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.ViewCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import br.com.hussan.cubosmovies.R
import br.com.hussan.cubosmovies.data.model.MovieView
import br.com.hussan.cubosmovies.databinding.ListItemMovieBinding

class MoviesAdapter(private val clickListenerItem: (MovieView, ImageView, TextView) -> Unit) :
    RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    private var movie: List<MovieView> = listOf()

    inner class MovieViewHolder(val binding: ListItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val binding: ListItemMovieBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.list_item_movie, parent, false)
        return MovieViewHolder(binding)
    }

    fun setItems(items: List<MovieView>) {
        movie = items
        notifyDataSetChanged()
    }

    fun addItems(items: List<MovieView>) {
        movie += items
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movie[position]
        holder.binding.movie = movie

        holder.binding.root.setOnClickListener {
            clickListenerItem.invoke(movie, holder.binding.imgPoster.image, holder.binding.txtTitle)
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ViewCompat.setTransitionName(holder.binding.imgPoster.image, movie.posterPath)
            ViewCompat.setTransitionName(holder.binding.txtTitle, movie.title)
        }
    }

    override fun getItemCount() = movie.size

}
