# MyWebview

# usage: #

## 初始化 ##


		private void initWebView(String data) {

        mWebView = new MyWebView(this);
		//适配字体大小
        int sdkInt = Build.VERSION.SDK_INT;
        if (sdkInt > 21) {
            mWebView.setTextSize(220);
        }
        //mWebView.setZoom();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mWebView.setLayoutParams(params);
        llWebview.addView(mWebView);
		//mWebView.loadUrl(url);
        mWebView.loadDataWithBaseURL("", data, "text/html", "utf-8", null);//加载的是5内容
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                LoggerUtil.e(url);
               view.loadUrl(url);
                return true;
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
            }

            @Override
            public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
                super.onReceivedHttpError(view, request, errorResponse);
            }
        });
    }

## 结束时注意销毁 ##
    
	 @Override
    protected void onDestroy() {
        if (mWebView != null) {
            mWebView.destroyWebview();
            mWebView = null;
        }
        super.onDestroy();
    }