package com.example.jcquotesapp

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import com.example.jcquotesapp.models.Quote
import com.google.gson.Gson

object DataManager {

    var data = emptyArray<Quote>()
    var currentQoute :Quote? = null
    var currentPage = mutableStateOf(Pages.Listing)
    var isDataLoaded = mutableStateOf(false)
    fun loadAssetsFromFile(context : Context){

        val inputStream = context.assets.open("quotes.json")
        val size:Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val json = String(buffer, Charsets.UTF_8)
        val gson = Gson()

        data =gson.fromJson(json,Array<Quote>::class.java)
        isDataLoaded.value = true
    }


    fun switchPages(quote: Quote?) {
        if (currentPage.value == Pages.Listing){
            currentQoute =quote
            currentPage.value=Pages.Detail
        }else{
            currentPage.value =Pages.Listing
        }
    }

}