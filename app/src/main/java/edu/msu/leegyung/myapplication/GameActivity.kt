package edu.msu.leegyung.myapplication

import android.content.SharedPreferences
import android.graphics.Canvas
import android.graphics.Point
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class GameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        val actionBar = supportActionBar
        actionBar!!.hide()


        val pref : SharedPreferences = this.getSharedPreferences("size", MODE_PRIVATE)
        pref.edit().putInt("height", resources.displayMetrics.heightPixels).apply()
        pref.edit().putInt("width", resources.displayMetrics.widthPixels).apply()



    }
}