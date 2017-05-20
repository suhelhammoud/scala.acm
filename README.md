# scala.acm
Several ACM  problems solved in Scala Programming Language

I've created this repository to try Scala programming language in solving problems presented by ACM contests. In such contests, C++ is the dominant used language.

So far, I have tried few problems from [codeforces](http://codeforces.com).

### My own remarks: (to be updated)

* Problems usually are solved within tens of lines of code. Modular programming using classes and traits does not show that benefits in such small amount of code. Actually, procedural programming is the first way to go here.
* Even with most trivial Scala application, execution times are no less than 260 ms. This alone is enough to make Scala not suitable for ACM contests with time restriction of no more than two seconds. I believe Scala has inherited this from the  jvm startup time where hundreds of classes should be loaded into the memory before executing the user code, now add to this a group of necessary Scala classes as well. However, once the application is started it is really fast enough to be competitive with C++ code. Several solutions suggest reducing the startup time, but they require contest server configuration.
* Scala syntax is very versatile. Tasks can be written in several ways. However, when trying to solve couple of problems with relatively small input data and with intensive repetitive CPU processing, only imperative style code of “while loops” and of mutable variables could pass the test. And then, the control flow seems similar to C++ one in such time critical sections. Outside these critical sections, the style is relaxed with nice expressive functional Scala code.


### Conclusion (so far)
  Scala is not good candidate language for ACM contests yet due to startup times. Also, code in time critical sections tends to be similar to C++ imperative programming one with loops working on mutable datastructures.
