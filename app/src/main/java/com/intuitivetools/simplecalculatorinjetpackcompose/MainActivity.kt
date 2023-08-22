package com.intuitivetools.simplecalculatorinjetpackcompose

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.intuitivetools.simplecalculatorinjetpackcompose.ui.theme.SimpleCalculatorInJetpackComposeTheme
import com.intuitivetools.simplecalculatorinjetpackcompose.ui.theme.buttonColor

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimpleCalculatorInJetpackComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Calculator(viewmodel = ViewModelCalculator())

                }
            }
        }
    }
}

@Composable
fun Calculator(viewmodel: ViewModelCalculator) {

    val buttons = listOf(
        "%", "CE", "C", "⌫",
        "1/x", "x²", "√", "÷",
        "7", "8", "9", "x",
        "4", "5", "6", "-",
        "1", "2", "3", "+",
        "+/-", "0", ",", "="
    )

    val operations = listOf("+", "-", "x", "÷")

    var expression by remember { mutableStateOf("") }
    var lastCharIsOperation by remember { mutableStateOf(false) }
    var lastChar by remember { mutableStateOf("") }
    var percentClicked by remember { mutableStateOf(false) }
    var isValueInverted by remember { mutableStateOf(false) }
    var resultWasClicked by remember { mutableStateOf(false) }



    Column(verticalArrangement = Arrangement.Bottom) {
        Text(
            text = expression,
            textAlign = TextAlign.End,
            fontSize = 24.sp,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(top = 20.dp, end = 20.dp)
                .height(60.dp)
        )
        Text(
            text = viewmodel.result,
            textAlign = TextAlign.End,
            fontSize = 24.sp,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .height(60.dp)
                .padding(end = 20.dp)
        )
        buttons.chunked(4).forEach { rowButtons ->
            Row {
                rowButtons.forEach { button ->
                    Button(
                        onClick = {
                            if (expression != "") {
                                lastChar = expression.last().toString()
                                lastCharIsOperation = operations.any { it == lastChar }
                            }
                            when (button) {
                                "=" -> {
                                    expression =
                                        viewmodel.doCalculation(operations, expression, button)

                                    viewmodel.firstNumber = ""
                                    viewmodel.secondNumber = ""
                                    resultWasClicked = true
                                }

                                "+", "-", "x", "÷" -> {
                                    if (!lastCharIsOperation) {
                                        expression += button
                                        viewmodel.qualOperacao = button
                                    }
                                }

                                "⌫" -> expression = expression.dropLast(1)

                                "C" -> {
                                    viewmodel.clear()
                                    expression = ""
                                    lastCharIsOperation = false
                                    lastChar = ""
                                    percentClicked = false
                                }

                                "CE" -> expression = viewmodel.calcelEntry(expression, operations)

                                "%" -> {
                                    expression = viewmodel.doPercentCalculate(
                                        percentClicked,
                                        expression,
                                        operations
                                    )
                                    percentClicked = true
                                }

                                "0", "1", "2", "3", "4",
                                "5", "6", "7", "8", "9" -> if (!resultWasClicked)expression += button else {
                                    viewmodel.clear()
                                    expression = ""
                                    lastCharIsOperation = false
                                    lastChar = ""
                                    percentClicked = false
                                    resultWasClicked = false
                                    expression += button
                                }

                                "√" -> expression = viewmodel.squareRoot(expression, operations)

                                "x²" -> expression = viewmodel.powerOfTwo(expression, operations)

                                "1/x" -> expression = viewmodel.oneDividedBy(expression, operations)

                                "+/-" -> {
                                    expression = viewmodel.invertSignal(
                                        expression,
                                        isValueInverted,
                                        operations
                                    )
                                    isValueInverted = true
                                }

                                "," -> expression += viewmodel.insertComma(expression, operations)

                            }
                            /*TODO (inicialmente vou implementar os calculos sendo feitos com
                               dois valores e sem validação. Implementar validações para o texto
                               que for senddo digitado)
                            */

                        },
                        colors = ButtonDefaults.buttonColors(buttonColor),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .width(100.dp)
                            .height(100.dp)
                            .padding(1.dp)
                            .weight(1f)
                    ) {
                        Text(text = button, color = Color.White)
                    }
                }
            }
        }
    }
}


//@Preview(name = "Light Mode")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "Dark Mode"
)
@Composable
fun CalculatorPreview() {
    SimpleCalculatorInJetpackComposeTheme {
        Calculator(viewmodel = ViewModelCalculator())
    }
}