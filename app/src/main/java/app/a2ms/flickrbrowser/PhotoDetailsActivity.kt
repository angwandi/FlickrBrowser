package app.a2ms.flickrbrowser

import android.os.Bundle

class PhotoDetailsActivity : BasicActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_details)
        activateToolbar(true)
    }
}
