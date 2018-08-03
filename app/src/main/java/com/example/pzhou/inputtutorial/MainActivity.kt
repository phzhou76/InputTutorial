package com.example.pzhou.inputtutorial

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.*
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity()
{
    companion object
    {
        const val COORDINATES = "COORDINATES"
    }

    private var mIsBound = false
    private var mInputMessenger: Messenger? = null
    private val mServiceConnection: ServiceConnection = object : ServiceConnection
    {
        /**
         * This method is only called if the client unexpectedly disconnects from
         * the service.
         */
        override fun onServiceDisconnected(componentName: ComponentName?)
        {
            mIsBound = false
        }

        /**
         * This method is called if the client successfully connects to the service.
         *
         * @param binder The binder that contains a Messenger or a reference to the
         *      service.
         */
        override fun onServiceConnected(componentName: ComponentName?, binder: IBinder?)
        {
            binder?.let {
                mInputMessenger = Messenger(binder)
                mIsBound = true
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonSendCoordinates.setOnClickListener {
            /* Only send coordinates if the Activity is connected to the service. */
            if (mIsBound)
            {
                val message = Message.obtain()

                val coordinates = InputPoint(editTextXCoordinate.text.toString().toFloat(),
                        editTextYCoordinate.text.toString().toFloat())

                /* Place InputPoint object into a Bundle as a Parcelable object. */
                val coordinateBundle = Bundle()
                coordinateBundle.putParcelable(COORDINATES, coordinates)

                /* Have the Message hold the Bundle. */
                message.obj = coordinateBundle

                try
                {
                    mInputMessenger?.send(message)
                }
                catch (e: RemoteException)
                {
                    e.printStackTrace()
                }
            }
        }
    }

    override fun onStart()
    {
        super.onStart()

        val intent = Intent(this, InputProxyService::class.java)
        bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE)
    }

    override fun onStop()
    {
        super.onStop()

        if (mIsBound)
        {
            unbindService(mServiceConnection)
            mIsBound = false
        }
    }
}