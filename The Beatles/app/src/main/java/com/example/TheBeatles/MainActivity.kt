package com.example.TheBeatles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedReader
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {
    companion object
    {
        private var instance : MainActivity? = null

        public fun getInstance() : MainActivity
        {
            return instance!!
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        instance = this


        val navController = Navigation.findNavController(this, R.id.fragment)
        bottomNavigationView.setupWithNavController(navController)
    }

    fun readFile(file: String): Array<Array<String>>{
        var f = this.getAssets().open(file)
        var buffer = BufferedReader(InputStreamReader(f))

        var lines1 = buffer.readLines()
        //Convert the List to an array of Lines
        var  arrayLines1 = lines1.toTypedArray()

        //Create a 2D Array
        var allData1 = arrayOf<Array<String>>()

        //Store the fields as a 2-D Array of Strings (Parse the , delimited fields)
        //Parse into fields
        for (i in 0..arrayLines1.size -1)
        {
            var array1 = arrayLines1[i].split("^")

            allData1 +=  array1.toTypedArray()
        }


        return allData1
    }
}
