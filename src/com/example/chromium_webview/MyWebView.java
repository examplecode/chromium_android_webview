package com.example.chromium_webview;



import org.chromium.android_webview.AwLayoutSizer;
import org.chromium.android_webview.AwBrowserContext;
import org.chromium.android_webview.AwContents;
import org.chromium.android_webview.AwHttpAuthHandler;
import org.chromium.android_webview.InterceptedRequestData;
import org.chromium.android_webview.JsPromptResultReceiver;
import org.chromium.android_webview.JsResultReceiver;
import org.chromium.android_webview.AwContentsClient;

import org.chromium.content.browser.ContentViewCore;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Picture;
import android.graphics.Rect;
import android.net.http.SslError;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.webkit.ConsoleMessage;
import android.webkit.ValueCallback;
import android.webkit.GeolocationPermissions.Callback;
import android.widget.FrameLayout;

@SuppressLint("WrongCall")
public class MyWebView extends FrameLayout {
	
	private final static String PREFERENCES_NAME = "webviewPrefs";
	private static AwBrowserContext mBrowserContext;
	
    private AwContents mAwContents;
    private AwContents.InternalAccessDelegate mInternalAccessDelegate;
    
    
    
    private  AwContentsClient awContentsClient = new org.chromium.android_webview.AwContentsClient() {
		
		@Override
		public boolean shouldOverrideUrlLoading(String arg0) {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public boolean shouldOverrideKeyEvent(KeyEvent arg0) {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public InterceptedRequestData shouldInterceptRequest(String arg0) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public void onUnhandledKeyEvent(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onScaleChangedScaled(float arg0, float arg1) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		protected void onRequestFocus() {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onReceivedTouchIconUrl(String arg0, boolean arg1) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onReceivedTitle(String arg0) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onReceivedSslError(ValueCallback<Boolean> arg0, SslError arg1) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onReceivedLoginRequest(String arg0, String arg1, String arg2) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onReceivedIcon(Bitmap arg0) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onReceivedHttpAuthRequest(AwHttpAuthHandler arg0, String arg1,
				String arg2) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onReceivedError(int arg0, String arg1, String arg2) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onProgressChanged(int arg0) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onPageStarted(String arg0) {
			// TODO Auto-generated method stub
			Log.i("webview","Page started >>>>>>>>>>>>>>>>>>>");
		
		}
		
		@Override
		public void onPageFinished(String arg0) {
			// TODO Auto-generated method stub
			Log.i("webview","Page finished >>>>>>>>>>>>>>>>>>>");
		}
		
		@Override
		public void onNewPicture(Picture arg0) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onLoadResource(String arg0) {
			// TODO Auto-generated method stub
			Log.i("webview","Page finished >>>>>>>>>>>>>>>>>>>"+ arg0);
			
		}
		
		@Override
		public void onHideCustomView() {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onGeolocationPermissionsShowPrompt(String arg0, Callback arg1) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onGeolocationPermissionsHidePrompt() {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onFormResubmission(Message arg0, Message arg1) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onFindResultReceived(int arg0, int arg1, boolean arg2) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onDownloadStart(String arg0, String arg1, String arg2,
				String arg3, long arg4) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		protected boolean onCreateWindow(boolean arg0, boolean arg1) {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public boolean onConsoleMessage(ConsoleMessage arg0) {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		protected void onCloseWindow() {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		protected void handleJsPrompt(String arg0, String arg1, String arg2,
				JsPromptResultReceiver arg3) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		protected void handleJsConfirm(String arg0, String arg1,
				JsResultReceiver arg2) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		protected void handleJsBeforeUnload(String arg0, String arg1,
				JsResultReceiver arg2) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		protected void handleJsAlert(String arg0, String arg1, JsResultReceiver arg2) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void getVisitedHistory(ValueCallback<String[]> arg0) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		protected View getVideoLoadingProgressView() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public Bitmap getDefaultVideoPoster() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public void doUpdateVisitedHistory(String arg0, boolean arg1) {
			// TODO Auto-generated method stub
			
		}
	};

	
	
	
    public MyWebView(Context context) {
        super(context);
        mInternalAccessDelegate = new InternalAccessAdapter();
        setOverScrollMode(View.OVER_SCROLL_ALWAYS);
        
        SharedPreferences sharedPreferences =
                context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        if (mBrowserContext == null) {
                mBrowserContext = new AwBrowserContext(sharedPreferences);
         }
        
        mAwContents =  new AwContents(mBrowserContext, this,getInternalAccessDelegate(),
                awContentsClient, false, new AwLayoutSizer(), true);
        
        mAwContents.getSettings().setJavaScriptEnabled(true);
        
    }


    public ContentViewCore getContentViewCore() {
        return mAwContents.getContentViewCore();
    }

    public AwContents getAwContents() {
        return mAwContents;
    }

    public AwContents.InternalAccessDelegate getInternalAccessDelegate() {
        return mInternalAccessDelegate;
    }

    public void destroy() {
        mAwContents.destroy();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mAwContents.onConfigurationChanged(newConfig);
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        mAwContents.onAttachedToWindow();
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mAwContents.onDetachedFromWindow();
    }

    @Override
    public void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
        mAwContents.onFocusChanged(focused, direction, previouslyFocusedRect);
    }

    @Override
    public InputConnection onCreateInputConnection(EditorInfo outAttrs) {
        return mAwContents.onCreateInputConnection(outAttrs);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        return mAwContents.onKeyUp(keyCode, event);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        return mAwContents.dispatchKeyEvent(event);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mAwContents.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public void onSizeChanged(int w, int h, int ow, int oh) {
        super.onSizeChanged(w, h, ow, oh);
        mAwContents.onSizeChanged(w, h, ow, oh);
    }

    @Override
    public void onOverScrolled(int scrollX, int scrollY, boolean clampedX, boolean clampedY) {
        mAwContents.onContainerViewOverScrolled(scrollX, scrollY, clampedX, clampedY);
    }

    @Override
    public void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (mAwContents != null) {
            mAwContents.onContainerViewScrollChanged(l, t, oldl, oldt);
        }
    }

    @Override
    public void computeScroll() {
        mAwContents.computeScroll();
    }

    @Override
    public void onVisibilityChanged(View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        mAwContents.onVisibilityChanged(changedView, visibility);
    }

    @Override
    public void onWindowVisibilityChanged(int visibility) {
        super.onWindowVisibilityChanged(visibility);
        mAwContents.onWindowVisibilityChanged(visibility);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        super.onTouchEvent(ev);
        return mAwContents.onTouchEvent(ev);
    }

    @Override
    public void onDraw(Canvas canvas) {
        mAwContents.onDraw(canvas);
        super.onDraw(canvas);
    }

    // TODO: AwContents could define a generic class that holds an implementation similar to
    // the one below.
    private class InternalAccessAdapter implements AwContents.InternalAccessDelegate {

        @Override
        public boolean drawChild(Canvas canvas, View child, long drawingTime) {
            return MyWebView.super.drawChild(canvas, child, drawingTime);
        }

        @Override
        public boolean super_onKeyUp(int keyCode, KeyEvent event) {
            return MyWebView.super.onKeyUp(keyCode, event);
        }

        @Override
        public boolean super_dispatchKeyEventPreIme(KeyEvent event) {
            return MyWebView.super.dispatchKeyEventPreIme(event);
        }

        @Override
        public boolean super_dispatchKeyEvent(KeyEvent event) {
            return MyWebView.super.dispatchKeyEvent(event);
        }

        @Override
        public boolean super_onGenericMotionEvent(MotionEvent event) {
            return MyWebView.super.onGenericMotionEvent(event);
        }

        @Override
        public void super_onConfigurationChanged(Configuration newConfig) {
        	MyWebView.super.onConfigurationChanged(newConfig);
        }

        @Override
        public void super_scrollTo(int scrollX, int scrollY) {
            // We're intentionally not calling super.scrollTo here to make testing easier.
        	MyWebView.this.scrollTo(scrollX, scrollY);
        }

        @Override
        public void overScrollBy(int deltaX, int deltaY,
                int scrollX, int scrollY,
                int scrollRangeX, int scrollRangeY,
                int maxOverScrollX, int maxOverScrollY,
                boolean isTouchEvent) {
            // We're intentionally not calling super.scrollTo here to make testing easier.
        	MyWebView.this.overScrollBy(deltaX, deltaY, scrollX, scrollY,
                     scrollRangeX, scrollRangeY, maxOverScrollX, maxOverScrollY, isTouchEvent);
        }

        @Override
        public void onScrollChanged(int l, int t, int oldl, int oldt) {
        	MyWebView.super.onScrollChanged(l, t, oldl, oldt);
        }

        @Override
        public boolean awakenScrollBars() {
            return MyWebView.super.awakenScrollBars();
        }

        @Override
        public boolean super_awakenScrollBars(int startDelay, boolean invalidate) {
            return MyWebView.super.awakenScrollBars(startDelay, invalidate);
        }

        @Override
        public void setMeasuredDimension(int measuredWidth, int measuredHeight) {
        	MyWebView.super.setMeasuredDimension(measuredWidth, measuredHeight);
        }

        @Override
        public int super_getScrollBarStyle() {
            return MyWebView.super.getScrollBarStyle();
        }

        @Override
        public boolean requestDrawGL(Canvas canvas) {
            return false;
        }
    }
	
	

}
