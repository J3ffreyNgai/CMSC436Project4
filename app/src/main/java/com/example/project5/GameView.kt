package com.example.project5

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.View

class GameView(context: Context, private var width: Int, private var height: Int) : View(context) {
    private lateinit var paint : Paint
    private lateinit var pong : Pong
    private var currentScoreText: String? = null
    private var bestScoreText: String? = null

    init {
        paint = Paint()
        paint.color = Color.BLACK
        paint.isAntiAlias = true
        paint.strokeWidth = 20.0f
        val gameRect = Rect(0, 0, width, height)
        pong = Pong((width / 2.0f), 50.0f, 25.0f, (width / 2.0f) - (150.0f / 2.0f),
            (width / 2.0f) + (150.0f / 2.0f), height - 50.0f, gameRect)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawLine(pong.getBarStartX(), pong.getBarY(), pong.getBarEndX(), pong.getBarY(), paint)
        canvas.drawCircle(pong.getBallX(), pong.getBallY(), pong.getRadius().toFloat(), paint)

        currentScoreText?.let {
            val textPaint = Paint().apply {
                color = Color.BLACK
                textSize = 75.0f
                textAlign = Paint.Align.CENTER
            }
            canvas.drawText(it, (width / 2).toFloat(), (height / 2).toFloat() - 75, textPaint)
        }

        bestScoreText?.let {
            val textPaint = Paint().apply {
                color = Color.BLACK
                textSize = 75.0f
                textAlign = Paint.Align.CENTER
            }
            canvas.drawText(it, (width / 2).toFloat(), (height / 2).toFloat() + 75, textPaint)
        }
    }
    fun setScoreText(score: Int?) {
        currentScoreText = "Score: $score"
    }

    fun clearScoreText() {
        currentScoreText = null
    }

    fun setBestScoreText(score: Int?) {
        bestScoreText = "Best Score: $score"
    }

    fun clearBestScoreText() {
        bestScoreText = null
    }

    fun getPong( ) : Pong {
        return pong
    }

}