package {{ cookiecutter.package_name }}.di

import dagger.Module
{% if cookiecutter.add_network_stack == "y" %}import dagger.Provides{% endif %}
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
{% if cookiecutter.add_network_stack == "y" %}
{% if cookiecutter.add_network_logging == "y" %}import okhttp3.logging.HttpLoggingInterceptor
{% if cookiecutter.logging_api == "timber" %}import timber.log.Timber{% else %}import android.util.Log{% endif %}
{% endif %}
import android.content.Context
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.File
import javax.inject.Singleton
{% endif %}
@Module
@InstallIn(SingletonComponent::class)
interface DataModule{% if cookiecutter.add_network_stack == "y" %} {
    companion object {
        {% if cookiecutter.add_network_logging == "y" %}@Provides
        fun providesHttpLoggingInterceptor() =
            HttpLoggingInterceptor {
                {% if cookiecutter.logging_api == "timber" %}Timber.tag("OkHttp").d(it){% else %}Log.d("OkHttp", it){% endif %}
            }.apply {
                setLevel(HttpLoggingInterceptor.Level.BODY)
            }
        {% endif %}

        @Provides
        fun providesOkHttpClient(
            @ApplicationContext context: Context,
            {% if cookiecutter.add_network_logging == "y" %}loggingInterceptor: HttpLoggingInterceptor,{% endif %}
        ) =
            OkHttpClient.Builder()
                {% if cookiecutter.add_network_logging == "y" %}.addInterceptor(loggingInterceptor){% endif %}
                .cache(Cache(File(context.cacheDir, "http_cache"), 50L * 1024L * 1024L))
                .build()

        @Provides
        @Singleton
        fun providesRetrofit(client: OkHttpClient) =
            Retrofit.Builder()
                .baseUrl("localhhost.com")
                .client(client)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
    }
}{% endif %}
