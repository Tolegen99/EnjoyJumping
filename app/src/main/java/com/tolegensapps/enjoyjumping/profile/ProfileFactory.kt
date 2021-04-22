package com.tolegensapps.enjoyjumping.profile

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ProfileFactory(val application: Application,val userId: String):
        ViewModelProvider.AndroidViewModelFactory(application) {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProfileViewModel(application, userId) as T
    }
}