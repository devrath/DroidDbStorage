package com.istudio.code.presentation.modules.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import com.istudio.code.presentation.modules.home.content.HomeContainer
import com.istudio.code.presentation.theme.MaterialAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            MaterialAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewModelStoreOwner = checkNotNull(LocalViewModelStoreOwner.current) {
                        "No ViewModelStoreOwner was provided via LocalViewModelStoreOwner"
                    }
                    CurrentScreen(viewModelStoreOwner)
                }
            }
        }
    }

    override fun onBackPressed() {
        return
        super.onBackPressed()
    }
}

@Composable
fun CurrentScreen(storeOwner: ViewModelStoreOwner) {
    HomeContainer(storeOwner)
}

@Preview(showBackground = true)
@Composable
private fun CurrentScreenPreview() {
    val viewModelStoreOwner = checkNotNull(LocalViewModelStoreOwner.current)
    MaterialAppTheme { CurrentScreen(storeOwner =viewModelStoreOwner) }
}