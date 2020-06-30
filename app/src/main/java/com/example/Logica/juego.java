package com.example.Logica;

import android.widget.Toast;

import java.util.Random;

public class juego {
    private Random aleatorio;
    private int [][] tablero;
    private int posj;
    private int posi;
    private int []numerosAleatorios= {2,4};
    private static int best=0;
    private int score;
    public juego(){
        this.tablero = new int[4][4];
        this.score =0;
        this.aleatorio = new Random();
        for(int i=0; i<this.tablero.length; i++){
            for(int j=0; j<this.tablero.length; j++){
                this.tablero[i][j]=0;
            }
        }
        posAleatoria(2);
        posAleatoria(2);
    }
    public int getBest(){
        return this.best;
    }
    public int getScore(){
        return this.score;
    }

    public void setScore(int score) {
        this.score += score;
        if(this.score > this.best){
            this.best= this.score;
        }
    }

    public void derecha(){
        boolean movi1;
        boolean movi2;
        boolean aceptar = false;
        for(int i=0; i<this.tablero.length; i++){
            movi1= unirNumeros(i);
            movi2 = eliminarEspacios(i);
            if(movi1 || movi2){
                aceptar = true;
            }
        }
        if(aceptar){
            int pos = this.aleatorio.nextInt(2);
            posAleatoria(this.numerosAleatorios[pos]);
        }


    }
    public void izquierda(){
        boolean movi1;
        boolean movi2;
        boolean aceptar = false;
        for(int i=0; i<this.tablero.length; i++){
            movi1= unirNumerosIzquierda(i);
            movi2 = eliminarEspaciosIzquierda(i);
            if(movi1 || movi2){
                aceptar = true;
            }
        }
        if(aceptar){
            int pos = this.aleatorio.nextInt(2);
            posAleatoria(this.numerosAleatorios[pos]);
        }


    }
    public void abajo(){
        boolean movi1;
        boolean movi2;
        boolean aceptar = false;
        for(int i=0; i<this.tablero.length; i++){
            movi1= unirNumerosAbajo(i);
            movi2 = eliminarEspaciosAbajo(i);
            if(movi1 || movi2){
                aceptar = true;
            }
        }
        if(aceptar){
            int pos = this.aleatorio.nextInt(2);
            posAleatoria(this.numerosAleatorios[pos]);
        }


    }

    private boolean unirNumerosAbajo(int pos) {
        boolean movi =false;
        int aux=0;
        int auxj=0;
        //dar prioridad al de mas abajo
        for(int i=this.tablero.length-1; i>=0; i--) {
            if (this.tablero[i][pos] != 0) {

                if (aux != 0) {
                    if (aux == this.tablero[i][pos]) {
                        this.tablero[auxj][pos] += this.tablero[i][pos];
                        setScore(this.tablero[i][pos]);
                        this.tablero[i][pos] = aux = auxj = 0;
                        movi = true;
                    } else {
                        aux = this.tablero[i][pos];
                        auxj = i;
                    }
                } else {
                    aux = this.tablero[i][pos];
                    auxj = i;
                }

            }
        }
        return movi;
    }

    private boolean eliminarEspaciosAbajo(int pos) {
        boolean movi = false;
        int cont=0;
        for(int i=this.tablero.length-1; i>=0; i--){
            if(this.tablero[i][pos]!=0){
                int aux=this.tablero[i][pos];
                this.tablero[i][pos]=0;
                this.tablero[i+cont][pos]= aux;
                if(i+cont==i){
                    cont=0;
                }else{
                    i=i+cont-1;
                    cont=1;
                    movi = true;
                }

            }else{
                cont++;
            }

        }
        return movi;
    }

    public void arriba(){
        boolean movi1;
        boolean movi2;
        boolean aceptar = false;
        for(int i=0; i<this.tablero.length; i++){
            movi1= unirNumerosArriba(i);
            movi2 = eliminarEspaciosArriba(i);
            if(movi1 || movi2){
                aceptar = true;
            }
        }
        if(aceptar){
            int pos = this.aleatorio.nextInt(2);
            posAleatoria(this.numerosAleatorios[pos]);
        }


    }

