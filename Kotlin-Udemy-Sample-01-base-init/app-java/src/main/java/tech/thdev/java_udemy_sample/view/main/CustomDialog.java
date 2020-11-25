package tech.thdev.java_udemy_sample.view.main;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

import tech.thdev.java_udemy_sample.R;

public class CustomDialog extends Dialog {

    Context mContext;
    WebView webView;
    private WebSettings webSettings;

    public CustomDialog(Context context) {
        super(context);
        mContext = context;
    }

    public CustomDialog(Context context, int res) {
        super(context, res);
        requestWindowFeature(Window.FEATURE_NO_TITLE);   //다이얼로그의 타이틀바를 없애주는 옵션입니다.
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));  //다이얼로그의 배경을 투명으로 만듭니다.


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.customdialog);     //다이얼로그에서 사용할 레이아웃입니다.

        webView = findViewById(R.id.wb);

        webView.setWebViewClient(new WebViewClient());

        webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webView.loadUrl("http://daum.net");


    }


}
