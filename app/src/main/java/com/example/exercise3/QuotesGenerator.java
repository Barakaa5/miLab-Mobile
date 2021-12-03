package com.example.exercise3;

import java.util.concurrent.ThreadLocalRandom;

public class QuotesGenerator {
    String[] quotesList= {"The best way to predict the future is to invent it - Alan Kay", "The fastest way to move forward in life is not doing more. it start with stopping the behavior holding you back - Benjamin P. Hardy", "Perfection is the enemy of progress - Winston Churchill", "Easy choices, hard life, Hard choices, easy life - Jerzy Gregorek","Life begins at the end of your comfort zone - Neale Donald Walsch"};
    QuotesGenerator() {
        this.quotesList = quotesList;
    }

    public String getQuote() {
        int randomNum = (int) (Math.random()*quotesList.length);
        return this.quotesList[randomNum];
    }
}
