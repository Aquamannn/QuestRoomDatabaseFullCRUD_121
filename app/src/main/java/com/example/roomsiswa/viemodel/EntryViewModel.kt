package com.example.roomsiswa.viemodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.roomsiswa.room.Siswa

class EntryViewModel (private val repeatable: Repeatable): ViewModel(){
    var uiStateSiswa by mutableStateOf(UIStateSiswa())
    private set

    private fun validasiInput(uiState: DetailSiswa = uiStateSiswa.detailSiswa) :Boolean {
        return with(uiState){
            nama.isNotBlank() && alamat.isNotBlank() && telpon.isNotBlank()
        }
    }

    fun updateUiState(detailSiswa: DetailSiswa){
        uiStateSiswa =
            UIStateSiswa(detailSiswa = detailSiswa, isEntryValid = validasiInput(detailSiswa))
    }

    suspend fun saveSiswa(){
        if(validasiInput()){
            repositoriSiswa.insertSiswa(uiStateSiswa.detailSiswa.toSiwa())
        }
    }
}

data class UIStateSiswa(
    val detailSiswa:DetailSiswa = DetailSiswa(),
    val isEntryValid: Boolean = false
)

data class DetailSiswa(
    val id: Int = 0,
    val nama: string = "",
    val alamat: string = "",
    val telpon: string = "",
)

fun DetailSiswa.toSiswa(): Siswa = Siswa(
    id = id,
    nama = nama,
    alamat = alamat,
    telpon = telpon
)

fun Siswa.toUiStateSiswa(isEntryValid: Boolean = false): UIStateSiswa = UIStateSiswa(
    detailSiswa = this.toDetailSiswa(),
    isEntryValid = isEntryValid
)

fun Siswa.toDetailSiswa(): DetailSiswa = DetailSiswa(
    id = id,
    nama = nama,
    alamat = alamat,
    telpon = telpon
)



