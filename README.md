# Issue with custom openApiGenerate Task

Run `gradle clean build` and you will get:

```shell
FAILURE: Build failed with an exception.

* What went wrong:
Some problems were found with the configuration of task ':web:openApiGenerate-custom' (type 'GenerateTask').
  - In plugin 'org.openapi.generator' type 'org.openapitools.generator.gradle.plugin.tasks.GenerateTask' property 'inputSpec' specifies file 'C:\Users\dennis.ritter\github-projects\gradle-plugins-issues-1193\src\main\resources\api.yml' which doesn't exist.
    
    Reason: An input file was expected to be present but it doesn't exist.
    
    Possible solutions:
      1. Make sure the file exists before the task is called.
      2. Make sure that the task which produces the file is declared as an input.
    
    For more information, please refer to https://docs.gradle.org/8.10/userguide/validation_problems.html#input_file_does_not_exist in the Gradle documentation.
  - Gradle detected a problem with the following location: 'C:\Users\dennis.ritter\github-projects\gradle-plugins-issues-1193\web\build\generated\openapi'.
    
    Reason: Task ':web:generateEffectiveLombokConfig' uses this output of task ':web:openApiGenerate-custom' without declaring an explicit or implicit dependency. This can lead to incorrect results being produced, depending on what order the tasks are executed.
    
    Possible solutions:
      1. Declare task ':web:openApiGenerate-custom' as an input of ':web:generateEffectiveLombokConfig'.
      2. Declare an explicit dependency on ':web:openApiGenerate-custom' from ':web:generateEffectiveLombokConfig' using Task#dependsOn.
      3. Declare an explicit dependency on ':web:openApiGenerate-custom' from ':web:generateEffectiveLombokConfig' using Task#mustRunAfter.
    
    For more information, please refer to https://docs.gradle.org/8.10/userguide/validation_problems.html#implicit_dependency in the Gradle documentation.
```

# Initial Issue (resolved with plugin version `8.10.2`)

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