package com.example.eventmanagment

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import java.util.Locale


object Translation{
    @Composable
    fun SetLanguage(local:String){
        val localObj = Locale(local)
        val configuration = LocalConfiguration.current
        configuration.setLayoutDirection(localObj)
        configuration.setLocale(localObj)
        val resources = LocalContext.current.resources
        resources.updateConfiguration(configuration,resources.displayMetrics)
    }


//fun setLocale(context: Context, language: String) {
//    val locale = Locale(language)
//    Locale.setDefault(locale)
//    val resources = context.resources
//    val config = Configuration(resources.configuration)
//    config.setLocale(locale)
//    resources.updateConfiguration(config, resources.displayMetrics)
//}
//
//    @Composable
//    fun SetLanguage(language: String) {
//        val context = LocalContext.current
//        val configuration = LocalConfiguration.current
//
//        SideEffect {
//            setLocale(context, language)
//        }
//    }
//    private fun restartActivity(context: Context) {
//        val activity = (context as? Activity) ?: return
//        activity.recreate()
//    }

}