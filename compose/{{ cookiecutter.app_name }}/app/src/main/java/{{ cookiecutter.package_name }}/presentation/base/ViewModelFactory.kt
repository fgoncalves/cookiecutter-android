package {{ cookiecutter.package_name }}.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

interface ViewModelFactory : ViewModelProvider.Factory

class ViewModelFactoryImpl @Inject constructor(
    private val viewModels: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>,
) : ViewModelFactory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        viewModels[modelClass]?.get() as? T
            ?: throw IllegalArgumentException("Cannot create view model for $modelClass")
}