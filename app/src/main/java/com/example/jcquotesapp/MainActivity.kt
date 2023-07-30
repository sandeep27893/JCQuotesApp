package com.example.jcquotesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.example.jcquotesapp.screens.QouteListScreen
import com.example.jcquotesapp.screens.QuoteDetail
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        CoroutineScope(Dispatchers.IO).launch {
            delay(10000)
            DataManager.loadAssetsFromFile(applicationContext)
        }

        setContent {
            App()
        }

    }

    @Composable
    private fun App() {

        if (DataManager.isDataLoaded.value) {
            if(DataManager.currentPage.value==Pages.Listing) {
                QouteListScreen(data = DataManager.data) {
                        DataManager.switchPages(it)
                }
            }else{
                DataManager.currentQoute?.let { QuoteDetail(quote = it) }
            }
        } else {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize(1f)
            ) {
                Text(text = "Loading....",
                    fontFamily = FontFamily(Font(R.font.montserrat_regular))
                )
            }
        }
    }
}


enum class Pages{
    Listing,
    Detail
}

