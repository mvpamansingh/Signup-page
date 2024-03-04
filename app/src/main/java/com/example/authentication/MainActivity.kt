package com.example.authentication

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.authentication.navigation.AppNavigation
import com.example.authentication.navigation.ContactScreen
import com.example.authentication.navigation.UserProfileScreen
import com.example.authentication.ui.theme.AuthenticationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AuthenticationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //UserProfileScreen()
                       ContactScreen()

//                    val application = LocalContext.current.applicationContext as Application
//                    val signInViewModel = viewModel<SignInViewModel>(factory = ViewModelProvider.AndroidViewModelFactory(application))
//                    AppNavigation(viewModel =signInViewModel )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AuthenticationTheme {
        Greeting("Android")
    }
}