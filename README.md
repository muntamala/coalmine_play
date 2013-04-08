Play Framework Connector for Coalmine
=====================================

*For use with Play Framework 1.x.*

This connector allows you to easily send messages to the Coalmine API.

[Coalmine](https://www.getcoalmine.com) is a cloud-based exception and error tracking service for your web apps.

Source
------

You can always find the latest source code on [GitHub](https://github.com/coalmine/coalmine_play).

Setup
-----

***Note:*** *The official Play 1.x module repository for the `play install` command is now read-only, so we'll have to do this the hard way.  Thankfully, the hard way is just five steps.  ;-)*

1\. From the `dist` directory, download `coalmine-{version}.zip`, where "{version}" is the latest version of this connector.

2\. Unzip the file into a directory named `coalmine-{version}`

3\. Move this directory into the `modules` directory of your framework installation

4\. Add the module as a dependency in your application:

    require:
        - play -> coalmine {version}

5\. Run `play deps` within your application directory

Configuration
-------------

Add the following block in `conf/application.conf`, with values appropriate to your application:

    # Coalmine
    # ~~~~~
    coalmine.signature=MY_COALMINE_SIGNATURE
    coalmine.version=1.0.0

The following options are available:

**coalmine.signature**

The signature for your application.  *This is required.*

**coalmine.environment**

Coalmine detects whether your application is in development mode or production mode.  If you use a special mode (such as "staging"), you must specify that here.  For example, `%staging.coalmine.environment=staging`.

**coalmine.enabledEnvironments**

A comma-delimited list of environments that Coalmine is enabled for.  Exceptions thrown for apps running in a different environment will not be sent to Coalmine.  The default is "production,staging".

*Note that "DEV" and "PROD" modes are called "development" and "production", respectively.*

**coalmine.timeout**

The timeout for a request to Coalmine, in milliseconds.  The default is 5000, or 5 seconds.

**coalmine.version**

Your application version.  The default is "1.0.0".
