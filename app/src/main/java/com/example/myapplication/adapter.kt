package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemListBinding

class adapter(var items:ArrayList<dataClass>):RecyclerView.Adapter<adapter.ContentViews>() {


    class ContentViews(v:ItemListBinding):RecyclerView.ViewHolder(v.root) {

        val txt:TextView

        init{
            txt=v.txtItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): adapter.ContentViews {

        val view=ItemListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ContentViews(view)

    }

    override fun onBindViewHolder(holder: adapter.ContentViews, position: Int) {

        val reg=items.get(position)

        holder.apply {
            txt.text=reg.nombres
        }

    }

    override fun getItemCount()=items.size

}