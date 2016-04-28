package com.cyou.ui.shapeimageview.shader;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;

import com.cyou.ui.R;


public class BubbleShader extends ShaderHelper {
    private static final int DEFAULT_HEIGHT_DP = 10;

    public enum ArrowPosition {
        @SuppressLint("RtlHardcoded")
        LEFT,
        RIGHT
    }

    private final Path path = new Path();

    private int triangleHeightPx;
    private ArrowPosition arrowPosition = ArrowPosition.LEFT;

    public BubbleShader() {
    }

    @Override
    public void init(Context context, AttributeSet attrs, int defStyle) {
        super.init(context, attrs, defStyle);
        borderWidth = 0;
        if(attrs != null){
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ShaderImageView, defStyle, 0);
            triangleHeightPx = typedArray.getDimensionPixelSize(R.styleable.ShaderImageView_siTriangleHeight, 0);
            int arrowPositionInt = typedArray.getInt(R.styleable.ShaderImageView_siArrowPosition, 
                    ArrowPosition.LEFT.ordinal());
            arrowPosition = ArrowPosition.values()[arrowPositionInt];
            typedArray.recycle();
        }

        if(triangleHeightPx == 0) {
            triangleHeightPx = dpToPx(context.getResources().getDisplayMetrics(), DEFAULT_HEIGHT_DP);
        }
    }

    @Override
    public void draw(Canvas canvas, Paint imagePaint, Paint borderPaint) {
        canvas.save();
        canvas.concat(matrix);
        canvas.drawPath(path, imagePaint);
        canvas.restore();
    }

    @Override
    public void calculate(int bitmapWidth, int bitmapHeight,
                          float width, float height,
                          float scale,
                          float translateX, float translateY) {
        path.reset();
        float x = -translateX;
        float y = -translateY;
        float scaledTriangleHeight = triangleHeightPx / scale;
        float resultWidth = bitmapWidth + 2 * translateX;
        float resultHeight = bitmapHeight + 2 * translateY;

        //add arrow
        float horizonX = 0;
        float horizonY = 0;
        float radius = 10;
        float diameter = 2 * radius;
        float w;
        float h;
        //float centerY  = resultHeight / 2f + y;

        path.setFillType(Path.FillType.EVEN_ODD);
        float rectLeft;
        float rectRight;
        switch (arrowPosition) {
            case LEFT:
                rectLeft = scaledTriangleHeight + x;
                rectRight = resultWidth + rectLeft;

                /*path.addRect(rectLeft, y, rectRight, resultHeight + y, Path.Direction.CW);
                path.moveTo(x, horizonY);
                path.lineTo(rectLeft, horizonY - scaledTriangleHeight);
                path.lineTo(rectLeft, horizonY + scaledTriangleHeight);
                path.lineTo(x, horizonY);*/

                w = rectRight;
                h = resultHeight;

                path.moveTo(w, 10);
                path.arcTo(new RectF(w - diameter, horizonY, diameter, diameter), -90, 90);
                path.lineTo(horizonX, horizonY);
                path.lineTo(scaledTriangleHeight , scaledTriangleHeight);
                path.lineTo(scaledTriangleHeight, h - radius);
                path.arcTo(new RectF(scaledTriangleHeight, h - diameter, scaledTriangleHeight + diameter, h), 90, 90);
                path.lineTo(scaledTriangleHeight + radius, h);
                path.arcTo(new RectF(w - diameter, h - diameter, w, h), 0, 90);
                path.close();

                break;
            case RIGHT:
                rectLeft = x;
                float imgRight = resultWidth + rectLeft;
                rectRight = imgRight - scaledTriangleHeight;

                w = rectRight;
                h = resultHeight;

                path.moveTo(horizonX, radius);
                path.arcTo(new RectF(horizonX, horizonY, diameter, diameter),180,90);
                path.lineTo(w + scaledTriangleHeight, horizonY);
                path.lineTo(w  , scaledTriangleHeight);
                path.lineTo(w , h - radius);
                path.arcTo(new RectF(w - diameter, h - diameter, w, h),0,90);
                path.lineTo(radius, h);
                path.arcTo(new RectF(horizonX, h - diameter , diameter, h),90,90);
                path.close();

                //path.addRect(rectLeft, y, rectRight, resultHeight + y, Path.Direction.CW);
                /*path.addRoundRect(rectF, 10, 10, Path.Direction.CW);
                path.moveTo(imgRight, horizonY);
                path.lineTo(rectRight, horizonY);
                path.lineTo(rectRight , horizonY + scaledTriangleHeight);
                path.lineTo(imgRight, horizonY);
                path.moveTo(rectRight, horizonY);
                path.lineTo(0, horizonY);*/
                break;
        }
    }

    @Override
    public void reset() {
        path.reset();
    }

    public int getTriangleHeightPx() {
        return triangleHeightPx;
    }

    public void setTriangleHeightPx(final int triangleHeightPx) {
        this.triangleHeightPx = triangleHeightPx;
    }

    public ArrowPosition getArrowPosition() {
        return arrowPosition;
    }

    public void setArrowPosition(final ArrowPosition arrowPosition) {
        this.arrowPosition = arrowPosition;
    }
}