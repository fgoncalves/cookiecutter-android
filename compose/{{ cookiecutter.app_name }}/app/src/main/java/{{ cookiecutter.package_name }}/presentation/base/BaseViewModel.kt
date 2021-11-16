package {{ cookiecutter.package_name }}.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import {{ cookiecutter.package_name }}.presentation.intent.Intent
import {{ cookiecutter.package_name }}.presentation.state.State
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

abstract class BaseViewModel<S : State> : ViewModel() {
    private val intents = MutableSharedFlow<Intent>()

    protected val mutableStateFlow by lazy { MutableStateFlow(initialState()) }

    val state = mutableStateFlow.asStateFlow()

    init {
        intents
            .map(::reduce)
            .distinctUntilChanged()
            .onEach { mutableStateFlow.value = it }
            .launchIn(viewModelScope)
    }

    fun dispatch(intent: Intent) {
        viewModelScope.launch {
            intents.emit(intent)
        }
    }

    protected abstract fun initialState(): S

    protected abstract suspend fun reduce(intent: Intent): S
}