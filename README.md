# taf-kotlin-samples

Repository with samples that could be used in test automation

## System Properties Overview

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

## Author and Contact Information

**Author:** Evgeny Tumashchick

For any questions, issues, or contributions, you can reach out to me via:

- **Email:** [tumashchick@gmail.com](mailto:tumashchick@gmail.com)
- **LinkedIn:** [Evgeny Tumashchick](https://www.linkedin.com/in/tumashchick-yauhen/)
- **GitHub:** [evgeny-tumashchick](https://github.com/evgeny-tumashchick/evgeny-tumashchick)

Feel free to open an issue on the GitHub repository for bug reports, feature requests, or any other inquiries.