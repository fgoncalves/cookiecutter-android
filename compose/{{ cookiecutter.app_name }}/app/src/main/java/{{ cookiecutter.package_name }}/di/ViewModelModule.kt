package {{ cookiecutter.package_name }}.di

import androidx.lifecycle.ViewModel
import {{ cookiecutter.package_name }}.presentation.MainViewModel
import {{ cookiecutter.package_name }}.presentation.base.ViewModelFactory
import {{ cookiecutter.package_name }}.presentation.base.ViewModelFactoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap

@Module
@InstallIn(SingletonComponent::class)
interface ViewModelModule {
    @Binds
    fun providesViewModelFactory(factory: ViewModelFactoryImpl): ViewModelFactory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun providesMainViewModel(viewModel: MainViewModel): ViewModel
}