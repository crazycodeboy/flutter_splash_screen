package com.example.example

import android.os.Bundle
import io.flutter.embedding.android.FlutterActivity
import org.devio.flutter.splashscreen.SplashScreen
/**
 * SplashScreen
 * 启动屏
 * from：http://www.devio.org
 * Author:CrazyCodeBoy
 * GitHub:https://github.com/crazycodeboy
 * Email:crazycodeboy@gmail.com
 */
class MainActivity: FlutterActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        SplashScreen.show(this, true) // here
        super.onCreate(savedInstanceState)
    }
}
