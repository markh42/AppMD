package com.coursera.unam.appmd;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    EditText fullName;
    EditText date;
    EditText email;
    EditText telephone;
    EditText description;
    DatePickerDialog datePickerDialog;
    Button nextButtonOne;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fullName = (EditText) findViewById(R.id.fullName);
        date = (EditText) findViewById(R.id.selectDate);
        telephone = (EditText) findViewById(R.id.mainTelephone);
        date = (EditText) findViewById(R.id.selectDate);
        email = (EditText) findViewById(R.id.email);
        description = (EditText) findViewById(R.id.description);

        Bundle parameters    = getIntent().getExtras();

        if(parameters != null) {

            String pName          = parameters.getString(getResources().getString(R.string.pName));
            String pDescription   = parameters.getString(getResources().getString(R.string.pDescription));
            String pTelephone     = parameters.getString(getResources().getString(R.string.pTelephone));
            String pEmail         = parameters.getString(getResources().getString(R.string.pEmail));
            String pDate          = parameters.getString(getResources().getString(R.string.pDate));

            fullName.setText(pName);
            date.setText(pDate);
            telephone.setText(pTelephone);
            email.setText(pEmail);
            description.setText(pDescription);

        }

        fullName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {

                    if(fullName.getText().toString().
                            equals(getResources().getString(R.string.msgErrorName))) {
                        fullName.setText("");
                        fullName.setTextColor(Color.BLACK);
                    }
                }
            }
        });


        //Date Picker
        date.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if(hasFocus){

                    if(date.getText().toString().
                            equals(getResources().getString(R.string.msgDateEmail))) {
                        date.setText("");
                        date.setTextColor(Color.BLACK);
                    }
                    datePickerOption();
                    telephone.requestFocus();
                }

            }
        });

        email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    System.out.println(email.getText().toString());

                    if(email.getText().toString().equals(
                            getResources().getString(R.string.msgErrorEmail).toString())) {
                        email.setText("");
                        email.setTextColor(Color.BLACK);
                    }
                }
            }
        });

        //Next Button
        nextButtonOne = (Button) findViewById(R.id.nextButtonAOne);
        nextButtonOne.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Boolean validForm = true;
                if(fullName.getText().toString().equals("")) {
                    fullName.setText(getResources().getString(R.string.msgErrorName));
                    fullName.setTextColor(Color.RED);
                    validForm = false;
                }
                if(!isValidEmail(email.getText().toString())) {
                    email.setText(getResources().getString(R.string.msgErrorEmail));
                    email.setTextColor(Color.RED);
                    validForm = false;
                }

                if(!isValidDate(date.getText().toString())) {
                    date.setText(getResources().getString(R.string.msgDateEmail));
                    date.setTextColor(Color.RED);
                    validForm = false;
                }

                if(validForm){
                    Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                    intent.putExtra(getResources().getString(R.string.pName), fullName.getText().toString());
                    intent.putExtra(getResources().getString(R.string.pDate), date.getText().toString());
                    intent.putExtra(getResources().getString(R.string.pTelephone), telephone.getText().toString());
                    intent.putExtra(getResources().getString(R.string.pEmail), email.getText().toString());
                    intent.putExtra(getResources().getString(R.string.pDescription), description.getText().toString());
                    startActivity(intent);
                }

            }
        });



    }

    public void datePickerOption(){

        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        datePickerDialog = new DatePickerDialog(MainActivity.this, R.style.AppTheme, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                date.setText(paddingLeft(dayOfMonth) + "/" + paddingLeft(monthOfYear) + "/" + year);
            }
        }, year, month,day);

        datePickerDialog.show();



    }

    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private String paddingLeft(int number) {

        return String.format("%02d", number);
    }

    private boolean isValidDate(String date) {

        System.out.println(date);
        String DATE_PATTERN ="^(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\\d{2}$";

        Pattern pattern = Pattern.compile(DATE_PATTERN);
        Matcher matcher = pattern.matcher(date);
        return matcher.matches();
    }





}
