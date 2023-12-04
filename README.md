# Automated-E-commerce-Checkout-Selenium
This project contains a Selenium WebDriver script written in Java for automating the checkout process on an e-commerce website. The script covers various functionalities to streamline the user journey, including login, button verification, navigation, billing details input, payment method selection, and broken link detection.


- **Functions:**
    1. `main` Method:
        - Entry point for the script.
        - Initializes WebDriver, sets window position and size, performs auto-login, and executes various checkout steps.
    2. `chromedriver` Method:
        - Configures and returns a ChromeDriver.
        - Navigates to the login page of the target website.
    3. `AutoLogin` Method:
        - Automates the login process by providing email and password.
    4. `buttonLocator` Method:
        - Identifies and prints information about buttons on the page.
    5. `navigation` Method:
        - Navigates through different pages on the website, interacts with elements, and performs product-related actions.
    6. `BillingDetails` Method:
        - Verifies and fills in billing details on the checkout page.
    7. `PaymentMethod` Method:
        - Verifies payment methods and performs related actions.
    8. `brokenLinks` Method:
        - Checks for broken links on the page and prints the results.

- **Libraries/Classes Used:**
    - **WebDriver:**
        - Used for browser automation.
    - **ChromeDriver:**
        - Specific WebDriver for Chrome.
    - **Point, Dimension:**
        - Classes for manipulating window position and size.
    - **Select:**
        - Used for handling dropdowns.
    - **HttpURLConnection, URL:**
        - Used for checking the status of hyperlinks.


#### Other Libraries:

- **guava-jre.jar**
- **failsafe.jar**
- **selenium-java**
