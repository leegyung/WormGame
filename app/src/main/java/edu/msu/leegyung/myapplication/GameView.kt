package edu.msu.leegyung.myapplication

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Canvas
import android.graphics.Point
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.AttributeSet
import android.view.Display
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import java.util.*

class GameView : View{
    private var touchX : Float = 0.0f
    private var touchY : Float = 0.0f


    private var joyStick : JoyStick = JoyStick()
    private var gameZone : GamePlay


    private lateinit var timer : Timer

    constructor(context : Context) : this(context, null)
    constructor(context : Context, attrs : AttributeSet?): this(context, attrs, 0)
    constructor(context : Context, attrs : AttributeSet?, defStyle: Int ): super(context, attrs,defStyle)

    init {
        val pref : SharedPreferences = context.getSharedPreferences("size",
            AppCompatActivity.MODE_PRIVATE
        )
        joyStick.setSize(pref.getInt("width", 0), pref.getInt("height", 0))
        gameZone = GamePlay(pref.getInt("width", 0), pref.getInt("height", 0))

        start()

    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        super.onTouchEvent(event)
        when (event?.actionMasked){
            MotionEvent.ACTION_DOWN -> {
                joyStick.setInitialTouch(event.x, event.y)
            }
            MotionEvent.ACTION_MOVE -> {
                touchX = event.x
                touchY = event.y
            }
            MotionEvent.ACTION_UP -> {
                joyStick.touchUp()
            }


        }
        return true

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        joyStick.onDraw(touchX, touchY, canvas)
        gameZone.onDraw(canvas, joyStick.getDirection())

    }

    private fun start(){
        timer = kotlin.concurrent.timer(period = 30){ //여기서 프레임 조절해서 부드럽게
                                                      //혹은 칸을 100 이상으로 쪼개서 더 부드럽게
            invalidate()
        }
    }


}