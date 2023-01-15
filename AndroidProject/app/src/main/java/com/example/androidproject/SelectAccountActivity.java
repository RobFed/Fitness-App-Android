package com.example.androidproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class SelectAccountActivity extends AppCompatActivity {

    DatabaseHelper db;
    ArrayList<String> listItems = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    ArrayList<String> passwordItems = new ArrayList<String>();
    ArrayList<String> emailItems = new ArrayList<String>();
    ArrayList<String> idItems = new ArrayList<String>();

    ListView list;
    String selectedItem = "";
    int index = 0;
    String id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_account);
        list = (ListView) findViewById(R.id.listView);

        db = new DatabaseHelper(this);
        Cursor cursor = db.getAllData();

        if (cursor.getCount() == 0){
            showMessage("Error", "No accounts found");
        } else {
            while (cursor.moveToNext()) {
                String temp = "Name: ";
                String pass = "";
                String email = "";
                String id;
                id = cursor.getString(0);
                temp += cursor.getString(1) + ". ";
                email = cursor.getString(2);
                temp += cursor.getString(3) + ". ";
                temp += cursor.getString(4);
                pass = cursor.getString(7);
                idItems.add(id);
                emailItems.add(email);
                passwordItems.add(pass);
                listItems.add(temp);
            }
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listItems);
            setListAdapter(adapter);

        }
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(SelectAccountActivity.this, "Selected: " + list.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
                selectedItem = list.getItemAtPosition(position).toString();
                index = position;
            }
        });
        Button btn_go_back = (Button) findViewById(R.id.button_go_back_selected);
        btn_go_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(SelectAccountActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        Button btn_login = (Button) findViewById(R.id.button_login_selected);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedItem.matches("")) {
                    Toast.makeText(SelectAccountActivity.this, "Please select an account", Toast.LENGTH_SHORT).show();
                } else {
                    final Intent intent = new Intent(SelectAccountActivity.this, EnterPasswordActivity.class);
                    intent.putExtra("ACCOUNT_ID", selectedItem);
                    intent.putExtra("PASSWORD_ID", passwordItems.get(index));
                    intent.putExtra("EMAIL_ID", emailItems.get(index));
                    startActivity(intent);
                }

            }
        });
        Button btn_delete = (Button) findViewById(R.id.button_delete_account);
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedItem.matches("")) {
                    Toast.makeText(SelectAccountActivity.this, "Please select an account", Toast.LENGTH_SHORT).show();
                } else {
                    String index1 = idItems.get(index);
                    Toast.makeText(SelectAccountActivity.this, "Attempting to delete ID=" + index1, Toast.LENGTH_SHORT).show();
                    db.removeData(index1);
                    finish();
                    startActivity(getIntent());
                }
            }
        });


    }

    protected ListView getListView() {
        if (list == null) {
            list = (ListView) findViewById(R.id.listView);
        }
        return list;
    }

    protected void setListAdapter(ListAdapter adapter){
        getListView().setAdapter(adapter);
    }

    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }



}