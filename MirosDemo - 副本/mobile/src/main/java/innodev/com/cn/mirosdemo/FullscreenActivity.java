package innodev.com.cn.mirosdemo;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FullscreenActivity extends AppCompatActivity {

    RelativeLayout rl;

    ImageView iv;
    private int containerWidth;
    private int containerHeight;
    float lastX, lastY;

    ImageView iv2;
    WebView wv;

//    private int containerWidth2;
//    private int containerHeight2;
//    float lastX2, lastY2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

//        requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置全屏, 取消状态栏

        super.onCreate(savedInstanceState);
        //属性动画
        this.setContentView(R.layout.activity_fullscreen);

        rl = (RelativeLayout) findViewById(R.id.rl);

        iv = (ImageView) findViewById(R.id.iv);
        iv2 = (ImageView) findViewById(R.id.iv2);

        iv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN:
                        lastX = event.getRawX();
                        lastY = event.getRawY();
                        return true;
                    case MotionEvent.ACTION_MOVE:
                        //  不要直接用getX和getY,这两个获取的数据已经是经过处理的,容易出现图片抖动的情况
                        float distanceX = lastX - event.getRawX();
                        float distanceY = lastY - event.getRawY();

                        float nextY = iv.getY() - distanceY;
                        float nextX = iv.getX() - distanceX;

                        // 不能移出屏幕
                        if (nextY < 0) {
                            nextY = 0;
                        } else if (nextY > containerHeight - iv.getHeight()) {
                            nextY = containerHeight - iv.getHeight();
                        }
                        if (nextX < 0)
                            nextX = 0;
                        else if (nextX > containerWidth - iv.getWidth())
                            nextX = containerWidth - iv.getWidth();

                        // 属性动画移动
                        ObjectAnimator y = ObjectAnimator.ofFloat(iv, "y", iv.getY(), nextY);
                        ObjectAnimator x = ObjectAnimator.ofFloat(iv, "x", iv.getX(), nextX);

                        AnimatorSet animatorSet = new AnimatorSet();
                        animatorSet.playTogether(x, y);
                        animatorSet.setDuration(0);
                        animatorSet.start();

                        lastX = event.getRawX();
                        lastY = event.getRawY();
                }
                return false;
            }
        });

        iv2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN:
                        lastX = event.getRawX();
                        lastY = event.getRawY();
                        return true;
                    case MotionEvent.ACTION_MOVE:
                        //  不要直接用getX和getY,这两个获取的数据已经是经过处理的,容易出现图片抖动的情况
                        float distanceX = lastX - event.getRawX();
                        float distanceY = lastY - event.getRawY();

                        float nextY = iv2.getY() - distanceY;
                        float nextX = iv2.getX() - distanceX;

                        // 不能移出屏幕
                        if (nextY < 0) {
                            nextY = 0;
                        } else if (nextY > containerHeight - iv2.getHeight()) {
                            nextY = containerHeight - iv2.getHeight();
                        }
                        if (nextX < 0)
                            nextX = 0;
                        else if (nextX > containerWidth - iv2.getWidth())
                            nextX = containerWidth - iv2.getWidth();

                        // 属性动画移动
                        ObjectAnimator y = ObjectAnimator.ofFloat(iv2, "y", iv2.getY(), nextY);
                        ObjectAnimator x = ObjectAnimator.ofFloat(iv2, "x", iv2.getX(), nextX);

                        AnimatorSet animatorSet = new AnimatorSet();
                        animatorSet.playTogether(x, y);
                        animatorSet.setDuration(0);
                        animatorSet.start();

                        lastX = event.getRawX();
                        lastY = event.getRawY();
                }
                return false;
            }
        });


    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        // 这里来获取容器的宽和高
        if (hasFocus) {
            containerHeight = rl.getHeight();
            containerWidth = rl.getWidth();
        }
    }

}
