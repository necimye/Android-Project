package com.example.androidapp

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.androidapp.ui.theme.AndroidAppTheme

class MainActivity : ComponentActivity() {

    // Permission launcher for requesting MSE412 permission
    private val permissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            // Permission granted, start SecondActivity
            val explicitIntent = Intent(this, SecondActivity::class.java)
            startActivity(explicitIntent)
        } else {
            // Permission denied, show a message
            Toast.makeText(this, "Permission denied to access SecondActivity", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidAppTheme {
                MainScreen(
                    fullName = "Suraj Pokhrel",
                    studentId = "1541823",
                    onExplicitClick = {
                        // Check and request permission before starting SecondActivity
                        if (checkSelfPermission("com.example.androidapp.MSE412") == PackageManager.PERMISSION_GRANTED) {
                            // Permission already granted
                            val explicitIntent = Intent(this, SecondActivity::class.java)
                            startActivity(explicitIntent)
                        } else {
                            // Request permission
                            permissionLauncher.launch("com.example.androidapp.MSE412")
                        }
                    },
                    onImplicitClick = {
                        // Check and request permission for implicit intent
                        if (checkSelfPermission("com.example.androidapp.MSE412") == PackageManager.PERMISSION_GRANTED) {
                            val implicitIntent = Intent("com.example.androidapp.ACTION_SHOW_SECOND").apply {
                                addCategory(Intent.CATEGORY_DEFAULT)
                            }
                            if (implicitIntent.resolveActivity(packageManager) != null) {
                                startActivity(implicitIntent)
                            }
                        } else {
                            permissionLauncher.launch("com.example.androidapp.MSE412")
                        }
                    },
                    onViewImageClick = {
                        // Explicit Intent to launch ThirdActivity (no permission needed)
                        val viewImageIntent = Intent(this, ThirdActivity::class.java)
                        startActivity(viewImageIntent)
                    }
                )
            }
        }
    }
}

@Composable
fun MainScreen(
    fullName: String,
    studentId: String,
    onExplicitClick: () -> Unit,
    onImplicitClick: () -> Unit,
    onViewImageClick: () -> Unit
) {
    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Full Name: $fullName",
                style = MaterialTheme.typography.headlineLarge,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Student ID: $studentId",
                style = MaterialTheme.typography.headlineLarge,
                color = Color.Blue
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = onExplicitClick,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Start Activity Explicitly")
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = onImplicitClick,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Start Activity Implicitly")
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = onViewImageClick,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "View Image Activity")
            }
        }
    }
}