package cn.misection.autoreport.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.xuexiang.xui.XUI;

import cn.misection.autoreport.R;
import cn.misection.autoreport.constant.Campus;
import cn.misection.autoreport.constant.IntentParam;
import cn.misection.autoreport.databinding.ActivityMainBinding;
import cn.misection.autoreport.entity.ReportInfo;
import cn.misection.autoreport.entity.SwufeUser;
import cn.misection.autoreport.util.stringutil.StringUtil;
import cn.misection.autoreport.util.timeutil.HourMinuteUnit;

/**
 * @author Administrator
 */
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mBinding;

    private SwufeUser mUser;

    private ReportInfo mReportInfo;


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
        mBinding.setReportInfo(mReportInfo);
        mBinding.setUser(mUser);
    }

    private void initUI() {
        initStartTimePicker();
        initEndTimePicker();
        initRadioButtonState();
    }

    private void initData() {
        mUser = SwufeUser.getInstance();
        mReportInfo = new ReportInfo.Builder().create();
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
        mBinding.defaultStartTimeOneminagoRadioButton.setChecked(true);
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
                mUser.setUsername(String.valueOf(s));
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
                        mUser.setPassword(String.valueOf(s));
                    }
                }
        );

        mBinding.campusRadioGroup.setOnCheckedChangeListener(
                (group, checkedId) -> {
                    switch (checkedId) {
                        case R.id.campus_liulin_radio_button:
                            mReportInfo.setCampus(Campus.LIU_LIN);
                            break;
                        case R.id.campus_guanghua_radio_button:
                            mReportInfo.setCampus(Campus.GUANG_HUA);
                            break;
                        default:
                            break;
                    }
                }
        );

        mBinding.startTimeRadioGroup.setOnCheckedChangeListener(
                (group, checkedId) -> {
                    mReportInfo.setStartTime(HourMinuteUnit.timePrevMinutesUnit(1).toFormatString());
                    switch (checkedId) {
                        case R.id.default_start_time_oneminago_radio_button:
                            mBinding.customStartTimeLayout.setVisibility(View.GONE);
                            break;
                        case R.id.start_time_show_custom_radio_button:
                            mBinding.customStartTimeLayout.setVisibility(View.VISIBLE);
                            break;
                        default:
                            break;
                    }
                }
        );

        mBinding.endTimeRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                mReportInfo.setEndTime("22:55");
                switch (checkedId) {
                    case R.id.default_end_time_2255_radio_button:
                        mBinding.customEndTimeLayout.setVisibility(View.GONE);
                        break;
                    case R.id.end_time_show_custom_radio_button:
                        mBinding.customEndTimeLayout.setVisibility(View.VISIBLE);
                        break;
                    default:
                        break;
                }
            }
        });

        mBinding.destinationRadioGroup.setOnCheckedChangeListener(
                new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        switch (checkedId) {
                            case R.id.default_destination_east_radio_button:
                                mReportInfo.setDestination(String.valueOf(mBinding.defaultDestinationEastRadioButton.getText()));
                                mBinding.customDestinationLayout.setVisibility(View.GONE);
                                break;
                            case R.id.default_destination_west_radio_button:
                                mReportInfo.setDestination(String.valueOf(mBinding.defaultDestinationWestRadioButton.getText()));
                                mBinding.customDestinationLayout.setVisibility(View.GONE);
                                break;
                            case R.id.default_destination_canyon_radio_button:
                                mReportInfo.setDestination(String.valueOf(mBinding.defaultDestinationCanyonRadioButton.getText()));
                                mBinding.customDestinationLayout.setVisibility(View.GONE);
                                break;
                            case R.id.destination_show_custom_radio_button:
                                mBinding.customDestinationLayout.setVisibility(View.VISIBLE);
                                break;
                            default:
                                break;
                        }
                    }
                }
        );

        mBinding.startTimePicker.setOnTimeChangedListener(
                (view, hourOfDay, minute) -> {
                    mReportInfo.setStartTime(String.format("%02d:%02d", hourOfDay, minute));
                }
        );

        mBinding.endTimePicker.setOnTimeChangedListener(
                (view, hourOfDay, minute) -> {
                    mReportInfo.setEndTime(String.format("%02d:%02d", hourOfDay, minute));
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
                mReportInfo.setDestination(String.valueOf(s));
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
                mReportInfo.setTransportation(String.valueOf(s));
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
                mReportInfo.setReason(String.valueOf(s));
            }
        });
    }

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
        if (mReportInfo.getCampus() == null) {
            mReportInfo.setCampus(Campus.LIU_LIN);
        }
        if (StringUtil.isNullOrEmpty(mReportInfo.getStartTime())) {
            mReportInfo.setStartTime(HourMinuteUnit.timePrevMinutesUnit(1).toFormatString());
        }
        if (StringUtil.isNullOrEmpty(mReportInfo.getEndTime())) {
            mReportInfo.setEndTime("22:55");
        }
        if (StringUtil.isNullOrEmpty(mReportInfo.getDestination())) {
            mReportInfo.setDestination(getString(R.string.default_destination_hint));
        }
        if (StringUtil.isNullOrEmpty(mReportInfo.getTransportation())) {
            mReportInfo.setTransportation(getString(R.string.default_transportation_hint));
        }
        if (StringUtil.isNullOrEmpty(mReportInfo.getReason())) {
            mReportInfo.setReason(getString(R.string.default_reason_hint));
        }
        Intent intent = new Intent(this, WebViewActivity.class);
        intent.putExtra(IntentParam.REPORT_INFO.getKey(), mReportInfo);
        startActivity(intent);
    }
}