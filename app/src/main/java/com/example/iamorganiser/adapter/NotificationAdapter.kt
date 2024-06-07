package com.example.iamorganiser.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.iamorganiser.databinding.RowItemNotificationListBinding
import com.example.iamorganiser.modal.NotiModel

class NotificationAdapter(val con: Context, val list: ArrayList<NotiModel>) :
    RecyclerView.Adapter<NotificationAdapter.MyViewHolder>() {
    class MyViewHolder(val mView: RowItemNotificationListBinding) :
        RecyclerView.ViewHolder(mView.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        return MyViewHolder(
            RowItemNotificationListBinding.inflate(
                LayoutInflater.from(con),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.mView.img.setImageResource(list[position].img)
        holder.mView.date.text = list[position].date


    }

    override fun getItemCount(): Int {
        return list.size
    }
}