package {{ cookiecutter.package_name }}

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
{% if cookiecutter.logging_api == "timber" %}import timber.log.Timber.DebugTree
import timber.log.Timber
{% endif %}

@HiltAndroidApp
class {{ cookiecutter.app_name | to_camel }}Application : Application() {% if cookiecutter.logging_api == "timber" %}{
    override fun onCreate() {
        super.onCreate()

        Timber.plant(if (BuildConfig.DEBUG) DebugTree() else CrashReportingTree())
    }

    class CrashReportingTree : Timber.Tree() {
        override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
            // TODO: Fix this in template
        }
    }
}
{% endif %}
