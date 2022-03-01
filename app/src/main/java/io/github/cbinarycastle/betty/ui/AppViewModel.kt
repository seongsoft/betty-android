package io.github.cbinarycastle.betty.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor() : ViewModel() {

    private val _exitSignal = MutableSharedFlow<Unit>()
    val exitSignal = _exitSignal.asSharedFlow()

    fun exit() {
        viewModelScope.launch {
            _exitSignal.emit(Unit)
        }
    }
}