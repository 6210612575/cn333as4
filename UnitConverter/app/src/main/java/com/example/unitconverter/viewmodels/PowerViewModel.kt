package com.example.unitconverter.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.unitconverter.R

class PowerViewModel : ViewModel() {
    private val _ePower: MutableLiveData<Int> = MutableLiveData(R.string.watt)

    val ePower: LiveData<Int>
        get() = _ePower

    fun setUnit(value: Int) {
        _ePower.value = value
    }

    private val _power: MutableLiveData<String> = MutableLiveData("")

    val power: LiveData<String>
        get() = _power

    fun getPowerAsFloat(): Float = (_power.value ?: "").let {
        return try {
            it.toFloat()
        } catch (e: NumberFormatException) {
            Float.NaN
        }
    }

    fun setPower(value: String) {
        _power.value = value
    }

    fun convert() = getPowerAsFloat().let {
        if (!it.isNaN())
            if (_ePower.value == R.string.watt)
                it / 745.699865796395153F
            else
                it * 745.699865796395153F
        else
            Float.NaN
    }
}