

package com.example.authentication

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun SignInScreen(viewModel: SignInViewModel, navigateToSignUp:()->Unit) {
    val email by viewModel.email.collectAsState()
    val password by viewModel.password.collectAsState()


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
        

        Spacer(Modifier.height(20.dp))
        

        Text(text = "Create Account 9", modifier= Modifier.clickable { navigateToSignUp()
            Log.d("Navigation", "Trying to navigate to signup")})
        Spacer(modifier =Modifier.height(19.dp))
        PrimaryButton("SignIn'") { viewModel.signIn() }

    }
}