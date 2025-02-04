package com.example.androidapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidAppTheme {
                MainScreen(
                    fullName = "Suraj Pokhrel",
                    studentId = "1541823",
                    onExplicitClick = {
                        // Explicit Intent
                        val explicitIntent = Intent(this, SecondActivity::class.java)
                        startActivity(explicitIntent)
                    },
                    onImplicitClick = {
                        // Implicit Intent with custom action
                        val implicitIntent = Intent("com.example.androidapp.ACTION_SHOW_SECOND").apply {
                            addCategory(Intent.CATEGORY_DEFAULT)
                        }
                        if (implicitIntent.resolveActivity(packageManager) != null) {
                            startActivity(implicitIntent)
                        }
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
    onImplicitClick: () -> Unit
) {
    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Full Name: $fullName", style = MaterialTheme.typography.headlineLarge, color=Color.Black)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Student ID: $studentId", style = MaterialTheme.typography.headlineLarge, color= Color.Blue)
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
        }
    }
}
