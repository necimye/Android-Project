package com.example.androidapp
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

class SecondActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidAppTheme {
                SecondScreen(
                    onMainActivityClick = {
                        // Return to the main activity (finish this activity)
                        finish()
                    }
                )
            }
        }
    }
}

@Composable
fun SecondScreen(onMainActivityClick: () -> Unit) {
    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Text(
                text = "Mobile Software Engineering Challenges:",
                style = MaterialTheme.typography.headlineLarge,
                color= Color.Blue
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "1. Software Security")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "2. UI Adjustment for different devices")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "3. Backward and forward compatibility")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "4. Rapid Technology changes")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "5. Different mobile OS")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "6. Ensuring better user experience")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "7. Time constraint to develop prototype")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "8. Performance Optimization")
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = onMainActivityClick,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Main Activity")
            }
        }
    }
}
