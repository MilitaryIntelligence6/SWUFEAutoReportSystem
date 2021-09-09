package cn.misection.autoreport.util.uiutil;

import android.view.View;
import android.widget.RadioButton;

/**
 * @author Military Intelligence 6 root
 * @version 1.0.0
 * @ClassName VisibilityChecker
 * @Description TODO
 * @CreateTime 2021年09月09日 21:10:00
 */
public class VisibilityChecker {

    private VisibilityChecker() {

    }

    public static void check(View showView, RadioButton checkActiveButton) {
        showView.setVisibility(
                checkActiveButton.isChecked()
                        ? View.VISIBLE
                        : View.GONE
        );
    }
}
