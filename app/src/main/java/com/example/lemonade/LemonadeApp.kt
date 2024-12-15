package com.example.lemonade

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LemonadeApp() {
    var currentStep by remember { mutableStateOf(1) }
    var squeezeCount by remember { mutableStateOf(0) }
    var maxSqueezeCount by remember { mutableStateOf((2..4).random()) }

    val imageResource = when (currentStep) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }

    val textResource = when (currentStep) {
        1 -> R.string.tap_lemon_tree
        2 -> R.string.keep_tapping_lemon
        3 -> R.string.tap_lemonade
        else -> R.string.tap_empty_glass
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Lemonade",
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                    ) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Yellow)
            )
        }
    ) { paddingValues ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues) // Respect the padding from the AppBar
        ) {
            Box(
                modifier = Modifier
                    .size(220.dp)
                    .padding(16.dp)
                    .background(color = Color(0xFFD1F2E3))
            ) {
                Image(
                    painter = painterResource(id = imageResource),
                    contentDescription = null,
                    modifier = Modifier
                        .size(200.dp)
                        .clickable {
                            when (currentStep) {
                                1 -> {
                                    currentStep = 2
                                    maxSqueezeCount = (2..4).random()
                                    squeezeCount = 0
                                }

                                2 -> {
                                    squeezeCount++
                                    if (squeezeCount >= maxSqueezeCount) {
                                        currentStep = 3
                                    }
                                }

                                3 -> currentStep = 4
                                4 -> currentStep = 1
                            }
                        }
                )
            }
            Text(
                text = stringResource(id = textResource),
                fontSize = 18.sp,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}
