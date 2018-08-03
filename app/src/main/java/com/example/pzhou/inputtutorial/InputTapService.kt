package com.example.pzhou.inputtutorial

import android.accessibilityservice.AccessibilityService
import android.content.Intent
import android.view.accessibility.AccessibilityEvent

class InputTapService : AccessibilityService()
{
    companion object
    {
        private var SHARED_INSTANCE: InputTapService? = null

        fun getSharedInstance(): InputTapService?
        {
            return SHARED_INSTANCE
        }
    }

    /**
     * This method is called when the Android system binds to the Accessibility
     * Service.
     */
    override fun onServiceConnected()
    {
        super.onServiceConnected()
        SHARED_INSTANCE = this
    }

    /**
     * This method is called when Android unbinds from the Accessibility Service.
     *
     * @return True if onRebind(Intent) should be called when new clients bind to
     *      the Service.
     */
    override fun onUnbind(intent: Intent?): Boolean
    {
        SHARED_INSTANCE = null
        return super.onUnbind(intent)
    }

    /**
     * This method is called when the system wants to interrupt the feedback the
     * Accessibility Service is providing. Typically, this method will be called
     * in response to a user action, like moving focus to a different control.
     */
    override fun onInterrupt()
    {
        TODO("not implemented")
    }

    /**
     * This method is called by the system when it detects an event that matches
     * the event(s) specified by the Accessibility Service. For example, if this
     * Accessibility Service specifies that button clicks are to be captured, any
     * button click events that occur will cause the system to call this method.
     *
     * @param accessibilityEvent The event that occurred.
     */
    override fun onAccessibilityEvent(accessibilityEvent: AccessibilityEvent?)
    {
        TODO("not implemented")
    }
}