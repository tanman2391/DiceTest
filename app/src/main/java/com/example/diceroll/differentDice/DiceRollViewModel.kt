package com.example.diceroll.differentDice

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DiceRollViewModel : ViewModel() {

    private val repository by lazy { RollDieRepository() }

    private val _dieFaceLiveData = MutableLiveData<Int>()
    val dieFaceLiveData get() = _dieFaceLiveData as LiveData<Int>

    fun rollDie(sides: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            // Obtain the result from repository
            val randomNumber = repository.rollDie(sides)

            // Update observers of new value via livedata
            _dieFaceLiveData.postValue(randomNumber)
        }
    }

}
