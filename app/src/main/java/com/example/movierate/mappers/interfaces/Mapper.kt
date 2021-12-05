package com.example.movierate.mappers.interfaces

interface Mapper<in From, out To> {
    fun map(from: From): To

    fun map(from: List<From?>): List<To> {
        return from.filterNotNull()
            .map { map(it) }
    }
}