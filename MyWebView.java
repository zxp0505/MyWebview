package cn.com.ethank.PMSMaster.app.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import cn.com.ethank.mylibrary.resourcelibrary.core.sys.CoyoteSystem;
import cn.com.ethank.mylibrary.resourcelibrary.core.sys.SysInfoImp;

/**
 * Created by ping on 2017/4/14.
 */

public class MyWebView extends WebView {
    private static final String TAG = "MyWebView";
    private float startX;
    private float startY;

    public MyWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initSetting();
    }

    public MyWebView(Context context) {
        super(context);
        initSetting();
    }

    public MyWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initSetting();
    }

    private void initSetting() {
        String content = getSettings().getUserAgentString();
        if (content.isEmpty()) {
            content = "";
        }
        getSettings().setUserAgentString(content + "ethank-browser-Android-" + ((SysInfoImp) CoyoteSystem.getCurrent().getSysInfo()).getAppVersionName());
//        getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        getSettings().setJavaScriptEnabled(true);
        setVerticalScrollBarEnabled(false);
        setHorizontalScrollBarEnabled(false);
        setVerticalScrollbarOverlay(false);
        setHorizontalScrollbarOverlay(false);
//        wb.setOverScrollMode(OVER_SCROLL_NEVER);
        getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        getSettings().setUseWideViewPort(true);
        getSettings().setBuiltInZoomControls(false);
        getSettings().setCacheMode(android.webkit.WebSettings.LOAD_DEFAULT);
        getSettings().setSupportMultipleWindows(true);
//		webSettings.setMediaPlaybackRequiresUserGesture(false);
        getSettings().setGeolocationEnabled(true);
        getSettings().setDatabaseEnabled(true);
        getSettings().setAllowContentAccess(true);
//        wb.getSettings().setGeolocationDatabasePath(dir);
        getSettings().setDomStorageEnabled(true);
//-------------屏幕适配
//        getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        //LayoutAlgorithm是一个枚举用来控制页面的布局，有三个类型：
        //1.NARROW_COLUMNS：可能的话使所有列的宽度不超过屏幕宽度
        //2.NORMAL：正常显示不做任何渲染
        //3.SINGLE_COLUMN：把所有内容放大webview等宽的一列中
        //用SINGLE_COLUMN类型可以设置页面居中显示，页面可以放大缩小，但这种方法不怎么
        //好，有时候会让你的页面布局走样而且我测了一下，只能显示中间那一块，超出屏幕的部分
        //都不能显示。
        //b:加上这两句基本上就可以做到屏幕适配了
//        getSettings().setUseWideViewPort(true);
        getSettings().setLoadWithOverviewMode(true);
//        ------------------------
    }

    public void setTextSize(int size) {
        getSettings().setTextZoom(size);
    }

    /**
     * 设置缩放
     */
    public void setZoom() {
        getSettings().setSupportZoom(true);
        getSettings().setBuiltInZoomControls(true);//支持缩放
        getSettings().setDisplayZoomControls(false);//不显示放大缩小的图标
    }

    /**
     * webview释放
     */
    public void destroyWebview() {
        removeAllViews();
        ViewGroup parent = (ViewGroup) getParent();
        if (parent != null) {
            parent.removeView(this);
        }
        setTag(null);
        clearHistory();
        destroy();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = event.getX();
                startY = event.getY();
                Log.e(TAG, "ACTION_DOWN;;;;" + "事件传给自己了");
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_MOVE:
                float offestX = Math.abs(event.getX() - startX);
                float offestY = Math.abs(event.getY() - startY);
                if (offestX > offestY) {
                    Log.e(TAG, "ACTION_MOVE;;;;" + "事件传给自己了");
                    getParent().requestDisallowInterceptTouchEvent(true);
                } else {
                    Log.e(TAG, "ACTION_MOVE;;;;" + "事件传给父类了");
                    getParent().requestDisallowInterceptTouchEvent(false);
                }
                break;
            case MotionEvent.ACTION_UP:
                Log.e(TAG, "ACTION_UP;;;;" + "事件传给父类了");
                getParent().requestDisallowInterceptTouchEvent(false);
                break;
        }
        return super.onTouchEvent(event);
    }
}
