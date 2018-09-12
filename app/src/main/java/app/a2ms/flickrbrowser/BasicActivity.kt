package app.a2ms.flickrbrowser

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View

internal const val FLICKR_QUERY = "FLICKR_QUERY"
internal const val PHOTO_TRANSFER = "PHOTO_TRANSFER"

@SuppressLint("Registered")
open class BasicActivity : AppCompatActivity() {
    private val TAG = "BasicActivity"
    internal fun activateToolbar(enableHome: Boolean) {
        val toolbar = findViewById<View>(R.id.toolbar) as android.support.v7.widget.Toolbar
        Log.d(TAG, ".activateToolbar")
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(enableHome)
    }
}
