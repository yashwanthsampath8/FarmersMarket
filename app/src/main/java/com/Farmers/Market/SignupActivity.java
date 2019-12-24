package com.Farmers.Market;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {

    //

    private static final String record[] = new String[User.tbl_customer_fields.length];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        final Button clerkLoginButton    = (Button)   findViewById(R.id.clerkLoginBtn);
        final Button customerLoginButton = (Button)   findViewById(R.id.customerLoginBtn);
        final EditText username          = (EditText) findViewById(R.id.usernameEditText);
        final EditText password          = (EditText) findViewById(R.id.passwordEditText);
        final EditText firstname         = (EditText) findViewById(R.id.firstnameEditText);
        final EditText lastname          = (EditText) findViewById(R.id.lastnameEditText);
        final EditText address           = (EditText) findViewById(R.id.addressEditText);
        final EditText city              = (EditText) findViewById(R.id.cityEditText);
        final EditText postalCode        = (EditText) findViewById(R.id.postalCodeEditText);
        //final TextView display           = (TextView) findViewById(R.id.display);

        final DatabaseManager db = new DatabaseManager(this);
        //db.deleteDatabase(getApplicationContext());
        //db.createDatabase(getApplicationContext());
        //db.dbInitialize( tableCreatorString);

        // Handle Save button

        customerLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                record[1] = username.getText().toString();
                record[2] = password.getText().toString();
                record[3] = firstname.getText().toString();
                record[4] = lastname.getText().toString();
                record[5] = address.getText().toString();
                record[6] = city.getText().toString();
                record[7] = postalCode.getText().toString();
                if (!record[1].matches("[a-zA-Z0-9._-]+@[a-z]+.[.][a-z]+")) {
                    Toast.makeText(getBaseContext(), "Invalid Email Address !!", Toast.LENGTH_SHORT).show();
                }
                if (record[1].equals("") || record[2].equals("") || record[3].equals("") || record[4].equals("") ||
                        record[5].equals("") || record[6].equals("") || record[7].equals("")) {
                    Toast.makeText(getBaseContext(), "Please Fill in all your Details", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    User customer = new User(record);
                    customer.addToDatabaseCustomer();
                }
                Toast.makeText(getBaseContext(), "Sign up successful!", Toast.LENGTH_SHORT).show();
                onBackPressed();
            }
        });

        clerkLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                record[1] = username.getText().toString();
                record[2] = password.getText().toString();
                record[3] = firstname.getText().toString();
                record[4] = lastname.getText().toString();
                record[5] = address.getText().toString();
                record[6] = city.getText().toString();
                record[7] = postalCode.getText().toString();
                if (!record[1].matches("[a-zA-Z0-9._-]+@[a-z]+.[.][a-z]+")) {
                    Toast.makeText(getBaseContext(), "Invalid Email Address !!", Toast.LENGTH_SHORT).show();
                }
                if (record[1].equals("") || record[2].equals("") || record[3].equals("") || record[4].equals("") ||
                        record[5].equals("") || record[6].equals("") || record[7].equals("")) {
                    Toast.makeText(getBaseContext(), "Please Fill in all your Details", Toast.LENGTH_SHORT).show();
                }else
                {
                    User clerk = new User(record);
                    clerk.addToDatabaseClerk();
                }
                Toast.makeText(getBaseContext(), "Sign up successful!", Toast.LENGTH_SHORT).show();
                onBackPressed();
            }
        });

    }
}
