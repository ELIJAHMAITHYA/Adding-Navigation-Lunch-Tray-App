/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.lunchtray

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lunchtray.datasource.DataSource
import com.example.lunchtray.model.OrderUiState
import com.example.lunchtray.ui.AccompanimentMenuScreen
import com.example.lunchtray.ui.CheckoutScreen
import com.example.lunchtray.ui.EntreeMenuScreen
import com.example.lunchtray.ui.OrderViewModel
import com.example.lunchtray.ui.SideDishMenuScreen
import com.example.lunchtray.ui.StartOrderScreen

// TODO: Screen enum
enum class LunchTrayScreen {
    Start,
    Entree_menu,
    Sidedish_menu,
    Accompaniment_menu,
    Checkout,
}

// TODO: AppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LunchTrayApp() {
    // TODO: Create Controller and initialization
val navController = rememberNavController()
    // Create ViewModel
    val viewModel: OrderViewModel = viewModel()

    Scaffold(
        topBar = {
            // TODO: AppBar
        }
    ) { innerPadding ->
        val uiState by viewModel.uiState.collectAsState()
        Text(text = "Hello World")
        // TODO: Navigation host
        NavHost(
            navController = navController,
            startDestination = LunchTrayScreen.Start.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = LunchTrayScreen.Start.name) {
                val context = LocalContext.current
                StartOrderScreen(onStartOrderButtonClicked = { })
            }
            composable(route = LunchTrayScreen.Entree_menu.name) {
                val context = LocalContext
                EntreeMenuScreen(
                    options = DataSource.entreeMenuItems,
                    onCancelButtonClicked = { /*TODO*/ },
                    onNextButtonClicked = { /*TODO*/ },
                    onSelectionChanged = {}
                )
            }
            composable(route = LunchTrayScreen.Accompaniment_menu.name) {
                val context = LocalContext.current
                AccompanimentMenuScreen(
                    options = DataSource.accompanimentMenuItems,
                    onCancelButtonClicked = { /*TODO*/ },
                    onNextButtonClicked = { /*TODO*/ },
                    onSelectionChanged ={}
                )
            }
            composable(route = LunchTrayScreen.Sidedish_menu.name) {
                val context = LocalContext.current
                SideDishMenuScreen(
                    options = DataSource.sideDishMenuItems,
                    onCancelButtonClicked = { /*TODO*/ },
                    onNextButtonClicked = { /*TODO*/ },
                    onSelectionChanged = {}
                )

            }
            composable(route = LunchTrayScreen.Checkout.name) {
                CheckoutScreen(
                    orderUiState = OrderUiState(),
                    onNextButtonClicked = { /*TODO*/ },
                    onCancelButtonClicked = { /*TODO*/ })

            }

        }
    }
}
