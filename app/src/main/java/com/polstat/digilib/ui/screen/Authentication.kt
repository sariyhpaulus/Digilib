package com.polstat.digilib.ui.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Preview
@Composable
fun Preview(){

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController){
    val context = LocalContext.current
    val email = remember { mutableStateOf(TextFieldValue()) }
    val emailErrorState  = remember { mutableStateOf(false) }
    val password = remember { mutableStateOf(TextFieldValue()) }
    val passwordErrorState  = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ){
        Text(text = buildAnnotatedString {
            withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)){
                append("Login")
            }
        }, fontSize = 30.sp)

        Spacer(modifier = Modifier.size(16.dp))

        OutlinedTextField(
            value = email.value,
            onValueChange = {
                if (emailErrorState.value){
                    emailErrorState.value = false
                }
                email.value = it
            },
            isError = emailErrorState.value,
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = "Enter Email")},
            )
        if (emailErrorState.value){
            Text(text = "Required", color = Color.Red)
        }

        Spacer(modifier = Modifier.size(16.dp))
        val passwordVisibility = remember { mutableStateOf(true) }

        OutlinedTextField(
            value = password.value,
            onValueChange = {
                if(passwordErrorState.value){
                    passwordErrorState.value = false
                }
                password.value = it
            },
            isError = passwordErrorState.value,
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(text = "Enter Password")
            },
            trailingIcon = {
                IconButton(onClick = {passwordVisibility.value = !passwordVisibility.value}) {
                    Icon(
                        imageVector = if(passwordVisibility.value) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                        contentDescription = "visibility",
                        tint = Color.Black
                    )
                }
            },
            visualTransformation = if(passwordVisibility.value) PasswordVisualTransformation() else VisualTransformation.None
        )
        if (passwordErrorState.value){
            Text(text = "Required", color = Color.Red)
        }

        Spacer(modifier = Modifier.size(16.dp))

        Button(onClick = {
            when {
                email.value.text.isEmpty() -> {
                    emailErrorState.value = true
                }
                password.value.text.isEmpty() -> {
                    passwordErrorState.value = true
                }
                else ->{
                    passwordErrorState.value = false
                    emailErrorState.value = false
                    Toast.makeText(
                        context,
                        "Logged in succesfully",
                        Toast.LENGTH_SHORT
                    ).show()
                    navController.navigate("book_collection"){
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                }
            }
        },
            content = {
                Text(text = "Login", color = Color.White)
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
        )

        Spacer(modifier = Modifier.size(16.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            TextButton(onClick = { navController.navigate("register") }) {
                Text(text = "Register ?", color = MaterialTheme.colorScheme.primary)
            }
        }
    }
}
