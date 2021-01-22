package com.anlyn.domain.usercase

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableTransformer
import com.anlyn.domain.Transformer

abstract class UseCase<T>(private val transformer:Transformer<T>){
    abstract fun createObservable(data:Map<String,Any?>? = null): Observable<T>
    fun observable(data:Map<String,Any?>? = null):Observable<T>{
        return createObservable(data).compose(transformer)
    }
}