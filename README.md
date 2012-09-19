Moulder-j
=======================

A (relatively) tiny jQuery-like HTML templating library written in Java. In a nutshell, here's how to use `moulder`:

1. parse a document
2. select one or more elements (using jQuery's selectors syntax)
3. apply one or more modifiers on the previous selection (add/remove/modify attribute, set text, repeat, ...)
4. go back to 2 to alter as many other elements as needed
5. profit !


Using this library
------------------

To use this library in your projects, just add the following to the `dependencies` section of your
`pom.xml` (moulder is deployed in central):

```xml
<dependency>
  <groupId>com.plecting</groupId>
  <artifactId>moulder</artifactId>
  <version>1.0.0</version>
</dependency>
```

I've compiled [some simple and elementary use cases in this gist](http://gist.github.com/522028), and [some more complex and advanced use cases in this gist](http://gist.github.com/522037).

Here's a quick sample of how `moulder` can be used to manipulate html:

Given this markup:

```html
<html>
    <body>
        <h1>dummy text to be replaced</h1>
    </body>
</html>
```

This moulder based snippet:

```java
Document doc = Jsoup.parse(HTML);
MoulderShop m = new MoulderShop();

m.register("h1",
    repeat(Arrays.asList("Spring", "Summer", "Autumn", "Winter")),
    attr("class", new Values<String>("even", "odd").cycle()),
    text(new ElementDataValue<String>()),
    append("<p>content</p>")
    );

m.process(doc);
```

Will generate the following:

```html
<html>
    <head>
    </head>
    <body>
        <h1 class="even">Spring</h1>
        <p>content</p>
        <h1 class="odd">Summer</h1>
        <p>content</p>
        <h1 class="even">Autumn</h1>
        <p>content</p>
        <h1 class="odd">Winter</h1>
        <p>content</p>
    </body>
</html>
```

Or in plain english:

* For each item in the list of seasons, repeat the h1 element
* For each generated h1 element, set it's class to even or odd
* Also set it's text content to the corresponding season
* And finally, append a paragraph after it


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


Troubleshooting
---------------

Please consider using [Github issues tracker](http://github.com/jawher/moulder-j/issues) to submit bug reports or feature requests.


License
-------

See `LICENSE` for details.
