package com.example.project5

import android.graphics.Point

class Pong {
    private var barStartX = 0.0f
    private var barEndX = 0.0f
    private var barY = 0.0f
    private var barSpeed = 0
    private var ballX = 0.0f
    private var ballY = 0.0f
    private var radius = 0
    private var ballAngle = 0
    private var ballSpeed = 0
    private var hitCollision = false

    constructor(newBallX : Float, newBallY : Float, newBallRadius : Int, newBallSpeed : Int,
        newBarStartX : Float, newBarEndX : Float, newBarY : Float, newBarSpeed: Int, newBallAngel : Float) {
        setBarLocation(newBarStartX, newBarEndX, newBarY)
    }
    fun setBarLocation(newBarStartX : Float, newBarEndX : Float, newBarY : Float) {
        barStartX = newBarStartX
        barEndX = newBarEndX
        barY = newBarY
    }

    fun updateBarPosition(x: Float) {
        val barWidth = barEndX - barStartX
        barStartX = x - barWidth / 2
        barEndX = x + barWidth / 2
    }


    fun setBarSpeed(newBarSpeed : Int) {
        barSpeed = newBarSpeed
    }

    fun getBarStartX() : Float {
        return barStartX
    }

    fun getBarEndX() : Float {
        return barEndX
    }

    fun getBarY() : Float {
        return barY
    }
}