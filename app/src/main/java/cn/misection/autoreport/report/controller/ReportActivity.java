package cn.misection.autoreport.report.controller;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.xuexiang.xui.XUI;

import cn.misection.autoreport.R;
import cn.misection.autoreport.common.constant.Campus;
import cn.misection.autoreport.common.constant.IntentParam;
import cn.misection.autoreport.common.util.stringutil.StringUtil;
import cn.misection.autoreport.common.util.timeutil.HourMinuteUnit;
import cn.misection.autoreport.common.util.uiutil.VisibilityChecker;
import cn.misection.autoreport.databinding.ActivityReportBinding;
import cn.misection.autoreport.report.entity.ReportInfo;
import cn.misection.autoreport.report.entity.SwufeUser;
import cn.misection.util.oututil.system.AppSystem;

/**
 * @author Administrator
 */
public class ReportActivity extends AppCompatActivity {

    /**
     * binding;
     */
    private ActivityReportBinding mBinding;

    /**
     * 西财用户;
     */
    private SwufeUser mUser;

    /**
     * 上报表单实体;
     * TODO: 9/13/21 应该做成 request
     */
    private ReportInfo mReportInfo;

    /**
     * 用户配资;
     */
    private SharedPreferences mPreferences;

    /**
     * 上次点击退出时间;
     */
    private long mLastExitTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        XUI.initTheme(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        flushStartTimePicker();
    }

    @Override
    public void onBackPressed() {
        dispatchExitEvent();
    }

    private void init() {
        initData();
        initBinding();
        initUI();
        initActionListener();
    }

