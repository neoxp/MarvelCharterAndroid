package es.appdevelopment.marvelapi

import android.app.Application
import package es.appdevelopment.marvelapi.framework.prefs.Prefs

class MarvelApp : Application() {
    companion object {
        lateinit var prefs: Prefs
    }

    override fun onCreate() {
        super.onCreate()

        prefs = Prefs(applicationContext)
    }
}