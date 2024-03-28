package com.example.project5

import android.util.Log
class Pong {
    private var barStartX = 0.0f
    private var barEndX = 0.0f
    private var barY = 0.0f
    private var barSpeed = 0
    private var ballX = 0.0f
    private var ballY = 0.0f
    private var radius = 0.0f
    private var ballAngle = 0.0f
    private var ballSpeedX = 0
    private var ballSpeedY = 0
    private var hitCollision = false
    private var gameStarted = false

    constructor(newBallX : Float, newBallY : Float, newBallRadius : Float, newBallSpeedX : Int, newBallSpeedY : Int,
        newBarStartX : Float, newBarEndX : Float, newBarY : Float, newBarSpeed: Int, newBallAngel : Float) {
        setBarLocation(newBarStartX, newBarEndX, newBarY)
        setBallLocation(newBallX, newBallY, newBallRadius)
    }

    fun setBarLocation(newBarStartX : Float, newBarEndX : Float, newBarY : Float) {
        barStartX = newBarStartX
        barEndX = newBarEndX
        barY = newBarY
    }

    fun setBallLocation(newBallX: Float, newBallY: Float, newBallRadius: Float) {
        ballX = newBallX
        ballY = newBallY
        radius = newBallRadius
    }

    fun updateBarPosition(x: Float) {
        val barWidth = barEndX - barStartX
        barStartX = x - barWidth / 2
        barEndX = x + barWidth / 2
    }

    fun updateBallPosition() {
        ballX += ballSpeedX
        ballY += ballSpeedY
        Log.w("Pong", ballX.toString())
        Log.w("Pong", ballY.toString())
    }

    fun startGame(x: Float, width: Int) {
        gameStarted = true

        Log.w("Pong", "Game Started")
        if (x < width / 2) {
            ballSpeedX = -10
        } else if (x > width / 2){
            ballSpeedX = 10
        }
        ballSpeedY = 10
//        ballAngle = (Math.PI / 4).toFloat()
        updateBallPosition()
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

    fun getBallX() : Float {
        return ballX
    }

    fun getBallY() : Float {
        return ballY
    }

    fun getRadius() : Float {
        return radius
    }

    fun isGameStarted() : Boolean {
        return gameStarted
    }
}