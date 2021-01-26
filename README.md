# Plugins for a simpler Gradle life ![Gradle build](https://github.com/CarlosR-B/plugins/workflows/Gradle%20build/badge.svg?branch=main)

Plugins is a super-secret military-grade repository for the best plugin(s) in the Gradle world.

## Versioning plugin

This is a very simple, somewhat useful plugin to automatically change the semantic version number on your build.gradle.

Why would you want that, you ask? Well, this mostly came up as a use case when dealing with workflow automations and
pipelines. Say, you want to increment the patch version number whenever a new commit makes it into the develop branch
(because, you know, reasons), then this is the plugin for you!

Also, you can just manually invoke any of the tasks provided because maybe you are lazy (like yours truly here) and do
not like changing version numbers manually.

On a more serious note, it might be the case that you have some sort of dyslexia or that making a mistake makes you
anxious, this plugin might ease some of that.

### Assumptions

Basically, the plugin expects a `version` field inside your `build.gradle` file with a semantic versioning style version
number. It should support any of the following version number styles:

- `major.minor.patch`
- `major.minor.patch-LABEL`
- `major.minor`
- `major.minor-LABEL`

### Usage

The plugin provides three tasks.

- `incrementMajorVersion`: when invoked, the task increments the major number by one and set any other numbers to zero,
  respecting whether there are patch numbers or labels.  
  For example:
    - `1.2 => 2.0`
    - `1.2.4-RELEASE => 2.0.0-RELEASE`

- `incrementMinorVersion`: when invoked, the task increments the minor number by one and set the patch number to zero (
  if it exists), respecting whether there are labels attached.  
  For example:
    - `3.2 => 3.3`
    - `2.2.4-RELEASE => 2.3.0-RELEASE`

- `incrementPatchVersion`: when invoked, the task increments the patch number by one if it exists or creates one with a
  zero value if it does not, also respecting the labels.  
  For example:
    - `3.2 => 3.2.0`
    - `2.2.4-RELEASE => 2.2.5-RELEASE`

### Known Issues

- The version number is matched exactly inside the `build.gradle` file, which means that, if we find that version number
  before the version field's one, then the former one will get changed instead of the `version` field one.  
  For most use cases, this should not be a big problem, as the version number usually comes right after the plugins, so
  there is not that big of a chance to find the exact same number on the `plugins` closure.  
  If using groovy, you can also write all your version numbers inside a properties file, which solves the problem
  altogether.

### Related Links

[Gradle Versioning Plugin page](https://plugins.gradle.org/plugin/dev.quiescence.plugins.versioning)
