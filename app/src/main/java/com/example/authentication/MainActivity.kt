package com.example.authentication

import android.animation.ObjectAnimator
import android.app.Application
import android.os.Bundle
import android.view.View
import android.view.animation.OvershootInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.navArgument
import com.example.authentication.navigation.AppNavigation
import com.example.authentication.navigation.ContactScreen
import com.example.authentication.navigation.navgraph

import com.example.authentication.ui.theme.AuthenticationTheme

class MainActivity : ComponentActivity() {

    private val splashviewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().apply {
                setKeepOnScreenCondition{

                    !splashviewModel.isReady.value
                }
            setOnExitAnimationListener{screen->
                val zoomX= ObjectAnimator.ofFloat(
                    screen.view,
                    View.SCALE_X,
                    0.4f,
                    0.0f
                )
                zoomX.interpolator = OvershootInterpolator()
                zoomX.duration = 500L
                zoomX.doOnEnd { screen.remove() }

                val zoomY = ObjectAnimator.ofFloat(
                    screen.iconView,
                    View.SCALE_Y,
                    0.4f,
                    0.0f
                )
                zoomY.interpolator = OvershootInterpolator()
                zoomY.duration = 500L
                zoomY.doOnEnd { screen.remove() }

                zoomX.start()
                zoomY.start()
            }
        }
        setContent {
            AuthenticationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //UserProfileScreen()
                       //ContactScreen()

//                    val application = LocalContext.current.applicationContext as Application
                  //  val signInViewModel = viewModel<SignInViewModel>(factory = ViewModelProvider.AndroidViewModelFactory(application))
//                    AppNavigation(viewModel =signInViewModel )
                  //  navgraph(viewModel = signInViewModel, canlogin = signInViewModel.isLoggedIn.value)

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