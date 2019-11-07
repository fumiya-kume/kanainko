package kuu.nagoya.util

import androidx.annotation.MainThread

@MainThread
open class MutableLiveEvent<T> : LiveEvent<T>() {
    fun sendEvent(value: T) {
        this.postValue(value)
    }
}