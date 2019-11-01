package com.example.TheBeatles


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_bottom_second.*


/**
 * A simple [Fragment] subclass.
 *
 */
class BottomSecondFragment : Fragment()
{
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>? = null
    companion object
    {
        private var instance : BottomSecondFragment? = null
        public fun getInstance() : BottomSecondFragment
        {
            return instance!!
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bottom_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)
        instance = this
        layoutManager = LinearLayoutManager(MainActivity.getInstance())
        recycler_view.layoutManager = layoutManager
        adapter = RecyclerAdapter()
        recycler_view.adapter = adapter

    }

}
