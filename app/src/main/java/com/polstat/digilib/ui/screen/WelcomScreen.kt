package com.polstat.digilib.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.polstat.digilib.R

@Composable
fun WelcomeScreen(navController: NavController){
    val image = painterResource(R.drawable.library)
    //val navController = rememberNavController()

    Column {
        Box {
            Image(
                painter = image,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth()
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
        ){
            Text(
                text = "Selamat Datang di Digilib Polstat-STIS",
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "Pusat koleksi buku statistik di sini",
                style = MaterialTheme.typography.bodyMedium
            )
            Button(
                onClick = { navController.navigate("login")},
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Login")
            }
            OutlinedButton(
                onClick = { navController.navigate("register")},
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Register")
            }
        }
    }
}

@Preview
@Composable
fun PreviewWelcomeScreen(){
    val navController = rememberNavController()
    WelcomeScreen(navController)
}