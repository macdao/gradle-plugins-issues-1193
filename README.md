run `gradle clean build` will get:

```
> Task :web:generateContractTests FAILED

FAILURE: Build failed with an exception.

* What went wrong:
A problem was found with the configuration of task ':web:generateContractTests' (type 'GenerateServerTestsTask').
  - Gradle detected a problem with the following location: '***/gradle-plugins-issues-1193/web/build/generated-test-sources/contractTest/java'.

    Reason: Task ':web:generateContractTestEffectiveLombokConfig' uses this output of task ':web:generateContractTests' without declaring an explicit or implicit dependency. This can lead to incorrect results being produced, depending on what order the tasks are executed.

    Possible solutions:
      1. Declare task ':web:generateContractTests' as an input of ':web:generateContractTestEffectiveLombokConfig'.
      2. Declare an explicit dependency on ':web:generateContractTests' from ':web:generateContractTestEffectiveLombokConfig' using Task#dependsOn.
      3. Declare an explicit dependency on ':web:generateContractTests' from ':web:generateContractTestEffectiveLombokConfig' using Task#mustRunAfter.

    For more information, please refer to https://docs.gradle.org/8.10/userguide/validation_problems.html#implicit_dependency in the Gradle documentation.

* Try:
> Run with --stacktrace option to get the stack trace.
> Run with --info or --debug option to get more log output.
> Run with --scan to get full insights.
> Get more help at https://help.gradle.org.

BUILD FAILED in 6s
12 actionable tasks: 12 executed
```

while if changed `id("io.freefair.lombok") version "8.10"` in web/build.gradle.kts to `id("io.freefair.lombok") version "8.7.1"` will get

```
BUILD SUCCESSFUL in 12s
15 actionable tasks: 15 executed
```