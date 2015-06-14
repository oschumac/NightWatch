package com.dexdrip.stephenblack.nightwatch;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import java.util.Date;

/**
 * Created by stephenblack on 6/13/15.
 */
public class ModernWatchfaceRingsDark extends ModernWatchface{

    @Override
    public int getLowColor() {
        return Color.argb(255, 255, 120, 120);
    }

    @Override
    public int getInRangeColor() {
        return Color.argb(255,120,255,120);

    }

    @Override
    public int getHighColor() {
        return Color.argb(255,255,255,120);

    }

    @Override
    public int getBackgroundColor() {
        return Color.BLACK;
    }

    @Override
    public int getTextColor() {
        return Color.WHITE;
    }

    @Override
    public int holdInMemory() {
        return 6;
    }

    @Override
    public String getMinutes() {
        return "";
    }

    @Override
    public String getDelta() {
        return "";
    }
    @Override
    public void drawOtherStuff(Canvas canvas) {
        //Perfect low and High indicators
        if(bgDataList.size() > 0) {
            addIndicator(canvas, 100, Color.GRAY);
            addIndicator(canvas, (float) bgDataList.get(0).low, Color.GRAY);
            addIndicator(canvas, (float) bgDataList.get(0).high, Color.GRAY);
        }
        for(int i=bgDataList.size(); i > 0; i--) {
            addReading(canvas, bgDataList.get(i - 1), i);
        }
    }

    public void addReading(Canvas canvas, BgWatchData entry, int i) {
        double size;
        int color = Color.DKGRAY;
        float offsetMultiplier = (((displaySize.x / 2f) - PADDING) / 12f);
        float offset = (float) Math.max(1, Math.ceil((new Date().getTime() - entry.timestamp) / (1000 * 60 * 5)));
        if(entry.sgv > 100){
            size = (((entry.sgv - 100f) / 300f) * 225f) + 135;
        } else {
            size = ((entry.sgv / 100) * 135);
        }
        addArch(canvas, offset * offsetMultiplier + 10, color, (float) size);
        addArch(canvas, (float) size, offset * offsetMultiplier + 10, getBackgroundColor(), (float) (360 - size));
        addArch(canvas, (offset + .8f) * offsetMultiplier + 10, getBackgroundColor(), 360);
    }

    public void addArch(Canvas canvas, float offset, int color, float size) {
        Paint paint = new Paint();
        paint.setColor(color);
        RectF rectTemp = new RectF(PADDING + offset - CIRCLE_WIDTH / 2, PADDING + offset - CIRCLE_WIDTH / 2, (displaySize.x - PADDING - offset + CIRCLE_WIDTH / 2),(displaySize.y - PADDING - offset + CIRCLE_WIDTH / 2));
        canvas.drawArc(rectTemp, 270, size, true, paint);
    }
}