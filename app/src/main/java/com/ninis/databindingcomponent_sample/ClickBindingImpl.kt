package com.ninis.databindingcomponent_sample

import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.subjects.PublishSubject
import java.util.concurrent.TimeUnit

class ClickBindingImpl(lifecycle: Lifecycle) : ClickBinding, LifecycleObserver {

    private val publishSubject = PublishSubject.create<Pair<View, View.OnClickListener>>()
    private val disposables = CompositeDisposable()
    private val TIME_OUT = 1000L

    init {
        lifecycle.addObserver(this)
        disposables.add(
            publishSubject.throttleFirst(
                TIME_OUT,
                TimeUnit.MILLISECONDS,
                AndroidSchedulers.mainThread()
            ).subscribe { pair ->
                pair?.let {
                    if (it.first != null && it.second != null) {
                        pair.second.onClick(pair.first)
                    }
                }
            })
    }

    override fun setOnClickListener(view: View, onClickListener: View.OnClickListener) {
        view.setOnClickListener { v ->
            publishSubject.onNext(Pair(v, onClickListener))
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroyed() {
        if (disposables.isDisposed) {
            disposables.dispose()
        }
    }
}