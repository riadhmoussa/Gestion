package com.android.smartfridge.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.smartfridge.R
import com.android.smartfridge.dataApp.model.User
import com.android.smartfridge.databinding.ItemFollowersBinding

class AdapterFollowers(val activity: Activity, val user: User?=null, var isSearch:Boolean):
    RecyclerView.Adapter<AdapterFollowers.ViewHolder>() {
    var listFollowers:ArrayList<User> = arrayListOf()
        set(value) {
            val oldSize = field.size
            val newSize = value.size
            field.addAll(value)

            notifyItemRangeInserted(oldSize, newSize)
        }

    class ViewHolder(binding: ItemFollowersBinding) : RecyclerView.ViewHolder(binding.root){
        val binding=binding
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var binding: ItemFollowersBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_followers,
            parent,
            false
        )

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listFollowers.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvName.text = "Nom & Prénom :"+listFollowers[position].first_name+" "+listFollowers[position].last_name
        holder.binding.tvNameOwner.text = "Numéro Téléphone :"+listFollowers[position].phone
        holder.binding.tvPhone.text = "Email : "+listFollowers[position].email
        if(listFollowers[position].matricule!=null){
            holder.binding.tvMatricle.text = "Matricule : "+listFollowers[position].matricule
        }
        if(listFollowers[position].adresse!=null){
            holder.binding.tvAdddres.text  = "Adresse : "+listFollowers[position].adresse
        }

    }

}