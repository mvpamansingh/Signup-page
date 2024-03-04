package com.example.authentication.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.authentication.SignInViewModel

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@Composable
fun OnboardingScreen1(onContinue: () -> Unit, isFinished: Boolean) {
    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Onboarding Screen 1", fontSize = 39.sp)
        Spacer(modifier= Modifier.height(40.dp))

        Button(onClick = onContinue) {
            Text("Go to 2nd board")
        }
    }
}

@Composable
fun OnboardingScreen2(onContinue: () -> Unit, isFinished: Boolean) {
    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Onboarding Screen 2", fontSize = 39.sp)
        Spacer(modifier = Modifier.height(50.dp))

        Button(onClick = onContinue) {
            Text("Go to 3rd onboard")
        }
    }
}

@Composable
fun OnboardingScreen3(onContinue: () -> Unit, isFinished: Boolean) {
    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Onboarding Screen 3", fontSize = 39.sp)
        Spacer(Modifier.height(40.dp))

        Button(onClick = onContinue) {
            Text("Finish Onboarding")
        }
    }
}





@Composable
fun MainScreen(viewModel: SignInViewModel, viewModelScope: CoroutineScope) {

    Column {
        Text("Main Screen (User is Signed In)")

        Button(onClick = {
            viewModelScope.launch { // Launch a coroutine
                viewModel.signOut()
            }
        }) {
            Text("Logout")
        }
    }

}

