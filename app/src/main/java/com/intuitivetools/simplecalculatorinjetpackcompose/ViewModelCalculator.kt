package com.intuitivetools.simplecalculatorinjetpackcompose

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ViewModelCalculator : ViewModel() {

    var firstNumber by mutableStateOf("")
    var secondNumber by mutableStateOf("")
    var qualOperacao by mutableStateOf("")
    var result by mutableStateOf("")
    private var calculateModel = CalculatorModel()
    private val numeric = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 0)
    private val operations = listOf("+", "-", "x", "÷")


    fun isNumeric(value: String): Boolean {
        return value.all { it.toString().toIntOrNull() in numeric }
    }

    fun isOperation(value: String): Boolean {
        return value in operations
    }


    fun realizaCalculo() {
        when (qualOperacao) {
            "+" -> {
                result = calculateModel.add(firstNumber.toInt(), secondNumber.toInt()).toString()
            }

            "-" -> {
                result = calculateModel.subtract(firstNumber.toInt(), secondNumber.toInt()).toString()
            }

            "x" -> {
                result = calculateModel.multiply(firstNumber.toInt(), secondNumber.toInt()).toString()
            }

            "÷" -> {
                result = calculateModel.divide(firstNumber.toDouble(), secondNumber.toDouble()).toString()
            }
        }
    }
}