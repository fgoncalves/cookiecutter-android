package {{ cookiecutter.package_name }}.presentation.state

sealed class State

data class ExampleState(
    val greet: String,
) : State()
