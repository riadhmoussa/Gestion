package com.halal.halalproject.adapter

import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.android.smartfridge.R
import com.android.smartfridge.dataApp.model.Annonce
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_annonces.view.*
import java.util.*




class AnnonceAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder> {
    var listAnnonces: ArrayList<Annonce>
    var context: Context

    constructor(listAnnonces: ArrayList<Annonce>, context: Context) {
        this.listAnnonces = listAnnonces
        this.context = context

    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder{
        val view = LayoutInflater.from(context).inflate(R.layout.item_annonces, parent, false)
        return AnnonceAdapter.ItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listAnnonces.size
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        var ann = listAnnonces[position]
        holder.itemView.product_name.text = ann.name
        holder.itemView.date_text.text = ann.date_annoce
        holder.itemView.owner_text.text = ann.price
        holder.itemView.adress_text.text = ann.adresse
             Glide
            .with(context.applicationContext)
            .load(ann.photos.get(0))
            .centerCrop()
            .into(holder.itemView.product_img)

    }
    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    }


