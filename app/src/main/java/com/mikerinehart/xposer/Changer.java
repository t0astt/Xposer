package com.mikerinehart.xposer;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

public class Changer implements IXposedHookLoadPackage {

    public void handleLoadPackage(final LoadPackageParam lpparam) throws Throwable {
        if (!lpparam.packageName.equals("com.mikerinehart.xposeme"))
            return;
        findAndHookMethod("com.mikerinehart.xposeme.MainActivity", lpparam.classLoader, "changeText", new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                XposedBridge.log("Method hooked!");
                XposedHelpers.callMethod(param.thisObject, "xposedChange"); // Call new method afterwards
            }
        });

    }
}
