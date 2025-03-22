# This repository serves to reproduce the bug in desugaring while running instrumentation tests on release build type

### Reproduction:
```
./gradlew -PtestBuildType=release connectedAndroidTest
```

will output
```
> Task :app:connectedReleaseAndroidTest
Starting 0 tests on Pixel_4_API_33(AVD) - 13

Finished 0 tests on Pixel_4_API_33(AVD) - 13

> Task :app:connectedReleaseAndroidTest FAILED
Test run failed to complete. Instrumentation run failed due to Process crashed.
Logcat of last crash: 
Process: desugaring.test.app, PID: 28242
java.lang.NoSuchMethodError: No static method toString(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/String; in class Lj$/util/Objects; or its super classes (declaration of 'j$.util.Objects' appears in /data/app/~~ww0QvZjMv4QgCYrqj2GV5A==/desugaring.test.app.test-GhbfR3gg6T6We785SJkviw==/base.apk!classes2.dex)
        at desugaring.test.app.ApplicationInstance.onCreate(ApplicationInstance.kt:10)
        at android.app.Instrumentation.callApplicationOnCreate(Instrumentation.java:1277)
        at androidx.test.runner.MonitoringInstrumentation.callApplicationOnCreate(MonitoringInstrumentation.java:470)
        at android.app.ActivityThread.handleBindApplication(ActivityThread.java:6811)
        at android.app.ActivityThread.-$$Nest$mhandleBindApplication(Unknown Source:0)
        at android.app.ActivityThread$H.handleMessage(ActivityThread.java:2133)
        at android.os.Handler.dispatchMessage(Handler.java:106)
        at android.os.Looper.loopOnce(Looper.java:201)
        at android.os.Looper.loop(Looper.java:288)
        at android.app.ActivityThread.main(ActivityThread.java:7924)
        at java.lang.reflect.Method.invoke(Native Method)
        at com.android.internal.os.RuntimeInit$MethodAndArgsCaller.run(RuntimeInit.java:548)
        at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:936)


FAILURE: Build failed with an exception.

* What went wrong:
Execution failed for task ':app:connectedReleaseAndroidTest'.
> There were failing tests.

* Try:
> Run with --stacktrace option to get the stack trace.
> Run with --info or --debug option to get more log output.
> Run with --scan to get full insights.
> Get more help at https://help.gradle.org.

BUILD FAILED in 23s
76 actionable tasks: 21 executed, 55 up-to-date
```

> [!IMPORTANT]
> - The error is reproducible only while running the test on `testBuildType = release`. The tests run file on the debug variant.  
> - The error is reproducible on all versions of Android (The example output is from Android 13 (API - 33).  
> - Increasing the minSdk to 24 resolves the error.  
> - Running the app (Both the release and debug variants) works fine.  
