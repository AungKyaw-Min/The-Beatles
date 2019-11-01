package com.example.TheBeatles

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
//import com.google.android.youtube.player.YouTubeInitializationResult
//import com.google.android.youtube.player.YouTubePlayer
//import com.google.android.youtube.player.YouTubePlayerFragment
//import com.google.android.youtube.player.YouTubePlayerFragment.*
import kotlinx.android.synthetic.main.fragment_video.*

import org.json.JSONObject
import java.net.URL


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class VideoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_video, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        var detail = this.arguments?.getStringArrayList("song detail")
        var song = detail!!.get(0)
        song?.replace(" ", "+")
        var origSong = detail!!.get(0)
        println(origSong)


        //Set the artist
        var artist = "The Beatles"
        artist = artist.replace(" ","+")
        var origArtist = "The Beatles"
        //Encode search for YouTube
        val keywords = artist + "+" + song //+ "+" +
        val max = 50 //max num of results(restriction from JSON

        //Set the youtube search
        val string = "https://www.googleapis.com/youtube/v3/search?part=snippet&q=$keywords&order=viewCount&maxResults=$max&type=video&videoCategory=Music&key=AIzaSyAJiIwkPJA1F7Xmy_jZobS0KRYA2ystamE"

        //url, originalSong, original Artist as parameters
        var helper = Helper(string, origSong!!, origArtist)
        var thread = Thread(helper)
        thread.start()


    }
    //Data can't be read from URL within the mainThread
    //Helper thread will help mainthread to read URL
    inner class Helper : Runnable
    {
        private var url : String = ""
        private var song : String = ""
        private var artist : String = ""

        constructor(url : String, song : String, artist : String)
        {
            this.url = url
            this.song = song
            this.artist = artist
        }
        override public fun  run()
        {
            //A URL and readText is then performed within the run method of the helper thread:
            //read the url and use JSON to make it plain text -> hashmap
            val data = URL(url).readText()
            println(data)

            // This reads the data as text – it next needs to be converted to JSON:
            var json = JSONObject(data)
            //items is the key for hashmap
            var items = json.getJSONArray("items") // this is the "items: [ ] part

            var titles = ArrayList<String>()
            var videos = ArrayList<String>()

            for (i in 0 until items.length())
            {
                //Get the video item from 50 of them
                var videoObject = items.getJSONObject(i)

                //Extracth the id Hashmap
                var idDict = videoObject.getJSONObject("id")

                //Get the videoid using videoId key
                var videoId = idDict.getString("videoId")
                println(videoId)
                //Get the snippet Hashmap
                var snippetDict = videoObject.getJSONObject("snippet")
                //Get the title
                var title =  snippetDict.getString("title")
                println("title: " + title)
                //Add the titles to the lists
                titles.add(title)
                videos.add(videoId)
            }
            //select 14 for now instead of finding it with for loop
            var selected_video : String = ""
            var selected_title : String = ""

            for (i in 0 until titles.size)
            {
                if( titles.get(i).toLowerCase().contains("cover") && !titles.get(i).toLowerCase().contains("guiter")
                    && !titles.get(i).toLowerCase().contains("vocal") && !titles.get(i).toLowerCase().contains("tutorial")
                    && titles.get(i).contains("The Beatles"))
                {
                    selected_video = videos[i]
                    selected_title = titles[i]
                }
            }
            if(selected_video.isEmpty()){
                println("Video Not Found!")
                selected_video = videos[0]
                selected_title = titles[0]
            }

//            selected_video = videos[0]
//            selected_title = titles[0]

            var helper1 = UIThreadHelper(selected_video)
            MainActivity.getInstance().runOnUiThread(helper1)


        }
    }

    inner class UIThreadHelper : Runnable
    {
        private var video : String = ""

        constructor(video : String)
        {
            this.video = video
        }
        override  fun run()
        {
            val settings = web.getSettings()
            settings.setJavaScriptEnabled(true)
            settings.setDomStorageEnabled(true)
            settings.setMinimumFontSize(10)
            settings.setLoadWithOverviewMode(true)
            settings.setUseWideViewPort(true)
            settings.setBuiltInZoomControls(true)
            settings.setDisplayZoomControls(false)
            web.setVerticalScrollBarEnabled(false)
            settings.setDomStorageEnabled(true)
            web.setWebChromeClient(WebChromeClient())
            var str = "https://www.youtube.com/watch?v=" + video
            web.loadUrl(str)

        }
    }
}


