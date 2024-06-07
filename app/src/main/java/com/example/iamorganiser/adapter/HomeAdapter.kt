package com.example.iamorganiser.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.iamorganiser.activity.eventDetail.EventDetailActivity
import com.example.iamorganiser.databinding.HomeItemBinding
import com.example.iamorganiser.helper.convertMinutesToHoursAndMinutes
import com.example.iamorganiser.helper.setFormatDate
import com.example.iamorganiser.modal.remote.home.HomeEventRes

class HomeAdapter(
    val con: Context,
    val list: ArrayList<HomeEventRes.Data.PaginationData>,
    val onPress: HomeDateSlot
) :
    RecyclerView.Adapter<HomeAdapter.MyViewHolder>() {
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
        if (!list[position].eventStartDate.isNullOrEmpty()) {
            holder.mView.tvDateTime.text =
                "${setFormatDate(list[position].eventStartDate ?: "")} , ${setFormatDate(list[position].eventEndDate ?: "")}"
        }

        holder.mView.tvAddress.text = list[position].eventLocation?.locationName ?: ""
        holder.mView.tvTicket.text = "${list[position].bookedTickets ?: 0} Tickets Bought"
        holder.mView.tvEntry.text = "${list[position].totalEntries ?: 0} Entries"
        holder.itemView.setOnClickListener {
//            con.startActivity(
//                Intent(con, EventDetailActivity::class.java).putExtra(
//                    "id",
//                    list[position]._id ?: ""
//                )
//            )
            onPress.onClick(list[position]._id ?: "", position)
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface HomeDateSlot {
        fun onClick(id: String, position: Int)
    }
}