package com.vacuum.avaz.Adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.vacuum.avaz.Model.Article
import com.vacuum.avaz.R
import kotlinx.android.synthetic.main.article_list_item.view.*

class ArticleAdapter(val items_article: ArrayList<Article>, val context: Context) : RecyclerView.Adapter<ViewHolder>() {

    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return items_article.size
    }

    // Inflates the item views
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.article_list_item, parent, false))
    }

    // Binds each animal in the ArrayList to a view
    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.title?.text = items_article[position].title
        holder?.description?.text = items_article[position].description
        Glide.with(context)
                .load(items_article[position].poster)
                .asBitmap().centerCrop()
                .into(holder?.poster)

    }
}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val title = view.title
    val description = view.description
    val poster = view.poster

}
