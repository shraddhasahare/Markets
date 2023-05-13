package com.example.markets.search

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.markets.R
import com.example.markets.baseUtils.OnItemClickListener
import com.example.markets.search.data.AutoSuggestionDisplayItem
import kotlinx.android.synthetic.main.item_search_suggestion.view.*

class AutoSuggestionAdapter(private val autoSuggestionListItems: MutableList<AutoSuggestionDisplayItem>,
                            private var onItemClickListener: OnItemClickListener.OnItemClickCallback, private val context: Context):RecyclerView.Adapter<AutoSuggestionAdapter.ViewHolder>() {
    lateinit var onItemClickCallback: OnItemClickListener.OnItemClickCallback
    private var queryString:String?= null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).
        inflate(R.layout.item_search_suggestion,parent,false))
    }
    override fun getItemCount(): Int {
        return autoSuggestionListItems.size
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(autoSuggestionListItems[position].autoSuggestionText)
        holder.searchItemName.setOnClickListener(OnItemClickListener(position,onItemClickCallback))
        holder.searchItemArrow.setOnClickListener(OnItemClickListener(position,onItemClickCallback))
    }
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var clAutoSuggestionItem = itemView.clAutoSuggestionItem
        var searchItemName = itemView.tvAutoSuggestionSearchItemName
        var searchItemArrow = itemView.ivSearchArrow
        init {
            onItemClickCallback = onItemClickListener
        }
        fun bind(product: String){
            itemView.tvAutoSuggestionSearchItemName.text = product
        }
    }

    fun setQueryString(queryString: String) {
        this.queryString = queryString
    }

}