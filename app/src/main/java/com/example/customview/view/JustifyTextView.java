package com.example.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

/**
 * @author ddc
 * 邮箱: 931952032@qq.com
 * <p>description:
 */
public class JustifyTextView extends AppCompatTextView {

    private int mLineY;
    private int mViewWidth;


    private int text_start = -1;
    private int text_end = -1;

    private boolean isSelectColor;
    private int color_select = Color.rgb(255, 255, 255);

    public JustifyTextView(Context context) {
        super(context);
    }

    public JustifyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setSelectColor(int text_start, int text_end, int color) {
        this.text_start = text_start;
        this.text_end = text_end;
        this.color_select = color;
        isSelectColor = true;
        invalidate();
    }



    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        TextPaint paint = getPaint();
        paint.setColor(getCurrentTextColor());
        // 返回绘制状态的资源ID数组表示视图的当前状态
        paint.drawableState = getDrawableState();
        // 对View上的内容进行测量后得到的View内容占据的宽度
        // 前提是你必须在父布局的onLayout()方法或者此View的onDraw()方法里调用measure(0,0);
        // 否则你得到的结果和getWidth()得到的结果一样。
        mViewWidth = getMeasuredWidth();
        // 获取文本
        String text = getText().toString();
        mLineY = 0;
        mLineY += getTextSize();
        // 获取用于显示当前文本的布局
        Layout layout = getLayout();

        if (layout == null) {
            return;
        }

        Paint.FontMetrics fm = paint.getFontMetrics();

        int textHeight = (int) (Math.ceil(fm.descent - fm.ascent));
        textHeight = (int) (textHeight * layout.getSpacingMultiplier() + layout.getSpacingAdd());

        for (int i = 0; i < layout.getLineCount(); i++) {
            // 返回文本中的指定行开头的偏移
            int lineStart = layout.getLineStart(i);
            // 返回文本中的指定行最后一个字符的偏移
            int lineEnd = layout.getLineEnd(i);
            float width = StaticLayout.getDesiredWidth(text, lineStart, lineEnd, getPaint());
            String line = text.substring(lineStart, lineEnd);

            int line_length = line.length();
            int index_length = lineStart + line_length;
            if (line.equals("")) {
                break;
            }

            if (i < layout.getLineCount() - 1) {
                if (needScale(line)) {
                    drawScaledText(canvas, lineStart, line, width);
                } else {
                    drawOther(canvas, line, lineStart, index_length, line_length, paint);
                }
            } else {
                drawOther(canvas, line, lineStart, index_length, line_length, paint);
            }

            // 增加行高
            mLineY += textHeight;
        }
    }

    private void drawScaledText(Canvas canvas, int lineStart, String line,
                                float lineWidth) {
        float x = 0;
        if (isFirstLineOfParagraph(lineStart, line)) {
            String blanks = "  ";
            canvas.drawText(blanks, x, mLineY, getPaint());
            float bw = StaticLayout.getDesiredWidth(blanks, getPaint());
            x += bw;

            line = line.substring(3);
        }

        int gapCount = line.length() - 1;
        int i = 0;
        if (line.length() > 2 && line.charAt(0) == 12288
                && line.charAt(1) == 12288) {
            String substring = line.substring(0, 2);
            float cw = StaticLayout.getDesiredWidth(substring, getPaint());
            canvas.drawText(substring, x, mLineY, getPaint());
            x += cw;
            i += 2;
        }

        float d = (mViewWidth - lineWidth) / gapCount;
        for (; i < line.length(); i++) {
            int text_index = i + lineStart;
            if (contains(text_index) && isSelectColor) {
                getPaint().setColor(color_select);
            } else {
                getPaint().setColor(getCurrentTextColor());
            }
            String c = String.valueOf(line.charAt(i));
            float cw = StaticLayout.getDesiredWidth(c, getPaint());
            canvas.drawText(c, x, mLineY, getPaint());
            x += cw + d;
        }
    }

    private boolean isFirstLineOfParagraph(int lineStart, String line) {
        return line.length() > 3 && line.charAt(0) == ' ' && line.charAt(1) == ' ';
    }

    private boolean needScale(String line) {
        if (line.length() == 0) {
            return false;
        } else {
//            return line.charAt(line.length() - 1) != '%';
            return line.charAt(line.length() - 1) != '\n';
        }
    }

    private boolean contains(final int number) {
        return text_start <= number && number <= text_end;
    }

    private void drawOther(Canvas canvas, String line, int line_tart, int index_length, int line_length, Paint paint) {
        try {
            if (line_tart <= text_start && text_start <= index_length || line_tart <= text_end && text_end <= index_length) { //区间内

                float bw = 0;
                int draw_start = 0;
                if (line_tart < text_start) {
                    draw_start = line_length - (index_length - text_start);

                    paint.setColor(getCurrentTextColor());
                    canvas.drawText(line.substring(0, draw_start), 0, mLineY, paint);
                    bw = StaticLayout.getDesiredWidth(line.substring(0, draw_start), getPaint());
                }

                if (line_tart <= text_end && text_end <= index_length) {
                    int draw_end = line.length() - (index_length - text_end);
                    paint.setColor(color_select);
                    canvas.drawText(line.substring(draw_start, draw_end), bw, mLineY, paint);

                    bw += StaticLayout.getDesiredWidth(line.substring(draw_start, draw_end), getPaint());

                    paint.setColor(getCurrentTextColor());
                    canvas.drawText(line.substring(draw_end), bw, mLineY, paint);
                } else {
                    paint.setColor(getCurrentTextColor());
                    canvas.drawText(line.substring(draw_start), bw, mLineY, paint);
                }
            } else {
                paint.setColor(getCurrentTextColor());
                canvas.drawText(line, 0, mLineY, paint);
            }
        } catch (Exception e) {
            paint.setColor(getCurrentTextColor());
            canvas.drawText(line, 0, mLineY, paint);
        }

    }
}
