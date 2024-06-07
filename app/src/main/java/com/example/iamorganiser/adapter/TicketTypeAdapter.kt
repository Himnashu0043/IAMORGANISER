package com.example.iamorganiser.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.iamorganiser.databinding.TicketItemBinding
import com.example.iamorganiser.modal.remote.home.ScanlistRes

class TicketTypeAdapter(val con: Context, val list: ArrayList<ScanlistRes.Data.EventTicketType>) :
    RecyclerView.Adapter<TicketTypeAdapter.MyViewHolder>() {
    class MyViewHolder(var mView: TicketItemBinding) : RecyclerView.ViewHolder(mView.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        return MyViewHolder(TicketItemBinding.inflate(LayoutInflater.from(con), parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.mView.textView23.text = list[position].ticketType ?: ""
        holder.mView.textView24.text = "$ ${list[position].adultPrice ?: 0}"
    }

    override fun getItemCount(): Int {
        return list.size
    }
}