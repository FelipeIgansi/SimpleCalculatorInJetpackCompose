package com.intuitivetools.simplecalculatorinjetpackcompose

class CalculatorModel {
    fun add(firstNumber: Int, secondNumber: Int): Int {
        return firstNumber + secondNumber
    }

    fun subtract(firstNumber: Int, secondNumber: Int): Int {
        return firstNumber - secondNumber
    }

    fun multiply(firstNumber: Int, secondNumber: Int): Int {
        return firstNumber * secondNumber
    }

    fun divide(firstNumber: Double, secondNumber: Double): Double {
        if (secondNumber == 0.0) {
            throw ArithmeticException("Division by zero is not allowed")
        }
        return firstNumber / secondNumber
    }
}
