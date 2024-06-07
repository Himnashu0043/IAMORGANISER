package com.example.iamorganiser.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.example.iamorganiser.activity.playVideo.PlayVideoActivity
import com.example.iamorganiser.databinding.VideoItemBinding

class ViewPagerVideoAdapter(val con: Context, var list: ArrayList<String?>) : PagerAdapter() {
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val binding: VideoItemBinding = VideoItemBinding.inflate(
            LayoutInflater.from(
                con
            ), container, false
        )
        if (position in 0 until list.size) {
            val old = list[position]?.replace(" ", "%20")
            Glide.with(binding.root).load(old).fitCenter()
                .into(binding.imageView52)
            binding.root.setOnClickListener {
                con.startActivity(
                    Intent(con, PlayVideoActivity::class.java).putExtra(
                        "video_url", old
                    )
                )
            }
            // binding.imageView52.loadImagesWithGlideExt(old)
        }

        container.addView(binding.root, 0)
        return binding.root
    }

    override fun getCount(): Int {
        return list.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}