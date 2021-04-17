package com.hernandazevedo.zaap.core.base

abstract class BaseMapper<T, K> {

    abstract fun mapFrom(source: K): T

    abstract fun mapTo(source: T): K

    fun mapFromList(source: List<K>): List<T> {
        return source.map { src -> mapFrom(src) }
    }

    fun mapToList(source: List<T>): List<K> {
        return source.map { src -> mapTo(src) }
    }
}