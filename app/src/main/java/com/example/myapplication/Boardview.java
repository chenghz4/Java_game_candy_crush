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


public class Boardview extends SurfaceView implements SurfaceHolder.Callback {



    int id[]={R.drawable.c1,R.drawable.c2,R.drawable.c3,R.drawable.c4,R.drawable.c5,R.drawable.c6};
    ArrayList<Bitmap> p=new ArrayList<Bitmap>();
    Bitmap mybitmap0= BitmapFactory.decodeResource (getResources () ,id[0]) ;
    Bitmap mybitmap1= BitmapFactory.decodeResource (getResources () ,id[1]) ;
    Bitmap mybitmap2= BitmapFactory.decodeResource (getResources () ,id[2]) ;
    Bitmap mybitmap3= BitmapFactory.decodeResource (getResources () ,id[3]) ;
    Bitmap mybitmap4= BitmapFactory.decodeResource (getResources () ,id[4]) ;
    Bitmap mybitmap5= BitmapFactory.decodeResource (getResources () ,id[5]) ;
     //p.add(Bitmap);
    float x;
    float y;
    Candy candy[]=new Candy[81];
    int stateMap[][]=new int[9][9];
    user a=new user();

    public Boardview(Context context) {
        super(context) ;
        getHolder().addCallback(this) ;
        setFocusable(true) ; // Very i m p o r t a n t

        for(int i=0;i<81;i++) {
            candy[i]=new Candy();
            candy[i].set_type();
        }

        p.add(mybitmap0);
        p.add(mybitmap1);
        p.add(mybitmap2);
        p.add(mybitmap3);
        p.add(mybitmap4);
        p.add(mybitmap5);


    }

    //return the number of same candy
    public int checkCandy(int index){
        int sameNum = 0;

        int i = index/9; //row
        int j = index%9; //column

        int r = checkright(index, j);
        int l = checkleft(index,j);
        int u = checkup(index, i);
        int d = checkdown(index,i);

        if((r-l+1) >= 3) {//>=2
            sameNum = r - l;
            for(int m = r ; m<=l;m++ ){
                stateMap[i][m] = -1; //fill the state map
            }
        }
        if((d-u+1) >= 3) {
            sameNum = sameNum + (d - u);
            for(int n =u; n<=d; n++)
                stateMap[n][j] = -1;  //fill the state map
        }
        sameNum ++; //plus center

        if(sameNum >=3)
            return sameNum;

        return -1;
    }

    public boolean dropCandies(){
        try {
            for (int col = 0; col < 9; col++) {
                for (int row = 8; row >= 0; row--) {
                    //check every possible pitfall and drop candy from up to down
                    if (stateMap[row][col] == -1) {
                        //resume the state
                        stateMap[row][col] = 0;
                        Candy c = new Candy();
                        c.set_type();
                        int work = row;
                        for (work = row; work > 0; work--) {
                            candy[work * 9 + col] = candy[work * 9 - 9 + col]; //replace the candy
                        }
                        //in the end ,work =0; 0*9 + col = col
                        candy[col] = c;
                    }
                }
            }

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

        return true;
    }


    //retrun the rightmost same index
    public int checkright(int index, int j){
        int work = index;
        int originType = candy[index].get_type();

        while(j<8){
            work++;
            j++;
            if (candy[work].get_type() != originType)
                break;
        }
        return j;
    }

    //return the leftmost same index
    public int checkleft(int index, int j){
        int work = index;
        int originType = candy[index].get_type();
        while(j>0){
            work--;
            j--;
            if (candy[work].get_type() != originType) {
                break;
            }
        }
        return j;
    }

    //return the upmost same index
    public int checkup(int index, int i){
        int work = index;
        int originType = candy[index].get_type();

        while(i>0){
            work = work-9;
            i--;
            if (candy[work].get_type() != originType) {
               break;
            }
        }
        return i;
    }

    //return the downmost same index
    public int checkdown(int index,int i){
        int work = index;
        int originType = candy[index].get_type();
        while(i<8){
            work = work+9;
            i++;
            if (candy[work].get_type()!= originType)
                break;
        }
        return i;
    }

    public boolean isDone(){
        return false;
    }

    public boolean checkAndRefreshMap(){

        return false;
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
    public boolean onTouchEvent(MotionEvent e){
        //draw a rectangle at the pos you click on the screen
        x = e.getX();
        y = e.getY();

        Canvas c = getHolder().lockCanvas();
        //Rect dst =new Rect ( ) ;
       // dst.set((int)x,(int)y,(int)x+100,(int)y+100);
        //c.drawBitmap(mybitmap2,null,dst,null);
        //getHolder().unlockCanvasAndPost(c);
        return true;
    }

    protected void onDraw (Canvas c ){

        super.onDraw(c);
        c.drawColor(Color.BLACK);//draw the background color to red
        Rect dst =new Rect ( ) ;
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++) {
                dst.set(150+i*130,300+j*130,250+i*130,400+j*130);
                c.drawBitmap(p.get(candy[i*9+j].get_type()),null,dst,null);

            }
        }

        //c.drawBitmap(mybitmap1,null,dst,null);//draw the image you putted in the folder drawble
    }
}

