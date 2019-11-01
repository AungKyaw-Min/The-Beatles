package com.example.TheBeatles


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView

class RecylerAdapterCover  : RecyclerView.Adapter<RecylerAdapterCover.ViewHolder>
{
    private var album =""
    private var songs = arrayOf(arrayOf(String()))

    constructor(album : String) : this()
    {
        this.album = album
        songs = MainActivity.getInstance().readFile(album)
    }
    constructor()
    {

    }

    public fun setAlbum(album : String)
    {
        this.album = album
    }
    override fun onBindViewHolder(holder: RecylerAdapterCover.ViewHolder, position: Int)
    {

        holder.itemSongName.text = songs[position].get(0)
        holder.itemSinger.text = songs[position].get(1)
    }
    override fun getItemCount(): Int
    {
        return songs.size
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecylerAdapterCover.ViewHolder
    {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.song_cardlayout, parent, false)
        return ViewHolder(v)
    }
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        var itemSongName: TextView
        var itemSinger: TextView
        init
        {
            itemSongName = itemView.findViewById(R.id.songName)
            itemSinger = itemView.findViewById(R.id.singer)

            var handler = Handler()
            itemView.setOnClickListener(handler)
        }

        inner class Handler() : View.OnClickListener
        {
            var songTitle = ""
            var artist = ""
            override fun onClick(p0: View?) {
                val position = getLayoutPosition()

                var navController = Navigation.findNavController(SongFragment.getInstance().view!!)
                val bundle = Bundle()

                var songDetail = arrayListOf<String>(songs[position].get(0), songs[position].get(1))
                bundle.putStringArrayList("song detail", songDetail)
                navController.navigate(R.id.songToVideo, bundle)
            }

        }
    }
}