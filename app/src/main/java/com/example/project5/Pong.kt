package com.example.project5

import android.graphics.Rect
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
    private var gameStarted = false
    private var gameRect : Rect? = null
    private var gameOver = false
    private var score = 0

    constructor(newBallX : Float, newBallY : Float, newBallRadius : Float, newBallSpeedX : Int, newBallSpeedY : Int,
        newBarStartX : Float, newBarEndX : Float, newBarY : Float, newBarSpeed: Int, newBallAngel : Float, newGameRect : Rect?) {
        setBarLocation(newBarStartX, newBarEndX, newBarY)
        setBallLocation(newBallX, newBallY, newBallRadius)
        gameRect = newGameRect
    }

    fun setBarLocation(newBarStartX : Float, newBarEndX : Float, newBarY : Float) {
        barStartX = newBarStartX
        barEndX = newBarEndX
        barY = newBarY
    }

    fun resetBall() {
        setBallLocation((gameRect!!.width() / 2.0f), 50.0f, radius)
        ballSpeedX = 0
        ballSpeedY = 0
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
    }

    fun startGame(x: Float, width: Int) {
        gameStarted = true
        Log.w("Pong", "Game Started")
        if (x < width / 2) {
            ballSpeedX = -5
        } else if (x > width / 2){
            ballSpeedX = 5
        }
        ballSpeedY = 10
//        ballAngle = (Math.PI / 4).toFloat()
        updateBallPosition()
    }


    fun ballCollisionWall(){
        if(ballX <= 0 || ballX >= gameRect!!.width()) {
            ballSpeedX *= -1
        }
        if(ballY <= 0){
            ballSpeedY *= -1
        }
    }
    fun gameOverCheck(){
        if (ballY >= gameRect!!.height()) {
            gameOver = true
            gameStarted = false
            score = 0
        } else {
            gameOver = false
        }
    }

    fun ballCollisionBar() {
        if(ballX in barStartX..barEndX && ballY == barY) {
            ballSpeedY *= -1
            ballSpeedX *= -1
            score += 1
            Log.w("Pong", score.toString())
        }
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

    fun isGameOver() : Boolean {
        return gameOver
    }
}