package com.garciaa.baseball;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

public class BaseballFieldView extends View {

    public BaseballFieldView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Initialize Paint object
        Paint paint = new Paint();
        paint.setTextSize(80);
        paint.setColor(Color.BLACK);
        paint.setTextAlign(Paint.Align.CENTER);

        // Get the dimensions of the canvas
        int width = getWidth();
        int height = getHeight();

        // Calculate the center point of the canvas
        float centerX = width / 2f;
        float centerY = height / 2f;

        // Set the text to be drawn
        String text = "Joaquin";

        // Get the bounds of the text
        Rect textBounds = new Rect();
        paint.getTextBounds(text, 0, text.length(), textBounds);

        // Calculate the position of the text
        float textX = centerX;
        float textY = centerY + textBounds.height() / 2f;

        // Draw the text on the canvas
        canvas.drawText(text, textX, textY, paint);
    }
}
