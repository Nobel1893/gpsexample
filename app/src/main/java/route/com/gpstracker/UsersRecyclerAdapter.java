package route.com.gpstracker;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import route.com.gpstracker.DataBase.Entities.User;

/**
 * Created by Mohamed Nabil Mohamed (Nobel) on 5/12/2018.
 * byte code SA
 * m.nabil.fci2015@gmail.com
 */

public class UsersRecyclerAdapter extends RecyclerView.Adapter<UsersRecyclerAdapter.ViewHolder> {

    List items;
    public UsersRecyclerAdapter(List items){
        this.items=items;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_user,null);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        User item=(User)items.get(position);
        holder.name.setText(item.getName());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(List items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView name;
        ViewHolder(View v){
            super(v);
            name=v.findViewById(R.id.name);
        }
    }
}