    private boolean eliminarEspaciosArriba(int pos) {
        boolean movi = false;
        int cont=0;
        for(int i=0; i<this.tablero.length; i++){
            if(this.tablero[i][pos]!=0){
                int aux=this.tablero[i][pos];
                this.tablero[i][pos]=0;
                this.tablero[i-cont][pos]=aux;
                if(i-cont==i){
                    cont=0;
                }else{
                    i=i-cont+1;
                    cont=1;
                    movi = true;
                }

            }else{
                cont++;
            }

        }
        return movi;

    }

    private boolean unirNumerosArriba(int pos) {
        boolean movi =false;
        int aux=0;
        int auxj=0;
        for(int i=0; i<this.tablero.length; i++) {
            if (this.tablero[i][pos] != 0) {
                if (aux != 0) {
                    if (aux == this.tablero[i][pos]) {
                        this.tablero[auxj][pos] += this.tablero[i][pos];
                        setScore(this.tablero[i][pos]);
                        this.tablero[i][pos] = aux = auxj = 0;
                        movi = true;
                    } else {
                        aux = this.tablero[i][pos];
                        auxj = i;
                    }
                } else {
                    aux = this.tablero[i][pos];
                    auxj = i;
                }

            }
        }
        return movi;
    }

    private boolean unirNumerosIzquierda(int pos) {
        boolean movi =false;
        int aux=0;
        int auxj=0;
        for(int j=0; j<this.tablero.length; j++) {
            if (this.tablero[pos][j] != 0) {
                if (aux != 0) {
                    if (aux == this.tablero[pos][j]) {
                        this.tablero[pos][auxj] += this.tablero[pos][j];
                        setScore(this.tablero[pos][j]);
                        this.tablero[pos][j] = aux = auxj = 0;
                        movi = true;
                    } else {
                        aux = this.tablero[pos][j];
                        auxj = j;
                    }
                } else {
                    aux = this.tablero[pos][j];
                    auxj = j;
                }

            }
        }
        return movi;
    }

    private boolean eliminarEspaciosIzquierda(int pos) {
        boolean movi = false;
        int cont=0;
        for(int j=0; j<this.tablero.length; j++){
            if(this.tablero[pos][j]!=0){
                int aux=this.tablero[pos][j];
                this.tablero[pos][j]=0;
                this.tablero[pos][j-cont]=aux;
                    if(j-cont==j){
                        cont=0;
                    }else{
                        j=j-cont+1;
                        cont=1;
                        movi = true;
                    }

            }else{
                cont++;
            }

        }
        return movi;
    }

    public boolean unirNumeros(int pos) {
        boolean movi =false;
        int aux=0;
        int auxj=0;
        //dar prioridad al de la mas derecha
        for(int j=this.tablero.length-1; j>=0; j--) {
            if (this.tablero[pos][j] != 0) {

                if (aux != 0) {
                    if (aux == this.tablero[pos][j]) {
                        this.tablero[pos][auxj] += this.tablero[pos][j];
                        setScore(this.tablero[pos][j]);
                        this.tablero[pos][j] = aux = auxj = 0;
                        movi = true;
                    } else {
                        aux = this.tablero[pos][j];
                        auxj = j;
                    }
                } else {
                    aux = this.tablero[pos][j];
                    auxj = j;
                }

            }
        }
        return movi;
    }

    public boolean eliminarEspacios(int pos) {
        boolean movi = false;
        int cont=0;
        //int j=this.tablero.length-1;
        for(int j=this.tablero.length-1; j>=0; j--){
            if(this.tablero[pos][j]!=0){
                int aux=this.tablero[pos][j];
                this.tablero[pos][j]=0;
                this.tablero[pos][j+cont]=aux;
                    if(j+cont==j){
                        cont=0;
                    }else{
                        j=j+cont-1;
                        cont=1;
                        movi = true;
                    }

            }else{
                cont++;
            }

        }
        return movi;
    }
    public void posAleatoria(int num) {
        do{
            this.posi = this.aleatorio.nextInt(4);
            this.posj = this.aleatorio.nextInt(4);
        }while(this.tablero[this.posi][this.posj]!=0);
        this.tablero[this.posi][this.posj]=num;
    }

    public int[][] getTablero(){
        return this.tablero;
    }
}
