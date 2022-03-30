package com.example.ungdungnghenhac.Activity

import android.graphics.Color
import android.media.AudioManager
import android.media.MediaPlayer
import android.media.MediaPlayer.OnCompletionListener
import android.os.AsyncTask
import android.os.Bundle
import android.os.Handler
import android.os.StrictMode
import android.view.View
import android.widget.ImageButton
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.ViewPager
import com.example.ungdungnghenhac.Adapter.ViewPagerPlaylistnhac
import com.example.ungdungnghenhac.Fragment.DiaNhacFragment
import com.example.ungdungnghenhac.Fragment.PlayDanhSachCacBaiHatFragment
import com.example.ungdungnghenhac.Model.BaiHat
import com.example.ungdungnghenhac.R
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class PlayNhacActivity : AppCompatActivity() {
    var toolbarplaynhac: Toolbar? = null
    var txtTimesong: TextView? = null
    var txtTotaltimesong:TextView? = null
    var sktime: SeekBar? = null
    var imgplay: ImageButton? = null
    var imgrepeat:ImageButton? = null
    var imgnext:ImageButton? = null
    var imgpre:ImageButton? = null
    var imgrandom:ImageButton? = null
    var viewPagerplaynhac: ViewPager? = null
    var diaNhacFragment: DiaNhacFragment? = null
    var playDanhSachCacBaiHatFragment: PlayDanhSachCacBaiHatFragment? = null
    var mediaPlayer: MediaPlayer? = null
    var position = 0
    var repeat = false
    var checkrandom = false
    var next = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play_nhac)
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        GetDataFromIntent()
        init()
        eventClick()
    }

    private fun eventClick() {
        val handler = Handler()
        handler.postDelayed(object : Runnable {
            override fun run() {
                if (adapternhac!!.getItem(1) != null) {
                    if (arrbaihat.size > 0) {
                        diaNhacFragment!!.Playnhac(arrbaihat[0].getHinhBaiHat())
                        handler.removeCallbacks(this)
                    } else {
                        handler.postDelayed(this, 300)
                    }
                }
            }
        }, 500)
        imgplay!!.setOnClickListener {
            if (mediaPlayer!!.isPlaying) {
                mediaPlayer!!.pause()
                imgplay!!.setImageResource(R.drawable.iconplay)
            } else {
                mediaPlayer!!.start()
                imgplay!!.setImageResource(R.drawable.iconpause)
            }
        }
        imgrepeat!!.setOnClickListener {
            if (repeat == false) {
                if (checkrandom == true) {
                    checkrandom = false
                    imgrepeat!!.setImageResource(R.drawable.iconsyned)
                    imgrandom!!.setImageResource(R.drawable.iconsuffle)
                }
                imgrepeat!!.setImageResource(R.drawable.iconsyned)
                repeat = true
            } else {
                imgrepeat!!.setImageResource(R.drawable.iconrepeat)
                repeat = false
            }
        }
        imgrandom!!.setOnClickListener {
            if (checkrandom == false) {
                if (repeat == true) {
                    repeat = false
                    imgrandom!!.setImageResource(R.drawable.iconshuffled)
                    imgrepeat!!.setImageResource(R.drawable.iconrepeat)
                }
                imgrandom!!.setImageResource(R.drawable.iconshuffled)
                checkrandom = true
            } else {
                imgrandom!!.setImageResource(R.drawable.iconsuffle)
                checkrandom = false
            }
        }
        sktime!!.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {}
            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {
                mediaPlayer!!.seekTo(seekBar.progress)
            }
        })
        imgnext!!.setOnClickListener {
            if (arrbaihat.size > 0) {
                if (mediaPlayer!!.isPlaying || mediaPlayer != null) {
                    mediaPlayer!!.stop()
                    mediaPlayer!!.release()
                    mediaPlayer = null
                }
                if (position < arrbaihat.size) {
                    imgplay!!.setImageResource(R.drawable.iconpause)
                    position++
                    if (repeat == true) {
                        if (position == 0) {
                            position = arrbaihat.size
                        }
                        position -= 1
                    }
                    if (checkrandom == true) {
                        val random = Random()
                        val index = random.nextInt(arrbaihat.size)
                        if (index == position) {
                            position = index - 1
                        }
                        position = index
                    }
                    if (position > arrbaihat.size - 1) {
                        position = 0
                    }
                    PlayMp3().execute(arrbaihat[position].getLinkBaiHat())
                    diaNhacFragment!!.Playnhac(arrbaihat[position].getHinhBaiHat())
                    supportActionBar!!.setTitle(arrbaihat[position].getTenBaiHat())
                    UpdateTime()
                }
            }
            imgpre!!.isClickable = false
            imgnext!!.isClickable = false
            val handler1 = Handler()
            handler1.postDelayed({
                imgpre!!.isClickable = true
                imgnext!!.isClickable = true
            }, 5000)
        }
        imgpre!!.setOnClickListener {
            if (arrbaihat.size > 0) {
                if (mediaPlayer!!.isPlaying || mediaPlayer != null) {
                    mediaPlayer!!.stop()
                    mediaPlayer!!.release()
                    mediaPlayer = null
                }
                if (position < arrbaihat.size) {
                    imgplay!!.setImageResource(R.drawable.iconpause)
                    position--
                    if (position < 0) {
                        position = arrbaihat.size - 1
                    }
                    if (repeat == true) {
                        position += 1
                    }
                    if (checkrandom == true) {
                        val random = Random()
                        val index = random.nextInt(arrbaihat.size)
                        if (index == position) {
                            position = index - 1
                        }
                        position = index
                    }
                    PlayMp3().execute(arrbaihat[position].getLinkBaiHat())
                    diaNhacFragment!!.Playnhac(arrbaihat[position].getHinhBaiHat())
                    supportActionBar!!.setTitle(arrbaihat[position].getTenBaiHat())
                    UpdateTime()
                }
            }
            imgpre!!.isClickable = false
            imgnext!!.isClickable = false
            val handler1 = Handler()
            handler1.postDelayed({
                imgpre!!.isClickable = true
                imgnext!!.isClickable = true
            }, 5000)
        }
    }

    private fun GetDataFromIntent() {
        val intent = intent
        arrbaihat.clear()
        if (intent != null) {
            if (intent.hasExtra("cakhuc")) {
                val baiHat: BaiHat? = intent.getParcelableExtra("cakhuc")
                arrbaihat.add(baiHat!!)
            }
            if (intent.hasExtra("cacbaihat")) {
                val baiHatArrayList = intent.getParcelableArrayListExtra<BaiHat>("cacbaihat")
                arrbaihat = baiHatArrayList!!
            }
        }
    }

    private fun init(){
        toolbarplaynhac = findViewById(R.id.toolbarplaynhac)
        txtTimesong = findViewById(R.id.txttimesong)
        txtTotaltimesong = findViewById(R.id.txttotaltimesong)
        sktime = findViewById(R.id.seekbarsong)
        imgplay = findViewById(R.id.imgbuttonplay)
        imgrepeat = findViewById(R.id.imgbuttonrepeat)
        imgnext = findViewById(R.id.imgbuttonnext)
        imgrandom = findViewById(R.id.imgbuttonsuffle)
        imgpre = findViewById(R.id.imgbuttonpre)
        viewPagerplaynhac = findViewById(R.id.viewpagerplaynhac)
        setSupportActionBar(toolbarplaynhac)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        toolbarplaynhac!!.setNavigationOnClickListener(View.OnClickListener {
            finish()
            mediaPlayer!!.stop()
            arrbaihat.clear()
        })
        toolbarplaynhac!!.setTitleTextColor(Color.WHITE)
        diaNhacFragment = DiaNhacFragment()
        playDanhSachCacBaiHatFragment = PlayDanhSachCacBaiHatFragment()
        adapternhac = ViewPagerPlaylistnhac(supportFragmentManager)
        adapternhac!!.AddFragment(playDanhSachCacBaiHatFragment)
        adapternhac!!.AddFragment(diaNhacFragment)
        viewPagerplaynhac!!.setAdapter(adapternhac)
        diaNhacFragment = adapternhac!!.getItem(1) as DiaNhacFragment
        if (arrbaihat.size > 0) {
            supportActionBar!!.setTitle(arrbaihat[0].getTenBaiHat())
            PlayMp3().execute(arrbaihat[0].getLinkBaiHat())
            imgplay!!.setImageResource(R.drawable.iconpause)
        }
    }
    companion object {
        var arrbaihat: ArrayList<BaiHat> = ArrayList()
        var adapternhac: ViewPagerPlaylistnhac? = null
    }

    internal inner class PlayMp3 : AsyncTask<String, Void, String>() {
        override fun doInBackground(vararg strings: String): String {
            return strings[0]
        }

        override fun onPostExecute(baihat: String) {
            super.onPostExecute(baihat)
            try {
                mediaPlayer = MediaPlayer()
                mediaPlayer!!.setAudioStreamType(AudioManager.STREAM_MUSIC)
                mediaPlayer!!.setOnCompletionListener(OnCompletionListener {
                    mediaPlayer!!.stop()
                    mediaPlayer!!.reset()
                })
                mediaPlayer!!.setDataSource(baihat)
                mediaPlayer!!.prepare()
            } catch (e: IOException) {
                e.printStackTrace()
            }
            mediaPlayer!!.start()
            TimeSong()
            UpdateTime()
        }
    }

    private fun TimeSong() {
        val simpleDateFormat = SimpleDateFormat("mm:ss")
        txtTotaltimesong!!.text = simpleDateFormat.format(mediaPlayer!!.duration)
        sktime!!.max = mediaPlayer!!.duration
    }

    private fun UpdateTime(){
        val handler = Handler()
        handler.postDelayed(object : Runnable {
            override fun run() {
                if (mediaPlayer != null) {
                    sktime!!.progress = mediaPlayer!!.currentPosition
                    val simpleDateFormat = SimpleDateFormat("mm:ss")
                    txtTimesong!!.text = simpleDateFormat.format(mediaPlayer!!.currentPosition)
                    handler.postDelayed(this, 300)
                    mediaPlayer!!.setOnCompletionListener {
                        next = true
                        try {
                            Thread.sleep(1000)
                        } catch (e: InterruptedException) {
                            e.printStackTrace()
                        }
                    }
                }
            }
        }, 300)
        val handler1 = Handler()
        handler1.postDelayed(object : Runnable {
            override fun run() {
                if (next == true) {
                    if (position < arrbaihat.size) {
                        imgplay!!.setImageResource(R.drawable.iconpause)
                        position++
                        if (repeat == true) {
                            if (position == 0) {
                                position = arrbaihat.size
                            }
                            position -= 1
                        }
                        if (checkrandom == true) {
                            val random = Random()
                            val index = random.nextInt(arrbaihat.size)
                            if (index == position) {
                                position = index - 1
                            }
                            position = index
                        }
                        if (position > arrbaihat.size - 1) {
                            position = 0
                        }
                        PlayMp3().execute(arrbaihat[position].getLinkBaiHat())
                        diaNhacFragment!!.Playnhac(arrbaihat[position].getHinhBaiHat())
                        supportActionBar!!.title = arrbaihat[position].getTenBaiHat()
                    }
                    imgpre!!.isClickable = false
                    imgnext!!.isClickable = false
                    val handler1 = Handler()
                    handler1.postDelayed({
                        imgpre!!.isClickable = true
                        imgnext!!.isClickable = true
                    }, 5000)
                    next = false
                    handler1.removeCallbacks(this)
                } else {
                    handler1.postDelayed(this, 1000)
                }
            }
        }, 1000)
    }
}