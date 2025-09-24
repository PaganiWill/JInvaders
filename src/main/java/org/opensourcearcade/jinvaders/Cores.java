package org.opensourcearcade.jinvaders;

import java.awt.*;

public class Cores {
    private static Color corBranca = Color.white;
    private static Color corFundoJogo = Color.black;
    private static Color corVermelha = Color.red;
    private static Color corVerde = Color.green;

    public static Color getCorVerde() {
        return corVerde;
    }

    public static void setCorVerde(Color corVerde) {
        Cores.corVerde = corVerde;
    }



    public static Color getCorVermelha() {
        return corVermelha;
    }

    public static void setCorVermelha(Color corVermelha) {
        Cores.corVermelha = corVermelha;
    }



    public static Color getCorFundoJogo() {
        return corFundoJogo;
    }

    public void setCorFundoJogo(Color corFundoJogo) {
        this.corFundoJogo = corFundoJogo;
    }



    public static Color getCorBranca() {
        return corBranca;
    }

    public static void setCorBranca(Color corBranca) {
        Cores.corBranca = corBranca;
    }


}
