package com.example.project5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent

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
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if( event != null )
            detector.onTouchEvent( event )
        return super.onTouchEvent(event)
    }

    inner class TouchHandler : GestureDetector.SimpleOnGestureListener( ) {

    }

}