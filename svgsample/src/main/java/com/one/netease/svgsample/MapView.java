package com.one.netease.svgsample;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import androidx.annotation.Nullable;
import androidx.core.graphics.PathParser;

/**
 * @author diaokaibin@gmail.com on 2020-01-30.
 */
public class MapView extends View {

    private int[] colorArray = new int[]{0xFF239BD7, 0xFF30A9E5, 0xFF80CBF1, 0xFFF88F8F};

    private Context context;
    private List<ProviceItem> itemList;
    private Paint paint;


    private ProviceItem select;// 选中的省份
    private RectF totalRect;

    private float scale = 1.0f;

    public MapView(Context context) throws ParserConfigurationException, IOException, SAXException {
        super(context);
        init(context);
    }

    public MapView(Context context, @Nullable AttributeSet attrs) throws ParserConfigurationException, IOException, SAXException {
        super(context, attrs);
        init(context);
    }

    public MapView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) throws ParserConfigurationException, IOException, SAXException {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    void init(Context context) throws ParserConfigurationException, IOException, SAXException {
        this.context = context;
        itemList = new ArrayList<>();
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mThread.start();


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (itemList != null) {
            canvas.save();
            canvas.scale(scale,scale);
            for (ProviceItem item : itemList) {
                if (item != select) {

                    item.drawItem(canvas, paint, false);
                } else {
                    select.drawItem(canvas, paint, true);

                }
            }


        }

    }

    private Thread mThread = new Thread() {

        @Override
        public void run() {
            try {
                InputStream inputStream = context.getResources().openRawResource(R.raw.china);
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = null;

                builder = factory.newDocumentBuilder();

                Document doc = builder.parse(inputStream);

                Element rootElement = doc.getDocumentElement();
                NodeList items = rootElement.getElementsByTagName("path");

                float left = -1;
                float right =-1;
                float top =-1;
                float bottom = -1;


                List<ProviceItem> list = new ArrayList<>();

                for (int i = 0; i < items.getLength(); i++) {
                    Element element = (Element) items.item(i);
                    String pathData = element.getAttribute("android:pathData");
                    String fillColor = element.getAttribute("android:fillColor");
                    System.out.println("color : " + fillColor);
                    @SuppressLint("RestrictedApi") Path path = PathParser.createPathFromPathData(pathData);
                    ProviceItem proviceItem = new ProviceItem(path);

                    proviceItem.setDrawColor(colorArray[i % 4]);
                    RectF rectF = new RectF();
                    path.computeBounds(rectF, true);
                    left = left == -1 ? rectF.left : Math.min(left, rectF.left);
                    right = right == -1 ? rectF.right : Math.max(right, rectF.right);
                    top = top == -1 ? rectF.top : Math.min(top, rectF.top);
                    bottom = bottom == -1 ? rectF.bottom : Math.max(bottom, rectF.bottom);


                    list.add(proviceItem);

//                刷新界面
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            requestLayout();
                            invalidate();
                        }
                    });
                }

                itemList=list;
                totalRect = new RectF(left,top,right,bottom);
                postInvalidate();

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    };

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        handleTouch(event.getX()/scale, event.getY()/scale);
        return super.onTouchEvent(event);
    }



    private void handleTouch(float x, float y) {
        if (itemList == null) {
            return;
        }
        ProviceItem selectItem = null;

        for (ProviceItem proviceItem : itemList) {
            if (proviceItem.isTouch(x, y)) {
                selectItem = proviceItem;
            }

        }

        if (selectItem != null) {
            select = selectItem;
            postInvalidate();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        if (totalRect != null) {
            float mapWidth = totalRect.width();
            scale = (float) (width / mapWidth);

        }


    }
}
