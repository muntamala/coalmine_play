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

1. [Download the module.](https://github.com/coalmine/coalmine_play/raw/master/dist/coalmine-0.1.0.zip)

2. Create a directory in your application named `repo`.

3. Unzip the file into `repo/coalmine-0.1.0`

4. Add the module as a dependency in your application:

        require:
            - play
            - coalmine -> coalmine
    
        repositories:
            - coalmine:
                type: local
                artifact: "${application.path}/repo/coalmine-0.1.0"
                contains:
                    - coalmine

5. Run `play deps` within your application directory.

Configuration
-------------

Add the following block in `conf/application.conf`, with values appropriate to your application:

    # Coalmine
    # ~~~~~
    coalmine.signature=<your-signature>
    coalmine.version=1.0.0

The following options are available:

**coalmine.signature**

The signature for your application.  *This is required.*

**coalmine.enabledEnvironments**

A comma-delimited list of environments that Coalmine is enabled for.  Environments are framework IDs, specified by commands such as `play run --%staging`.  Exceptions thrown for apps running in a different environment will not be sent to Coalmine.  The default is "production,staging".

**coalmine.timeout**

The timeout for a request to Coalmine, in milliseconds.  The default is 5000, or 5 seconds.

**coalmine.version**

Your application version.  The default is "1.0.0".

Usage
-----

Your app will automatically notify Coalmine whenever an exception is raised.

However, if no exceptions are sent, your app is most likely intercepting the exception before it reaches the Coalmine plugin.  For example, say you have a catch-all method, like so:

    public class Application extends Controller {
        @Catch(value = Exception.class, priority = 1)
        public static void handleException(Throwable e) {
            render("/public/500.html");
        }
        // ...
    }

You will want to manually notify Coalmine in this case.

    import play.modules.coalmine.CoalminePlugin;
    
    public class Application extends Controller {
        @Catch(value = Exception.class, priority = 1)
        public static void handleException(Throwable e) {
            CoalminePlugin coalmine = Play.plugin(CoalminePlugin.class);
            coalmine.notify(e);
            render("/public/500.html");
        }
        // ...
    }
