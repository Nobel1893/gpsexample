package route.com;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import route.com.gpstracker.DataBase.Entities.User;
import route.com.gpstracker.DataBase.MyDataBase;
import route.com.gpstracker.R;

public class AddUser extends AppCompatActivity implements View.OnClickListener {

    protected EditText name;
    protected Button insert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_add_user);
        initView();

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.insert) {
            String nname=name.getText().toString();

            if (nname.equals("")){
                Toast.makeText(this,"name is empty",Toast.LENGTH_LONG).show();
            }else {
                final User user=new User();
                user.setName(nname);

                Thread AddUserThread=new Thread(){
                    public void run(){
                        MyDataBase.getInstance().usersDao().AddUser(user);
                    }
                };
                AddUserThread.start();
                Toast.makeText(this,"user added success",Toast.LENGTH_LONG).show();

            }
        }
    }


    private void initView() {
        name = (EditText) findViewById(R.id.name);
        insert = (Button) findViewById(R.id.insert);
        insert.setOnClickListener(AddUser.this);
    }
}
