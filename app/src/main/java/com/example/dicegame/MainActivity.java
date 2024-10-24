package com.example.dicegame;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private int wynikLosowania = 0;
    private int wynikGry = 0;
    private int liczbaRzutow = 0;

    private TextView kostka1;
    private TextView kostka2;
    private TextView kostka3;
    private TextView kostka4;
    private TextView kostka5;

    private TextView[] iloscrzutow = new TextView[5];
    private int[] rzutyKoscmi = new int[5];

    private TextView wynikLosowaniaView;
    private TextView wynikGryView;
    private TextView liczbaRzutowView;

    private Button rzucKoscmi;
    private Button resetWyniku;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        kostka1 = findViewById(R.id.kostka1);
        kostka2 = findViewById(R.id.kostka2);
        kostka3 = findViewById(R.id.kostka3);
        kostka4 = findViewById(R.id.kostka4);
        kostka5 = findViewById(R.id.kostka5);

        iloscrzutow[0] = kostka1;
        iloscrzutow[1] = kostka2;
        iloscrzutow[2] = kostka3;
        iloscrzutow[3] = kostka4;
        iloscrzutow[4] = kostka5;

        wynikLosowaniaView = findViewById(R.id.wynikLosowania);
        wynikGryView = findViewById(R.id.wynikGry);
        liczbaRzutowView = findViewById(R.id.liczbaRzutow);

        rzucKoscmi = findViewById(R.id.rollButton);
        resetWyniku = findViewById(R.id.resetButton);

        rzucKoscmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rollDice();
            }
        });

        resetWyniku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetGame();
            }
        });
    }

    void rollDice() {
        Random random = new Random();
        int suma = 0;

        for(int i=0; i < rzutyKoscmi.length; i++)
        {
            int rzut = random.nextInt(6)+1;

            rzutyKoscmi[i] = rzut;
            suma+=rzut;
        }

        updateScore(suma);
        updateRollCount();
        displayDiceResults(rzutyKoscmi);
    }

    void resetGame() {
        for(int i=0; i < iloscrzutow.length; i++) {
            iloscrzutow[i].setText("?");
        }

        wynikLosowania = 0;
        wynikGry = 0;
        liczbaRzutow = 0;

        wynikLosowaniaView.setText("Wynik tego losowania: 0");
        wynikGryView.setText("Wynik gry: 0");
        liczbaRzutowView.setText("Liczba rzutów: 0");
    }

    void updateScore(int newScore) {
        System.out.println(newScore);
        wynikGry += newScore;
        wynikLosowaniaView.setText("Wynik tego losowania: " + String.valueOf(newScore));
        wynikGryView.setText("Wynik gry: " + String.valueOf(wynikGry));
    }

    void updateRollCount() {
        liczbaRzutow++;
        liczbaRzutowView.setText("Liczba rzutów: " + String.valueOf(liczbaRzutow));
    }

    void displayDiceResults(int[] diceResults) {
        for(int i=0; i < iloscrzutow.length; i++){
            iloscrzutow[i].setText(String.valueOf(diceResults[i]));
        }
    }
}