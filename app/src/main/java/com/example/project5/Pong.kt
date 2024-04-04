package com.example.project5

import android.content.SharedPreferences
import android.graphics.Rect
import android.util.Log
class Pong(
    newBallX: Float,
    newBallY: Float,
    newBallRadius: Float,
    newBarStartX: Float,
    newBarEndX: Float,
    newBarY: Float,
    newGameRect: Rect?
) {
    private var barStartX = 0.0f
    private var barEndX = 0.0f
    private var barY = 0.0f
    private var ballX = 0.0f
    private var ballY = 0.0f
    private var radius = 0.0f
    private var ballSpeedX = 0
    private var ballSpeedY = 0
    private var gameStarted = false
    private var gameRect : Rect? = newGameRect
    private var gameOver = false
    private var score = 0
    private var bestScore: Int = 0

    init {
        setBarLocation(newBarStartX, newBarEndX, newBarY)
        setBallLocation(newBallX, newBallY, newBallRadius)
    }

    private fun setBarLocation(newBarStartX : Float, newBarEndX : Float, newBarY : Float) {
        barStartX = newBarStartX
        barEndX = newBarEndX
        barY = newBarY
    }

    fun resetBall() {
        setBallLocation((gameRect!!.width() / 2.0f), 50.0f, radius)
        ballSpeedX = 0
        ballSpeedY = 0
    }

    fun resetScore() {
        score = 0
    }

    private fun setBallLocation(newBallX: Float, newBallY: Float, newBallRadius: Float) {
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
        } else {
            gameOver = false
        }
    }

    fun ballCollisionBar() {
        val collisionThreshold = 10
        if (ballX in barStartX..barEndX && ballY + radius >= barY - collisionThreshold && ballY - radius <= barY + collisionThreshold) {
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

    fun updateBestScore(sharedPreferences: SharedPreferences) {
        val editor = sharedPreferences.edit()
        if (score > bestScore) {
            bestScore = score
            editor.putInt("BEST_SCORE", bestScore)
            editor.apply()
        }
    }

    fun getScore() : Int {
        return score
    }

    fun getBestScore(sharedPreferences: SharedPreferences): Int {
        bestScore = sharedPreferences.getInt("BEST_SCORE", 0)
        return bestScore
    }

    fun paddleHit(): Boolean {
        val collisionThreshold = 10
        return (ballX + radius >= barStartX - collisionThreshold &&
                ballX - radius <= barEndX + collisionThreshold &&
                ballY + radius >= barY - collisionThreshold &&
                ballY - radius <= barY + collisionThreshold)
    }
}