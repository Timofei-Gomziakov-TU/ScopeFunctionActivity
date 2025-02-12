package edu.temple.scopefunctionactivity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import org.w3c.dom.Text
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // You can test your helper functions by  calling them from onCreate() and
        // printing their output to the Log, which is visible in the LogCat:
        // eg. Log.d("function output", getTestDataArray().toString())
        Log.d("getTestDataArray:", getTestDataArray().toString())
        val list = listOf<Double>(1.0,1.0,2.0,2.0,3.0)
        Log.d("averageLessThanMedian", averageLessThanMedian(list).toString())
        val intList = listOf<Int>(1,2,3,4,5)
        val storedView = getView(0, null, intList, this)
        Log.d("getView", getView(0, null, intList, this).toString())
        Log.d("getViewNotNull", getView(0, storedView, intList, this).toString())
    }

    // Return a list of random, sorted integers
    private fun getTestDataArray() : List<Int> = (MutableList(10){ Random.nextInt()}).apply {
        this.sort()
    }


    // Return true if average value in list is greater than median value, false otherwise
    private fun averageLessThanMedian(listOfNumbers: List<Double>): Boolean =
        if(listOfNumbers.sorted().size % 2 == 0)
            listOfNumbers.average() < (listOfNumbers.sorted()[listOfNumbers.sorted().size / 2] + listOfNumbers.sorted()[(listOfNumbers.sorted().size - 1) / 2]) / 2
            else listOfNumbers.average() < listOfNumbers.sorted()[listOfNumbers.sorted().size / 2]
        }


    // Create a view from an item in a collection, but recycle if possible (similar to an AdapterView's adapter)
    private fun getView(position: Int, recycledView: View?, collection: List<Int>, context: Context): View =
        if(recycledView != null) recycledView.apply{(this as TextView).text = collection[position].toString()}
        else TextView(context).apply{
            this.setPadding(5,10,10,0)
            this.textSize = 22f
        }