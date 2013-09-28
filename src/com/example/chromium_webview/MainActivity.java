package com.example.chromium_webview;


import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;


import org.chromium.content.browser.LoadUrlParams;

public class MainActivity extends Activity {
	
	private final static String INITIAL_URL = "http://3g.sina.com.cn";
	
    private EditText mUrlTextView;
    private ImageButton mPrevButton;
    private ImageButton mNextButton;
    private MyWebView mWebview ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mWebview = new MyWebView(this);
		
		mWebview.setLayoutParams(new FrameLayout.LayoutParams(
	                LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));

		
		LinearLayout contentContainer = (LinearLayout) findViewById(R.id.content_container);
		contentContainer.addView(mWebview);
	
		mWebview.requestFocus();
		
        initializeUrlField();
        initializeNavigationButtons();
		
		mWebview.getAwContents().loadUrl(new LoadUrlParams(INITIAL_URL));
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	
    private void setKeyboardVisibilityForUrl(boolean visible) {
        InputMethodManager imm = (InputMethodManager) getSystemService(
                Context.INPUT_METHOD_SERVICE);
        if (visible) {
            imm.showSoftInput(mUrlTextView, InputMethodManager.SHOW_IMPLICIT);
        } else {
            imm.hideSoftInputFromWindow(mUrlTextView.getWindowToken(), 0);
        }
    }
	
    private void initializeUrlField() {
        mUrlTextView = (EditText) findViewById(R.id.url);
        mUrlTextView.setOnEditorActionListener(new OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((actionId != EditorInfo.IME_ACTION_GO) && (event == null ||
                        event.getKeyCode() != KeyEvent.KEYCODE_ENTER ||
                        event.getKeyCode() != KeyEvent.ACTION_DOWN)) {
                    return false;
                }

                mWebview.getAwContents().loadUrl(
                        new LoadUrlParams(mUrlTextView.getText().toString()));
                mUrlTextView.clearFocus();
                setKeyboardVisibilityForUrl(false);
                mWebview.requestFocus();
                return true;
            }
        });
        mUrlTextView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                setKeyboardVisibilityForUrl(hasFocus);
                mNextButton.setVisibility(hasFocus ? View.GONE : View.VISIBLE);
                mPrevButton.setVisibility(hasFocus ? View.GONE : View.VISIBLE);
                if (!hasFocus) {
                    mUrlTextView.setText(mWebview.getContentViewCore().getUrl());
                }
            }
        });
    }
    
    
    
    private void initializeNavigationButtons() {
        mPrevButton = (ImageButton) findViewById(R.id.prev);
        mPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mWebview.getContentViewCore().canGoBack()) {
                	mWebview.getContentViewCore().goBack();
                }
            }
        });

        mNextButton = (ImageButton) findViewById(R.id.next);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mWebview.getContentViewCore().canGoForward()) {
                	mWebview.getContentViewCore().goForward();
                }
            }
        });
    }

}
