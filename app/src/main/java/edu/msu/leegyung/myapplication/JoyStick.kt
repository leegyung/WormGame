package edu.msu.leegyung.myapplication

import android.content.SharedPreferences
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.pow

class JoyStick {
    private var redPaint : Paint = Paint()
    private var blackPaint : Paint = Paint()
    private var grayPaint : Paint = Paint()

    private var height : Float = 0f
    private var width : Float = 0f

    private var height70 : Float = 0f

    private var radius : Float = 0f
    private var circleX : Float = 0f
    private var circleY : Float = 0f

    private var smallRadius : Float = 0f
    private var smallCircleX : Float = 0f
    private var smallCircleY : Float = 0f

    private var initialX : Float = 0f
    private var initialY : Float = 0f

    private lateinit var Direction : Direction

    init{
        redPaint.color = Color.RED
        redPaint.textSize = 100.0f

        grayPaint.color = Color.GRAY
        grayPaint.strokeWidth = 10f

        blackPaint.color = Color.BLACK
        blackPaint.strokeWidth = 10f

    }

    fun setSize(w : Int, h : Int){
        height = h.toFloat()
        width = w.toFloat()
        height70 = h * 0.7f

        radius = height * 0.1f - 20f
        smallRadius = radius/2

        circleY = height * 0.85f
        smallCircleY = height * 0.85f

        circleX = width / 2
        smallCircleX = width / 2

        Direction = Direction(circleX, circleY, radius)

    }

    fun onDraw(touchX : Float, touchY : Float, canvas : Canvas?){
        setDirection(touchX, touchY)
        canvas?.drawLine(0.0f, height70, width, height70, grayPaint)
        canvas?.drawCircle(circleX, circleY, radius, grayPaint)
        canvas?.drawCircle(smallCircleX, smallCircleY, smallRadius, blackPaint)


    }

    fun touchUp(){
        initialX = 0f
        initialY = 0f
        smallCircleY = height * 0.85f
        smallCircleX = width / 2
    }

    fun setInitialTouch(X : Float, Y : Float){
        initialX = X
        initialY = Y
    }

    private fun setDirection(touchX : Float, touchY : Float){
        if(initialTouchInCircle()){
            when(inCircle(touchX, touchY)){
                true->{
                    smallCircleX = touchX
                    smallCircleY = touchY
                    Direction.setDirection(touchX, touchY)
                    //각도 찾고 방향설정
                }
                false->{
                    val arr:Array<Float> = Direction.outCircleDir(touchX, touchY)
                    smallCircleX = arr[0]
                    smallCircleY = arr[1]
                }
            }
        }
    }

    private fun initialTouchInCircle() : Boolean {
       if(radius.pow(2) > ((circleX - initialX).pow(2) + (circleY - initialY).pow(2))){
           return true
       }
        return false
    }

    private fun inCircle(x : Float, y : Float) : Boolean {
        if(radius.pow(2) > ((circleX - x).pow(2) + (circleY - y).pow(2))){
            return true
        }
        return false
    }

    fun getDirection():String { return Direction.dir }


}