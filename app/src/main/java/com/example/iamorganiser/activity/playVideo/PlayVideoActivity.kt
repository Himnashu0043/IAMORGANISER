package com.example.iamorganiser.activity.playVideo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.iamorganiser.R
import com.example.iamorganiser.databinding.ActivityNotificationBinding
import com.example.iamorganiser.databinding.ActivityPlayVideoBinding
import com.example.iamorganiser.helper.ExoPlayerUtils.Companion.playVideo
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.Player

class PlayVideoActivity : AppCompatActivity() {
    lateinit var binding: ActivityPlayVideoBinding
    private var videoUrl: String? = ""
    var simpleExoPlayer: ExoPlayer? = null
    private val handle: () -> Unit = {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayVideoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        videoUrl = intent.getStringExtra("video_url") ?: ""
        println("==========vi$videoUrl")
        initView()
        listener()
    }

    private fun listener() {
        binding.imageView85.setOnClickListener {
            finish()
        }
    }

    private fun initView() {
        binding.simpleExoPlayerView2.visibility = View.VISIBLE
        simpleExoPlayer =
            binding.simpleExoPlayerView2.playVideo(videoUrl ?: "", Player.REPEAT_MODE_OFF) {
                if (it == Player.STATE_BUFFERING) {
                    binding.progressBar.visibility = View.VISIBLE
                } else {
                    binding.progressBar.visibility = View.GONE
                }
                if (it == Player.STATE_ENDED) {
                    handle.invoke()
                }
            }
    }

    override fun onDestroy() {
        super.onDestroy()
        simpleExoPlayer?.stop()
        simpleExoPlayer?.release()
        simpleExoPlayer = null
    }
}