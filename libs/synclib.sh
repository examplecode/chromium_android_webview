

echo "now sync lib file from $BUILDTYPE directory ..."
scp chengkai@chengkai-me.eicp.net:/home/chengkai/chromium_home/chromium/src/out/Release/lib.java/android_webview_java.jar .
scp chengkai@chengkai-me.eicp.net:/home/chengkai/chromium_home/chromium/src/out/Release/lib.java/base_java.jar .
scp chengkai@chengkai-me.eicp.net:/home/chengkai/chromium_home/chromium/src/out/Release/lib.java/content_java.jar .
scp chengkai@chengkai-me.eicp.net:/home/chengkai/chromium_home/chromium/src/out/Release/lib.java/media_java.jar .
scp chengkai@chengkai-me.eicp.net:/home/chengkai/chromium_home/chromium/src/out/Release/lib.java/navigation_interception_java.jar .
scp chengkai@chengkai-me.eicp.net:/home/chengkai/chromium_home/chromium/src/out/Release/lib.java/net_java.jar .
scp chengkai@chengkai-me.eicp.net:/home/chengkai/chromium_home/chromium/src/out/Release/lib.java/web_contents_delegate_android_java.jar .

scp chengkai@chengkai-me.eicp.net:/home/chengkai/chromium_home/chromium/src/out/Release/android_webview_apk/libs/armeabi-v7a/libwebviewchromium.so ./armeabi-v7a/


scp chengkai@chengkai-me.eicp.net:/home/chengkai/chromium_home/chromium/src/out/Release/android_webview_apk/assets/webviewchromium.pak  ../assets


echo "the libs sync success finished "
