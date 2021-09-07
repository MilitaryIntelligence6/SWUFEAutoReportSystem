package cn.misection.autoreport.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import cn.misection.autoreport.R;
import cn.misection.autoreport.databinding.ActivityMainBinding;
import cn.misection.autoreport.entity.ReportInfo;
import cn.misection.util.oututil.system.AppSystem;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mBinding;

    private ReportInfo info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        initBinding();
    }

    private void initBinding() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    }

    public void onSubmitButtonClicked(View view) {
        Intent intent = new Intent(this, WebViewActivity.class);
        intent.putExtra("reportInfo", new ReportInfo());
        startActivity(intent);
    }
}