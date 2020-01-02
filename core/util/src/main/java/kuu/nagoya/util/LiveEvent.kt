package kuu.nagoya.util

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean

@MainThread
open class LiveEvent<T> : LiveData<T>() {

    init {
        super.setValue(null)
    }

    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        val isFirst = AtomicBoolean(true)
        super.observe(owner, Observer {
            if (isFirst.getAndSet(false)) return@Observer
            observer.onChanged(it)
        })
    }
}

fun <T> LiveData<T>.filter(filterFunc: (T) -> Boolean): LiveData<T> {
    val result = MediatorLiveData<T>()
    result.addSource<T>(
        this
    ) { x ->
        if (filterFunc(x)) {
            result.setValue(x)
        }
    }
    return result
}

fun <T> LiveData<T>.toLiveEvent(): LiveEvent<T> {
    val result = MutableLiveEvent<T>()
    this.observeForever { result.sendEvent(it) }
    return result
}