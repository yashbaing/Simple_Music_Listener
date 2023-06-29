package com.example.musiclistener

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder

class MusicServices: Service() {
        private  var mp: MediaPlayer? = null
        override fun onBind(intent: Intent?): IBinder? {
            return null
        }
        override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
            return super.onStartCommand(intent, flags, startId)
            mp= MediaPlayer.create(this,R.raw.rukjananhi)
            mp?.start()
            return START_STICKY
        }
        override fun onDestroy() {
            super.onDestroy()
            mp?.stop()
        }

    }
