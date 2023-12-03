package org.devio.flutter.splashscreen

import android.app.Activity
import android.app.Dialog
import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.WindowManager
import org.devio.flutter.flutter_splash_screen.R
import java.lang.ref.WeakReference


/**
 * SplashScreen
 * 启动屏
 * from：https://www.geekailab.com/
 * Author:CrazyCodeBoy
 * GitHub:https://github.com/crazycodeboy
 * Email:crazycodeboy@gmail.com
 */
object SplashScreen {
    private var mSplashDialog: Dialog? = null
    private var mActivity: WeakReference<Activity>? = null

    /**
     * 打开启动屏
     */
    fun show(activity: Activity?, themeResId: Int, fullScreen: Boolean) {
        if (activity == null) return
        mActivity = WeakReference(activity)
        activity.runOnUiThread(Runnable {
            if (!activity.isFinishing) {
                mSplashDialog = Dialog(activity, themeResId)
                if (fullScreen) enableFullScreen(mSplashDialog)
                mSplashDialog?.setContentView(R.layout.launch_screen)
                mSplashDialog?.setCancelable(false)
                if (mSplashDialog?.isShowing == false) {
                    mSplashDialog?.show()
                }
            }
        })
    }
    /**
     * 打开启动屏
     */
    /**
     * 打开启动屏
     */
    @JvmOverloads
    fun show(activity: Activity?, fullScreen: Boolean = false) {
        val resourceId: Int = R.style.SplashScreen_SplashTheme
        show(activity, resourceId, fullScreen)
    }

    /**
     * 关闭启动屏
     */
    fun hide(target: Activity?) {
        var activity = target
        if (activity == null) {
            if (mActivity == null) {
                return
            }
            activity = mActivity!!.get()
        }
        if (activity == null) return
        activity.runOnUiThread {
            if (mSplashDialog != null && mSplashDialog!!.isShowing) {
                var isDestroyed = false
                isDestroyed = activity.isDestroyed
                if (!activity.isFinishing && !isDestroyed) {
                    mSplashDialog!!.dismiss()
                }
                mSplashDialog = null
            }
        }
    }

    private fun enableFullScreen(dialog: Dialog?) {
        val window = dialog!!.window
        if (window != null) {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
            window.decorView.setPadding(0, 0, 0, 0)
            window.decorView.setBackgroundColor(Color.WHITE)
            val layoutParams = window.attributes
            layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT
            layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT
            if (Build.VERSION.SDK_INT
                >= Build.VERSION_CODES.P
            ) {
                // 延伸显示区域到刘海
                val lp = window.attributes
                lp.layoutInDisplayCutoutMode =
                    WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
                window.attributes = lp
                // 设置页面全屏显示
                val decorView = window.decorView
                decorView.systemUiVisibility =
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            }
            window.attributes = layoutParams
        }
    }
}

