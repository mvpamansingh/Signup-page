package com.example.authentication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.realm.kotlin.mongodb.App
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class SignupViewModel : ViewModel() {
    val email = MutableStateFlow("")
    val password = MutableStateFlow("")
    // ... add more fields

    fun onEmailChange(newEmail: String) {
        email.value = newEmail
    }

    fun onPasswordChange(newPassword: String) {
        password.value = newPassword
    }

    fun signUp() {
        // Handle Sign Up logic here (API Call, Validation, etc.)

        val app = App.create("application-0-bshhq") // Initialize Realm App

        viewModelScope.launch {
            app.emailPasswordAuth.registerUser(email.value.toString(),password.value.toString())
        }
    }
}