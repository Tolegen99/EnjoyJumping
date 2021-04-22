package com.tolegensapps.enjoyjumping.profile

import android.app.Application
import android.content.Intent
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.backendless.Backendless
import com.backendless.BackendlessUser
import com.backendless.async.callback.AsyncCallback
import com.backendless.exceptions.BackendlessFault
import com.backendless.rt.data.EventHandler
import com.tolegensapps.enjoyjumping.WelcomeActivity
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*

class ProfileViewModel(application: Application, val userId: String) : AndroidViewModel(application) {

    var userName = MutableLiveData<String>()
    var userEmail = MutableLiveData<String>()
    var ticketEveningSub = MutableLiveData<String>()
    var ticketBalance = MutableLiveData<String>()
    var ticketValidity = MutableLiveData<String>()
    var intentLogout = MutableLiveData<Intent>()

//    init {
//        initProfileData()
//    }

    fun initProfileData() {
        Backendless.Data.of(BackendlessUser::class.java).findById(userId, object : AsyncCallback<BackendlessUser> {
            override fun handleResponse(response: BackendlessUser?) {
                userName.value = response?.getProperty("userName") as String
                userEmail.value = response.getProperty("userEmail") as String
                ticketEveningSub.value = "${checkEveningTrue(response.getProperty("ticketEveningSub") as Boolean)} абонемент"
                ticketBalance.value = "${response.getProperty("ticketBalance")}пос."
                ticketValidity.value = "Действителен до ${dateFormate(response.getProperty("ticketValidity") as Date)}г."
            }

            override fun handleFault(fault: BackendlessFault?) {
            }
        })
    }

//    fun onChangesDataListener() {
//        val userTableRT: EventHandler<BackendlessUser> = Backendless.Data.of(BackendlessUser::class.java).rt()
//        userTableRT.addUpdateListener(object : AsyncCallback<BackendlessUser> {
//            override fun handleResponse(response: BackendlessUser?) {
//                userName.value = response?.getProperty("userName") as String
//                userEmail.value = response.getProperty("userEmail") as String
//                ticketEveningSub.value = "${checkEveningTrue(response.getProperty("ticketEveningSub") as Boolean)} абонемент"
//                ticketBalance.value = "${response.getProperty("ticketBalance")}пос."
//                ticketValidity.value = "Действителен до ${dateFormate(response.getProperty("ticketValidity") as Date)}г."
//            }
//
//            override fun handleFault(fault: BackendlessFault?) {
//            }
//        })
//    }

    fun onClickExit(){

        Backendless.UserService.logout(object : AsyncCallback<Void> {
            override fun handleResponse(response: Void?) {
                intentLogout.value= Intent(getApplication(), WelcomeActivity::class.java)
            }

            override fun handleFault(fault: BackendlessFault?) {
            }
        })
    }

    private fun checkEveningTrue(isEvening: Boolean): String {
        if (isEvening)
            return "Вечерний"
        else
            return "Дневной"
    }

    private fun dateFormate(date: Date): String {
        val pattern = "dd.MM.yyyy"
        val simpleDateFormat = SimpleDateFormat(pattern)
        return simpleDateFormat.format(date)
    }

}