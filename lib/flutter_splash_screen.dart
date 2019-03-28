import 'dart:async';

import 'package:flutter/services.dart';

class FlutterSplashScreen {
  static const MethodChannel _channel =
      const MethodChannel('flutter_splash_screen');

  ///hide splash screen
  static Future<Null> hide() async {
    await _channel.invokeMethod('hide');
  }
}
