package Q1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Game {
    char[] card = {1,2,3,4,5,6,7,8};
    char[] washCard = {'A','A','Q','Q','K','K','J','J','2','2','5','5','6','6','9','9'};
    char[][] startCard = new char[4][4];//卡牌正面显示
    char[][] nowCard = new char[4][4];//当前游戏情况
    char[][] targetPoint = new char[4][4];//记录选择标位置
    int couple = 0;//匹配成功的牌数
    int[] nowTargetPosition = {0,0};//选择标位置
    int[] beforeTargetPosition ={0,0};//移动之后把之前的位置置为‘ ’
    int[] flipPointOne = new int[2];//记录翻开的牌的坐标进行匹配
    int[] flipPointTwo = new int[2];
    int ifFilp = 0;//判断是否有翻开的牌
    int cards;

    public void randomCard(){
        List card = new ArrayList();
        for (cards=0;cards<=15;cards++){
            card.add(washCard[cards]);
        }
        Collections.shuffle(card);
        for (cards=0;cards<=15;cards++){
            washCard[cards] = (char)card.get(cards);
        }
    }//随机排列每次生成不一样的卡牌排列方式

    public void showStartCard(){
        int i, j, row = 3, column = 3;
        for(i=0;i<=row;i++){
            for(j=0;j<=column;j++){
                System.out.print(startCard[i][j]);
            }
            j = 0;
            System.out.println("\n");
        }
    }//展示初始化的牌摆放位置

    public void showTargetPoint(){
        int i, j, row = 3, column = 3;
        for(i=0;i<=row;i++){
            for(j=0;j<=column;j++){
                System.out.print(targetPoint[i][j]);
            }
            System.out.println("\n");
        }
    }//展示选择标

    public void showNowCard(){
        int i, j, row = 3, column = 3;
        for(i=0;i<=row;i++){
            for(j=0;j<=column;j++){
                System.out.print(nowCard[i][j]);
            }
            System.out.print("\n");
            for(j=0;j<=column;j++){
                System.out.print(targetPoint[i][j]);
            }
            System.out.print("\n");
        }
    }//展示当前游戏进度的牌，如果是配对成功的就一直显示

    public void initTaegetPoint(){
        int i, j, row = 3, column = 3;
        for(i=0;i<=row;i++){
            for(j=0;j<=column;j++){
                targetPoint[i][j]=' ';
            }
            j = 0;
        }
        targetPoint[0][0] = '#';
    }//初始化选择标，最开始在第一个位置

    public void initNowCard(){
        int i, j, row = 3, column = 3;
        for(i=0;i<=row;i++){
            for(j=0;j<=column;j++){
                nowCard[i][j]='*';
            }
            j = 0;
        }
    }//将游戏进度的牌在游戏开始时初始化为‘*’

    public void initStartCard(){
        int i, j, row = 3, column = 3,flag = 0;
        for(i=0;i<=row;i++){
            for(j=0;j<=column;j++){
                startCard[i][j]=washCard[flag];
                flag++;
            }
            j = 0;
        }
    }//洗牌，然后摆放后的位置

    public void initAll() throws InterruptedException {
        randomCard();
        initNowCard();
        initStartCard();
        initTaegetPoint();
        showStartCard();
        Thread.sleep(1000);
        System.out.println("****************");
        System.out.println("****************");
        System.out.println("****************");
        System.out.println("****************");
        System.out.println("****************");

    }

    public void moveTarget(char m){
        if(m == 'd'){
            if(nowTargetPosition[1]==3){
                ;
            }
            else {
                nowTargetPosition[1]++;
                changeTarget();
            }
        }
        else if(m == 'a'){
            if(nowTargetPosition[1]==0){
                ;
            }
            else {
                nowTargetPosition[1]--;
                changeTarget();
            }
        }
        else if(m == 'w'){
            if(nowTargetPosition[0]==0){
                ;
            }
            else {
                nowTargetPosition[0]--;
                changeTarget();
            }
        }
        else if(m == 's'){
            if(nowTargetPosition[0]==3){
                ;
            }
            else {
                nowTargetPosition[0]++;
                changeTarget();
            }
        }

    }//移动选择标

    public void changeTarget(){
        targetPoint[nowTargetPosition[0]][nowTargetPosition[1]] ='#';
        targetPoint[beforeTargetPosition[0]][beforeTargetPosition[1]] =' ';
        beforeTargetPosition[0] = nowTargetPosition[0];
        beforeTargetPosition[1] = nowTargetPosition[1];
    }//当目标点发生变化时归空原来的位置同时赋值新的位置

    public boolean flip(){
        if(nowCard[nowTargetPosition[0]][nowTargetPosition[1]]=='*'){
            nowCard[nowTargetPosition[0]][nowTargetPosition[1]] = startCard[nowTargetPosition[0]][nowTargetPosition[1]];
            if(ifFilp==0){
                flipPointOne[0] = nowTargetPosition[0];
                flipPointOne[1] = nowTargetPosition[1];
                ifFilp = 1;
            }
            else {
                flipPointTwo[0] = nowTargetPosition[0];
                flipPointTwo[1] = nowTargetPosition[1];
            }
            return true;
        }
        else {
            return false;
        }

    }//翻转卡片，如果是第一张翻开的就赋值第一张，同时修改flag，将状态改为第二张，如果是第二张翻开的就赋值第二张

    public int check() throws InterruptedException {
        if(nowCard[flipPointTwo[0]][flipPointTwo[1]]==nowCard[flipPointOne[0]][flipPointOne[1]]){
            couple++;
            ifFilp = 0;
            if(couple==8){
                return 1;
            }
            else return 0;
        }
        else {
            showNowCard();
            Thread.sleep(1000);
            nowCard[flipPointTwo[0]][flipPointTwo[1]] = '*';
            nowCard[flipPointOne[0]][flipPointOne[1]] = '*';
            ifFilp = 0;
            return 0;
        }

    }//配对，如果相同就保持翻开的状态，同时配对数加一，如果不同就置为*
}



