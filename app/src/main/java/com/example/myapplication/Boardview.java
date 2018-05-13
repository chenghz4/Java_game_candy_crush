package com.example.myapplication;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.SurfaceHolder;
import java.util.*;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;


public class Boardview extends SurfaceView implements SurfaceHolder.Callback {


    int id[] = {R.drawable.c1, R.drawable.c2, R.drawable.c3, R.drawable.c4, R.drawable.c5, R.drawable.c6};
    ArrayList<Bitmap> p = new ArrayList<Bitmap>();
    Bitmap mybitmap0 = BitmapFactory.decodeResource(getResources(), id[0]);
    Bitmap mybitmap1 = BitmapFactory.decodeResource(getResources(), id[1]);
    Bitmap mybitmap2 = BitmapFactory.decodeResource(getResources(), id[2]);
    Bitmap mybitmap3 = BitmapFactory.decodeResource(getResources(), id[3]);
    Bitmap mybitmap4 = BitmapFactory.decodeResource(getResources(), id[4]);
    Bitmap mybitmap5 = BitmapFactory.decodeResource(getResources(), id[5]);

    float x1, x2, y1, y2;
    double xl, yl = 0;
    int xr, yr = 0;
    CandyHelper h=new CandyHelper();
    boolean flag1 = false;
    boolean restart=false;
    Candy temp = new Candy();
    int direction; //0:up,1:down,2:left,3:right
    Candy candy[][] = new Candy[9][9];
    static user a = new user();
    Paint paint = new Paint();

    public Boardview(Context context) {
        super(context);
        getHolder().addCallback(this);
        setFocusable(true); // Very i m p o r t a n t
        requestFocus();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                candy[i][j] = new Candy();
                candy[i][j].set_type();
            }
        }
       while(h.checkWholeMap(candy)) {
           for (int i = 0; i < 9; i++) {
               for (int j = 0; j < 9; j++) {
                   candy[i][j] = new Candy();
                   candy[i][j].set_type();
               }
           }
       };

        p.add(mybitmap0);
        p.add(mybitmap1);
        p.add(mybitmap2);
        p.add(mybitmap3);
        p.add(mybitmap4);
        p.add(mybitmap5);


    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Canvas c = holder.lockCanvas();
        this.onDraw(c);
        holder.unlockCanvasAndPost(c);

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }


    @Override
    public boolean onTouchEvent(MotionEvent e) {
        //draw a rectangle at the pos you click on the screen

        Rect dst = new Rect();
        // direction=5;
        Canvas c = getHolder().lockCanvas();
        if (e.getAction() == MotionEvent.ACTION_DOWN) {

            x1 = e.getX();
            y1 = e.getY();
            xl = Math.floor((x1 - 150) / 120);
            yl = Math.floor((y1 - 300) / 120);
            xr = (int) xl;
            yr = (int) yl;
        }
        if (e.getAction() == MotionEvent.ACTION_UP) {

            x2 = e.getX();
            y2 = e.getY();
            if (y1 - y2 > 80) direction = 0;//up
            else if (y2 - y1 > 80) direction = 1;//down
            else if (x1 - x2 > 80) direction = 2;// left
            else if (x2 - x1 > 80) direction = 3; //right
            else direction = 5;
            flag1 = true;
        }

        if(y1<=1800&&y1>=1700&&y2<=1800&&y2>=1700&&x1>1000&&x2>1000&&x1<1360&&x2<1360) restart=true;
        setRestart();
        if(y1<=2000&&y1>=1900&&y2<=2000&&y2>=1900&&x1>1000&&x2>1000&&x1<1220&&x2<1220){
            //restart=true;
        }
        if(a.check())    ;


        if (flag1 && xl < 9 && xl >= 0 && yl >= 0 && yl < 9 && x1 != 0 && x2 != 0) {
            switch (direction) {
                case 0:
                    if (yr > 0) {
                        temp = candy[xr][yr];
                        candy[xr][yr] = candy[xr][yr - 1];
                        candy[xr][yr - 1] = temp;
                    }
                    a.count++;
                    break;

                case 1:
                    if (yr < 8) {
                        temp = candy[xr][yr];
                        candy[xr][yr] = candy[xr][yr + 1];
                        candy[xr][yr + 1] = temp;
                    }
                    a.count++;
                    break;
                case 2:
                    if (xr > 0) {
                        temp = candy[xr][yr];
                        candy[xr][yr] = candy[xr - 1][yr];
                        candy[xr - 1][yr] = temp;
                    }
                    a.count++;
                    break;
                case 3:
                    if (xr < 8) {
                        temp = candy[xr][yr];
                        candy[xr][yr] = candy[xr + 1][yr];
                        candy[xr + 1][yr] = temp;
                    }
                    a.count++;
                    break;
                default:
                    break;
            }

//if(h.checkWholeMap(candy)) h.dropCandies(candy);
            flag1 = false;
        }
        paint.setColor(Color.WHITE);
        Rect aa = new Rect();
        aa.set(200,1600,800,2000);
        c.drawRect(aa,paint);
        onDraw(c);

        getHolder().unlockCanvasAndPost(c);
        return true;
    }

    protected void onDraw(Canvas c) {

        super.onDraw(c);
        c.drawColor(Color.TRANSPARENT);//draw the background color to red
        Rect dst = new Rect();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                dst.set(150 + i * 120, 300 + j * 120, 270 + i * 120, 420 + j * 120);
                c.drawBitmap(p.get(candy[i][j].get_type()), null, dst, null);

            }
        }
        paint.setColor(Color.BLACK);
        paint.setTextSize(100);
        paint.setStyle(Paint.Style.FILL);
        c.drawText("Move:  "+a.count,200,1800,paint);
        c.drawText("Score:  "+a.getScore(),200,2000,paint);

        c.drawText("Restart",1000,1800,paint);
        c.drawText("Back",1000,2000,paint);


    }


    public void setRestart(){


            if (restart) {
                if (!h.checkWholeMap(candy)) {
                    for (int i = 0; i < 9; i++) {
                        for (int j = 0; j < 9; j++) {
                            candy[i][j].set_type();
                        }
                    }
                }

                while (h.checkWholeMap(candy)) {
                    for (int i = 0; i < 9; i++) {
                        for (int j = 0; j < 9; j++) {
                            candy[i][j].set_type();
                        }
                    }

                }
                ;
                a.clear();
                ;
                restart = false;
            }
    }
}
