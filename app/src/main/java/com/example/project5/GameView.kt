package com.example.project5

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Point
import android.util.Log
import android.view.MotionEvent
import android.view.View

class GameView : View {
    private lateinit var paint : Paint
    private var height = 0
    private var width = 0
    private lateinit var pong : Pong
    private var barStartX = 0



    constructor(context : Context, width : Int, height : Int) : super(context) {
        paint = Paint()
        paint.color = Color.BLACK
        paint.isAntiAlias = true
        paint.strokeWidth = 20.0f
        this.height = height
        this.width = width

        pong = Pong((width / 2.0f), 50.0f, 40,
            5, (width / 2.0f) - (150.0f / 2.0f),
            (width / 2.0f) + (150.0f / 2.0f), height - 50.0f, 5, 45.0f)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawLine(pong.getBarStartX(), pong.getBarY(), pong.getBarEndX(), pong.getBarY(), paint)

        val ballStartX = (width / 2.0f)
        val ballYPosition = 50.0f
        val ballRadius = 40.0f
        canvas.drawCircle(ballStartX, ballYPosition, ballRadius,paint)
    }

    fun updateBar (e : MotionEvent) {
        Log.w("GameView", "Inside updateBar")
    }

    fun getPong( ) : Pong {
        return pong
    }

}