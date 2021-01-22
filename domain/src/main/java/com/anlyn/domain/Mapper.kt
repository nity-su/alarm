package com.anlyn.domain

abstract class Mapper<T,E>{
    abstract fun mapFrom(from:T):E
}