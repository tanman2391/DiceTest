package com.example.diceroll.differentDice

import kotlinx.coroutines.coroutineScope
import kotlin.random.Random

class RollDieRepository {

    suspend fun rollDie(sides: Int): Int {
        return coroutineScope {
            // Suppose this code was calling an async operation, eg. an API call or DB read
            Random.nextInt(sides)
        }
    }
}
