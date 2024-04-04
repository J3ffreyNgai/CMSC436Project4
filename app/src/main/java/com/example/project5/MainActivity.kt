package com.example.project5

import android.content.SharedPreferences
import android.media.SoundPool
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import java.util.Timer

class MainActivity : AppCompatActivity() {
    private lateinit var gameView: GameView
    private lateinit var detector : GestureDetector
    private lateinit var pong : Pong
    private lateinit var sharedPreferences: SharedPreferences

    private lateinit var pool : SoundPool
    private var paddleHitId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val width : Int = resources.displayMetrics.widthPixels
        val height : Int = resources.displayMetrics.heightPixels

        val statusBarHeightId : Int =
            resources.getIdentifier("status_bar_height","dimen", "android")

        val statusBarHeight : Int = resources.getDimensionPixelSize(statusBarHeightId)

        gameView = GameView(this, width, height - statusBarHeight)
        setContentView(gameView)
        pong = gameView.getPong()

        sharedPreferences = getSharedPreferences("PONG_PREFS", MODE_PRIVATE)

        val th : TouchHandler = TouchHandler()
        detector = GestureDetector( this, th )
        detector.setOnDoubleTapListener( th )

        val gameTimer : Timer = Timer( )
        val task : GameTimerTask = GameTimerTask( this )
        gameTimer.schedule( task, 0L, 10L )

        var builder : SoundPool.Builder = SoundPool.Builder( )
        pool = builder.build()
        paddleHitId = pool.load( this, R.raw.hit, 1 )
    }

    fun updateView( ) {
        gameView.postInvalidate()
    }
    fun updateBar(e : MotionEvent) {
        val x : Float = e.x
        pong.updateBarPosition(x)
    }

    fun updateBall() {
        pong.updateBallPosition()
        if(pong.paddleHit()) {
            playSound(paddleHitId)
        }
    }

    fun checkCollision() {
        pong.ballCollisionWall()
        pong.ballCollisionBar()
    }

    fun checkGameOver() {
        pong.gameOverCheck()
        if ( pong.isGameOver() ) {
            gameView.setScoreText(pong.getScore())
            gameView.setBestScoreText(pong.getBestScore(sharedPreferences))
        }
    }

    fun gameReset() {
        pong.gameOverCheck()
        if(pong.isGameOver()) {
            Log.w("main", "Game Over")
            pong.updateBestScore(sharedPreferences)
            gameView.clearScoreText()
            gameView.clearBestScoreText()
            pong.resetBall()
            pong.resetScore()
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if( event != null )
            detector.onTouchEvent( event )
        return super.onTouchEvent(event)
    }

    fun playSound( soundId : Int ) {
        pool.play( soundId, 1.0f, 1.0f, 0, 0, 1.0f )
    }

    inner class TouchHandler : GestureDetector.SimpleOnGestureListener() {
        override fun onScroll(
            e1: MotionEvent?,
            e2: MotionEvent,
            distanceX: Float,
            distanceY: Float
        ): Boolean {
            updateBar(e2)
            return super.onScroll(e1, e2, distanceX, distanceY)
        }

        override fun onSingleTapConfirmed(e: MotionEvent): Boolean {
//            Log.w("Main", "Single Tap")
            if ( ! pong.isGameStarted() ) {
                pong.startGame(e.x, gameView.width)
                val bestScore = pong.getBestScore(sharedPreferences)
                Log.w("Pong", "Best Score: $bestScore")
            }

            if ( pong.isGameOver() ) {
                gameReset()
            }
            return super.onSingleTapConfirmed(e)
        }
    }

}