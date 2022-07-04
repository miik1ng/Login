package com.miik1ng.login.messenger

import com.miik1ng.login.ICallback
import com.miik1ng.login.ICallbackNop
import java.lang.ref.WeakReference

@SuppressWarnings("rawtypes")
internal class WeakAction<T> {
    private var iCallbackNop: ICallbackNop? = null
    private var iCallback: ICallback<T>? = null
    private var isLive: Boolean? = null
    private var target: Any? = null
    private var reference: WeakReference<Any>? = null

    constructor(target: Any, iCallbackNop: ICallbackNop) {
        reference = WeakReference(target)
        this.iCallbackNop = iCallbackNop
    }

    constructor(target: Any, iCallback: ICallback<T>) {
        reference = WeakReference(target)
        this.iCallback = iCallback
    }

    fun execute() {
        if (iCallbackNop != null && isLive()) {
            iCallbackNop!!.back()
        }
    }

    fun execute(parameter: T) {
        if (iCallback != null && isLive()) {
            iCallback!!.back(parameter)
        }
    }

    fun markForDeletion() {
        reference?.clear()
        reference = null
        iCallbackNop = null
        iCallback = null
    }

    fun getICallbackNop(): ICallbackNop? {
        return iCallbackNop
    }

    fun getICallback(): ICallback<T>? {
        return iCallback
    }

    fun isLive(): Boolean {
        if (reference == null) {
            return false
        }

        if (reference!!.get() == null) {
            return false
        }

        return true
    }

    fun getTarget(): Any? {
        if (reference != null) {
            return reference!!.get()
        }

        return null
    }
}