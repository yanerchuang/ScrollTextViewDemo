package com.ywj.scrolltextviewdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;

public class SimpleScroolTextView extends TextView {
    public SimpleScroolTextView(Context con) {
        super(con);
    }

    public SimpleScroolTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SimpleScroolTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean isFocused() {
        return true;
    }

}