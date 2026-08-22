package com.datapesa.app

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFF03DAC5) // design_default_color_secondary approximation
                ) {
                    MainScreen(
                        onNavigateToDashboard = {
                            startActivity(Intent(this, Dashboard::class.java))
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun MainScreen(onNavigateToDashboard: () -> Unit) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var statusText by remember { mutableStateOf("Waiting for input...") }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            LeftSidebarContent()
        },
        gesturesEnabled = true
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = onNavigateToDashboard,
                modifier = Modifier.height(72.dp)
            ) {
                Text("DASHBOARD", fontSize = 32.sp)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    scope.launch {
                        drawerState.open()
                    }
                }
            ) {
                Text("OPEN SIDEBAR")
            }

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Hello World!",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Text(
                text = "Welcome to Skylix",
                fontSize = 18.sp,
                color = Color.DarkGray
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(onClick = { statusText = "Button clicked!" }) {
                Text("CLICK ME")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = statusText,
                fontSize = 14.sp
            )
        }
    }
}
