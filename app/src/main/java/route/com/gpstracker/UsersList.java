package route.com.gpstracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import route.com.AddUser;
import route.com.gpstracker.DataBase.Entities.User;
import route.com.gpstracker.DataBase.MyDataBase;

public class UsersList extends AppCompatActivity {

    RecyclerView recyclerView;
    UsersRecyclerAdapter adapter;
    LinearLayoutManager layoutManager;
    List<User> userslist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView=findViewById(R.id.recyclerView);
        userslist=new ArrayList<>();
        adapter=new UsersRecyclerAdapter(userslist);
        recyclerView.setAdapter(adapter);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UsersList.this, AddUser.class));
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();


        getUsersthread.start();


    }
    Thread getUsersthread=new Thread(){
        @Override
        public void run() {
            super.run();
            userslist=MyDataBase.getInstance().usersDao().getAllUsers();

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    adapter.setItems(userslist);
                }
            });

        }
    };
}
