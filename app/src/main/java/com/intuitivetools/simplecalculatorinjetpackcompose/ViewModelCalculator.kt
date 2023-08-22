package com.intuitivetools.simplecalculatorinjetpackcompose

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlin.math.sqrt

class ViewModelCalculator : ViewModel() {

    var firstNumber by mutableStateOf("")
    var secondNumber by mutableStateOf("")
    var qualOperacao by mutableStateOf("")
    var result by mutableStateOf("")
    private var calculateModel = CalculatorModel()

    fun clear() {
        firstNumber = ""
        secondNumber = ""
        qualOperacao = ""
        result = ""
    }



    fun squareRoot(
        expression: String,
        operations: List<String>
    ):String{
        if (expression.isNotEmpty()) {
            val parts =
                expression.split(Regex("[${operations.joinToString("") { Regex.escape(it) }}]"))
            if (parts.isNotEmpty()) {
                val lastPart = parts.last()
                if (lastPart.isNotEmpty()) {
                    val number = lastPart.toDouble()
                    val squareRoot = sqrt(number)
                    return expression.dropLast(lastPart.length) + squareRoot.toString()
                }
            }
        }
        return ""
    }


    fun oneDividedBy(
        expression: String,
        operations: List<String>
    ):String {
        if (expression.isNotEmpty()) {
            val parts =
                expression.split(Regex("[${operations.joinToString("") { Regex.escape(it) }}]"))
            if (parts.isNotEmpty()) {
                val lastPart = parts.last()
                if (lastPart.isNotEmpty()) {
                    val number = lastPart.toDouble()
                    val reciprocal = 1.0 / number
                    return expression.dropLast(lastPart.length) + reciprocal.toString()
                }
            }
        }
        return ""
    }


    fun invertSignal(
        expression: String,
        isValueInverted: Boolean,
        operations: List<String>
    ):String {
        if (expression.isNotEmpty()) {
            if (!isValueInverted) {
                val parts =
                    expression.split(Regex("[${operations.joinToString("") { Regex.escape(it) }}]"))
                if (parts.isNotEmpty()) {
                    val lastPart = parts.last()
                    if (lastPart.isNotEmpty()) {
                        val number = lastPart.toDouble()
                        val inverted = -number
                        return expression.dropLast(lastPart.length) + inverted.toString()
                    }
                }
            } else {
                return expression.drop(1) // Remove o sinal de menos para voltar ao valor positivo
            }
        }
        return ""
    }



    fun insertComma(
        expression: String,
        operations: List<String>
    ):String {
        val parts = expression.split(Regex("[${operations.joinToString("") { Regex.escape(it) }}]"))
        if (parts.isNotEmpty()) {
            val lastPart = parts.last()
            if (lastPart.isNotEmpty() && !lastPart.contains(',')) {
                return ","
            }
        }
        return ""
    }

    fun powerOfTwo(
        expression: String,
        operations: List<String>
    ):String {
        if (expression.isNotEmpty()) {
            val parts =
                expression.split(Regex("[${operations.joinToString("") { Regex.escape(it) }}]"))
            if (parts.isNotEmpty()) {
                val lastPart = parts.last()
                if (lastPart.isNotEmpty()) {
                    val number = lastPart.toDouble()
                    val squared = number * number
                    return expression.dropLast(lastPart.length) + squared.toString()
                }
            }
        }
        return ""
    }


    fun doCalculation(
        operations: List<String>,
        expression: String,
        button: String
    ):String {
        val regex = operations.joinToString("|") { Regex.escape(it) }
        val parts = expression.split(Regex(regex))

        firstNumber = parts[0]
        secondNumber = parts[1]

        realizaCalculo()
        return expression + button
    }


    fun doPercentCalculate(
        percentClicked: Boolean,
        expression: String,
        operation: List<String>,
    ): String {
        var expression1 = expression
        if (!percentClicked && expression1.isNotEmpty() && expression1.last().isDigit()) {
            val listValues = expression1.split(
                Regex(
                    "[${operation.joinToString("") { Regex.escape(it) }}]"
                )
            )
            if (listValues.size == 2) {
                val firstNumber = listValues[0].toDouble()
                val percent = listValues[1].toDouble()
                expression1 = expression1.dropLastWhile { it.isDigit() }
                val modifiedExpression = when (qualOperacao) {
                    "+", "-" -> ((firstNumber * (percent / 100)).toInt()).toString()
                    "x", "÷" -> "${(percent / 100)}"
                    else -> ""
                }
                return expression1 + modifiedExpression
            }
        }
        return ""
    }


    fun calcelEntry(
        expression: String,
        operations: List<String>
    ): String {
        val lastOperationIndex = expression.lastIndexOfAny(operations)
        return if (lastOperationIndex >= 0) expression.dropLast(lastOperationIndex)
        else ""
    }

    private fun realizaCalculo() {
        when (qualOperacao) {
            "+" -> {
                result =
                    calculateModel.add(firstNumber.toDouble(), secondNumber.toDouble()).toString()
            }

            "-" -> {
                result = calculateModel.subtract(firstNumber.toDouble(), secondNumber.toDouble())
                    .toString()
            }

            "x" -> {
                result = calculateModel.multiply(firstNumber.toDouble(), secondNumber.toDouble())
                    .toString()
            }

            "÷" -> {
                result = calculateModel.divide(firstNumber.toDouble(), secondNumber.toDouble())
                    .toString()
            }
        }
    }
}