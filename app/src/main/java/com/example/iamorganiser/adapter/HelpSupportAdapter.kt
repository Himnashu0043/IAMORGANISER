package com.example.iamorganiser.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.iamorganiser.databinding.ItemHelpRaisedTicketsBinding


class HelpSupportAdapter(val context: Activity, var list: List<String>)
    :RecyclerView.Adapter<HelpSupportAdapter.ViewHolder>() {

    inner class ViewHolder(val view : ItemHelpRaisedTicketsBinding) : RecyclerView.ViewHolder(view.root) {
    }

    fun setNewList(newList: ArrayList<String>){
        this.list = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemHelpRaisedTicketsBinding.inflate(LayoutInflater.from(context),parent,false))
    }

    override fun getItemCount(): Int {
        return 5
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


    }
}