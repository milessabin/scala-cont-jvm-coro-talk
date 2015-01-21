# Scala Continuations meet JVM Coroutines

Source code from the [talk](http://skillsmatter.com/podcast/scala/scala-continuations)
I gave at Skills Matter during Scala LiftOff London, 2011. This project is
open source, published under the Apache 2 licence.

Compile the Scala sources with -P:continuations:enable. Any Scala or Java
code which refers to the java.dyn package (ie. all the uses of Coroutine
or AsymCoroutine) should be compiled and run relative to the JVM
coroutine-enabled JDK available [here](http://ssw.jku.at/General/Staff/LS/coro/).

