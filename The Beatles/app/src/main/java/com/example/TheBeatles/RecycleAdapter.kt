package com.example.TheBeatles

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>()
{
    private var albums = MainActivity.getInstance().readFile("albums.txt")
    private var covers = arrayListOf<Covers>()
    //private var albumName = ArrayList<String>();

    override fun getItemCount(): Int
    {
        return albums.size
    }

    //This creates a ViewHolder object based on card_layout for each cell
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_layout, parent, false)

        return ViewHolder(v)
    }

    //This initializes the viewHolder to whatever the data source specifies
    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
        for (i in 0 until albums.size){
            covers.add(Covers(albums[i][0], albums[i][2],albums[i][3]))
            //albumName.add(albums)
        }
        holder.itemTitle.text = covers[position].getCoverName()
        holder.itemDetail.text = covers[position].getDate()
        holder.itemImage.setImageResource(MainActivity.getInstance().resources.getIdentifier(covers[position].getPic(),"drawable",
            MainActivity.getInstance().packageName))
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        var itemImage: ImageView
        var itemTitle: TextView
        var itemDetail: TextView
        init
        {
            itemImage = itemView.findViewById(R.id.imageView)
            itemTitle = itemView.findViewById(R.id.name)
            itemDetail = itemView.findViewById(R.id.title)

            var handler = Handler()
            itemView.setOnClickListener(handler)

        }

        inner class Handler() : View.OnClickListener
        {
            override fun onClick(v: View?)
            {
                val itemPosition = getLayoutPosition()
                //Get the navigation controller
                var navController = Navigation.findNavController(BottomSecondFragment.getInstance().view!!)
                val bundle = Bundle()
                bundle.putString("album", albums[itemPosition][3] + ".txt")

                navController.navigate(R.id.secondToSongs,bundle)


                //var buffer = bundleOf("" to i)
                println("Submitted" + albums[itemPosition][3])
            }
        }
    }
}