package edu.msu.leegyung.myapplication

import android.graphics.*
import java.lang.Math.random
import java.lang.Math.round
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.roundToInt

class GamePlay(width : Int, height : Int) {
    private var length = if(width < height * 0.7) width else (height * 0.7).roundToInt()
    private var grayPaint : Paint = Paint()
    private var wid = 0f
    private var space = 0f

    private val random = Random()

    private val circleArr : ArrayList<Circle> = arrayListOf()
    private var food : Food

    private var lastX = 0
    private var lastY = 0




    init{
        //뭐가 더작은지 정하고 작은거에 80~90% 로 가로세로 만들고 중앙에 박스 만든다
        wid = length * 0.9f
        space = (length - wid) / 2

        grayPaint.color = Color.GRAY
        grayPaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SCREEN)
        grayPaint.strokeWidth = 10f

        circleArr.add(Circle(0,0, wid, space))

        food = Food(random.nextInt(199), random.nextInt(199), wid, space)
    }


    fun onDraw(canvas : Canvas?, dir : String){
        canvas?.drawRect(space, space,wid + space,wid + space,grayPaint)
        when(dir) {
            "up"->{
                if(circleArr[0].getY() > 0){
                    movement()
                    circleArr[0].setXY(0,-1)
                }
            }
            "right"->{
                if(circleArr[0].getX() < 198){
                    movement()
                    circleArr[0].setXY(1,0)
                }
            }
            "left"->{
                if(circleArr[0].getX() > 0){
                    movement()
                    circleArr[0].setXY(-1,0)
                }
            }
            "down"->{
                if(circleArr[0].getY() < 198){
                    movement()
                    circleArr[0].setXY(0,1)
                }
            }

            "rightup"->{
                if(circleArr[0].getY() > 0 && circleArr[0].getX() < 198){
                    movement()
                    circleArr[0].setXY(1,-1)
                }
            }
            "leftup"->{
                if(circleArr[0].getY() > 0 && circleArr[0].getX() > 0){
                    movement()
                    circleArr[0].setXY(-1,-1)
                }
            }
            "rightdown"->{
                if(circleArr[0].getY() < 198 && circleArr[0].getX() < 198){
                    movement()
                    circleArr[0].setXY(1,1)
                }
            }
            "leftdown"->{
                if(circleArr[0].getY() < 198 && circleArr[0].getX() > 0){
                    movement()
                    circleArr[0].setXY(-1,1)

                }
            }
        }


        if(ateFood()){
            circleArr.add(Circle(lastX,lastY, wid, space))
            food.setXY(random.nextInt(199), random.nextInt(199))
        }

        food.onDraw(canvas)
        for (c:Circle in circleArr){
            c.onDraw(canvas)
        }
    }

    //머리 뒷애들 위치를 앞에 애들로 바꾸기
    private fun movement(){
        lastX = circleArr[circleArr.size - 1].getX()
        lastY = circleArr[circleArr.size - 1].getY()
        if(circleArr.size != 1){
            for(i : Int in circleArr.size - 1 downTo 1){
                circleArr[i].nextMove(circleArr[i-1].getX(), circleArr[i-1].getY())
            }
        }
    }

    //food 먹엇을때
    private fun ateFood() : Boolean{
        if(circleArr[0].getX() in food.getX() - 3..food.getX() + 3 && circleArr[0].getY() in food.getY() - 3..food.getY() + 3){
            return true
        }
        return false
    }



}