Play Framework Connector Test App
=================================

This small application lets the user easily send test errors to Coalmine.  It's used for black box testing of the Play Framework connector.

Setup
-----

From the current directory, run the following command:

    play run --%test -Dcoalmine.signature=<your-signature>

Usage
-----

Click on an exception name to throw an exception of that type.  That's it!
