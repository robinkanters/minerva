
Minerva
=======

_Dynamic data flows in Kotlin DSLs._

[![Build Status](https://travis-ci.org/robinkanters/minerva.svg?branch=develop)](https://travis-ci.org/robinkanters/minerva)
[![codecov](https://codecov.io/gh/robinkanters/minerva/branch/develop/graph/badge.svg)](https://codecov.io/gh/robinkanters/minerva)

Getting started
===============

Flows are expressed in Kotlin (or Kotlin Script) and can be invoked like functions.

To create a basic flow, use the `flow` function to create a flow and add components in the body. Here's a simple flow to
multiply a list of integers by 2:

```kotlin
val flow = flow<List<Int>> {
    // forEach creates a subflow of Flow<Int> so that you can work with items individually
    forEach {
        // multiply the integer by 2
        map { n -> n * 2 }
    }
}
```

At the end of the `forEach`, the list is automatically reconstructed with the changed contents.

You can call the flow above like this:

```kotlin
val list = listOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)

val multipliedList = flow(list)
```

The `multipliedList` now contains: `0, 2, 4, 6, 8, 10, 12, 14, 16, 18`.

Components
==========

`debug` component
---------------

The `debug` component is, obviously, meant to debug your flows. Put it anywhere inside a flow or subflow and it will
print the current payload (`.toString()` if necessary) to the console, like this:

```kotlin
data class Person(val name: String)

val flow = flow<Person> {
    debug()
}

val john = Person("John")
flow(john) // will print "Person(name=John)" to console
```

`filter` component
----------------

_(Only applies to `Flow<List<T>>`)_

Filters a list payload based on an expression.

The items are filtered asynchronously, but are put back in the payload in order.

For example, filter a `List<Int>` flow and only keep even numbers:

```kotlin
val flow = flow<List<Int>> {
    filter { it % 2 == 0 }
}

val numbers = (1..10).toList()
val evenNumbers = flow(numbers) // only contains 2, 4, 6, 8, 10
```

`forEach` component
----------------

_(Only applies to `Flow<List<T>>`)_

The `forEach` component allows you to build a subflow for individual flow items. At the end, the `List` is rebuilt and
you can continue to work with your `List` payload after the `forEach` block.

For example, loop through a `List<String>` flow and print them all individually:

```kotlin
val flow = flow<List<String>> {
    forEach {
        debug()
    }
}

val words = listOf("Foo", "Bar", "Baz")
flow(words) // will print "Foo", "Bar" and "Baz" in non-deterministic order
```

By default, `forEach` runs asynchronously, but you can change this with the `async` parameter. 
