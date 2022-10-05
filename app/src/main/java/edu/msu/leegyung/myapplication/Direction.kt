package edu.msu.leegyung.myapplication

import java.lang.Math.atan


//원 안에서의 방향
//원 밖에서의 방향(같은 fun)과 작은원 위치

class Direction(cX : Float, cY : Float, radius : Float) {
    private var cX = cX
    private var cY = cY
    private var radius = radius
    private val pi = 180 / 3.14159
    var dir = "stop"

    init{

    }


    fun setDirection(touchX : Float, touchY : Float){
        dir = when(kotlin.math.atan2(touchY-cY, touchX - cX) * pi){
            in -22.5..22.5 -> {
                "right"
            }
            in 22.5..67.5 -> {
                "rightdown"
            }
            in 67.5..112.5 -> {
                "down"
            }
            in 112.5..157.5 -> {
                "leftdown"
            }
            in -67.5..-22.5 -> {
                "rightup"
            }
            in -112.5..-67.5 -> {
                "up"
            }
            in -157.5..-112.5 -> {
                "leftup"
            }
            else -> {
                "left"
            }
        }
    }

    fun outCircleDir(touchX : Float, touchY : Float):Array<Float>{
        setDirection(touchX, touchY)
        val deg = kotlin.math.atan2(touchY-cY, touchX - cX)

        val x:Float = kotlin.math.cos(deg) * radius + cX
        val y:Float = kotlin.math.sin(deg) * radius + cY

        return arrayOf(x, y)
    }




}