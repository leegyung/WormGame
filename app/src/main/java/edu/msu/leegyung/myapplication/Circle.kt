package edu.msu.leegyung.myapplication

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint

class Circle (xPoint : Int, yPoint : Int, wid:Float, space:Float) {
    private var radius = 0f
    private var pointX = 0
    private var pointY = 0
    private var multiplyingPoint = 0f
    private var space = 0f

    private var paint = Paint()

    init{
        this.radius = wid/100/2
        this.pointX = xPoint
        this.pointY = yPoint
        this.space = space + radius
        this.multiplyingPoint = wid/200



        paint.color = Color.RED

    }

    fun getX():Int { return pointX }
    fun getY():Int { return pointY }
    fun setXY(x : Int, y : Int){
        pointX += x
        pointY += y
    }

    fun nextMove(x : Int, y : Int){
        this.pointX = x
        this.pointY = y
    }

    fun onDraw(canvas: Canvas?){
        canvas?.drawCircle(space + pointX * multiplyingPoint, space + pointY * multiplyingPoint, radius, paint)
    }


}