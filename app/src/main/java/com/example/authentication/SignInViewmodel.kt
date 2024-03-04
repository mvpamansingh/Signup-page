package com.example.authentication

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.realm.kotlin.Realm
import io.realm.kotlin.mongodb.App
import io.realm.kotlin.mongodb.Credentials
import io.realm.kotlin.mongodb.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

import kotlinx.coroutines.runBlocking


class SignInViewModel(application: Application) : AndroidViewModel(application) {
    val email = MutableStateFlow("")
    val password = MutableStateFlow("")


    private val user:User?= null

    private val sharedPreferences = application.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE) // Get Shared Preferences
   //1
    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn = _isLoggedIn.asStateFlow()

    init {
        val isPreviouslySignedIn = sharedPreferences.getBoolean("is_sign_in", false)
        //isSignIn.value = isPreviouslySignedIn

        if (isPreviouslySignedIn)
        {
            _isLoggedIn.value = isPreviouslySignedIn
        }
    }


    fun onEmailChange(newEmail: String) {
        email.value = newEmail
    }

    fun onPasswordChange(newPassword: String) {
        password.value = newPassword
    }


    fun signIn() {

        val app: App = App.create("application-0-bshhq") // Replace this with your App ID
        viewModelScope.launch {
            Log.d("SignInViewModel", "Authentication status before: ${isLoggedIn.value.toString()}")

            val emailPasswordCredentials = Credentials.emailPassword(email.value.toString(),
                password.value.toString()
            )
            val user = app.login(emailPasswordCredentials)
            if(user.loggedIn)
            {
                //isSignIn.value= true

                _isLoggedIn.value = true
                sharedPreferences.edit().putBoolean("is_sign_in", true).apply()


            }

            Log.d("SignInViewModel", "Authentication status after: ${isLoggedIn.value.toString()}") // Log after update
        }

    }

    suspend fun signOut() {
        // Implement logic to sign out the user from MongoDB Realm
        user?.logOut()

        //isSignIn.value = false

        _isLoggedIn.value = false
        sharedPreferences.edit().putBoolean("is_signed_in", false).apply()

    }
}