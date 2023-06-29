package com.example.musiclistener

import android.media.MediaParser
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.widget.Button
import android.widget.SeekBar

class MainActivity : AppCompatActivity() {
    private var mp: MediaPlayer? = null
    private var currentSong= mutableListOf(R.raw.rukjananhi)
    var btnPlay: Button? = null
    var btnPause: Button? = null
    var btnStop: Button? = null
    var seekBar:SeekBar?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnPlay = findViewById<Button>(R.id.btnPlay)
        btnPause = findViewById<Button>(R.id.btnPause)
        btnStop = findViewById<Button>(R.id.btnStop)
        seekBar = findViewById(R.id.sBar)
        ControlSound(currentSong[0])
    }

    private fun ControlSound(id:Int){

        btnPlay?.setOnClickListener() {
            if(mp==null){
                mp = MediaPlayer.create(this,id)
                InitialiseSeekBar()
            }
            mp?.start()
        }

        btnPause?.setOnClickListener() {
            if(mp!=null){
                mp?.pause()
            }
        }

        btnStop?.setOnClickListener() {
            if(mp!=null){
                mp?.stop()
                mp?.reset()
                mp?.release()
                mp=null
            }
        }

        seekBar?.setOnSeekBarChangeListener(object:SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if(fromUser)mp?.seekTo(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })
    }

    @Suppress("DEPRECATION")
    private fun InitialiseSeekBar(){
        seekBar?.max = mp!!.duration

        val handler = Handler()
        handler.postDelayed(object:Runnable{
            override fun run() {
                try{
                    seekBar?.progress=mp!!.currentPosition
                    handler.postDelayed(this,1000)
                }
                catch(e:Exception){
                    seekBar?.progress=0
                }
            }
        },0)

    }


}