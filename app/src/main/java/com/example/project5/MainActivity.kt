package com.example.project5

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var width : Int = resources.displayMetrics.widthPixels
        var height : Int = resources.displayMetrics.heightPixels

        var statusBarHeightId : Int =
            resources.getIdentifier("status_bar_height","dimen", "android")

        var statusBarHeight : Int = resources.getDimensionPixelSize(statusBarHeightId)

        gameView = GameView(this, width, height - statusBarHeight)
        setContentView(gameView)
        pong = gameView.getPong()

        var th : TouchHandler = TouchHandler()
        detector = GestureDetector( this, th )
        detector.setOnDoubleTapListener( th )

        var gameTimer : Timer = Timer( )
        var task : GameTimerTask = GameTimerTask( this )
        gameTimer.schedule( task, 0L, 100L )
    }

    fun updateView( ) {
        gameView.postInvalidate()
    }
    fun updateBar(e : MotionEvent) {
        var x : Float = e.x
        pong.updateBarPosition(x)
    }

    fun updateBall() {
        pong.updateBallPosition()
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if( event != null )
            detector.onTouchEvent( event )
        return super.onTouchEvent(event)
    }

    inner class TouchHandler : GestureDetector.SimpleOnGestureListener() {
        override fun onScroll(
            e1: MotionEvent?,
            e2: MotionEvent,
            distanceX: Float,
            distanceY: Float
        ): Boolean {
            Log.w("Main" , "Scroll")
            updateBar(e2)
            return super.onScroll(e1, e2, distanceX, distanceY)
        }

        override fun onSingleTapConfirmed(e: MotionEvent): Boolean {
            Log.w("Main", "Single Tap")

            if ( ! pong.isGameStarted() ) {
                pong.startGame(e.x, gameView.width)
            }
            return super.onSingleTapConfirmed(e)
        }
    }

}