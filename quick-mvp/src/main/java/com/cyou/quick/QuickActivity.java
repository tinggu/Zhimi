package com.cyou.quick;


import butterknife.ButterKnife;
import me.yokeyword.fragmentation.SupportActivity;

/**
 *
 */
public class QuickActivity extends SupportActivity {

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
    }

}
