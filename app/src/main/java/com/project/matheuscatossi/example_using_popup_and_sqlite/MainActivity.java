package com.project.matheuscatossi.example_using_popup_and_sqlite;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.project.matheuscatossi.example_using_popup_and_sqlite.adapter.UserCustomAdapter;
import com.project.matheuscatossi.example_using_popup_and_sqlite.handler.DatabaseHandler;
import com.project.matheuscatossi.example_using_popup_and_sqlite.model.User;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        db = new DatabaseHandler(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle(R.string.add_info);
                View viewInflated = LayoutInflater.from(MainActivity.this).inflate(R.layout.popup_text, (ViewGroup) view.getRootView(), false);
                final EditText input_name = (EditText) viewInflated.findViewById(R.id.input_name);
                final EditText input_age = (EditText) viewInflated.findViewById(R.id.input_age);
                 builder.setView(viewInflated);

                builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        db.addUser(new User(input_name.getText().toString(), Integer.parseInt(input_age.getText().toString())));
                        onResume();
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        db = new DatabaseHandler(this);

        ListView listViewProduto;
        listViewProduto = (ListView) findViewById(R.id.listUsers);

        ArrayList<User> consulta = (ArrayList<User>) db.getAllUsers();

        UserCustomAdapter rankingCustomAdapter;
        rankingCustomAdapter = new UserCustomAdapter(consulta, this);

        listViewProduto.setAdapter(rankingCustomAdapter);
    }
}
