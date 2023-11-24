package com.polstat.digilib

import android.app.Activity
import android.content.Intent
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.app.ShareCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.polstat.digilib.ui.screen.BookCollectionScreen
import com.polstat.digilib.ui.screen.BookCollectionViewModel
import com.polstat.digilib.ui.screen.BookDetailScreen
import com.polstat.digilib.ui.screen.LoginScreen
import com.polstat.digilib.ui.screen.RegistrationScreen
import com.polstat.digilib.ui.screen.WelcomeScreen
import com.polstat.digilib.ui.screen.dummyData


@Composable
fun DigilibApp(){
    val navController = rememberNavController()
    val activity = (LocalContext.current as Activity)
    //DigilibNavHost(navController = navController)

    NavHost(
        navController = navController,
        startDestination = "welcome"
    ) {
        composable("welcome") {
            WelcomeScreen(navController)
        }
        composable("book_collection"){
            BookCollectionScreen(
                onBookClick = {
                    Log.i("ID BOOK", "BookDetailScreen:${it.id}")
                    Log.i("ID BOOK", "BookDetailScreen:${it}")
                    navController.navigate("books/${it.id}")
                })
        }
        composable("books/{bookid}",
            arguments = listOf(navArgument("bookid"){
                type = NavType.IntType
            })
        ){
            val id = it.arguments?.getInt("bookid")
            Log.i("ID", "BookDetailScreen: ${id}")

            val viewModel: BookCollectionViewModel = viewModel()
            val dummyData = dummyData()
            viewModel.updateBookList(dummyData)

            //Mendapatkan detail buku berdasarkan ID buku
            val book = viewModel.getBookById(id)
            Log.i("BOOK", "BookDetailScreen: ${book}")

            //Memastikan book tidak null sebelum menampilkan detail buku
            if(book!=null){
                BookDetailScreen(
                    book = book,
                    onBackClick = { navController.navigateUp() },
                    onShareClick = { createShareIntent(activity, it) }
                )
            } else{
                //Menampilkan pesan jika buku tidak ditemukan
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Buku tidak ditemukan",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
        composable("login") {
            LoginScreen(navController)
        }
        composable("register") {
            RegistrationScreen(navController)
        }
    }
}


fun createShareIntent(activity: Activity, bookTitle: String) {
    val shareText = bookTitle
    val shareIntent = ShareCompat.IntentBuilder(activity)
        .setText(shareText)
        .setType("text/plain")
        .createChooserIntent()
        .addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT or Intent.FLAG_ACTIVITY_MULTIPLE_TASK)
    activity.startActivity(shareIntent)
}
