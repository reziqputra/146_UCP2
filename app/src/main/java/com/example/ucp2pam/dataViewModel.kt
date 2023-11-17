package com.example.ucp2pam

import androidx.lifecycle.ViewModel
import com.example.ucp2pam.data.dataUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class dataViewModel : ViewModel()  {
    private val _uiState = MutableStateFlow(dataUiState())
    val uiState: StateFlow<dataUiState> = _uiState.asStateFlow()


    fun setData(list: MutableList<String>){
        _uiState.update{ stateSaatIni -> stateSaatIni.copy(
            nama = list[0],
            nim = list[1],
            konsen = list[2],
            jdl = list[3],
            d1 = list[4],
            d2 = list[5]
        )
        }
    }
    fun setDosen1(pilihD1: String){
        _uiState.update { currentState -> currentState.copy(d1 = pilihD1) }
    }

    fun setDosen2(pilihD2: String){
        _uiState.update { currentState -> currentState.copy(d2 = pilihD2) }
    }
}