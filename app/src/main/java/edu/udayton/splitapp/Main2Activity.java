package edu.udayton.splitapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;


public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // Linking to Layout
        final Button btncalculate = (Button) findViewById(R.id.btncalculate);
        final EditText bill = (EditText) findViewById(R.id.bill);
        final EditText people=(EditText) findViewById(R.id.people);
        final Spinner txtGroup = (Spinner)findViewById(R.id.txtGroup);
        final TextView tip = (TextView) findViewById(R.id.tip);
        final TextView share = (TextView) findViewById(R.id.share);
        final TextView msg=(TextView)findViewById(R.id.msg);

        View.OnClickListener btncalculateListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double amount, finalTax, finalCost;
                int persons;

                Editable billAmount = bill.getText();
                Editable numberOfPeople = people.getText();
                String billAmountString = billAmount.toString();
                String numberOfPeopleString = numberOfPeople.toString();
                String txtGroupString = txtGroup.getSelectedItem().toString();

                try {
                    amount = Double.parseDouble(billAmountString);
                    persons = Integer.parseInt(numberOfPeopleString);

                    if (txtGroupString.equals("Excellent")){
                        finalTax = amount * 0.20;
                        msg.setText("One of the best meals ever!  I will recommend this place to everyone I know!");
                    }
                    else if (txtGroupString.equals("Average")) {
                        finalTax = amount * 0.15;
                        msg.setText("Everything was OK.");
                    }
                    else {
                        finalTax = amount * 0.05;
                        msg.setText( "Awful!  The worst!  I can't wait to give negative reviews on Yelp!");
                    }

                    finalCost = (amount+finalTax)/persons;
                    DecimalFormat currency = new DecimalFormat("$###,###.##");
                    String finalCostString = currency.format(finalCost);
                    String finalTaxString = currency.format(finalTax);
                    share.setText(finalCostString);
                    tip.setText(finalTaxString);
                }
                catch (Exception e){
                    Log.e(e.getMessage(), e.toString());
                }

            }
        };

        btncalculate.setOnClickListener(btncalculateListener);
    }
}
