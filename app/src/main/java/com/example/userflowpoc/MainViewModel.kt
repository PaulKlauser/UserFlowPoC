package com.example.userflowpoc

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class MainViewModel : ViewModel() {
    val count = MutableStateFlow(0)
}