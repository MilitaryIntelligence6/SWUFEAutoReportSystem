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

import cn.misection.autoreport.R;
import cn.misection.autoreport.constant.ConstString;
import cn.misection.autoreport.constant.Campus;
import cn.misection.autoreport.constant.IntentParam;
import cn.misection.autoreport.databinding.ActivityMainBinding;
import cn.misection.autoreport.entity.ReportInfo;
import cn.misection.autoreport.entity.SwufeUser;
import cn.misection.autoreport.util.timeutil.HourMinuteUnit;

/**
 * @author Administrator
 */
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mBinding;

    private SwufeUser user;

    private ReportInfo reportInfo;


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
        flushStartTimePicker();
    }

    private void init() {
        initData();
        initBinding();
        initUI();
        initActionListener();
    }

    private void initBinding() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mBinding.setReportInfo(reportInfo);
        mBinding.setUser(user);
    }

    private void initUI() {
        initStartTimePicker();
        initEndTimePicker();
        initRadioButtonState();
    }

    private void initData() {
        user = SwufeUser.getInstance();
        reportInfo = new ReportInfo.Builder().create();
    }

    private void initStartTimePicker() {
        mBinding.startTimePicker.setIs24HourView(true);
        flushStartTimePicker();
    }

    private void initEndTimePicker() {
        mBinding.endTimePicker.setIs24HourView(true);
        flushEndTimePicker();
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
                user.setUsername(String.valueOf(s));
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
                        user.setPassword(String.valueOf(s));
                    }
                }
        );

        mBinding.campusRadioGroup.setOnCheckedChangeListener(
                (group, checkedId) -> {
                    switch (checkedId) {
                        case R.id.campus_liulin_radio_button:
                            reportInfo.setCampus(Campus.LIU_LIN);
                            break;
                        case R.id.campus_guanghua_radio_button:
                            reportInfo.setCampus(Campus.GUANG_HUA);
                            break;
                        default:
                            break;
                    }
                }
        );

        mBinding.startTimePicker.setOnTimeChangedListener(
                (view, hourOfDay, minute) -> {
                    reportInfo.setStartTime(String.format("%02d:%02d", hourOfDay, minute));
                }
        );

        mBinding.endTimePicker.setOnTimeChangedListener(
                (view, hourOfDay, minute) -> {
                    reportInfo.setEndTime(String.format("%02d:%02d", hourOfDay, minute));
                }
        );

        mBinding.customDestinationEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                reportInfo.setDestination(String.valueOf(s));
            }
        });

        mBinding.customTransportationEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                reportInfo.setTransportation(String.valueOf(s));
            }
        });

        mBinding.customReasonEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                reportInfo.setReason(String.valueOf(s));
            }
        });
    }

    @SuppressLint("Range")
    private void flushStartTimePicker() {
        HourMinuteUnit unit = HourMinuteUnit.timePrevMinutesUnit(1);
        mBinding.startTimePicker.setHour(unit.getHourOfDay());
        mBinding.startTimePicker.setMinute(unit.getMinute());
    }

    private void flushEndTimePicker() {
        // FIXME: 2021/9/7 magic number;
        mBinding.endTimePicker.setHour(22);
        mBinding.endTimePicker.setMinute(55);
    }

    public void onSubmitButtonClicked(View view) {
//        Campus campus = Campus.LIU_LIN;
//        String startTimeString = String.format("%s:%s",
//                mBinding.startTimePicker.getHour(),
//                mBinding.startTimePicker.getMinute());
//        String endTimeString = String.format("%s:%s",
//                mBinding.endTimePicker.getHour(),
//                mBinding.endTimePicker.getMinute());
//        String destination = String.valueOf(mBinding.customDestinationEt.getText());
//        String transportation = String.valueOf(mBinding.customTransportationEt.getText());
//        String reason = String.valueOf(mBinding.customReasonEt.getText());
//        if (destination == null || destination.equals(ConstString.EMPTY.value())) {
//            destination = getString(R.string.default_destination_hint);
//        }
//        if (transportation == null || transportation.equals(ConstString.EMPTY.value())) {
//            transportation = getString(R.string.default_transportation_hint);
//        }
//        if (reason == null || reason.equals(ConstString.EMPTY.value())) {
//            reason = getString(R.string.default_reason_hint);
//        }
//        ReportInfo reportInfo = new ReportInfo.Builder()
//                .setCampus(campus)
//                .setStartTime(startTimeString)
//                .setEndTime(endTimeString)
//                .setDestination(destination)
//                .setTransportation(transportation)
//                .setReason(reason)
//                .create();
        if (reportInfo.getCampus() == null) {
            reportInfo.setCampus(Campus.LIU_LIN);
        }
        if (reportInfo.getStartTime() == null
                || reportInfo.getStartTime().equals(ConstString.EMPTY.value())
                || reportInfo.getStartTime().equals(ConstString.NULL.value())) {
            reportInfo.setStartTime(HourMinuteUnit.timePrevMinutesUnit(1).toFormatString());
        }
        if (reportInfo.getEndTime() == null
                || reportInfo.getEndTime().equals(ConstString.EMPTY.value())
                || reportInfo.getEndTime().equals(ConstString.NULL.value())) {
            reportInfo.setEndTime("22:55");
        }
        if (reportInfo.getDestination() == null
                || reportInfo.getDestination().equals(ConstString.EMPTY.value())
                || reportInfo.getDestination().equals(ConstString.NULL.value())) {
            reportInfo.setDestination(getString(R.string.default_destination_hint));
        }
        if (reportInfo.getTransportation() == null
                || reportInfo.getTransportation().equals(ConstString.EMPTY.value())
                || reportInfo.getTransportation().equals(ConstString.NULL.value())) {
            reportInfo.setTransportation(getString(R.string.default_transportation_hint));
        }
        if (reportInfo.getReason() == null
                || reportInfo.getReason().equals(ConstString.EMPTY.value())
                || reportInfo.getReason().equals(ConstString.NULL.value())) {
            reportInfo.setReason(getString(R.string.default_reason_hint));
        }
        Intent intent = new Intent(this, WebViewActivity.class);
        intent.putExtra(IntentParam.REPORT_INFO.getKey(), reportInfo);
        startActivity(intent);
    }
}