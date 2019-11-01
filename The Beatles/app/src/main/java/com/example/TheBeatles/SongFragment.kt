package com.example.TheBeatles

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_songs.*


/**
 * A simple [Fragment] subclass.
 *
 */

private var titles = arrayOf("Jim's Schedule", "Mary's Schedule", "Jo's Schedule")
private var layoutManager: RecyclerView.LayoutManager? = null
private var adapter: RecyclerView.Adapter<RecylerAdapterCover.ViewHolder>? = null

class SongFragment : Fragment()
{
    companion object
    {
        private var instance : SongFragment? = null
        public fun getInstance() : SongFragment
        {
            return instance!!
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {

        instance = this

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_songs, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        var arguments = this.getArguments()
        var album = arguments?.getString("album")

        layoutManager = LinearLayoutManager(MainActivity.getInstance())
        recycler_view2.layoutManager = layoutManager
        adapter = RecylerAdapterCover(album!!) //this is the desired contact selected
        recycler_view2.adapter = adapter
    }


}
