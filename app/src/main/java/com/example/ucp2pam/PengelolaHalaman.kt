@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.ucp2pam

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

enum class PengelolaHalaman {
    Home,
    Rasa,
    form,
    Summary,

}

@Composable
fun EsJumboApp(
    viewModel: dataViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    navController: NavHostController = rememberNavController()
) {
    Scaffold() { innerPadding ->
        val uiState by viewModel.uiState.collectAsState()
        NavHost(
            navController = navController,
            startDestination = PengelolaHalaman.Home.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = PengelolaHalaman.Home.name) {
                HalamanHome(
                    onNextButtonClicked = { navController.navigate(PengelolaHalaman.form.name) }
                )
            }
            composable(route = PengelolaHalaman.form.name){
                Halamansatu(onSubmitButtonClicked = {
                    viewModel.setData(it)
                    navController.navigate(PengelolaHalaman.Rasa.name)
                })
            }
            composable(route = PengelolaHalaman.Rasa.name) {
                val context = LocalContext.current
                HalamanSatu(
                    pilihanRasa = flavors.map { id -> context.resources.getString(id) },
                    onSelectionChanged = { viewModel.setRasa(it) },
                    onConfirmButtonClicked = { viewModel.setJumlah(it) },
                    onNextButtonClicked = { navController.navigate(PengelolaHalaman.Summary.name) },
                    onCancelButtonClicked = {
                        cancelOrderAndNavigateToHome(
                            viewModel,
                            navController
                        )
                    }
                )
            }
            composable(route = PengelolaHalaman.Summary.name){
                HalamanDua(orderUIState = uiState, onCancelButtonClicked = { cancelOrderAndNavigateToRasa(navController)  })
            }
        }
    }
}