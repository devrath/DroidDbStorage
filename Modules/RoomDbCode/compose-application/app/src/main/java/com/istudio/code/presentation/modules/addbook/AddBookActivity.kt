package com.istudio.code.presentation.modules.addbook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.istudio.code.presentation.modules.addbook.content.AddBookContainer
import com.istudio.code.presentation.theme.MaterialAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddBookActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CurrentScreen(){
                        // Close Screen
                        finish()
                    }
                }
            }
        }
    }
}

@Composable
private fun CurrentScreen(
    modifier: Modifier = Modifier,backPress: () -> Unit
) {
    AddBookContainer(onBackPress = backPress)
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MaterialAppTheme {
        CurrentScreen(){}
    }
}