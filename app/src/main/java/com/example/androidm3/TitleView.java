package com.example.androidm3;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TitleView extends LinearLayout {

    private LinearLayout titleView;
    private LinearLayout titleViewBackLayout;
    private LinearLayout titleViewCloseLayout;
    private ImageView    backImg;
    private ImageView    closeImg;
    private TextView     titleViewTv;
    private Activity     activity;

    public TitleView(Context context) {
        super(context);
        init(context);
    }

    public TitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        titleView = (LinearLayout) View.inflate(context, R.layout.title_view, this);
        if (isInEditMode())
            return;
        initView(context);
    }

    private void initView(Context context) {
        titleViewBackLayout = (LinearLayout) titleView.findViewById(R.id.title_view_back_layout);
        titleViewCloseLayout = (LinearLayout) titleView.findViewById(R.id.title_view_close_layout);
        backImg = (ImageView) titleView.findViewById(R.id.title_view_back_img);             //返回键
        titleViewTv = (TextView) titleView.findViewById(R.id.title_view_center_tv);         //标题栏
        closeImg = (ImageView) titleView.findViewById(R.id.title_view_close_img);           //关闭键

        //back  结束当前activity
        titleViewBackLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (activity != null) {
                    activity.finish();
                }
            }
        });

        //close  结束activity
        titleViewCloseLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (activity != null) {
                    activity.finish();
                }
            }
        });
    }

    public BindView bind(Activity activity) {
        this.activity = activity;
        return new BindView();
    }

    class BindView {
        public void setTv(String str) {
            titleViewTv.setText(str);
        }
    }
}