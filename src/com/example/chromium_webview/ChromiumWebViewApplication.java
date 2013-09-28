// Copyright (c) 2012 The Chromium Authors. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

package com.example.chromium_webview;

import android.app.Application;

import android.os.Debug;
import android.util.Log;

import org.chromium.android_webview.AwBrowserProcess;
import org.chromium.content.browser.ResourceExtractor;
import org.chromium.content.common.CommandLine;

public class ChromiumWebViewApplication extends Application {

    private static final String TAG = "AwShellApplication";
    /** The minimum set of .pak files the test runner needs. */
    private static final String[] MANDATORY_PAKS = {
        "webviewchromium.pak", "en-US.pak"
    };

    @Override
    public void onCreate() {
        super.onCreate();

        AwShellResourceProvider.registerResources(this);



        ResourceExtractor.setMandatoryPaksToExtract(MANDATORY_PAKS);
        ResourceExtractor.setExtractImplicitLocaleForTesting(false);
        AwBrowserProcess.loadLibrary();
        AwBrowserProcess.start(this);
    }
}
