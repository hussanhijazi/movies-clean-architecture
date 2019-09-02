package br.com.hussan.cubosmovies.ui.moviedetails

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import br.com.hussan.cubosmovies.R
import br.com.hussan.cubosmovies.databinding.ListItemVideoBinding
import br.com.hussan.cubosmovies.domain.Video

class VideosAdapter(
    val context: Context,
    private val clickListenerItem: (Video) -> Unit,
    private val imagePath: String?
) :
    RecyclerView.Adapter<VideosAdapter.MovieViewHolder>() {

    private var videos: List<Video> = listOf()

    inner class MovieViewHolder(val binding: ListItemVideoBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val binding: ListItemVideoBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.list_item_video, parent, false)
        return MovieViewHolder(binding)
    }

    fun addItems(items: List<Video>) {
        videos += items
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val video = videos[position]

        holder.binding.apply {
            this.video = video
            image = imagePath
            root.setOnClickListener {
                clickListenerItem.invoke(video)
            }
        }
    }

    override fun getItemCount() = videos.size

}
