package {{ cookiecutter.package_name }}.presentation.intent

sealed class Intent

data class ExampleNewGreetIntent(val greet: String) : Intent()