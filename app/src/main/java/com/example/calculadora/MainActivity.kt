package com.example.calculadora

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.calculadora.databinding.ActivityMainBinding
import java.lang.StringBuilder

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private var currentNumber = StringBuilder()
    private var firstNumber = 0.0
    private var operator = ' '
    

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        /*setContentView(R.layout.activity_main)*/
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.numberOne.setOnClickListener(this)
        binding.numberTwo.setOnClickListener(this)
        binding.numberThree.setOnClickListener(this)
        binding.numberFour.setOnClickListener(this)
        binding.numberFive.setOnClickListener(this)
        binding.numberSix.setOnClickListener(this)
        binding.numberSeven.setOnClickListener(this)
        binding.numberEight.setOnClickListener(this)
        binding.numberNine.setOnClickListener(this)
        binding.numberZero.setOnClickListener(this)
        binding.sum.setOnClickListener(this)
        binding.subtract.setOnClickListener(this)
        binding.multiplication.setOnClickListener(this)
        binding.division.setOnClickListener(this)
        binding.equal.setOnClickListener(this)
        binding.deleteAll.setOnClickListener(this)
    }

    override fun onClick(view:View) {
        when (view.id) {
            R.id.numberOne -> appendNumber("1")
            R.id.numberTwo -> appendNumber("2")
            R.id.numberThree -> appendNumber("3")
            R.id.numberFour -> appendNumber("4")
            R.id.numberFive -> appendNumber("5")
            R.id.numberSix -> appendNumber("6")
            R.id.numberSeven -> appendNumber("7")
            R.id.numberEight -> appendNumber("8")
            R.id.numberNine -> appendNumber("9")
            R.id.numberZero -> appendNumber("0")
            R.id.sum -> setOperator('+')
            R.id.subtract -> setOperator('-')
            R.id.multiplication -> setOperator('*')
            R.id.division -> setOperator('/')
            R.id.equal -> calculate()
            R.id.deleteAll -> clear()
        }
    }
    private fun appendNumber(number: String) {
        currentNumber.append(number)
        updateDisplay()
    }

    private fun setOperator(op: Char) {
        if (currentNumber.isNotEmpty()) {
            firstNumber = currentNumber.toString().toDouble()
            operator = op
            currentNumber.clear()
        }
    }

    private fun calculate() {
        if (currentNumber.isNotEmpty()) {
            val secondNumber = currentNumber.toString().toDouble()
            val result = when (operator) {
                '+' -> firstNumber + secondNumber
                '-' -> firstNumber - secondNumber
                '*' -> firstNumber * secondNumber
                '/' -> firstNumber / secondNumber
                else -> 0.0
            }
            currentNumber.clear()
            currentNumber.append(result)
            updateDisplay()
        }
    }

    private fun clear() {
        currentNumber.clear()
        firstNumber = 0.0
        operator = ' '
        updateDisplay()
    }

    private fun updateDisplay() {
        binding.editTextResult.setText(currentNumber.toString())
    }

}

