package com.example.pzhou.inputtutorial

import android.accessibilityservice.GestureDescription
import android.app.Service
import android.content.Intent
import android.graphics.Path
import android.os.IBinder
import android.os.Messenger

class InputProxyService : Service()
{
    /* The app will use this Messenger to send input commands to the proxy service. */
    private val mMessenger: Messenger = Messenger(InputTapHandler(this))

    override fun onBind(intent: Intent?): IBinder
    {
        return mMessenger.binder
    }

    fun sendInputTap(xCoordinate: Float, yCoordinate: Float)
    {
        val gestureDescriptionBuilder = GestureDescription.Builder()

        val path = Path()
        path.moveTo(xCoordinate, yCoordinate)

        val gestureStrokeDescription = GestureDescription.StrokeDescription(path,
                1, 1)
        gestureDescriptionBuilder.addStroke(gestureStrokeDescription)

        val gestureDescription = gestureDescriptionBuilder.build()

        /* Delay for testing purposes. */
        Thread.sleep(5000)

        InputTapService.getSharedInstance()?.dispatchGesture(gestureDescription,
                null, null)
    }
}