    private void initBinding() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_report);
        mPreferences = this.getPreferences(MODE_PRIVATE);
        mBinding.setReportInfo(mReportInfo);
        mBinding.setUser(mUser);
    }

    private void initUI() {
        initStartTimePicker();
        initEndTimePicker();
        initCheckVisibility();
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

    private void initCheckVisibility() {
        VisibilityChecker.check(
                mBinding.customStartTimeLayout,
                mBinding.startTimeShowCustomRadioButton
        );
        VisibilityChecker.check(
                mBinding.customEndTimeLayout,
                mBinding.endTimeShowCustomRadioButton
        );
        VisibilityChecker.check(
                mBinding.customDestinationLayout,
                mBinding.destinationShowCustomRadioButton
        );
        VisibilityChecker.check(
                mBinding.customTransportationLayout,
                mBinding.transportationShowCustomRadioButton
        );
        VisibilityChecker.check(
                mBinding.customReasonLayout,
                mBinding.reasonShowCustomRadioButton
        );
    }

    @SuppressLint("NonConstantResourceId")
    private void initActionListener() {
        mBinding.usernameEt.addTextChangedListener(
                new TextWatcher() {
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
                }
        );

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
                (RadioGroup group, int checkedId) -> {
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
                (RadioGroup group, int checkedId) -> {
                    mReportInfo.setStartTime(HourMinuteUnit.timePrevMinutesUnit(1).toFormatString());
                    VisibilityChecker.check(
                            mBinding.customStartTimeLayout,
                            mBinding.startTimeShowCustomRadioButton
                    );
                }
        );

        mBinding.endTimeRadioGroup.setOnCheckedChangeListener(
                (RadioGroup group, int checkedId) -> {
                    mReportInfo.setEndTime("22:55");
                    VisibilityChecker.check(
                            mBinding.customEndTimeLayout,
                            mBinding.endTimeShowCustomRadioButton
                    );
                }
        );

        mBinding.destinationRadioGroup.setOnCheckedChangeListener(
                (RadioGroup group, int checkedId) -> {
                    VisibilityChecker.check(
                            mBinding.customDestinationLayout,
                            mBinding.destinationShowCustomRadioButton
                    );
                    switch (checkedId) {
                        case R.id.default_destination_east_radio_button:
                            mReportInfo.setDestination(String.valueOf(mBinding.defaultDestinationEastRadioButton.getText()));
                            break;
                        case R.id.default_destination_west_radio_button:
                            mReportInfo.setDestination(String.valueOf(mBinding.defaultDestinationWestRadioButton.getText()));
                            break;
                        case R.id.default_destination_canyon_radio_button:
                            mReportInfo.setDestination(String.valueOf(mBinding.defaultDestinationCanyonRadioButton.getText()));
                            break;
                        case R.id.destination_show_custom_radio_button:
                            mReportInfo.setDestination(String.valueOf(mBinding.customDestinationEt.getText()));
                            break;
                        default:
                            break;
                    }
                }
        );

        mBinding.transportationRadioGroup.setOnCheckedChangeListener(
                (RadioGroup group, int checkedId) -> {
                    VisibilityChecker.check(
                            mBinding.customTransportationLayout,
                            mBinding.transportationShowCustomRadioButton
                    );
                    switch (checkedId) {
                        case R.id.default_transportation_walk_radio_button:
                            mReportInfo.setTransportation(String.valueOf(mBinding.defaultTransportationWalkRadioButton.getText()));
                            break;
                        case R.id.default_transportation_bike_radio_button:
                            mReportInfo.setTransportation(String.valueOf(mBinding.defaultTransportationBikeRadioButton.getText()));
                            break;
                        case R.id.default_transportation_fly_radio_button:
                            mReportInfo.setTransportation(String.valueOf(mBinding.defaultTransportationFlyRadioButton.getText()));
                            break;
                        case R.id.transportation_show_custom_radio_button:
                            mReportInfo.setTransportation(String.valueOf(mBinding.customTransportationEt.getText()));
                            break;
                        default:
                            break;
                    }
                }
        );

        mBinding.reasonRadioGroup.setOnCheckedChangeListener(
                (RadioGroup group, int checkedId) -> {
                    VisibilityChecker.check(
                            mBinding.customReasonLayout,
                            mBinding.reasonShowCustomRadioButton
                    );
                    switch (checkedId) {
                        case R.id.default_reason_eat_radio_button:
                            mReportInfo.setReason(String.valueOf(mBinding.defaultReasonEatRadioButton.getText()));
                            break;
                        case R.id.default_reason_findjob_radio_button:
                            mReportInfo.setReason(String.valueOf(mBinding.defaultReasonFindjobRadioButton.getText()));
                            break;
                        case R.id.default_reason_random_radio_button:
                            mReportInfo.setReason(String.valueOf(mBinding.defaultReasonRandomRadioButton.getText()));
                            break;
                        case R.id.reason_show_custom_radio_button:
                            mReportInfo.setReason(String.valueOf(mBinding.customReasonEt.getText()));
                            break;
                        default:
                            break;
                    }
                });

        mBinding.startTimePicker.setOnTimeChangedListener(
                (TimePicker view, int hourOfDay, int minute) -> {
                    mReportInfo.setStartTime(String.format("%02d:%02d", hourOfDay, minute));
                }
        );

        mBinding.endTimePicker.setOnTimeChangedListener(
                (TimePicker view, int hourOfDay, int minute) -> {
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

        mBinding.lawRiskCheckBox.setOnCheckedChangeListener(
                (CompoundButton buttonView, boolean isChecked) -> {
                    mBinding.submitButton.setEnabled(isChecked);
                }
        );
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
        savePreferences();
        if (!mBinding.lawRiskCheckBox.isChecked()) {
//            new AlertDialog.Builder()
//                    .setTitle()
            return;
        }
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
        Intent intent = new Intent(this, ReportWebActivity.class);
        intent.putExtra(IntentParam.REPORT_INFO.getKey(), mReportInfo);
        startActivity(intent);
    }

    private void savePreferences() {
        SharedPreferences.Editor edit = mPreferences.edit();
//        edit.putString(ReportPreferences.CAMPUS.getKey(), mReportInfo.);
    }

    private void dispatchExitEvent() {
        if ((System.currentTimeMillis() - mLastExitTime) > 2000) {
            AppSystem.out.printt(this, "再按一次返回键退出 App");
            mLastExitTime = System.currentTimeMillis();
        } else {
            AppSystem.out.printt(this, "Bye!");
            finish();
        }
    }
}