package tun.app.library.utils;

import android.util.Log;

import java.util.logging.Logger;

import tun.app.library.BuildConfig;

/**
 * Created by nourjbel on 26/02/2015.
 */
public class JFLog {

    private static final String TAG = Logger.class.getSimpleName();
    private static final String EMPTY = "";


    public static void d(String tag, String msg) {
       if (JFBuildConfig.DEBUG) {
            Log.d(tag, msg);
        }
    }
    public static void e(String tag, String msg) {
        if (JFBuildConfig.DEBUG) {
            Log.e(tag, msg);
        }
    }
    public static void i(String tag, String msg) {
        if (JFBuildConfig.DEBUG) {
            Log.i(tag, msg);
        }
    }
    public static void v(String tag, String msg) {
        if (JFBuildConfig.DEBUG) {
            Log.v(tag, msg);
        }
    }
    public static void w(String tag, String msg) {
        if (JFBuildConfig.DEBUG) {
            Log.w(tag, msg);
        }
    }

  }
