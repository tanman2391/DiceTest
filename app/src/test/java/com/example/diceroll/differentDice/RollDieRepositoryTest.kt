package com.example.diceroll.differentDice

import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class RollDieRepositoryTest {

    private val input = 4
    private val repository by lazy { RollDieRepository() }

    @Test
    fun TestRollDieResultMaxLimitHolds() {
        runBlocking {
            val rollDieResult = repository.rollDie(input)
            Assert.assertTrue(rollDieResult < input)
        }
    }

    @Test
    fun TestRollDieResultMinLimitHolds() {
        runBlocking {
            val rollDieResult = repository.rollDie(input)
            Assert.assertTrue(rollDieResult > 0)
        }
    }
}
