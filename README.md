Moulder-j
=======================

A tiny jQuery-like HTML templating library written in Java.

Building
--------

You need a Java 5 (or newer) environment and Maven 3 installed:

    $ mvn --version
    Apache Maven 3.0-beta-1 (r935667; 2010-04-19 19:00:39+0200)
    Java version: 1.6.0_20
    Java home: /usr/lib/jvm/java-6-sun-1.6.0.20/jre
    Default locale: en_US, platform encoding: UTF-8
    OS name: "linux" version: "2.6.32-22-generic" arch: "amd64" Family: "unix"

You should now be able to do a full build of `ojaas`:

    $ git clone git://github.com/jawher/moulder-j.git
    $ cd moulder-j
    $ mvn clean install

To use this library in your projects, add the following to the `dependencies` section of your
`pom.xml`:

    <dependency>
      <groupId>jawher</groupId>
      <artifactId>moulder-j</artifactId>
      <version>1.0-SNAPSHOT</version>
    </dependency>

Troubleshooting
---------------

Please consider using [Github issues tracker](http://github.com/jawher/moulder-j/issues) to submit bug reports or feature requests.


Using this library
------------------



License
-------

See `LICENSE` for details.
