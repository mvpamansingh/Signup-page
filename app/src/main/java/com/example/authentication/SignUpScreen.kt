package com.example.authentication

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


// SignupScreen.kt
@Composable
fun SignupScreen(viewModel: SignupViewModel,navigateSignin:()->Unit) {
    val email by viewModel.email.collectAsState()
    val password by viewModel.password.collectAsState()
    // Add more fields as needed

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AppLogo()
        InputField(email, { newEmail -> viewModel.onEmailChange(newEmail) }, "Email")
        InputField(password, { newPassword -> viewModel.onPasswordChange(newPassword) }, "Password")
        // Add more input fields as needed
        PrimaryButton("Sign Up") { viewModel.signUp()
        navigateSignin()}
        // Add link to navigate to Sign In screen
    }
}

// SignupViewModel.kt

