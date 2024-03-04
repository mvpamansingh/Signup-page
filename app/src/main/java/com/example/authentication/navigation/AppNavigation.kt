package com.example.authentication.navigation

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.authentication.SignInScreen
import com.example.authentication.SignInViewModel
import com.example.authentication.SignupScreen
import com.example.authentication.SignupViewModel




@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun AppNavigation(viewModel: SignInViewModel) {
    val navController = rememberNavController()
    val onboardingFinished = remember { mutableStateOf(false) }
    NavHost(navController = navController, startDestination = "onboarding") {
        // Onboarding Flow

        composable("onboarding") {
            // Track progress

            OnboardingScreen1(onContinue = { navController.navigate("onboarding2") },isFinished = onboardingFinished.value)

            if (onboardingFinished.value) {
                // User has finished onboarding
                LaunchedEffect(Unit) {
                    navController.navigate("signing") // Or your first auth screen
                }
            }
        }

        composable("onboarding2") {
            OnboardingScreen2(onContinue = { navController.navigate("onboarding3") },isFinished = onboardingFinished.value)
        }

        composable("onboarding3") {
            OnboardingScreen3(onContinue = { onboardingFinished.value = true},isFinished = onboardingFinished.value )

            if (onboardingFinished.value) {
                LaunchedEffect(Unit) {
                    Log.d("Onboarding", "Navigating to signin")
                    navController.navigate("signing")
                }
            }
        }

        // Auth Flow
        composable("signing") {
           // val viewModel = viewModel<SignInViewModel>(factory = ViewModelProvider.AndroidViewModelFactory(LocalContext.current.application))

            val application = LocalContext.current.applicationContext as Application
            val viewModelI = viewModel<SignInViewModel>(factory = ViewModelProvider.AndroidViewModelFactory(application))
            fun navigateToSignUp() {
                navController.navigate("signup")
            }

            val navigateToSignUpLambda = remember { { Log.d("Navigation", "Navigating to signup from lambda") ; navController.navigate("signup") }  }
            //SignInScreen(viewModelI, navigateToSignUpLambda)

        }
        composable("signup") {

            val viewModelU = viewModel<SignupViewModel>()

            SignupScreen(viewModelU)
            {
                navController.navigate("signing") { // Navigate to main screen
                   // popUpTo("signup") { inclusive = true } // Clear signup from stack
                }
            }
        }

        // Main Application Flow (Nested)
        navigation(startDestination = "main", route = "auth") {
            composable("main") { MainScreen(viewModel,viewModel.viewModelScope ) }
        }
    }

    // Observe the authentication state
    LaunchedEffect(viewModel.isLoggedIn.value) {
        Log.d("AppNavigation", "Observed isLoggedIn value: ${viewModel.isLoggedIn.value.toString()}")
        if (viewModel.isLoggedIn.value) {
            navController.navigate("auth") {
                // Clear backstack when moving from onboarding to auth
                popUpTo("onboarding") { inclusive = true }
            }
        } else {
            navController.navigate("onboarding") {
                // Clear backstack when going back to onboarding
                popUpTo(0)
            }
        }
    }
//    LaunchedEffect(viewModel.isLoggedIn.value) {
//        navController.navigate(if (viewModel.isLoggedIn.value) "auth/main" else "onboarding") {
//            popUpTo(0) // Clear backstack when navigating to a different root
//        }
//    }

}
