package com.coursera.unam.appmd;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

    Button edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Bundle parameters    = getIntent().getExtras();
        final String name          = parameters.getString(getResources().getString(R.string.pName));
        final String description   = parameters.getString(getResources().getString(R.string.pDescription));
        final String telephone     = parameters.getString(getResources().getString(R.string.pTelephone));
        final String email         = parameters.getString(getResources().getString(R.string.pEmail));
        final String date          = parameters.getString(getResources().getString(R.string.pDate));

        TextView tvName      = (TextView) findViewById(R.id.tvFullName);
        TextView tvDate      = (TextView) findViewById(R.id.tvDate);
        TextView tvTelephone = (TextView) findViewById(R.id.tvTelephone);
        TextView tvEmail     = (TextView) findViewById(R.id.tvEmail);
        TextView tvDescription     = (TextView) findViewById(R.id.tvDescription);

        tvName.setText(name);
        tvDate.setText(getResources().getString(R.string.birthDate) +  " : " + date);
        tvTelephone.setText(getResources().getString(R.string.mainTelephone) +  " : " + telephone);
        tvEmail.setText(getResources().getString(R.string.email) +  " : " + email);
        tvDescription.setText(getResources().getString(R.string.description) +  " : " + description);

        edit = (Button) findViewById(R.id.nextButtonATwo);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailsActivity.this, MainActivity.class);

                intent.putExtra(getResources().getString(R.string.pName), name);
                intent.putExtra(getResources().getString(R.string.pDate), date);
                intent.putExtra(getResources().getString(R.string.pTelephone), telephone);
                intent.putExtra(getResources().getString(R.string.pEmail), email);
                intent.putExtra(getResources().getString(R.string.pDescription), description);
                startActivity(intent);

            }
        });
    }
}
