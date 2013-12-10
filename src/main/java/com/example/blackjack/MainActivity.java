package com.example.blackjack;

import android.content.Context;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.BreakIterator;

public class MainActivity extends Activity {

    public final static int BET = 10;
    int bank, winnerCount;
    boolean winners[];
    TextView result, bet;
    Button bet10Button, hitButton, standButton;
    Deck deck;
    Bot playerArray[];
    Player player;
    Bot1 bot1;
    Bot2 bot2;
    Dealer dealer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = (TextView) findViewById(R.id.result);
        bet = (TextView) findViewById(R.id.bet);

        bet10Button = (Button) findViewById(R.id.bet10);
        hitButton = (Button) findViewById(R.id.hit);
        standButton = (Button) findViewById(R.id.stand);

        hitButton.setEnabled(false);
        standButton.setEnabled(false);
        bet10Button.setEnabled(true);

        result.setText("");
        player = new Player(this);
        bot1 = new Bot1(this);
        bot2 = new Bot2(this);
        dealer = new Dealer(this);
        playerArray = new Bot[3];
        playerArray[0] = player;
        playerArray[1] = bot1;
        playerArray[2] = bot2;

        for (Bot element: playerArray){
            element.scoreTextView.setText("Счет: " + element.sum);
        }

        deck = new Deck();
        deck.shuffle();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    public void onBet10(View view)
    {
        int n=1;
        winnerCount=0;
        for (Bot element: playerArray){
            if(element.canPlay()){
                n++;
                element.scoreTextView.setText("Счет: "+element.sum);
                element.start(element.hand, deck);
                element.handTextView.setText("");
                showHand(element.handTextView, element.hand);
            }
            else{
                element.scoreTextView.setText("Проиграл");
                element.hand.clear();
                element.handTextView.setText("");
            }
        }
        if(player.play == false){
            hitButton.setEnabled(false);
            standButton.setEnabled(false);
            bet10Button.setEnabled(false);
            result.setText("У вас недостаточно средств, перезапустите приложение!");
        }

        bank=BET*n;
        bet.setText("Банк: "+ bank);
        result.setText("");
        hitButton.setEnabled(true);
        standButton.setEnabled(true);
        bet10Button.setEnabled(false);

        dealer.start(dealer.hand, deck);
        dealer.handTextView.setText("");
        for(int i = 1;i < dealer.hand.getCardCount();i++)
            dealer.handTextView.setText(dealer.handTextView.getText()+"" + dealer.hand.getCard(i) + "\n\r");

        forWin21();
    }

    public void onHit(View view){
        for (Bot element: playerArray){
            if(element.play){
                element.hit(deck, player.hand.getValue());
                showHand(element.handTextView, element.hand);
            }
        }
        dealer.hit(deck,player.hand.getValue());
        dealer.handTextView.setText("");
        for(int i = 1;i < dealer.hand.getCardCount();i++){
            dealer.handTextView.setText(dealer.handTextView.getText()+"" + dealer.hand.getCard(i) + "\n\r");
        }
        forWin21();
        more21();
    }

    public void onStand(View view){
        int countPlayers=0;
        hitButton.setEnabled(false);
        standButton.setEnabled(false);
        bet10Button.setEnabled(false);
        do{
            countPlayers = 0;
            for (int i = 1;i < playerArray.length;i++){
                if(playerArray[i].finish == false){
                    if(playerArray[i].play){
                        playerArray[i].hit(deck, player.hand.getValue());
                        showHand(playerArray[i].handTextView, playerArray[i].hand);
                        countPlayers++;
                    }
                }
            }
            if(dealer.finish == false){
                dealer.hit(deck,player.hand.getValue());
                dealer.handTextView.setText("");
                for(int i = 1;i < dealer.hand.getCardCount();i++){
                    dealer.handTextView.setText(dealer.handTextView.getText()+"" + dealer.hand.getCard(i) + "\n\r");
                }
                countPlayers++;
            }
        }while(countPlayers > 0);
        checkWin();
    }
    public void more21(){
        if(player.hand.getValue() > 21){
            onStand(findViewById(android.R.id.content));
        }
    }
    public void checkWin(){
        int maxSum=0;
        for (Bot element: playerArray){
            if(maxSum < element.hand.getValue() && element.hand.getValue() <= 21){
                maxSum = element.hand.getValue();
            }
        }
        if(dealer.hand.getValue() <= maxSum || dealer.hand.getValue() > 21)
        {
            for (Bot element: playerArray){
                if(element.hand.getValue() == maxSum){
                    element.win=true;
                    winnerCount++;
                }
            }
        }
        else{
            dealer.win=true;
            winnerCount++;
        }
        if(winnerCount>0){
            whoWin21();
        }
    }
    public void forWin21(){
        for (Bot element: playerArray){
            if(element.hand.getValue() == 21){
                element.win=true;
                winnerCount++;
            }
        }
        if(winnerCount == 0){
            if (dealer.hand.getValue()==21){
                dealer.win=true;
                winnerCount++;
            }
        }
        if(winnerCount > 0){
            whoWin21();
        }
    }

    public void whoWin21(){
        showHand(dealer.handTextView,dealer.hand);
        if(winnerCount>1){
            result.setText("Победили: ");
        }
        else if(winnerCount==1){
            result.setText("Победил: ");
            if (dealer.win)
                result.setText("Дилер Победил! Вы проиграли.");
        }
        for (Bot element: playerArray){
            if (element.win){
                result.setText(result.getText()+element.name + " ");
                element.sum+=bank/winnerCount;;
            }
        }
        bank=0;
        hitButton.setEnabled(false);
        standButton.setEnabled(false);
        bet10Button.setEnabled(true);
        for (Bot element: playerArray){
            if(element.canPlay()){
                element.scoreTextView.setText("Счет: " + element.sum);
                element.win = false;
                element.finish = false;
                element.hand.clear();
                }
        }
        dealer.win = false;
        dealer.hand.clear();
        dealer.finish = false;
        if(player.play == false){
            hitButton.setEnabled(false);
            standButton.setEnabled(false);
            bet10Button.setEnabled(false);
            result.setText("У вас недостаточно средств, перезапустите приложение!");
        }
    }
    public void showHand(TextView where, BlackJackHand hand){
        where.setText("");
        for(int i = 0;i < hand.getCardCount();i++)
            where.setText(where.getText()+"" + hand.getCard(i) + "\n\r");
    }
}
