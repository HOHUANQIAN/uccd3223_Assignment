package my.edu.utar.mycalculator;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.DecimalFormat;

public class dividePercentage extends AppCompatActivity {

    public EditText inputNumPeople,inputTotalPrice;
    public EditText name,percentage,priceAfterDivide2;
    public TextView text,text2;
    public LinearLayout myLinearLayout,myLinearLayout2,myLinearLayout3,myLinearLayout4;

    public int numOfPeople;
    public double priceAfterDivide,totalPrice;
    public Button button3,button4,button5,button6;

    public EditText[] percentageList=new EditText[10];
    public double [] percentageList2=new double[10];
    public double [] priceList=new double [10];

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_divide_percentage);

        inputNumPeople=(EditText)findViewById(R.id.numOfPeople4);
        inputTotalPrice=(EditText)findViewById(R.id.totalPrice3);

        myLinearLayout = findViewById(R.id.layout2);
        myLinearLayout.removeAllViews(); // Clear any existing views to start fresh

        myLinearLayout2 = findViewById(R.id.layout3);
        myLinearLayout2.removeAllViews(); // Clear any existing views to start fresh

        myLinearLayout3 = findViewById(R.id.layout4);
        myLinearLayout3.removeAllViews(); // Clear any existing views to start fresh

        myLinearLayout4 = findViewById(R.id.layout5);
        myLinearLayout4.removeAllViews(); // Clear any existing views to start fresh

        button3=findViewById(R.id.createText);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numOfPeople=Integer.valueOf(inputNumPeople.getText().toString());

                if(numOfPeople>8){
                    AlertDialog.Builder builder=new AlertDialog.Builder(dividePercentage.this);
                    builder.setTitle("Error");
                    builder.setMessage("Do not enter more than 8 people");
                    builder.setCancelable(false);

                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            myLinearLayout.removeAllViews();
                            myLinearLayout2.removeAllViews();
                            myLinearLayout3.removeAllViews();
                            myLinearLayout4.removeAllViews();
                            dialog.cancel();
                        }
                    });

                    final AlertDialog alert= builder.create();

                    alert.show();
                }
                else{
                    myLinearLayout.removeAllViews();
                    myLinearLayout2.removeAllViews();
                    myLinearLayout3.removeAllViews();
                    myLinearLayout4.removeAllViews();

                    text = new TextView(dividePercentage.this);
                    text.setText("Name");
                    text.setTextSize(18.0f);
                    text.setTextColor(Color.WHITE);
                    text.setBackgroundColor(Color.parseColor("#cc7aa3"));
                    myLinearLayout.addView(text);

                    for (int i = 0; i < numOfPeople; i++) {
                        name = new EditText(dividePercentage.this); // Initialize a new TextView
                        name.setText("People "+(i+1));
                        name.setTextSize(15.0f);
                        myLinearLayout.addView(name);// Add the TextView to the linear layout
                        name.setKeyListener(null);
                    }

                    text2 = new TextView(dividePercentage.this);
                    text2.setText("Percentage (%)");
                    text2.setTextSize(18.0f);
                    text2.setTextColor(Color.WHITE);
                    text2.setBackgroundColor(Color.parseColor("#cc7aa3"));
                    myLinearLayout2.addView(text2);

                    for (int i = 0; i < numOfPeople; i++) {
                        percentage = new EditText(dividePercentage.this); // Initialize a new TextView
                        percentageList[i]=percentage;
                        percentage.setTextSize(15.0f);
                        myLinearLayout2.addView(percentage); // Add the TextView to the linear layout

                    }

                    text = new TextView(dividePercentage.this);
                    text.setText("Price After Divide");
                    text.setTextSize(18.0f);
                    text.setTextColor(Color.WHITE);
                    text.setBackgroundColor(Color.parseColor("#cc7aa3"));
                    myLinearLayout3.addView(text);

                    button4=new Button(dividePercentage.this);
                    button4.setText("Calculate");
                    myLinearLayout4.addView(button4);

                    button5=new Button(dividePercentage.this);
                    button5.setText("Clear");
                    myLinearLayout4.addView(button5);

                    button6=new Button(dividePercentage.this);
                    button6.setText("Save");

                    button4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            double percentage2;
                            double sum=0;

                            for(int i=0;i<numOfPeople;i++){
                                percentage2=Double.valueOf(percentageList[i].getText().toString());
                                percentageList2[i]=percentage2;
                                sum+=percentage2;
                            }

                            if(sum!=100){
                                AlertDialog.Builder builder=new AlertDialog.Builder(dividePercentage.this);
                                builder.setTitle("Error");
                                builder.setMessage("Sum of the percentage not equal to 100%");
                                builder.setCancelable(false);

                                builder.setPositiveButton("Enter Again", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        myLinearLayout.removeAllViews();
                                        myLinearLayout2.removeAllViews();
                                        myLinearLayout3.removeAllViews();
                                        myLinearLayout4.removeAllViews();

                                        dialog.cancel();
                                    }
                                });

                                final AlertDialog alert= builder.create();

                                alert.show();
                            }

                            // Get the total price from the input field
                            totalPrice = Double.valueOf(inputTotalPrice.getText().toString());

                            for (int i = 0; i < numOfPeople; i++) {
                                priceAfterDivide = (Double.valueOf(percentageList[i].getText().toString()))/100 * totalPrice;
                                priceList[i]=priceAfterDivide;

                                // Create a DecimalFormat object with the desired format pattern
                                DecimalFormat decimalFormat = new DecimalFormat("#.00");
                                // Use the format() method to format the number with two decimal places
                                String formattedPrice = decimalFormat.format(priceAfterDivide);

                                priceAfterDivide2 = new EditText(dividePercentage.this);
                                priceAfterDivide2.setText(formattedPrice);
                                priceAfterDivide2.setTextSize(15.0f);
                                priceAfterDivide2.setKeyListener(null);

                                // Add the TextView to the linear layout
                                myLinearLayout3.addView(priceAfterDivide2);
                            }

                            myLinearLayout4.addView(button6);
                        }
                    });

                    button5.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            myLinearLayout.removeAllViews();
                            myLinearLayout2.removeAllViews();
                            myLinearLayout3.removeAllViews();
                            myLinearLayout4.removeAllViews();
                        }
                    });

                    button6.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent=new Intent(v.getContext(),History_Run.class);
                            intent.putExtra("numberOfPeople2",numOfPeople);
                            intent.putExtra("percentages",percentageList2);
                            intent.putExtra("totalPrice2",totalPrice);
                            intent.putExtra("priceDivide",priceList);
                            intent.putExtra("dividePercentage",true);
                            startActivity(intent);
                        }
                    });

                }

            }
        });
    }
}