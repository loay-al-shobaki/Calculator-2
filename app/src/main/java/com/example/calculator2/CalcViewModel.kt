package com.example.calculator2

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlin.math.pow

class CalcViewModel : ViewModel() {

    private val state: MutableStateFlow<State> = MutableStateFlow(State())

    internal val viewState = state
        .map { state ->
            val num1 = state.num1.ifEmpty { "0" }
            val operator = state.operator?.symbol.orEmpty()
            val num2 = state.num2

            ViewState("$num1 $operator $num2")
        }

    internal data class ViewState(val result: String)
    private data class State(
        val num1: String = "",
        val operator: Operators? = null,
        val num2: String = ""
    )

    fun dispatch(action: ActionType) {
        when (action) {
            is ActionType.Number -> onNumberClicked(action.number)
            is ActionType.Calculate -> onCalculateClicked()
            is ActionType.Clear -> onClearClicked()
            is ActionType.Decimal -> onDecimalClicked()
            is ActionType.Delete -> onDeleteClicked()
            is ActionType.Operator -> onOperatorClicked(action.operatior)
            is ActionType.Percentage -> {}
        }
    }

    private fun onOperatorClicked(operator: Operators) {
        val currenState = state.value

        if (currenState.num1.isNotEmpty() && currenState.operator == null) {
            state.value = currenState.copy(operator = operator)
        }

    }

    private fun onDeleteClicked() {
        val currenState = state.value
        if (currenState.operator == null) {
            state.value = currenState.copy(num1 = currenState.num1.dropLast(1))
        } else if (currenState.num2.isEmpty()) {
            state.value = currenState.copy(operator = null)
        } else {
            state.value = currenState.copy(num2 = currenState.num2.dropLast(1))
        }
    }

    private fun onDecimalClicked() {
        val currentState = state.value
        if (currentState.operator == null &&
            currentState.num2.isEmpty() &&
            !currentState.num1.contains(".")
        ) {
            state.value = currentState.copy(num1 = currentState.num1 + ".")
        } else if (currentState.operator != null &&
            currentState.num2.isNotEmpty() &&
            !currentState.num2.contains(".")
        ) {
            state.value = currentState.copy(num2 = currentState.num2 + ".")
        }
    }

    private fun onClearClicked() {
        val currentState = state.value
        state.value = currentState.copy(num1 = "", num2 = "", operator = null)
    }

    private fun onCalculateClicked() {
        val currenState = state.value

        if (currenState.num1.isNotEmpty() && currenState.num2.isNotEmpty() && currenState.operator != null) {
            val num1 = currenState.num1.toDouble()
            val num2 = currenState.num2.toDouble()

            val result = when (currenState.operator) {
                Operators.Add -> num1 + num2
                Operators.Subtract -> num1 - num2
                Operators.Multiply -> num1 * num2
                Operators.Divid -> num1 / num2
                Operators.Power -> num1.pow(num2)
            }

            state.value = currenState.copy(num1 = result.toString(), num2 = "", operator = null)
        }
    }

    private fun onNumberClicked(number: Int) {
        val currentState = state.value

        if (currentState.operator == null) {

            state.value = currentState.copy(num1 = currentState.num1 + number)
        } else {
            state.value = currentState.copy(num2 = currentState.num2 + number)
        }
    }


}