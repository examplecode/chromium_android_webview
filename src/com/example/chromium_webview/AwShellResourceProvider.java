// Copyright (c) 2012 The Chromium Authors. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

package com.example.chromium_webview;

import android.content.Context;

import org.chromium.android_webview.AwResource;

public class AwShellResourceProvider {
    private static boolean sInitialized = false;

    public static void registerResources(Context context) {
        if (sInitialized) {
            return;
        }

        AwResource.setResources(context.getResources());

        AwResource.RAW_LOAD_ERROR = R.raw.blank_html;
        AwResource.RAW_NO_DOMAIN = R.raw.blank_html;

        AwResource.STRING_DEFAULT_TEXT_ENCODING = R.string.hello_world;

        sInitialized = true;
    }
}
