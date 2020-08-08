package com.bove.martin.popcorn.adapters

/**
 * Created by Martín Bove on 07-Aug-20.
 * E-mail: mbove77@gmail.com
 */

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import coil.api.load
import coil.transform.CircleCropTransformation
import com.bove.martin.popcorn.Commons.Constantes
import com.bove.martin.popcorn.R
import com.bove.martin.popcorn.model.Movie
import kotlinx.android.synthetic.main.movie_item.view.*


class MovieRecyclerViewAdapter(popularMovies: List<Movie>) : RecyclerView.Adapter<MovieRecyclerViewAdapter.ViewHolder>()  {
    private val mOnClickListener: View.OnClickListener
    private var movies: List<Movie> = ArrayList()

    init {
        movies = popularMovies
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as Movie
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]
        holder.tvTitle.text = movie.title
        holder.tvRating.text = movie.vote_average.toString()
        holder.ivPhoto.load(Constantes.API_IMAGE_URL + movie.poster_path) {
            crossfade(true)
            placeholder(R.drawable.placeholder)
        }

        with(holder.mView) {
            tag = movie
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = movies.size

    fun setData(popularMovies: List<Movie>) {
        movies = popularMovies
        notifyDataSetChanged()
    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val ivPhoto: ImageView = mView.image_view_photo
        val tvTitle: TextView = mView.text_view_title
        val tvRating: TextView = mView.text_view_rating
    }
}