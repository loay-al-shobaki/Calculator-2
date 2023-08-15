package com.example.calculator2

import androidx.compose.ui.graphics.Color
import com.example.calculator2.ui.theme.ButtonBlue
import com.example.calculator2.ui.theme.ButtonPink
import com.example.calculator2.ui.theme.ButtonYellow

sealed class ActionType(val symbol: String, val buttonColor: Color) {
    data class Number(val number: Int) : ActionType(number.toString(), ButtonBlue)
    data class Operator(val operatior: Operators) : ActionType(operatior.symbol, ButtonPink)

    object Calculate : ActionType("=", ButtonYellow)
    object Delete : ActionType("←", ButtonBlue)
    object Clear : ActionType("AC", ButtonPink)
    object Decimal : ActionType(".", ButtonBlue)
    object Percentage : ActionType("%", ButtonPink)

}


sealed class Operators(val symbol: String) {
    object Add : Operators("+")
    object Subtract : Operators("-")
    object Multiply : Operators("x")
    object Divid : Operators("÷")
    object Power : Operators("^")
}
