# taf-kotlin-samples

The **TAF Kotlin Samples** project is a test automation framework built using Kotlin and Gradle. The framework is
designed to facilitate both core functionality testing and UI testing using Selenide. Below is an overview of the key
components and configurations used in the project.

## Content

- [Project Structure](#project-structure)
- [Key Features and Dependencies](#Key-Features-and-Dependencies)
- [Programming Patterns Used in Test Automation Framework](#Programming-Patterns-Used-in-test-automation-framework)
- [Working with framework](#working-with-framework)
    - [System Properties Overview](#system-properties-overview)
    - [Available System Properties](#available-system-properties)
    - [System Properties Description](#system-properties-description)
- [TAF Core key features overview](#TAF-Core-key-features-overview)
    - [File Conversion](#File-Conversion)
    - [System Properties Management](#System-Properties-Management)
    - [Dynamic Configuration Field](#Dynamic-Configuration-Field)
    - [Custom Logger](#Custom-Logger)
    - [Execution Environment Provider](#Execution-Environment-Provider)
    - [URL Service Utility](#URL-Service-Utility)
- [Author and Contact Information](#Author-and-Contact-Information)

## Project Structure

The project consists of the following modules:

- **`:core`**: Contains core testing logic and utilities.
- **`:ui_selenide`**: Focuses on UI testing, leveraging the Selenide library.

## Key Features and Dependencies

- **Core & UI Testing**: The framework supports both core logic and UI testing with Selenide.
- **Parallel & Cached Execution**: Leveraging Gradle features to improve build performance.
- **Kotlin & Java 17**: Built with Kotlin 2.0.20 and targeted for JVM 17.

The project uses modern libraries and tools to ensure high-quality testing:

- **Kotlin 2.0+**: For developing the framework.
- **JUnit Jupiter 5+**: For unit testing.
- **Selenide 7.4+**: For UI testing.
- **Jackson 2.17+**: For JSON processing and configuration management.
- **Log4j 1.2.17 & SLF4J 1.7.32**: For logging.

## Programming Patterns Used in Test Automation Framework

### Singleton Pattern

In the test automation framework, we utilize the **Singleton** pattern to ensure a single, globally accessible instance
of certain classes. The Singleton pattern is a design pattern that restricts the instantiation of a class to one single
instance and provides a global point of access to that instance.

#### Characteristics of the Singleton Pattern:

- **Single Instance**: Guarantees that only one instance of the class exists throughout the application.
- **Global Access**: Provides a global point of access to the instance, allowing it to be accessed from anywhere within
  the application.
- **Controlled Access**: Ensures controlled access to the unique instance, preventing multiple instantiations.

In our framework, the Singleton pattern is implemented in
the [`CustomLogger`](core/src/main/kotlin/org/timasha/logger/CustomLogger.kt) object.

## Working with framework

### System Properties Overview

The `taf-kotlin-samples` project uses several system properties to configure various aspects of the Test Automation
Framework (TAF). These properties can be set at runtime to control the behavior of the WebDriver and the environment in
which the tests are executed.

### Available System Properties

| Property Name                | Description                                                                            |
|------------------------------|----------------------------------------------------------------------------------------|
| `webdriver.type`             | Specifies the type of WebDriver to use (e.g., Chrome, Firefox).                        |
| `webdriver.host`             | The host address where the WebDriver is running.                                       |
| `webdriver.port`             | The port number for the WebDriver.                                                     |
| `webdriver.browser.name`     | The name of the browser to be used for testing (e.g., Chrome, Firefox).                |
| `webdriver.browser.headless` | If set to `true`, runs the browser in headless mode.                                   |
| `webdriver.nodeId`           | The node ID for a Selenium Grid setup.                                                 |
| `webdriver.chrome.version`   | Specifies the version of Chrome WebDriver to use.                                      |
| `webdriver.firefox.version`  | Specifies the version of Firefox WebDriver to use.                                     |
| `environment`                | Defines the target environment for the tests (e.g., development, staging, production). |
| `user.dir`                   | The working directory of the user executing the tests.                                 |
| `DYNAMIC_PARAM`              | A placeholder for dynamic parameters that can be set at runtime.                       |

### System Properties Description

System properties default values related to webdriver config could be changed
in [`default_driver_config.yaml`](ui_selenide/src/test/resources/driver/default_driver_config.yaml).

##### webdriver.type

- **Options:** { **REMOTE** | **LOCAL** }
- **Default value:** { **LOCAL** }.
- **Example:** `-Dwebdriver.type=REMOTE`
- **Description:** Selects the location where the WebDriver will be executed.
    - **remote**: Starts a remote WebDriver session from a Selenium server with the configured `host` and `port`.
    - **local**: Starts a WebDriver session on the local machine directly using the WebDriver binary.

##### webdriver.host

- **Options:** Any valid host address.
- **Default value:** { **127.0.0.1** }.
- **Example:** `-Dwebdriver.host=192.168.1.100`
- **Description:** Specifies the host address where the remote WebDriver server is running. This is required
  when `webdriver.type` is set to `remote`.

##### webdriver.port

- **Options:** Any valid port number.
- **Default value:** { **4444** }.
- **Example:** `-Dwebdriver.port=4444`
- **Description:** Specifies the port number for the remote WebDriver server. This is required when `webdriver.type` is
  set to `remote`.

##### webdriver.browser.name

- **Options:** { **CHROME** | **FIREFOX** }
- **Default value:** { **CHROME** }
- **Example:** `-Dwebdriver.browser.name=FIREFOX`
- **Description:** Specifies the browser to be used for testing.

##### webdriver.browser.headless

- **Options:** { **true** | **false** }
- **Default value:** { **false** }
- **Example:** `-Dwebdriver.browser.headless=true`
- **Description:** Determines whether the browser should run in headless mode, which means without a graphical user
  interface.

##### webdriver.nodeId

- **Options:** Any valid node ID.
- **Default value:** { **tafSeleniumNode**}.
- **Example:** `-Dwebdriver.nodeId=node01`
- **Description:** Specifies the node ID for a Selenium Grid setup, used when running tests on a distributed setup.

##### webdriver.chrome.version

- **Options:** Any valid Chrome WebDriver version.
- **Default value:** Latest available version.
- **Example:** `-Dwebdriver.chrome.version=127.0.6533.119`
- **Description:** Specifies the version of the Chrome WebDriver to use.

##### webdriver.firefox.version

- **Options:** Any valid Firefox WebDriver version.
- **Default value:** Latest available version.
- **Example:** `-Dwebdriver.firefox.version=0.26.0`
- **Description:** Specifies the version of the Firefox WebDriver to use.

##### environment

- **Options:** Any environment name.
- **Default value:** { **development** }
- **Example:** `-Denvironment=staging`
- **Description:** Defines the target environment for the tests (e.g., `development`, `staging`, `production`).

##### user.dir

- **Options:** Any valid directory path.
- **Default value:** Current working directory.
- **Example:** `-Duser.dir=/path/to/project`
- **Description:** Specifies the working directory of the user executing the tests. This is typically set automatically.

##### DYNAMIC_PARAM

- **Options:** Any string value.
- **Default value:** None.
- **Example:** `-DDYNAMIC_PARAM=myValue`
- **Description:** A placeholder for dynamic parameters that can be set at runtime. This allows for flexible and custom
  configuration during test execution.

### Using System Properties

You can set these system properties when executing your tests using the `-D` flag in your command-line interface. For
example:

```bash
./gradlew test -Dwebdriver.type=chrome -Dwebdriver.browser.headless=true
```

## TAF Core key features overview

### File Conversion

The [`FileConvert`](core/src/main/kotlin/org/timasha/convert/FileConvert.kt) object simplifies converting YAML files
into Kotlin objects using Jackson's `ObjectMapper` with Kotlin-specific configurations.

#### Key Features:

- **File to Generic Object Conversion**: Converts YAML files into objects of any specified type.
- **Reflection Cache Size**: Optimizes reflection-based access with a configurable cache size.
- **Null Handling**: Configurable null handling for collections, maps, and default values.
- **Singleton Support**: Optionally enables singleton support for Kotlin objects during deserialization.
- **Strict Null Checks**: Optionally enforces strict null checks.

#### Example Usage

```kotlin
// Convert a YAML file into a Kotlin object
val config: MyConfigClass = FileConvert.resourceFileToObject("config.yaml")
```

### System Properties Management

This framework provides a flexible way to work with system properties, which can be easily defined, accessed, and
managed using the [SystemProperties](core/src/main/kotlin/org/timasha/properties/SystemProperties.kt) interface and
[TafSystemProperties](core/src/main/kotlin/org/timasha/properties/TafSystemProperties.kt) enum. These properties allow
the configuration of the WebDriver, Environment, and other critical settings without modifying the codebase directly.

#### `SystemProperties` Interface

The `SystemProperties` interface provides a set of methods for managing system properties:

- **`getName()`**: Retrieves the name of the system property.
- **`get()`**: Returns the value of the system property, or `null` if it is not set.
- **`getOrDefault(defaultValue: String)`**: Returns the value of the system property or a default value if the property
  is not set.
- **`set(value: String)`**: Sets the system property to the specified value.
- **`clear()`**: Clears the system property and returns its previous value, if any.

#### Example Usage

```kotlin
// Get system property value
val webdriverType = WEBDRIVER_TYPE.get()

// Get system property value with a default fallback
val webdriverTypeOrDefault = WEBDRIVER_TYPE.getOrDefault("LOCAL")

// Set a system property
WEBDRIVER_TYPE.set("REMOTE")

// Clear a system property
WEBDRIVER_TYPE.clear()
```

### Dynamic Configuration Field

This feature allows you to maintain a single configuration file that adapts to different environments by substituting
dynamic parameters at runtime, enhancing the flexibility and reusability of your test automation framework.

The [`DynamicConfigField`](core/src/main/kotlin/org/timasha/config/DynamicConfigField.kt) interface is designed to
process and replace placeholders in configuration values with actual values provided at runtime. This is particularly
helpful when you have configuration parameters that need to be adjusted based on the environment in which the tests are
running.

#### Key Components

- **Companion Object Constants**:
    - `DEFAULT_EMPTY_STRING_VALUE`: A constant used as the default value for dynamic parameters that are not set.
    - `PATTERN_TO_REPLACE`: The pattern used in configuration files that will be replaced with actual values.

- **Properties**:
    - `valueToUpdate`: Retrieves the dynamic value to replace the placeholder, fetched from system properties.

- **Functions**:
    - `processDynamicValue(value: String?)`: Processes the input value by replacing the `PATTERN_TO_REPLACE` with the
      actual value obtained from `valueToUpdate`.
    - `getDynamicFieldOrThrowConfigException(fieldName: String)`: Extension function for `String?` that returns the
      processed dynamic value or throws an exception if the value is null or not properly initialized.

### Usage

In a typical configuration file, you might have placeholders like `DYNAMIC_PARAM` that need to be replaced based on
system properties. For example, if your configuration file includes a placeholder `DYNAMIC_PARAM` for the host, it would
initially look like this:

```yaml
host: DYNAMIC_PARAM
```

When your configuration class implements DynamicConfigField, you define the property as follows:

```kotlin
class Configuration : DynamicConfigField {
  var host: String? = null
    get() = field.getDynamicFieldOrThrowConfigException("host")
}
```

If the system property DYNAMIC_PARAM is set during runtime, for instance:

```bash
./gradlew test -DYNAMIC_PARAM=example.com
```

Accessing the host property from your configuration object will automatically replace the placeholder with the system
property value, resulting in:

```kotlin
val configuration: Configuration = Configuration()
configuration.host // Returns "example.com"
```

### Custom Logger

The **Custom Logger Utility** provides logging functionality for tests, built on top of **Log4j**. It allows perform
context-specific logging for each test case, outputting logs to uniquely named files, neatly organized by test name and
date.

#### Key Features

- **Context-Aware Logging**: Dynamically generates log file names based on test method and name.
- **Custom File Appender**: Configures Log4j to output logs to specific files using a custom log pattern.
- **Thread-local Logging**: Manages logger instances per thread to ensure thread safety.

#### Components

##### 1. [`LogInstance`](core/src/main/kotlin/org/timasha/logger/LogInstance.kt) Interface

Defines the core logging operations:

- `getLogger()`: Retrieves the current logger instance.
- `setLoggerContext(testMethodName:
  String, testName:
  String)`: Sets up the logger context with custom file output based
  on the test method and name.
- `resetLog()`: Resets the logger instance.

##### 2. [`IdfCustomLogAppender`](core/src/main/kotlin/org/timasha/logger/IdfCustomLogAppender.kt)

A utility function that configures a **Log4j FileAppender** with a custom log output pattern.

##### 3. [`CustomLogger`](core/src/main/kotlin/org/timasha/logger/CustomLogger.kt)

Implements the `LogInstance` interface, managing logger instances for each test case. The log file path is dynamically
generated based on the test name and method.

#### Example Usage

  ```kotlin
  // Set up logging context for a specific test
CustomLogger.setLoggerContext("testMethod1", "TestName1")

// Log messages using the context-specific logger
val logger = CustomLogger.getLogger()
logger.info("This is a log message for TestName1")

// Reset the logger after the test
CustomLogger.resetLog()
```

### Execution Environment Provider

The **Execution Environment Provider** allows you to dynamically manage and retrieve the current execution environment
for tests based on system properties. It is based on two main classes
[`ExecutionEnvironmentProvider`](core/src/main/kotlin/org/timasha/execution/environment/ExecutionEnvironmentProvider.kt)
and
[ExecutionEnvironmentType](core/src/main/kotlin/org/timasha/execution/environment/ExecutionEnvironmentType.kt)

#### Key Features

- **Dynamic Environment Selection**: Retrieves the execution environment from system properties, allowing tests to be
  run in different environments like **DEFAULT** or **DYNAMIC_ENV**.
- **Default Fallback**: If no system property is set, it defaults to the **DEFAULT** execution environment.

#### Example Usage

```kotlin
// Retrieve the current execution environment
val environment = ExecutionEnvironmentProvider.getExecutionEnvironment()

// Handle different environments
when (environment) {
  ExecutionEnvironmentType.DEFAULT -> println("Running in default environment")
  ExecutionEnvironmentType.DYNAMIC_ENV -> println("Running in dynamic environment")
}
```

### URL Service Utility

The [`UrlService`](core/src/main/kotlin/org/timasha/url/UrlService.kt) object provides methods for constructing HTTP and
HTTPS URLs with predefined prefixes.

#### Key Features:

- **HTTPS/HTTP URL Generation**: Easily create URLs with `http://` or `https://` prefixes.

#### Example Usage

```kotlin
val secureUrl = UrlService.getHttpsUrl("example.com")
val nonSecureUrl = UrlService.getHttpUrl("example.com")
```

## Author and Contact Information

**Author:** Evgeny Tumashchick

For any questions, issues, or contributions, you can reach out to me via:

- **Email:** [tumashchick@gmail.com](mailto:tumashchick@gmail.com)
- **LinkedIn:** [Evgeny Tumashchick](https://www.linkedin.com/in/tumashchick-yauhen/)
- **GitHub:** [evgeny-tumashchick](https://github.com/evgeny-tumashchick/evgeny-tumashchick)

Feel free to open an issue on the GitHub repository for bug reports, feature requests, or any other inquiries.