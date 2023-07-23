package org.devio.flutter.splashscreen;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import org.devio.flutter.splashscreen.flutter_splash_screen.R;
import java.lang.ref.WeakReference;

/**
 * SplashScreen
 * 启动屏
 * from：https://www.geekailab.com/
 * Author:CrazyCodeBoy
 * GitHub:https://github.com/crazycodeboy
 * Email:crazycodeboy@gmail.com
 */
public class SplashScreen {
    private static Dialog mSplashDialog;
    private static WeakReference<Activity> mActivity;

    /**
     * 打开启动屏
     */
    public static void show(final Activity activity, final int themeResId, boolean fullScreen) {
        if (activity == null) return;
        mActivity = new WeakReference<>(activity);
        activity.runOnUiThread(() -> {
            if (!activity.isFinishing()) {
                mSplashDialog = new Dialog(activity, themeResId);
                if (fullScreen) enableFullScreen(mSplashDialog);
                mSplashDialog.setContentView(R.layout.launch_screen);
                mSplashDialog.setCancelable(false);

                if (!mSplashDialog.isShowing()) {
                    mSplashDialog.show();
                }
            }
        });
    }

    /**
     * 打开启动屏
     */
    public static void show(final Activity activity, final boolean fullScreen) {
        int resourceId = R.style.SplashScreen_SplashTheme;

        show(activity, resourceId, fullScreen);
    }

    /**
     * 打开启动屏
     */
    public static void show(final Activity activity) {
        show(activity, false);
    }

    /**
     * 关闭启动屏
     */
    public static void hide(Activity activity) {
        if (activity == null) {
            if (mActivity == null) {
                return;
            }
            activity = mActivity.get();
        }

        if (activity == null) return;

        final Activity _activity = activity;

        _activity.runOnUiThread(() -> {
            if (mSplashDialog != null && mSplashDialog.isShowing()) {
                boolean isDestroyed = false;

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    isDestroyed = _activity.isDestroyed();
                }

                if (!_activity.isFinishing() && !isDestroyed) {
                    mSplashDialog.dismiss();
                }
                mSplashDialog = null;
            }
        });
    }

    private static void enableFullScreen(Dialog dialog) {
        Window window = dialog.getWindow();
        if (window != null) {
            window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
            window.getDecorView().setPadding(0, 0, 0, 0);
            window.getDecorView().setBackgroundColor(Color.WHITE);
            WindowManager.LayoutParams layoutParams = window.getAttributes();
            layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
            layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT;
            if (Build.VERSION.SDK_INT
                    >= Build.VERSION_CODES.P) {
                // 延伸显示区域到刘海
                WindowManager.LayoutParams lp =
                        window.getAttributes();
                lp.layoutInDisplayCutoutMode = WindowManager
                        .LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
                window.setAttributes
                        (lp);
                // 设置页面全屏显示
                final View decorView = window.getDecorView();
                decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View
                        .SYSTEM_UI_FLAG_LAYOUT_STABLE);
            }
            window.setAttributes(layoutParams);
        }
    }
}

