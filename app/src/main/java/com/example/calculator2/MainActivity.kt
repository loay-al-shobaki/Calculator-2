package com.example.calculator2

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

import com.example.calculator2.ui.CalcButtonComponent
import com.example.calculator2.ui.InputDisplayComponent
import com.example.calculator2.ui.theme.Calculator2Theme
import com.example.calculator2.ui.theme.spacing

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Calculator2Theme {
                val viewModel = viewModel<CalcViewModel>()
                val state =
                    viewModel.viewState.collectAsState(initial = CalcViewModel.ViewState("0")).value
                CalcScreen(state){
                    viewModel.dispatch(it)
                }

            }
        }
    }
}

@Composable
private fun CalcScreen(state: CalcViewModel.ViewState, dispatcher:(ActionType)->Unit) {
    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .padding(horizontal = MaterialTheme.spacing.lg, vertical = MaterialTheme.spacing.sm)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center)
            ) {
                InputDisplayComponent(state)
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.lg))
                CalcButtonGridLayouy(dispatcher)
            }
        }

    }
}

@Composable
fun CalcButtonGridLayouy(dispatcher: (ActionType) -> Unit) {
    val buttons = listOf(
        ActionType.Clear,
        ActionType.Operator(Operators.Power),
        ActionType.Percentage,
        ActionType.Operator(Operators.Divid),
        ActionType.Number(7),
        ActionType.Number(8),
        ActionType.Number(9),
        ActionType.Operator(Operators.Multiply),
        ActionType.Number(4),
        ActionType.Number(5),
        ActionType.Number(6),
        ActionType.Operator(Operators.Subtract),
        ActionType.Number(3),
        ActionType.Number(2),
        ActionType.Number(1),
        ActionType.Operator(Operators.Add),
        ActionType.Number(0),
        ActionType.Decimal,
        ActionType.Delete,
        ActionType.Calculate
    )
    Log.d("loay", "CalcButtonGridLayouy: ${ActionType.Operator(Operators.Divid).symbol} + ")
    Log.d("loay", "CalcButtonGridLayouy: ${ActionType.Number(4).number} + ")
    LazyVerticalGrid(columns = GridCells.Fixed(4),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.st),
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.st),
        content = {
            items(buttons) {
                CalcButtonComponent(
                    color = it.buttonColor,
                    symbol = it.symbol,
                    modifier = Modifier.aspectRatio(1f)

                ) {
                    dispatcher(it)
                }

            }

        })
}

