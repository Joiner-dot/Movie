package com.example.movierate.utils

import kotlin.random.Random

class RandomGenerator {
    companion object {
        fun generateRandomString(from: Int, to: Int) = Random.nextInt(from, to).toString()
    }
}