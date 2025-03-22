package desugaring.test.app

import android.app.Application
import java.util.Objects
import kotlin.random.Random

class ApplicationInstance : Application() {
    override fun onCreate() {
        super.onCreate()
        Objects.toString(Random(12), "Yes")
    }
}