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

    fun clear(){
        firstNumber = ""
        secondNumber = ""
        qualOperacao = ""
        result = ""
    }


    fun realizaCalculo() {
        when (qualOperacao) {
            "+" -> {
                result = calculateModel.add(firstNumber.toDouble(), secondNumber.toDouble()).toString()
            }

            "-" -> {
                result = calculateModel.subtract(firstNumber.toDouble(), secondNumber.toDouble()).toString()
            }

            "x" -> {
                result = calculateModel.multiply(firstNumber.toDouble(), secondNumber.toDouble()).toString()
            }

            "÷" -> {
                result = calculateModel.divide(firstNumber.toDouble(), secondNumber.toDouble()).toString()
            }
        }
    }
}