import 'dart:async';

import 'package:flutter/material.dart';
import 'package:flutter_splash_screen/flutter_splash_screen.dart';

void main() => runApp(const MyApp());

class MyApp extends StatefulWidget {
  const MyApp({super.key});

  @override
  MyAppState createState() => MyAppState();
}

class MyAppState extends State<MyApp> {
  @override
  void initState() {
    super.initState();
    hideScreen();
  }

  ///hide your splash screen
  Future<void> hideScreen() async {
    Future.delayed(const Duration(milliseconds: 3600), () {
      FlutterSplashScreen.hide();
    });
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('flutter_splash_screen'),
        ),
        body: const Center(
          child: Text(
            'by CrazyCodeBoy',
            style: TextStyle(fontSize: 20),
          ),
        ),
      ),
    );
  }
}
