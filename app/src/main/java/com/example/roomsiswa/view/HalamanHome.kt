package com.example.roomsiswa.view

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.roomsiswa.uicontroller.DestinasiNavigasi
import com.example.roomsiswa.viemodel.HomeViewModel
import com.example.roomsiswa.viemodel.PenyediaViewModel
import java.lang.reflect.Modifier

object DestinasiHome : DestinasiNavigasi {
    override  val route = "home"

    override  val titleRes = R.String.app_name
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navigateToItemEntry: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold (
        modifier =  modifier.nestedScroll(scrollBehavior.nestedScrollConnection),

        topBar = {
            SiswaTopAppBar(
                title = stringResource(DestinasiHome.titleRes),
                canNavigateBack = false,
                scrollBehavior = scrollBehavior
            )
        },
        floatingActionButton = {
            FloatingActionButton (
                onClick = navigateToItemEntry,
                shape = M
            )
        }
    ) {  }
}