package com.mansao.myquran.ui

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mansao.myquran.R
import com.mansao.myquran.data.network.response.ResponseItem
import com.mansao.myquran.databinding.ActivityDetailBinding
import java.util.concurrent.Executors

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var mediaPlayer: MediaPlayer
    private var getData: ResponseItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.progressBar.visibility = View.GONE

        setDetail()
    }

    private fun setDetail() {
        getData = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_DATA, ResponseItem::class.java)
        } else {
            intent.getParcelableExtra(EXTRA_DATA)
        }
        supportActionBar?.apply {
            title = getData?.nama
            setDisplayHomeAsUpEnabled(true)
        }


        binding.apply {
            tvSurah.text = getData?.nama
            tvTitle.text = getData?.asma
            tvAyat.text = StringBuilder(getData?.ayat.toString()).append(getString(R.string.ayat))
            tvDescription.text = getData?.keterangan

            btnPlay.setOnClickListener {
                binding.progressBar.visibility = View.VISIBLE
                val executor = Executors.newSingleThreadExecutor()
                val handler = Handler(Looper.getMainLooper())
                executor.execute {
                    playAudio()
                    handler.post {
                        Toast.makeText(this@DetailActivity, getString(R.string.audio_played), Toast.LENGTH_SHORT)
                            .show()
                        binding.progressBar.visibility = View.GONE
                    }
                }
            }

            btnStop.setOnClickListener {
                stopAudio()
            }
        }
    }

    private fun playAudio() {
        val audioUrl = getData?.audio

        mediaPlayer = MediaPlayer()
        mediaPlayer.setAudioAttributes(
            AudioAttributes
                .Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build()
        )
        try {
            mediaPlayer.apply {
                setDataSource(audioUrl)
                prepare()
                start()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun stopAudio() {
        mediaPlayer.apply {
            if (isPlaying) {
                stop()
                reset()
                release()
                Toast.makeText(this@DetailActivity, getString(R.string.audio_stop), Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(
                    this@DetailActivity,
                    getString(R.string.audio_isnot_playing),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        stopAudio()
        return super.onSupportNavigateUp()
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}