package com.chillguy.stockvest.livedata

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean

class SingleMutableLiveData<T> : MutableLiveData<T>() {

    private val isConsumed: AtomicBoolean = AtomicBoolean(false)

    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        super.observe(owner) { t ->
            if (isConsumed.compareAndSet(false, true)) {
                observer.onChanged(t)
            }
        }
    }

    @MainThread
    override fun setValue(value: T) {
        isConsumed.set(false)
        super.setValue(value)
    }

}