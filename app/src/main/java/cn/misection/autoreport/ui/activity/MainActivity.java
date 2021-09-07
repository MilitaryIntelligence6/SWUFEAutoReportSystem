package cn.misection.autoreport.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import java.util.Calendar;

import cn.misection.autoreport.R;
import cn.misection.autoreport.constant.EnumCampus;
import cn.misection.autoreport.databinding.ActivityMainBinding;
import cn.misection.autoreport.entity.ReportInfo;

/**
 * @author Administrator
 */
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
        initStartTimePicker();
        initEndTimePicker();
    }

    private void initBinding() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    }

    private void initStartTimePicker() {
        mBinding.startTimePicker.setIs24HourView(true);
        flushStartTime();
    }

    private void initEndTimePicker() {
        mBinding.endTimePicker.setIs24HourView(true);
        flushEndTime();
    }

    private void flushStartTime() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE) - 1;
        if (minute == -1) {
            minute += 60;
            --hour;
        }
        mBinding.startTimePicker.setHour(hour);
        mBinding.startTimePicker.setMinute(minute);
    }

    private void flushEndTime() {
        // FIXME: 2021/9/7 magic number;
        mBinding.endTimePicker.setHour(22);
        mBinding.endTimePicker.setMinute(55);
    }

    public void onSubmitButtonClicked(View view) {
        EnumCampus campus = EnumCampus.LIU_LIN;
        String startTimeString = String.format("%s:%s",
                mBinding.startTimePicker.getHour(),
                mBinding.startTimePicker.getMinute());
        String endTimeString = String.format("%s:%s",
                mBinding.endTimePicker.getHour(),
                mBinding.endTimePicker.getMinute());
        String destination = String.valueOf(mBinding.customDestinationEt.getText());
        String transportation = String.valueOf(mBinding.customTransportationEt.getText());
        String reason = String.valueOf(mBinding.customReasonEt.getText());
        if (destination == null || destination.equals(getString(R.string.empty))) {
            destination = getString(R.string.default_destination_hint);
        }
        if (transportation == null || transportation.equals(getString(R.string.empty))) {
            transportation = getString(R.string.default_transportation_hint);
        }
        if (reason == null || reason.equals(getString(R.string.empty))) {
            reason = getString(R.string.default_reason_hint);
        }
        ReportInfo reportInfo = new ReportInfo.Builder()
                .setCampus(campus)
                .setStartTime(startTimeString)
                .setEndTime(endTimeString)
                .setDestination(destination)
                .setTransportation(transportation)
                .setReason(reason)
                .create();
        Intent intent = new Intent(this, WebViewActivity.class);
        intent.putExtra(getString(R.string.activity_param_key), reportInfo);
        startActivity(intent);
    }
}