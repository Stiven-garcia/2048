package com.example.a2048;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Logica.juego;

public class MainActivity extends AppCompatActivity {
    TableRow lineas [];
    TableLayout tabla;
    TextView score ;
    TextView best;
    juego juego;
    int [][] tablero;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.lineas = new TableRow[4];
        this.lineas[0]= findViewById(R.id.linea1);
        this.lineas[1]= findViewById(R.id.linea2);
        this.lineas[2]= findViewById(R.id.linea3);
        this.lineas[3]= findViewById(R.id.linea4);
        this.score = findViewById(R.id.PuntajeS);
        this.best = findViewById(R.id.PuntajeB);
        this.tabla = findViewById(R.id.tableLayout);
        iniciar();
        View pantalla = getWindow().getDecorView();
        pantalla.setOnTouchListener(new View.OnTouchListener() {
            float x1;
            float x2;
            float y1;
            float y2;
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()== MotionEvent.ACTION_DOWN){
                    x1 = event.getX();
                    y1 = event.getY();
                }else{
                    if(event.getAction() == MotionEvent.ACTION_UP){
                        x2 = event.getX();
                        y2= event.getY();
                        movimiento(x1, x2, y1, y2);
                    }
                }
                return false;
            }
        });
    }

    private void movimiento(float x1, float x2, float y1, float y2) {
        float difx = x2 - x1;
        float dify = y2 - y1;
        String movimiento;
        if(Math.abs(difx) > Math.abs(dify)){
            //se movio en el eje x
            if(difx > 0){
                movimiento = "Derecha";
                derecha();

            }else{
                movimiento = "Izquierda";
                izquierda();
            }
        }else{
            //se movio en el eje y
            if(dify > 0){
                movimiento = "Abajo";
                abajo();
            }else{
                movimiento = "Arriba";
                arriba();
            }
        }
        Toast.makeText(getApplicationContext(),movimiento,Toast.LENGTH_SHORT).show();
    }

    private void arriba() {
        this.juego.arriba();
        for(int i=0; i<lineas.length;i++){
            lineas[i].removeAllViews();
        }
        puntaje();
        pintarTblero();
    }

    private void abajo() {
        this.juego.abajo();
        for(int i=0; i<lineas.length;i++){
            lineas[i].removeAllViews();
        }
        puntaje();
        pintarTblero();
    }

    private void izquierda() {
        this.juego.izquierda();
        for(int i=0; i<lineas.length;i++){
            lineas[i].removeAllViews();
        }
        puntaje();
        pintarTblero();
    }

    private void derecha() {
        this.juego.derecha();
        for(int i=0; i<lineas.length;i++){
            lineas[i].removeAllViews();
        }
        puntaje();
        pintarTblero();
    }
    private void puntaje(){
        this.score.setText(Integer.toString(this.juego.getScore()));
        this.best.setText(Integer.toString(this.juego.getBest()));
    }
    private void iniciar() {
        this.juego = new juego();
        puntaje();
        pintarTblero();
    }

    private void pintarTblero() {

        this.tablero = this.juego.getTablero();
        for(int i =0; i<this.lineas.length;i++){
            for(int j=0; j<this.lineas.length; j++){
                ImageView cuadro = new ImageView(this);
                TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(180, 180);
                layoutParams.setMargins(0, -2, 0, -2); // (left, top, right, bottom)
                cuadro.setLayoutParams(layoutParams);
                //  cuadro.setLayoutParams(new TableRow.LayoutParams(180, 180));
                if(tablero[i][j]==0){
                    cuadro.setImageResource(R.drawable.vacio);
                }else{
                    if(tablero[i][j]==2){
                        cuadro.setImageResource(R.drawable.dos);
                    }else{
                        if(tablero[i][j]==4){
                            cuadro.setImageResource(R.drawable.cuatro);
                        }else{
                            if(tablero[i][j]==8){
                                cuadro.setImageResource(R.drawable.ocho);
                            }else{
                                if(tablero[i][j]==16){
                                    cuadro.setImageResource(R.drawable.diezyseis);
                                }else{
                                    if(tablero[i][j]==32){
                                        cuadro.setImageResource(R.drawable.treintaydos);
                                    }else{
                                        if(tablero[i][j]==64){
                                            cuadro.setImageResource(R.drawable.sesentaycuatro);
                                        }else{
                                            if(tablero[i][j]==128){
                                                cuadro.setImageResource(R.drawable.ciento28);
                                            }else{
                                                if(tablero[i][j]==256){
                                                    cuadro.setImageResource(R.drawable.dos56);
                                                }else{
                                                    if(tablero[i][j]==512){
                                                        cuadro.setImageResource(R.drawable.cinco12);
                                                    }else{
                                                        if(tablero[i][j]==1024){
                                                            cuadro.setImageResource(R.drawable.diez24);
                                                        }else{
                                                            if(tablero[i][j]==2048){
                                                                cuadro.setImageResource(R.drawable.doz48);
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                }

                this.lineas[i].addView(cuadro);

            }
            this.lineas[i].setGravity(Gravity.CENTER);
        }
    }
}
