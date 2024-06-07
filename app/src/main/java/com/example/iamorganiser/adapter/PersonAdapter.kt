package com.example.iamorganiser.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.iamorganiser.databinding.PrsonItemsBinding
import com.example.iamorganiser.helper.setFormatDate
import com.example.iamorganiser.modal.remote.home.TicketVerifyRes

class PersonAdapter(
    val con: Context,
    var mlist: ArrayList<TicketVerifyRes.Data.TicketAttendee>
) :
    RecyclerView.Adapter<PersonAdapter.MyViewHOlder>() {
    class MyViewHOlder(val mView: PrsonItemsBinding) : RecyclerView.ViewHolder(mView.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHOlder {
        return MyViewHOlder(PrsonItemsBinding.inflate(LayoutInflater.from(con), parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHOlder, position: Int) {
        holder.mView.textView28.text = mlist[position].name ?: ""
        holder.mView.tvYear.text = "${mlist[position].age ?: 0} year"

        holder.mView.checkBox2.setOnClickListener {
            mlist[position].isSeleted = !mlist[position].isSeleted

        }
        if (mlist[position].is_checkedIn == true) {
            holder.mView.tvChekedIn.text = "Checked In"
            holder.mView.tvDateAllowBy.text =
                "Date : ${setFormatDate(mlist[position].checkInTime ?: "")} allow by ${
                    mlist[position].userData?.get(position)?.firstName ?: ""
                } ${mlist[position].userData?.get(position)?.lastName ?: ""}"
        }

    }

    fun getList(): ArrayList<String> {
        val arrayList = arrayListOf<String>()
        mlist.forEach {
            if (it.isSeleted) {
                arrayList.add(it._id.toString())
            }
        }
        return arrayList
    }

    override fun getItemCount(): Int {
        return mlist.size
    }


}