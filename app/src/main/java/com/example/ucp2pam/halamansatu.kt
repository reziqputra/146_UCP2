@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.ucp2pam

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2pam.data.dataSource.dosen1
import com.example.ucp2pam.data.dataSource.dosen2
import com.example.ucp2pam.data.dataUiState


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Halamansatu(
    dataViewModel: dataViewModel = viewModel(),
    onSubmitButtonClicked: (MutableList<String>) -> Unit) {

    var namaTxt by remember {
        mutableStateOf("")
    }
    var konsenTxt by remember {
        mutableStateOf("")
    }
    var nimTxt by remember {
        mutableStateOf("")
    }
    var jdlTxt by remember {
        mutableStateOf("")
    }
    var listData: MutableList<String> = mutableListOf(namaTxt,konsenTxt,nimTxt,jdlTxt,)
    val context = LocalContext.current



    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {

        OutlinedTextField(
            value = namaTxt,
            onValueChange = {
                namaTxt = it
            },
            label = { Text(text = stringResource(id = R.string.nama)) }
        )

        OutlinedTextField(
            value = nimTxt,
            onValueChange = {
                nimTxt = it
            },
            label = { Text(text = stringResource(id = R.string.nim)) }
        )

        OutlinedTextField(
            value = konsenTxt,
            onValueChange = {
                konsenTxt = it
            },
            label = { Text(text = stringResource(id = R.string.konsentrasi)) }
        )

        OutlinedTextField(
            value = jdlTxt,
            onValueChange = {
                jdlTxt = it
            },
            label = { Text(text = stringResource(id = R.string.judul)) }
        )

        SelectD1(
            option = dosen1.map { id -> context.resources.getString(id) },
            onSelectionChanged = { dataViewModel.setDosen1(it) })
        SelectD2(option = dosen2.map { ib -> context.resources.getString(ib) },
            onSelectionChanged = { dataViewModel.setDosen2(it) })

        Spacer(modifier = Modifier.height(15.dp))

        Button(onClick = { onSubmitButtonClicked(listData) }) {
            Text(text = stringResource(id = R.string.btn_submit))
        }

    }
}
@Composable
fun SelectD1(option: List<String>, onSelectionChanged: (String) -> Unit = {}) {
    var selectedValue by rememberSaveable { mutableStateOf("") }

    Column( verticalArrangement = Arrangement.SpaceEvenly,modifier = Modifier
        .padding(5.dp)
        .fillMaxWidth()) {
        Text(text = "Dosen Pembimbing 1 ")
        Row(
            modifier = Modifier.padding(3.dp))
        {
            option.forEach { item ->
                Row (modifier = Modifier.selectable(
                    selected = selectedValue == item,
                    onClick = {
                        selectedValue = item
                        onSelectionChanged(item)
                    }),verticalAlignment = Alignment.CenterVertically){

                    RadioButton(selected = selectedValue == item,
                        onClick = {
                            selectedValue = item
                            onSelectionChanged(item)
                        })
                    Text(item)
                }
            }
        }
    }
}

@Composable
fun SelectD2(option: List<String>, onSelectionChanged: (String) -> Unit = {}) {
    var selectedValue by rememberSaveable { mutableStateOf("") }

    Column( verticalArrangement = Arrangement.SpaceEvenly,modifier = Modifier
        .padding(5.dp)
        .fillMaxWidth()) {
        Text(text = "Dosen Pembimbing 2 ")
        Row(
            modifier = Modifier.padding(3.dp))
        {
            option.forEach { item ->
                Row (modifier = Modifier.selectable(
                    selected = selectedValue == item,
                    onClick = {
                        selectedValue = item
                        onSelectionChanged(item)
                    }),verticalAlignment = Alignment.CenterVertically){

                    RadioButton(selected = selectedValue == item,
                        onClick = {
                            selectedValue = item
                            onSelectionChanged(item)
                        })
                    Text(item)
                }
            }
        }
    }
}