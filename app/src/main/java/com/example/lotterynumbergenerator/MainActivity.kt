package com.example.lotterynumbergenerator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.NumberPicker
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val numberPicker: NumberPicker by lazy {
        findViewById<NumberPicker>(R.id.numberPicker)
    }

    private val buttonAdd: Button by lazy {
        findViewById<Button>(R.id.buttonAdd)
    }

    private val buttonReset: Button by lazy {
        findViewById<Button>(R.id.buttonReset)
    }

    private val textView1: TextView by lazy {
        findViewById<TextView>(R.id.textView1)
    }

    private val textView2: TextView by lazy {
        findViewById<TextView>(R.id.textView2)
    }

    private val textView3: TextView by lazy {
        findViewById<TextView>(R.id.textView3)
    }

    private val textView4: TextView by lazy {
        findViewById<TextView>(R.id.textView4)
    }

    private val textView5: TextView by lazy {
        findViewById<TextView>(R.id.textView5)
    }

    private val textView6: TextView by lazy {
        findViewById<TextView>(R.id.textView6)
    }

    private val buttonExecution: Button by lazy {
        findViewById<Button>(R.id.buttonExecution)
    }

    private var executed = false

    private val pickNumberSet = hashSetOf<Int>()

    private val numberTextViewList: List<TextView> by lazy {
        listOf<TextView>(
                findViewById<TextView>(R.id.textView1),
                findViewById<TextView>(R.id.textView2),
                findViewById<TextView>(R.id.textView3),
                findViewById<TextView>(R.id.textView4),
                findViewById<TextView>(R.id.textView5),
                findViewById<TextView>(R.id.textView6)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        numberPicker.minValue = 1
        numberPicker.maxValue = 45

        buttonAdd.setOnClickListener(this)
        buttonReset.setOnClickListener(this)
        buttonExecution.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.buttonAdd -> addNumber()
            R.id.buttonReset -> resetNumbers()
            R.id.buttonExecution -> getRandomNumbers()
        }
    }

    private fun resetNumbers() {
        pickNumberSet.clear()
    }

    private fun addNumber() {
        if (executed) {
            Toast.makeText(this, "Please reset first and try again", Toast.LENGTH_SHORT).show()
            return
        }
        if (pickNumberSet.size > 5) {
            Toast.makeText(this, "You can select up to 5 numbers", Toast.LENGTH_SHORT).show()
            return
        }
        if (pickNumberSet.contains(numberPicker.value)) {
            Toast.makeText(this, "The number has already been selected", Toast.LENGTH_SHORT).show()
            return
        }

        val textView = numberTextViewList[pickNumberSet.size]
        textView.isVisible = true
        textView.text = numberPicker.value.toString()

        pickNumberSet.add(numberPicker.value)
    }

    private fun getRandomNumbers(): List<Int> {
        val numberList = mutableListOf<Int>().apply {
            for (i in 1..45) {
                this.add(i)
            }
        }

        numberList.shuffle()

        val resultList = numberList.subList(0, 6)

        return resultList.sorted()
    }


}