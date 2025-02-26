package com.example.pertemuan_1hanyarunrun.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.pertemuan_1hanyarunrun.data.AppDatabase
import com.example.pertemuan_1hanyarunrun.data.DataEntity
import com.example.pertemuan_1hanyarunrun.data.ProfileEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DataViewModel(application: Application) : AndroidViewModel(application) {
    private val database = AppDatabase.getDatabase(application)
    private val dataDao = database.dataDao()
    private val profileDao = database.profileDao()

    // LiveData untuk DataEntity
    val dataList: LiveData<List<DataEntity>> = dataDao.getAll()

    // LiveData untuk ProfileEntity
    val profileList: LiveData<List<ProfileEntity>> = profileDao.getAllProfiles()

    //Fungsi untuk Menambahkan DataEntity
    fun insertData(
        kodeProvinsi: String,
        namaProvinsi: String,
        kodeKabupatenKota: String,
        namaKabupatenKota: String,
        agen: String,
        jumlah: String,
        satuan: String,
        tahun: String
    ) {
        viewModelScope.launch {
            val totalValue = jumlah.toDoubleOrNull() ?: 0.0
            val tahunValue = tahun.toIntOrNull() ?: 0
            dataDao.insert(
                DataEntity(
                    kodeProvinsi = kodeProvinsi,
                    namaProvinsi = namaProvinsi,
                    kodeKabupatenKota = kodeKabupatenKota,
                    namaKabupatenKota = namaKabupatenKota,
                    agen = agen,
                    jumlah = totalValue,
                    satuan = satuan,
                    tahun = tahunValue
                )
            )
        }
    }

    // Fungsi untuk Memperbarui DataEntity
    fun updateData(data: DataEntity) {
        viewModelScope.launch {
            dataDao.update(data)
        }
    }

    //Fungsi untuk Menghapus DataEntity
    fun deleteData(data: DataEntity) {
        viewModelScope.launch {
            dataDao.delete(data)
        }
    }

    //Fungsi untuk Mengambil DataEntity berdasarkan ID
    suspend fun getDataById(id: Int): DataEntity? {
        return withContext(Dispatchers.IO) {
            dataDao.getById(id)
        }
    }

    //Fungsi untuk Menambahkan ProfileEntity
    fun insertProfile(nama: String, email: String, studentId: String) {
        viewModelScope.launch {
            profileDao.insert(ProfileEntity(name = nama, email = email, studentId = studentId))
        }
    }

    //Fungsi untuk Memperbarui ProfileEntity
    fun updateProfile(profile: ProfileEntity) {
        viewModelScope.launch {
            profileDao.update(profile)
        }
    }

    //Fungsi untuk Menghapus ProfileEntity
    fun deleteProfile(profile: ProfileEntity) {
        viewModelScope.launch {
            profileDao.delete(profile)
        }
    }

    //Fungsi untuk Mengambil ProfileEntity berdasarkan ID
    suspend fun getProfileById(id: Int): ProfileEntity? {
        return withContext(Dispatchers.IO) {
            profileDao.getProfileById(id)
        }
    }
}
