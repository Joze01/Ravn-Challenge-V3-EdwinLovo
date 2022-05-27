package com.elovo.ravnchallenge.presentation.utils

import androidx.lifecycle.LiveData

interface Connectivity {
    val monitorLiveNetwork: LiveData<Boolean>
    fun hasNetworkAccess(): Boolean
}
