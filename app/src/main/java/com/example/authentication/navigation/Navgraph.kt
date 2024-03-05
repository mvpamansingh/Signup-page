package com.example.authentication.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.authentication.SignInScreen
import com.example.authentication.SignInViewModel
import com.example.authentication.SignupScreen
import com.example.authentication.SignupViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun navgraph(viewModel: SignInViewModel, isLoggedIn:String)
{
    val navController = rememberNavController()
    val onboardingFinished = remember { mutableStateOf(false) }
    //val uiState by viewModel.isLoggedIn.collectAsState()
    NavHost(navController = navController, startDestination = if(isLoggedIn=="main") "main" else "auth") {

        navigation(
            startDestination = "onboarding",
            route = "auth"
        ) {

            composable("onboarding") {

                OnboardingScreen1(onContinue = { navController.navigate("onboarding2") },isFinished = onboardingFinished.value)

//                if (onboardingFinished.value) {
//                    // User has finished onboarding
//                    LaunchedEffect(Unit) {
//                        navController.navigate("signin")
//                    }
//                }
            }

            composable("onboarding2") {
                OnboardingScreen2(onContinue = { navController.navigate("onboarding3") },isFinished = onboardingFinished.value)
            }

            composable("onboarding3") {
                OnboardingScreen3(onContinue = { onboardingFinished.value = true},isFinished = onboardingFinished.value )

                if (onboardingFinished.value) {
                    LaunchedEffect(Unit) {
                        Log.d("Onboarding", "Navigating to signin")
                        navController.navigate("signin")
                    }
                }
            }
            composable("signin") {
                //val viewModell = it.sharedViewModel<SignInViewModel>(navController)

                //val uiState by viewModel.isLoggedIn.collectAsState()
                SignInScreen(
                    viewModel = viewModel,
                    navigateToSignUp = { navController.navigate("signup") },
                    navController = navController
                )


//                    // Check isLoggedIn to determine navigation (remove hardcoded delay)
//                    if(uiState) {
//                        navController.navigate("main"){
//                            popUpTo("onboarding2")
//                        }
//                    } else {
//                        navController.navigate("signup")
//                    }
//                }

            }


            composable("signup") {
                val viewModelu = it.sharedViewModel<SignupViewModel>(navController)

              // val viewModelu = viewModel<SignupViewModel>()
                SignupScreen(viewModel = viewModelu) {
                    navController.navigate("signin")
                }
            }

        }
        navigation(
            startDestination = "mainscreen",
            route = "main"
        ) {
            composable("mainscreen") {
                //

                MainScreen(viewModel = viewModel, viewModelScope =viewModel.viewModelScope )
            }

        }


    }




}





@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.sharedViewModel(navController: NavController): T {
    val navGraphRoute = destination.parent?.route ?: return viewModel()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return viewModel(parentEntry)
}