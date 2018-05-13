package com.example.myapplication;

public class CandyHelper {
    int stateMap[][] = new int [9][9];   //show if this status is checked out.


    CandyHelper(){
        super();  //initial to 0;
    }

    public void PrintState(){
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                System.out.printf("%4d", this.stateMap[i][j]);
            }
            System.out.println();
        }
    }

    public void PrintCandy(Candy candy[][]){
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                System.out.printf("%4d",candy[i][j].get_type());
            }
            System.out.println();
        }
    }

    /* Check a single candy if it has same neighbours
     *
     * @param : candy, candy position => (row,col)
     * @return : wheather exists candy to crush
     */
    public boolean checkSingleCandy(Candy candy[][],int row,int col){
        int sameNum = 0;

        int i = row; //row
        int j = col; //column

        int r = checkright(candy,i, j);
        int l = checkleft(candy,i,j);
        int u = checkup(candy,i, j);
        int d = checkdown(candy,i,j);


        //change statemap
        if((r-l+1) >= 3) {               //>=2
            sameNum = r - l;
            for(int m = r ; m<=l;m++ ){
                this.stateMap[i][m] = -1; //fill the state map
            }
        }
        if((d-u+1) >= 3) {
            sameNum = sameNum + (d - u);
            for(int n =u; n<=d; n++)
                this.stateMap[n][j] = -1;  //fill the state map
        }
        sameNum ++; //plus center

        if(sameNum >=3)
            return true;

        return false;
    }

    //return the rightmost same index
    public int checkright(Candy candy[][],int i, int j){
        int work = j;                       //col
        int originType = candy[i][j].get_type();

        while(work < 8 ){   //&&(stateMap[work][j]!=-1)){       //if current candy has been checked【only works in whole map checking】 , cancel the checking
            work++;     //at most to 8

            if (candy[i][work].get_type() != originType)
                break;

            j++;
        }

        return j;
    }

    //return the leftmost same index
    public int checkleft(Candy candy[][],int i, int j){
        int work = j;           //col
        int originType = candy[i][j].get_type();

        while(work>0){                         //&&(stateMap[work][j]!=-1)){
            work--;

            if (candy[i][work].get_type() != originType) {
                break;
            }

            j--;
        }
        return j;
    }

    //return the upmost same index
    public int checkup(Candy candy[][],int i, int j){
        int work = i;             //row
        int originType = candy[i][j].get_type();

        while(work>0){              // &&(stateMap[i][work]!=-1)){
            work --;

            if (candy[work][j].get_type() != originType) {
                break;
            }
            i--;
        }
        return i;
    }

    //return the downmost same index
    public int checkdown(Candy candy[][],int i,int j){
        int work = i;
        int originType = candy[i][j].get_type();

        while(work<8){    // && (stateMap[i][work]!=-1)){
            work ++;

            if (candy[work][j].get_type()!= originType)
                break;

            i++;
        }
        return i;
    }


    /* Check the whole map of candies
     *
     * @param candy
     * @return amount of candies that could be eliminated
     */
    public boolean checkWholeMap(Candy candy[][]){
        boolean score[][] = new boolean[9][9];

        boolean result = false;
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                boolean thisRound = checkSingleCandy(candy,i,j);
                score[i][j] = thisRound;      //（划掉：has avoid recounting in checkLeft/right/up/down）
                result = thisRound||result;

            }
        }

//        System.out.println("The score Map");
//        for(int i=0;i<9;i++) {
//            for (int j = 0; j < 9; j++)
//                System.out.printf("%4s", score[i][j]?"t":"f");
//            System.out.println();
//        }

        return result;
    }

    /***
     *
     * @param candy
     * @return if it is Succeed
     */
    public boolean dropCandies(Candy candy[][]){
        try {
            for (int col = 0; col < 9; col++) {
                for (int row = 0; row < 9; row++) {   //scan from up to down
                    //check every possible pitfall and drop candy from up to down
                    if (this.stateMap[row][col] == -1) {
                        //resume the state
                        this.stateMap[row][col] = 0;
                        Candy c = new Candy();
                        c.set_type();
                        int work = row;
                        for (work = row; work > 0; work--) {
                            candy[work][col] = candy[work-1][col]; //replace the candy
                        }
                        //in the end ,work =0; 0*9 + col = col
                        candy[0][col] = c;
                    }
                }
            }

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

        return true;
    }


    /**Check if the map has any other solutions
     *
     *
     * @return
     */
    public boolean isDone(){

        return false;
    }




    public static void main(String args[]){
        CandyHelper h = new CandyHelper();

        System.out.println("helper stateMap initialized: ");

        h.PrintState();
        Candy candy[][] = new Candy[9][9];
        for(int i=0;i<9;i++)
            for(int j=0;j<9;j++){
            candy[i][j]=new Candy();
            candy[i][j].set_type();
        }
        System.out.println("Candy map initialized");
        h.PrintCandy(candy);

        System.out.println("Statemap after first check ");
        boolean score = h.checkWholeMap(candy);

        h.PrintState();

        System.out.println("Droping Candy" );
        h.dropCandies(candy);

        h.PrintCandy(candy);


    }
}
