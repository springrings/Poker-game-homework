package Q1;
import java.util.Scanner;
import Q1.Game;

import java.io.*;
public class test {
    public static void main(String[] args) throws IOException, InterruptedException {
        int i = 1,flipFlag =0,couple;
        char inputOp;
        Scanner sc = new Scanner(System.in);
        Game game = new Game();
        game.initAll();//初始化卡牌随机排列
        while (i==1)
        {
            game.showNowCard();//展示翻开的卡牌
            inputOp = (char)System.in.read();//读入第一个字符
            if(inputOp=='j'){
                if(flipFlag==1){//翻开第一张牌时不需要进行匹配，翻开两张才进行匹配
                    if(game.flip()){//处理重复翻一张牌的情况
                        couple = game.check();
                        if(couple==1){//翻完结束游戏
                            System.out.println("win");
                            break;
                        }
                        else flipFlag = 0;
                    }
                    else {
                        ;
                    }
                }
                else {
                    game.flip();
                    flipFlag = 1;
                }
            }//按j翻牌
            else if(inputOp=='w'||inputOp=='s'||inputOp=='a'||inputOp=='d'){
                game.moveTarget(inputOp);//wasd移动翻牌位置
            }
            else {
                ;
            }
            sc.nextLine();//吃掉后面的输入，因为System.in.read()只接收一个字符，但是实际用的时候有时候会多打几个字符，这个时候应该只对第一个字符进行判断，而把后面的都接收掉防止影响下次
        }


    }
}