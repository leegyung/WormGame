package edu.msu.leegyung.myapplication

import android.app.ActionBar
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.viewpager.widget.ViewPager

class MainActivity : AppCompatActivity() {
    private lateinit var start : ImageView
    private lateinit var worm : ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val actionBar = supportActionBar
        actionBar!!.hide()

        start = findViewById(R.id.start_btn)
        worm = findViewById(R.id.Worm_Image)
        start.setOnClickListener{
            startClick()
        }
        worm.setOnClickListener{
            rotate()
        }
    }

    private fun startClick(){
        start.setPadding(30,30,30,30)
        Handler().postDelayed(Runnable {
            Toast.makeText(this,"Start",Toast.LENGTH_SHORT).show()
            start.setPadding(0,0,0,0)
            val intent = Intent(this,GameActivity::class.java)
            startActivity(intent)
        }, 100)

    }



    private fun rotate(){
        val rot = AnimationUtils.loadAnimation(this,R.anim.rotate_clockwise)
        worm.startAnimation(rot)
    }

}