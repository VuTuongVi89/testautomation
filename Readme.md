# Ak35: Selenium Java


## How to run test by test plan

```shell
$mvn clean test -Dtestplan=<file-name>
```

exm: 
```shell
$mvn clean test -Dtestplan=amazon-signup-testplan
```


## Test plan template example:

```xml
<suite name="Amazon">
    <parameter name="url"  value="about:"/>
    <test name="Create Account - Chrome">
        <parameter name="browser"  value="chrome"/>
        <classes>
            <class name="testsuites.amazon.SignUpTest"/>
        </classes>
    </test>
    <test name="Create Account - Safari">
        <parameter name="browser"  value="safari"/>
        <classes>
            <class name="testsuites.amazon.SignUpTest"/>
        </classes>
    </test>
</suite>
```

**parameter name="browser"  value="chrome"** : set browser to run

## Project structure:

```shell

.
├── Readme.md
├── amazon-signup-testplan.xml
├── body-mass-index-calculator-testplan.xml
├── drivers
│   ├── chromedriver
│   ├── chromedriver.exe
│   └── geckodriver
├── pom.xml
├── regression-test-plan.xml
├── sample-test-plan.xml
├── smoke-test-plan.xml
└── src
    └── test
        └── java
            ├── base
            │   └── BaseTest.java
            ├── pages
            │   ├── amazon
            │   │   └── SignUpPage.java
            │   └── calculator
            │       └── BodyMassIndexCalculatorPage.java
            ├── supports
            │   ├── Browser.java
            │   └── JavaUtils.java
            └── testsuites
                ├── amazon
                │   ├── AddItemToCartTest.java
                │   ├── CartAmountTest.java
                │   └── SignUpTest.java
                ├── browsers
                │   ├── ChromeBrowserTest.java
                │   ├── FirefoxBrowserTest.java
                │   └── SafariBrowserTest.java
                ├── calculator
                │   └── BodyMassIndexTest.java
                ├── demo
                │   ├── Sample2Test.java
                │   └── SampleTest.java
                └── theInternet
                    ├── CheckboxesTest.java
                    ├── ContextMenuTest.java
                    ├── DropDownTest.java
                    ├── DynamicLoadingTest.java
                    ├── FormAuthenticationTest.java
                    ├── FrameSetTest.java
                    ├── HoverTest.java
                    ├── HyperLinkTest.java
                    ├── JsAlertTest.java
                    ├── MultipleOptionsTest.java
                    └── TableTest.java

```
## Reports
1. Check under **target/surefire-reports**