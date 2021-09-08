package cn.misection.autoreport.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.xuexiang.xui.XUI;

import java.util.Calendar;

import cn.misection.autoreport.R;
import cn.misection.autoreport.constant.EnumCampus;
import cn.misection.autoreport.databinding.ActivityMainBinding;
import cn.misection.autoreport.entity.ReportInfo;
import cn.misection.autoreport.entity.SwufeUser;

/**
 * @author Administrator
 */
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mBinding;

    private SwufeUser user;

    private ReportInfo info;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        XUI.initTheme(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        flushStartTime();
    }

    private void init() {
        initBinding();
        initUI();
        initData();
        initActionListener();
    }

    private void initBinding() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    }

    private void initUI() {
        initStartTimePicker();
        initEndTimePicker();
        initRadioButtonState();
    }

    private void initData() {
        user = SwufeUser.getInstance();
        info = new ReportInfo.Builder().create();
    }

    private void initStartTimePicker() {
        mBinding.startTimePicker.setIs24HourView(true);
        flushStartTime();
    }

    private void initEndTimePicker() {
        mBinding.endTimePicker.setIs24HourView(true);
        flushEndTime();
    }

    private void initRadioButtonState() {
        mBinding.campusLiulinRadioButton.setChecked(true);
    }

    private void initActionListener() {
        mBinding.usernameEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mBinding.passwordEt.addTextChangedListener(
                new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                    }
                }
        );

        mBinding.campusRadioGroup.setOnCheckedChangeListener(
                (group, checkedId) -> {
                    switch (checkedId) {
                        case R.id.campus_liulin_radio_button:
                            info.setCampus(EnumCampus.LIU_LIN);
                            break;
                        case R.id.campus_guanghua_radio_button:
                            info.setCampus(EnumCampus.GUANG_HUA);
                            break;
                        default:
                            break;
                    }
                }
        );

        mBinding.startTimePicker.setOnTimeChangedListener(
                (view, hourOfDay, minute) -> {
                    info.setStartTime(String.format("%02d:%02d", hourOfDay, minute));
                }
        );

        mBinding.endTimePicker.setOnTimeChangedListener(
                (view, hourOfDay, minute) -> {
                    info.setEndTime(String.format("%02d:%02d", hourOfDay, minute));
                }
        );
    }

    @SuppressLint("Range")
    private void flushStartTime() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE) - 1;
        if (minute == -1) {
            minute += 60;
            --hour;
            if (hour < 0) {
                hour += 24;
            }
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