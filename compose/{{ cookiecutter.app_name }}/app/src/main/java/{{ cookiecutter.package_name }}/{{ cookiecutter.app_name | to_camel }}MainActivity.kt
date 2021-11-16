package {{ cookiecutter.package_name }}

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import {{ cookiecutter.package_name }}.presentation.MainViewModel
import {{ cookiecutter.package_name }}.presentation.base.ViewModelFactory
import {{ cookiecutter.package_name }}.presentation.intent.ExampleNewGreetIntent
import {{ cookiecutter.package_name }}.presentation.theme.MyApplicationTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class {{ cookiecutter.app_name | to_camel }}MainActivity : ComponentActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]

        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    val state by viewModel.state.collectAsState()

                    Greeting(state.greet) {
                        viewModel.dispatch(ExampleNewGreetIntent(it))
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Greeting(
    name: String = "World",
    onNewGreet: (String) -> Unit = {},
) {
    Column {
        var text by rememberSaveable { mutableStateOf(name) }

        TextField(
            value = text,
            onValueChange = {
                text = it
            },
        )

        Text(text = "Hello $name!")

        Button(onClick = { onNewGreet(text) }) {
            Text(text = "Greet!")
        }
    }
}