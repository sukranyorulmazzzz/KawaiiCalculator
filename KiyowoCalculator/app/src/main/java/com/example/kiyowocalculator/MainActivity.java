package com.example.kiyowocalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import org.mariuszgromada.math.mxparser.*;

public class MainActivity extends AppCompatActivity {

    EditText display;
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        display=findViewById(R.id.display);
        display.setShowSoftInputOnFocus(false);
        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getString(R.string.tappme).equals(display.getText().toString())){
                    display.setText("");

                }
            }
        });

    }

    public void anyButton(View view) {
        switch (view.getId()){
            case R.id.c: display.setText("");break;
            case R.id.parantez: parantezEkle();break;
            case R.id.us: islemYap("^");break;
            case R.id.yedi: islemYap("7");break;
            case R.id.sekiz: islemYap("8");break;
            case R.id.dokuz: islemYap("9");break;
            case R.id.sifir: islemYap("0");break;
            case R.id.ucsifir:displayiucsifirla(); break;
            case R.id.bolu:islemYap("÷"); break;
            case R.id.carpi:islemYap("x"); break;
            case R.id.eksi:islemYap("-"); break;
            case R.id.arti:islemYap("+"); break;
            case R.id.dort:islemYap("4"); break;
            case R.id.bes:islemYap("5"); break;
            case R.id.alti:islemYap("6"); break;
            case R.id.bir:islemYap("1"); break;
            case R.id.iki:islemYap("2"); break;
            case R.id.uc:islemYap("3"); break;
            case R.id.nokta:islemYap("."); break;
            case R.id.esittir:islemiSonuclandir(); break;
            case R.id.silme:sil(); break;

        }
    }

    private void sil() {
         int cursorPos=display.getSelectionStart();
         if (cursorPos>0){
             String oldDisplay=display.getText().toString();
             String leftSideofDisplay=oldDisplay.substring(0,cursorPos-1);
             String rightSideofDisplay=oldDisplay.substring(cursorPos);
             String newText=leftSideofDisplay+rightSideofDisplay;
             display.setText(newText);
             display.setSelection(cursorPos-1);
         }
    }

    private void displayiucsifirla() {
        int cursorPosition=display.getSelectionStart();
        if (getString(R.string.tappme).equals(display.getText().toString())){
            display.setText("0");

        }
        else {
            String oldDisplay=display.getText().toString();
            String leftSideofDisplay=oldDisplay.substring(0,cursorPosition);
            String rightSideofDisplay=oldDisplay.substring(cursorPosition);
            String newText=leftSideofDisplay+"000"+rightSideofDisplay;
            display.setText(newText);
        }
        display.setSelection(cursorPosition+3);
    }

    private void islemiSonuclandir() {
         String textDisplay=display.getText().toString();
         String reTextDisplay=textDisplay.replaceAll("÷","/");
         reTextDisplay=textDisplay.replaceAll("x","*");
         Expression ifade=new Expression(reTextDisplay);
         String results=String.valueOf(ifade.calculate()).toString();
         if (!results.equals("Nan")){

             display.setText(results);
             display.setSelection(results.length());
         }
         else{

             Toast.makeText(this,"incorrect entry...",Toast.LENGTH_LONG).show();
         }
    }

    private void islemYap(String yazi) {
         int cursorPosition=display.getSelectionStart();
        if (getString(R.string.tappme).equals(display.getText().toString())){
            display.setText(yazi);

        }
        else {
            String oldDisplay=display.getText().toString();
            String leftSideofDisplay=oldDisplay.substring(0,cursorPosition);
            String rightSideofDisplay=oldDisplay.substring(cursorPosition);
            String newText=leftSideofDisplay+yazi+rightSideofDisplay;
            display.setText(newText);
        }
        display.setSelection(cursorPosition+1);
    }

    private void parantezEkle() {

         String textDisplay=display.getText().toString();
         int cursorPos=display.getSelectionStart();
         int countBrackets=0;
         for (int i=0;i<textDisplay.length();i++){
             if (textDisplay.substring(i,i+1).equalsIgnoreCase("(")) countBrackets++;
             if (textDisplay.substring(i,i+1).equalsIgnoreCase(")")) countBrackets--;
         }
         String lastCharOfTextDisplay=textDisplay.substring(textDisplay.length()-1);
         if (countBrackets==0||lastCharOfTextDisplay.equals("(")) islemYap("(");
         else if (countBrackets>0&&!lastCharOfTextDisplay.equals(")")) islemYap(")");
    }
}