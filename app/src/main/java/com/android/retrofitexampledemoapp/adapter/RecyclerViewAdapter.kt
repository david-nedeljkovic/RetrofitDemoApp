package com.android.retrofitexampledemoapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.retrofitexampledemoapp.R
import com.android.retrofitexampledemoapp.model.Post

class RecyclerViewAdapter: RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    private var listPosts: List<Post> = emptyList()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvUserId: TextView
        var tvId: TextView
        var tvTitle: TextView
        var tvBody: TextView

        init {
            tvUserId = view.findViewById(R.id.textViewUserId)
            tvId = view.findViewById(R.id.textViewId)
            tvTitle = view.findViewById(R.id.textViewTitle)
            tvBody = view.findViewById(R.id.textViewBody)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.post_row, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listPosts.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvUserId.text = listPosts[position].userId.toString()
        holder.tvId.text = listPosts[position].id.toString()
        holder.tvTitle.text = listPosts[position].title
        holder.tvBody.text = listPosts[position].body
    }

    fun setListPosts(newList: List<Post>) {
        listPosts = newList
        notifyDataSetChanged()
    }

}
