package com.polstat.digilib.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.polstat.digilib.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationScreen(navController: NavController){
    val image = painterResource(id = R.drawable.background)
    var nameState by remember{ mutableStateOf(TextFieldValue("")) }
    var nimState by remember{ mutableStateOf(TextFieldValue("")) }
    var kelasState by remember{ mutableStateOf(TextFieldValue("")) }
    var emailState by remember{ mutableStateOf(TextFieldValue("")) }
    var passwordState by remember{ mutableStateOf(TextFieldValue("")) }

    Box(
        modifier = Modifier.fillMaxSize()
    ){
        Image(
            painter = image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Registrasi Akun",
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            TextField(
                value = nameState,
                onValueChange = {nameState = it},
                label = { Text(text = "Nama")},
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primary.copy(0.1F))
            )
            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = nimState,
                onValueChange = {nimState = it},
                label = { Text(text = "NIM")},
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primary.copy(0.1F))
            )
            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = kelasState,
                onValueChange = {kelasState = it},
                label = { Text(text = "Kelas")},
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primary.copy(0.1F))
            )
            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = emailState,
                onValueChange = {emailState = it},
                label = { Text(text = "Email")},
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primary.copy(0.1F))
            )
            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = passwordState,
                onValueChange = {passwordState = it},
                label = { Text(text = "Password")},
                modifier = Modifier
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { navController.navigate("welcome")},
                modifier = Modifier.fillMaxWidth()) {
                Text(text = "Register")
            }
        }
    }
}

@Preview
@Composable
fun PreviewRegistrationScreen(){
    val navController = rememberNavController()
    RegistrationScreen(navController)
}