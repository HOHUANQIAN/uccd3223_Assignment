package my.edu.utar.mycalculator;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DecimalFormat;

public class History_Run extends AppCompatActivity {

    public SQLiteAdapter mySQLiteAdapter;
    public String name;
    public double totalPrice, priceDivide, serviceTax;
    public int numOfPeople2;
    public boolean divideSame, dividePercentage;
    public LinearLayout ll;
    public double[] percentageList,priceList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_run);

        ll = findViewById(R.id.layout6);

        TextView attribute=new TextView(this);
        attribute.setText("Name    |"+"Total Price"+"|"+"Tax/Percent"+"|"+"Price Divide");
        attribute.setTextSize(18.0f);
        attribute.setBackgroundColor(Color.LTGRAY);

        TextView listContent = new TextView(this);
        listContent.setTextSize(18.0f);

        ll.addView(attribute);
        ll.addView(listContent);

        divideSame = getIntent().getBooleanExtra("divideSame", true);
        if (divideSame) {
            serviceTax = getIntent().getDoubleExtra("serviceTax", 1.0);
            totalPrice = getIntent().getDoubleExtra("totalPrice", 0.0);
            priceDivide = getIntent().getDoubleExtra("priceAfterDivide", 0.0);

            mySQLiteAdapter = new SQLiteAdapter(this);
            // Open to write
            mySQLiteAdapter.openToWrite();

            // Insert data
            mySQLiteAdapter.insert("People ", totalPrice, serviceTax, priceDivide);

            // Close
            mySQLiteAdapter.close();

            // Open to read
            mySQLiteAdapter.openToRead();
            String contentRead = mySQLiteAdapter.queueAll_Two();
            mySQLiteAdapter.close();
            listContent.setText(contentRead);
        }

        dividePercentage = getIntent().getBooleanExtra("dividePercentage", false);
        if (dividePercentage) {

            numOfPeople2 = getIntent().getIntExtra("numberOfPeople2", 1);
            totalPrice=getIntent().getDoubleExtra("totalPrice2",0.0);
            percentageList=new double[10];
            percentageList=getIntent().getDoubleArrayExtra("percentages");
            priceList=new double[numOfPeople2];
            priceList=getIntent().getDoubleArrayExtra("priceDivide");

            mySQLiteAdapter = new SQLiteAdapter(this);
            // Open to write
            mySQLiteAdapter.openToWrite();

            // Insert data
            for (int i = 0; i < numOfPeople2; i++) {
                mySQLiteAdapter.insert(("People "+(i+1)), totalPrice,percentageList[i],priceList[i] );
            }

            // Close
            mySQLiteAdapter.close();

            // Open to read
            mySQLiteAdapter.openToRead();
            // String contentRead=mySQLiteAdapter.queueAll();
            String contentRead = mySQLiteAdapter.queueAll_Two();
            mySQLiteAdapter.close();
            listContent.setText(contentRead);
        }
    }
}
