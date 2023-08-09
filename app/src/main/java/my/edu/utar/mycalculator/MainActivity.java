package my.edu.utar.mycalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button myButton,myButton2,myButton3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //find divideSame Button id
        //Change the page to divideSame page
        myButton=findViewById(R.id.divideSame);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(),divideSame.class);
                startActivity(intent);
            }
        });

        //find dividePercentage Button id
        //Change the page to dividePercentage page
        myButton2=findViewById(R.id.dividePercentage);
        myButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(),dividePercentage.class);
                startActivity(intent);
            }
        });

        //find History Button id
        //Change the page to History page
        myButton3=findViewById(R.id.history);
        myButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(),History_Run.class);
                startActivity(intent);
            }
        });
    }
}