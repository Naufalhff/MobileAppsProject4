package com.example.pertemuan_1hanyarunrun

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Berpindah ke MainActivity setelah splash screen tampil
        startActivity(Intent(this, MainActivity::class.java))
        finish() // Tutup SplashActivity agar tidak bisa dikembalikan dengan tombol back
    }
}