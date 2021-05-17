package tech.thdev.kotlin_udemy_sample.view.main;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;


// java -> kotlin value 호출.
class TestJavaActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainActivityKt.getKotlinValue(TestJavaActivity.this);

    }
}
