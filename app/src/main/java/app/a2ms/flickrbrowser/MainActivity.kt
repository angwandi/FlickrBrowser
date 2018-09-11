package app.a2ms.flickrbrowser

import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity(), GetRawData.OnDownloadComplete, GetFlickrJsonData.OnDataAvailable {

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "OnCreate called")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        val url = createUri("https://api.flickr.com/services/feeds/photos_public.gne", "android,oreo", "en-us", true)
        val getRawData = GetRawData(this)
//        getRawData.setDownloadCompleteListener(this)
        getRawData.execute(url)
        Log.d(TAG, "onCreate ends")
    }

    private fun createUri(baseUrl: String, searchCriteria: String, lang: String, matchAll: Boolean): String {
        Log.d(TAG, "createUri starts")
        return Uri.parse(baseUrl)
                .buildUpon()
                .appendQueryParameter("tags", searchCriteria)
                .appendQueryParameter("tagmode", if (matchAll) "ALL" else "ANY")
                .appendQueryParameter("lang", lang)
                .appendQueryParameter("format", "json")
                .appendQueryParameter("nonjsoncallback", "1")
                .build().toString()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        Log.d(TAG, "onCreateOptionsMenu called")
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Log.d(TAG, "onOptionsItemSelected called")

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDownloadComplete(data: String, status: DownloadStatus) {
        if (status == DownloadStatus.OK) {
            val getFlickrJsonData = GetFlickrJsonData(this)
            getFlickrJsonData.execute(data)
            Log.d(TAG, "onDownloadComplete called")
        } else {
            //download failed
            Log.d(TAG, "onDownloadCompleted failed with status $status. Error message is: $data")
        }
    }

    override fun onDataAvailable(data: List<Photo>) {
        Log.d(TAG, "onDataAvailable called")
        Log.d(TAG, "onDataAvailable ends")

    }

    override fun onError(exception: Exception) {
        Log.e(TAG, "onError called with ${exception.message}")
    }
}
