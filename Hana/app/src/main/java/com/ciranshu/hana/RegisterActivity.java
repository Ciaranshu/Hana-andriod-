package com.ciranshu.hana;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {
    private static final String DB_FILE = "users.db",DB_TABLE = "users";
    private SQLiteDatabase mUserDbRW;
    private EditText mEdtName,mEdtUsername,mEdtPassword,mEdtAge;
    protected void onDestroy(){
        //
        super.onDestroy();
        mUserDbRW.close();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mEdtName = (EditText)findViewById(R.id.etName);
        mEdtUsername = (EditText)findViewById(R.id.etUsername);
        mEdtPassword = (EditText)findViewById(R.id.etPassword);
        mEdtAge = (EditText)findViewById(R.id.etAge);

        UserDatabaseHelper friDbHp = new UserDatabaseHelper(getApplicationContext(),DB_FILE, null,1);

        friDbHp.sCreateTableCommand = "CREATE TABLE "+DB_TABLE+" ("+
                "id integer primary key autoincrement, "+
                "name TEXT NOT NULL, "+
                "username TEXT NOT NULL, "+
                "password TEXT NOT NULL, "+
                "age INTEGER)";

        mUserDbRW = friDbHp.getWritableDatabase();

        Button registerButton = (Button) findViewById(R.id.bRegister);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            //if (接口)
            public void onClick(View view) {
                ContentValues newRow = new ContentValues();
                newRow.put("name", mEdtName.getText().toString());
                newRow.put("username", mEdtUsername.getText().toString());
                newRow.put("password", mEdtPassword.getText().toString());
                newRow.put("age", mEdtAge.getText().toString());
                mUserDbRW.insert(DB_TABLE, null, newRow);
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }
}
