package com.example.authentication

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import android.app.Application
import androidx.lifecycle.AndroidViewModel

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val _isReady = MutableStateFlow(false)
    val isReady = _isReady.asStateFlow()
    private val _navigate= MutableStateFlow("auth")
    val navigateasstateflow= _navigate.asStateFlow()
    private val sharedPreferences = application.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)
    init {
        viewModelScope.launch {
            delay(2000L)

            val isPreviouslySignedIn = sharedPreferences.getBoolean("is_sign_in", false)
            //isSignIn.value = isPreviouslySignedIn

            if (isPreviouslySignedIn)
            {
                //_isLoggedIn.value = true
                 _navigate.value= "main"
            }

            _isReady.value = true
        }
    }
}