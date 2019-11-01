package com.example.TheBeatles

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_bottom_main.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */

class BottomMainFragment : Fragment() {

    companion object {
        private var instance: BottomMainFragment? = null

        public fun getInstance(): BottomMainFragment {
            return instance!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        instance = this

        return inflater.inflate(R.layout.fragment_bottom_main, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var ss= Slideshow(3)
        ss.start()

    }
}

class Slideshow : Thread {
    var duration: Int = 0

    constructor(duration: Int) {
        this.duration = duration
    }

    public override fun run() {
        var imageArray = listOf("intro1", "abbeyroad", "beatlesforsale", "harddaysnight", "help", "letitbe", "magicalmysterytour", "pleasepleaseme", "revolver" , "rubber_soul", "sgt_pepper", "white", "with_the_beatles", "yellowsubmarine", "pastmastersvolume1", "pastmastersvolume2")

        var count = 0
        while (BottomMainFragment.getInstance().isResumed) {
            var handler = SlideShowHandler(imageArray[count])
            MainActivity.getInstance().runOnUiThread(handler)
            Thread.sleep(duration.toLong() * 1000) //Delay

            count++
            if (count >= imageArray.size) {
                count = 0
            }
        }
    }
}

class SlideShowHandler : Runnable {
    private var fn: String = ""

    constructor(fn: String) {
        this.fn = fn
    }

    override fun run() {
        var imageView = BottomMainFragment.getInstance().imageView
        var id = BottomMainFragment.getInstance().resources.getIdentifier(fn,"drawable", MainActivity.getInstance().packageName)
        imageView.setImageResource(id)
    }
}