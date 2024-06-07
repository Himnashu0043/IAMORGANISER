package com.example.iamorganiser.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.iamorganiser.databinding.MoreItemBinding
import com.example.iamorganiser.modal.MoreModal

class MoreAdapter(val con: Context, val list: ArrayList<MoreModal>) :
    RecyclerView.Adapter<MoreAdapter.MyViewHolder>() {
    class MyViewHolder(val mView: MoreItemBinding) : RecyclerView.ViewHolder(mView.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(MoreItemBinding.inflate(LayoutInflater.from(con), parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.mView.imageView14.setImageResource(list[position].img)
        holder.mView.textView34.text = list[position].name
        holder.mView.textView35.text = list[position].subName
    }

    override fun getItemCount(): Int {
        return list.size
    }
}