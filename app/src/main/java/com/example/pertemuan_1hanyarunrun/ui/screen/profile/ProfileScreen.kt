package com.example.pertemuan_1hanyarunrun.ui.screen.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pertemuan_1hanyarunrun.data.ProfileEntity
import com.example.pertemuan_1hanyarunrun.viewmodel.DataViewModel

@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: DataViewModel,
    modifier: Modifier = Modifier
) {
    val profileList by viewModel.profileList.observeAsState(emptyList())
    val profile = profileList.firstOrNull()

    var isEditing by remember { mutableStateOf(false) }
    var studentName by remember { mutableStateOf(profile?.name ?: "Mahasiswa JTK") }
    var studentId by remember { mutableStateOf(profile?.studentId ?: "22222") }
    var studentEmail by remember { mutableStateOf(profile?.email ?: "mahasiswa@jtk.polban.ac.id") }
    var profileUploaded by remember { mutableStateOf(false) }

    // Perbarui state ketika `profileList` berubah
    LaunchedEffect(profile) {
        if (profile != null) {
            studentName = profile.name
            studentId = profile.studentId
            studentEmail = profile.email
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            // Profile Image Section
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .border(2.dp, MaterialTheme.colorScheme.primary, CircleShape)
                    .background(Color.LightGray, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Profile Picture",
                    tint = if (profileUploaded) MaterialTheme.colorScheme.onPrimary else Color.Gray,
                    modifier = Modifier.size(80.dp)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))

            if (isEditing) {
                OutlinedTextField(
                    value = studentName,
                    onValueChange = { studentName = it },
                    label = { Text("Nama Mahasiswa") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = studentId,
                    onValueChange = { studentId = it },
                    label = { Text("Student ID") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = studentEmail,
                    onValueChange = { studentEmail = it },
                    label = { Text("Email") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { profileUploaded = true },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Upload Foto")
                }
                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        if (profile != null) {
                            viewModel.updateProfile(
                                ProfileEntity(profile.id, studentName, studentEmail, studentId)
                            )
                        } else {
                            viewModel.insertProfile(studentName, studentEmail, studentId)
                        }
                        isEditing = false
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Simpan")
                }
            } else {
                Text(text = studentName, style = MaterialTheme.typography.headlineSmall)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "ID: $studentId", style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = studentEmail, style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { isEditing = true },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Edit Profil")
                }
            }
        }
    }
}

