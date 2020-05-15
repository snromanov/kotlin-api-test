# Integration & API Automated Tests
 
## Tools
* [Kotlin](https://kotlinlang.org/)
* [Java 14](https://openjdk.java.net/projects/jdk/14/)
* [Fuel](https://github.com/kittinunf/fuel)
* [Allure](https://docs.qameta.io/allure/)
* [Junit 5](https://junit.org/junit5/docs/current/user-guide/#api-evolution/)
* [Gradle](https://gradle.org/)

## Pre-requirements
#### Install IntelliJ IDEA
* [mac os](https://www.jetbrains.com/idea/download/#section=mac)
* [linux os](https://www.jetbrains.com/idea/download/#section=linux)
* [windows os](https://www.jetbrains.com/idea/download/#section=windows)

#### Install Java 14
* **to mac os:**
```bash 
$ brew cask install adoptopenjdk
```
* **to windows** [go to](https://adoptopenjdk.net/installation.html?variant=openjdk14&jvmVariant=hotspot#x64_win-jdk)
* **to linux** [go to](https://adoptopenjdk.net/installation.html?variant=openjdk14&jvmVariant=hotspot#x64_linux-jdk)
#### Allure 
* **to mac os:** 
```bash 
$ brew install allure
```
* **to windows :**
```bash
$ scoop install allure
```
* **to linux :**
```bash
$ sudo apt-add-repository ppa:qameta/allure
$ sudo apt-get update 
$ sudo apt-get install allure
```
#### Quick start in IntelliJ IDEA after building the project
* **Before all (once)**
```bash
$ gradle wrapper
```

* **Run all tests:**
 ```bash 
 $ ./gradlew clean test
```
* **Generate Allure report:**
```bash 
$ ./gradlew allureReport
```
* **Generate Allure report and opens it in the default browser:** 
```bash
$ ./gradlew allureServe
``` 

* **Lazy: Run all tests and generate allure report and opens it in the default browser:**
 ```bash 
 $ ./gradlew clean test allureServe
```

* **Lazy: Run all tests and generate Allure report:** 
 ```bash 
 $ ./gradlew clean test allureReport
```

### Project check style
* [Use imported style checker](https://github.com/arturbosch/detekt)
    rules https://arturbosch.github.io/detekt/style.html

 **Quick style check**   
```bash
$ ./gradlew detekt
```

### Important
```bash
By default local tests are run with the following environment variables that a taken from build.gradle.kts.
If you need another variables  use gradle configurations with you environment variables.  
Example:  API_PASSWORD=password;ENV_ID=qa;API_LOGIN=admin;CI=1  where "CI" not null
```
See https://www.jetbrains.com/help/idea/creating-and-editing-run-debug-configurations.html

```bash
You may encounter a problem with check Allure report(openning index.html) locally
```
See https://github.com/allure-framework/allure2/issues/968
