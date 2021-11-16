package {{ cookiecutter.package_name }}.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(
    includes = [
        DataModule::class,
        DomainModule::class,
        ViewModelModule::class,
    ]
)
@InstallIn(SingletonComponent::class)
interface {{ cookiecutter.app_name | to_camel }}AppModule