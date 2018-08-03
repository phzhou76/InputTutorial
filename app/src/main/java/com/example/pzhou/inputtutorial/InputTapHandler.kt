package com.example.pzhou.inputtutorial

import android.os.Bundle
import android.os.Handler
import android.os.Message

class InputTapHandler(inputProxyService: InputProxyService) : Handler()
{
    private val mInputProxyService: InputProxyService = inputProxyService

    override fun handleMessage(message: Message?)
    {
        message?.let { validMessage ->

            val messageBundle: Bundle? = validMessage.obj as? Bundle

            messageBundle?.let { validBundle ->

                /* Need to set class loader for the bundle before obtaining its Parcelable. */
                validBundle.classLoader = InputPoint::class.java.classLoader

                val inputPoint = validBundle.getParcelable<InputPoint>(MainActivity.COORDINATES)
                mInputProxyService.sendInputTap(inputPoint.mXCoordinate,
                        inputPoint.mYCoordinate)

            }
        }
    }
}