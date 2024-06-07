package com.example.iamorganiser.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.iamorganiser.databinding.HomeItemBinding
import com.example.iamorganiser.helper.convertMinutesToHoursAndMinutes
import com.example.iamorganiser.helper.setFormatDate
import com.example.iamorganiser.modal.remote.home.ScanlistRes
import com.example.iamorganiser.modal.remote.scan.ScanEventListRes

class ScanAdapter(
    val con: Context,
    val list: ArrayList<ScanEventListRes.Data>,
    val onPress: ScanDate
) :
    RecyclerView.Adapter<ScanAdapter.MyViewHolder>() {
    class MyViewHolder(var mView: HomeItemBinding) : RecyclerView.ViewHolder(mView.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(HomeItemBinding.inflate(LayoutInflater.from(con), parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.mView.tvName.text = list[position].eventTitle ?: ""
        val (hours, minutes) = convertMinutesToHoursAndMinutes(list[position].eventDuration ?: 0)
        if (minutes == 0) {
            holder.mView.tvTime.text = "$hours hr"
        } else {
            holder.mView.tvTime.text = "$hours hr $minutes min"
        }

        holder.mView.tvDateTime.text =
            "${setFormatDate(list[position].eventStartDate ?: "")}"
        holder.mView.tvAddress.text = list[position].eventLocation?.locationName ?: ""
        holder.mView.tvTicket.text = "${list[position].totalTickets ?: 0} Tickets Bought"
        holder.mView.tvEntry.text = "${list[position].totalAttendees ?: 0} Entries"
        holder.itemView.setOnClickListener {
            onPress.onClickDate(list[position]._id ?: "", position)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface ScanDate {
        fun onClickDate(id: String, position: Int)
    }
}