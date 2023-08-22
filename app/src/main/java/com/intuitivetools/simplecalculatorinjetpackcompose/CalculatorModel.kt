package com.intuitivetools.simplecalculatorinjetpackcompose

class CalculatorModel {
    fun add(firstNumber: Double, secondNumber: Double): Double {
        return firstNumber + secondNumber
    }

    fun subtract(firstNumber: Double, secondNumber: Double): Double {
        return firstNumber - secondNumber
    }

    fun multiply(firstNumber: Double, secondNumber: Double): Double {
        return firstNumber * secondNumber
    }

    fun divide(firstNumber: Double, secondNumber: Double): Double {
        if (secondNumber == 0.0) {
            throw ArithmeticException("Division by zero is not allowed")
        }
        return firstNumber / secondNumber
    }
}
