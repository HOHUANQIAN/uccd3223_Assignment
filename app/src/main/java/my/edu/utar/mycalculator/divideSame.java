package my.edu.utar.mycalculator;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class divideSame extends AppCompatActivity {

     public double price,latestPrice,serviceTax;
     public int numOfPeople;
     public TextView getResult;
     public LinearLayout myLayout;

     public EditText inputPrice,inputNum,inputServiceTax;//get the input of price,number of people and service tax

     public Button myButton,myButton2,myButton3;//button

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_divide_same);

        //allow user to key in
        inputPrice=(EditText)findViewById(R.id.totalPrice2);
        inputNum=(EditText)findViewById(R.id.numOfPeople2);
        inputServiceTax=(EditText)findViewById(R.id.serviceCharge2);

        //find the layout id and add button to layout
        myLayout=new LinearLayout(this);
        myLayout=(LinearLayout)findViewById(R.id.layout1);
        myButton3=new Button(this);

        //button to click
        myButton=(Button) findViewById(R.id.calculate);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //change the variable type of price,number of people and service Tax
                price=Double.valueOf(inputPrice.getText().toString());
                numOfPeople=Integer.valueOf(inputNum.getText().toString());
                serviceTax=Double.valueOf(inputServiceTax.getText().toString());

                //calculate the price after divide
                latestPrice=price*((100+serviceTax)/100)/numOfPeople;

                // Create a DecimalFormat object with the desired format pattern
                DecimalFormat decimalFormat = new DecimalFormat("#.00");
                // Use the format() method to format the number with two decimal places
                String formattedPrice = decimalFormat.format(latestPrice);

                //Display the output
                getResult=(TextView) findViewById(R.id.priceAfterDivide2);
                getResult.setText(formattedPrice);
                getResult.setKeyListener(null);

                //Create a Save Button
                myButton3.setText("Save");
                myLayout.addView(myButton3);

            }
        });

        //Clear the data key in
        myButton2=(Button) findViewById(R.id.clear);
        myButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getResult=(TextView) findViewById(R.id.totalPrice2);
                getResult.setText("");
                getResult=(TextView) findViewById(R.id.numOfPeople2);
                getResult.setText("");
                getResult=(TextView) findViewById(R.id.serviceCharge2);
                getResult.setText("");
                getResult=(TextView) findViewById(R.id.priceAfterDivide2);
                getResult.setText("");
                myLayout.removeAllViews();
            }
        });

        //Go to history page
        myButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(),History_Run.class);
                intent.putExtra("numberOfPeople",numOfPeople);
                intent.putExtra("totalPrice",price);
                intent.putExtra("serviceTax",serviceTax);
                intent.putExtra("priceAfterDivide",latestPrice);
                intent.putExtra("divideSame",true);
                startActivity(intent);
            }
        });
    }
}