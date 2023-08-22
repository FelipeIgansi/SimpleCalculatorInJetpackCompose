# SimpleCalculatorInJetpackCompose# Android Calculator
Project created for my practice. <br/>
With a simple code was possible to create a default calculator in a little time and good readability.

```kotlin

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
                        onClick = {/*TODO this is just a preview. The code was in the file*/},
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
```

<b>Implemented functions:</b>

Function     |  Status
---------    |---------
Sum          | Done
Subtraction  | Done
Multiplication| Done
Division     | Done
Percent      | Done 
Invert Signal| Done
Clear        | Done
Cancel Entry | Done
