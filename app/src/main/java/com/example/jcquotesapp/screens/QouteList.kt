package com.example.jcquotesapp.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.example.jcquotesapp.models.Quote

@Composable
fun QouteList(data : Array<Quote> , onClick: (quote:Quote)->Unit) {
    
    LazyColumn(content = {
        items(data){
            QuoteListitem(quote = it, onClick)
        }
    })
}