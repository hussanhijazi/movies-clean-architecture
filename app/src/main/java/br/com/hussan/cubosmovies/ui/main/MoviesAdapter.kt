package br.com.hussan.cubosmovies.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import br.com.hussan.cubosmovies.R
import br.com.hussan.cubosmovies.data.model.MovieView
import br.com.hussan.cubosmovies.databinding.ListItemMovieBinding

class MoviesAdapter(private val clickListenerItem: (MovieView) -> Unit) :
    RecyclerView.Adapter<MoviesAdapter.ProductViewHolder>() {

    private var products: List<MovieView> = listOf()

    inner class ProductViewHolder(val binding: ListItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val binding: ListItemMovieBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.list_item_movie, parent, false)
        return ProductViewHolder(binding)
    }

    fun setItems(items: List<MovieView>) {
        products = items
        notifyDataSetChanged()
    }

    fun addItems(items: List<MovieView>) {
        products += items
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products[position]
        holder.binding.movie = product

        holder.binding.root.setOnClickListener {
            clickListenerItem.invoke(product)
        }
    }

    override fun getItemCount() = products.size

}
