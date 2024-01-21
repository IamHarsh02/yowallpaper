# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
# Keep the entry point in the main class
-keep public class com.example.yowallpaper.MainActivity {

}

# Keep all public and protected methods in Activity subclasses
-keepclassmembers class * extends android.app.Activity {
    public <methods>;
}
-keepclassmembers class com.example.yowallpaper.CategoryRVModal { *; }
-keepclassmembers class com.example.yowallpaper.WallpaperRVAdaptor { *; }
-keepclassmembers class com.example.yowallpaper.CategoryRVAdaptor { *; }
-keepclassmembers class com.example.yowallpaper.MainActivity { *; }
-keepclassmembers class com.example.yowallpaper.DeveloperActivity { *; }
-keepclassmembers class com.example.yowallpaper.R { *; }