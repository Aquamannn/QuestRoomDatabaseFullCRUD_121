package com.example.roomsiswa.view

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.roomsiswa.R
import com.example.roomsiswa.uicontroller.DestinasiNavigasi
import com.example.roomsiswa.uicontroller.SiswaTopAppBar
import com.example.roomsiswa.viewmodel.EditViewModel
import com.example.roomsiswa.viewmodel.PenyediaViewModel
import kotlinx.coroutines.launch

object DestinasiEditSiswa : DestinasiNavigasi {
    override val route = "item_edit"
    // Perbaikan 1: titleRes diubah menjadi Int (referensi resource)
    override val titleRes = R.string.edit_siswa
    // Perbaikan 2: Nama konstanta diubah mengikuti konvensi (huruf kapital)
    const val ITEM_ID_ARG = "idSiswa"
    val routeWithArgs = "$route/{$ITEM_ID_ARG}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditSiswaScreen(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: EditViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    // Perbaikan 3: Menggunakan rememberCoroutineScope untuk coroutine
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            SiswaTopAppBar(
                // Perbaikan 4: Tidak perlu stringResource karena sudah Int
                title = stringResource(DestinasiEditSiswa.titleRes),
                canNavigateBack = true,
                navigateUp = onNavigateUp
            )
        },
        modifier = modifier
    ) { innerPadding ->
        // Ganti EntrySiswaBody dengan nama Composable yang benar jika berbeda
        // Misalnya, EditSiswaBody jika ada
        EntrySiswaBody(
            uiStateSiswa = viewModel.uiStateSiswa,
            onSiswaValueChange = viewModel::updateUiState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.updateSiswa()
                    navigateBack()
                }
            },
            modifier = Modifier.padding(paddingValues = innerPadding)
        )
    }
}