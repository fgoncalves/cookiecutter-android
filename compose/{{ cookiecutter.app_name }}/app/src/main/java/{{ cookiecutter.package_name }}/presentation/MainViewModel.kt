package {{ cookiecutter.package_name }}.presentation

import {{ cookiecutter.package_name }}.presentation.base.BaseViewModel
import {{ cookiecutter.package_name }}.presentation.intent.ExampleNewGreetIntent
import {{ cookiecutter.package_name }}.presentation.intent.Intent
import {{ cookiecutter.package_name }}.presentation.state.ExampleState
import javax.inject.Inject

class MainViewModel @Inject constructor() : BaseViewModel<ExampleState>() {
    override fun initialState() = ExampleState("Hellos World!")

    override suspend fun reduce(intent: Intent): ExampleState =
        if (intent is ExampleNewGreetIntent)
            state.value.copy(greet = intent.greet)
        else
            state.value
}