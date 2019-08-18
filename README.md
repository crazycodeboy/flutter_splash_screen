# flutter_splash_screen


[![Download](https://img.shields.io/badge/Download-v0.1.0-ff69b4.svg) ](https://pub.dartlang.org/packages/flutter_splash_screen)
[ ![PRs Welcome](https://img.shields.io/badge/PRs-Welcome-brightgreen.svg)](https://github.com/crazycodeboy/flutter_splash_screen/pulls)
[ ![flutter_splash_screen release](https://img.shields.io/github/release/crazycodeboy/flutter_splash_screen.svg?maxAge=2592000?style=flat-square)](https://github.com/crazycodeboy/GitHubPopular/releases)
[ ![语言 中文](https://img.shields.io/badge/语言-中文-feb252.svg)](https://github.com/crazycodeboy/flutter_splash_screen/blob/master/README.zh.md)
[![License MIT](http://img.shields.io/badge/license-MIT-orange.svg?style=flat)](https://raw.githubusercontent.com/crazycodeboy/flutter-check-box/master/LICENSE)
[ ![RN](https://img.shields.io/badge/react-native-brightgreen.svg)](https://github.com/crazycodeboy/react-native-splash-screen)

A splash screen API for flutter which can programatically hide and show the splash screen. Works on Android and iOS.

## Content

- [Changes](#changes)
- [Installation](#installation)
- [Examples](#examples)
- [Getting started](#getting-started)
- [API](#api)
- [Testing](#testing)
- [Troubleshooting](#troubleshooting)
- [Contribution](#contribution)


## Changes



## Examples  
* [Examples](https://github.com/crazycodeboy/flutter_splash_screen/tree/master/example)

![flutter_splash_screen-Android](https://raw.githubusercontent.com/crazycodeboy/react-native-splash-screen/v3.0.0/examples/Screenshots/react-native-splash-screen-Android.gif)
![flutter_splash_screen-iOS](https://raw.githubusercontent.com/crazycodeboy/react-native-splash-screen/v3.0.0/examples/Screenshots/react-native-splash-screen-iOS.gif)



## Installation

### 1. Depend on it

Add this to your package's pubspec.yaml file:

```dart
dependencies:
  flutter_splash_screen: ^xxx
```

### 2. Install it
You can install packages from the command line:

with Flutter:

```bash
$ flutter packages get
```

Alternatively, your editor might support flutter packages get. Check the docs for your editor to learn more.


### 3. Plugin Configuration

#### Android

Update the `MainActivity.java` to use `flutter_splash_screen` via the following changes:

```java
package org.devio.flutter.splashscreen.example;
import android.os.Bundle;

+ import org.devio.flutter.splashscreen.SplashScreen;

import io.flutter.app.FlutterActivity;
import io.flutter.plugins.GeneratedPluginRegistrant;

public class MainActivity extends FlutterActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
 +       SplashScreen.show(this, true);// here
        super.onCreate(savedInstanceState);
        GeneratedPluginRegistrant.registerWith(this);
    }
}
```

#### iOS

No need for this step.

## Getting started  

### Import it

Now in your Dart code, you can use:

```
import 'package:flutter_splash_screen/flutter_splash_screen.dart';
```

### Android:

Create a file called `launch_screen.xml` in `app/src/main/res/layout` (create the `layout`-folder if it doesn't exist). The contents of the file should be the following:

```xml
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="vertical" android:layout_width="match_parent"
    android:layout_height="match_parent">
    <ImageView android:layout_width="match_parent" android:layout_height="match_parent" android:src="@drawable/launch_screen" android:scaleType="centerCrop" />
</RelativeLayout>
```

Customize your launch screen by creating a `launch_screen.png`-file and placing it in an appropriate `drawable`-folder. Android automatically scales drawable, so you do not necessarily need to provide images for all phone densities.
You can create splash screens in the following folders:
* `drawable-ldpi`
* `drawable-mdpi`
* `drawable-hdpi`
* `drawable-xhdpi`
* `drawable-xxhdpi`
* `drawable-xxxhdpi`

Add a color called `primary_dark` in `app/src/main/res/values/colors.xml`

```
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <color name="primary_dark">#000000</color>
</resources>
```


**Optional steps：**

If you want the splash screen to be transparent, follow these steps.

Open `android/app/src/main/res/values/styles.xml` and add `<item name="android:windowIsTranslucent">true</item>` to the file. It should look like this:

```xml
<resources>
    <!-- Base application theme. -->
    <style name="AppTheme" parent="Theme.AppCompat.Light.NoActionBar">
        <!-- Customize your theme here. -->
        <!--设置透明背景-->
        <item name="android:windowIsTranslucent">true</item>
    </style>
</resources>
```

**To learn more see [examples](https://github.com/crazycodeboy/flutter_splash_screen/tree/master/example)**


If you want to customize the color of the status bar when the splash screen is displayed:

Create `android/app/src/main/res/values/colors.xml` and add

```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <color name="status_bar_color"><!-- Colour of your status bar here --></color>
</resources>
```

Create a style definition for this in `android/app/src/main/res/values/styles.xml`:

```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <style name="SplashScreenTheme" parent="SplashScreen_SplashTheme">
        <item name="colorPrimaryDark">@color/status_bar_color</item>
    </style>
</resources>
```

Change your `show` method to include your custom style:
```java
SplashScreen.show(this, R.style.SplashScreenTheme);
```

### iOS    

Customize your splash screen via  `LaunchScreen.storyboard`,

**Learn more to see [examples](https://github.com/crazycodeboy/flutter_splash_screen/tree/master/example)**

## Usage

Use like so:

```dart
import 'dart:async';

import 'package:flutter/material.dart';
import 'package:flutter_splash_screen/flutter_splash_screen.dart';

void main() => runApp(MyApp());

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  @override
  void initState() {
    super.initState();
    hideScreen();
  }

  ///hide your splash screen
  Future<void> hideScreen() async {
    Future.delayed(Duration(milliseconds: 3600), () {
      FlutterSplashScreen.hide();
    });
  }
...
```

## API


| Method | Type     | Optional | Description                         |
|--------|----------|----------|-------------------------------------|
| show() | function | false    | Open splash screen (Native Method ) |
| hide() | function | false    | Close splash screen                 |

## Testing



## Contribution

Issues are welcome. Please add a screenshot of you bug and a code snippet. Quickest way to solve issue is to reproduce it in one of the examples.

Pull requests are welcome. If you want to change the API or do something big it is best to create an issue and discuss it first.

---

**[MIT Licensed](https://github.com/crazycodeboy/flutter_splash_screen/blob/master/LICENSE)**
