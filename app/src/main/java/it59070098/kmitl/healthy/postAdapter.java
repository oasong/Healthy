package it59070098.kmitl.healthy;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class postAdapter extends ArrayAdapter<postclass> {
    List<postclass> posts = new ArrayList<postclass>();
    Context context;

    public postAdapter(Context context, int resource, List<postclass> objects) {
        super(context, resource, objects);
        this.posts = objects;
        this.context = context;
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View postitem = LayoutInflater.from(context).inflate(R.layout.postadapter,parent,false);

        TextView post_id = postitem.findViewById(R.id.post_id);
        TextView post_title = postitem.findViewById(R.id.post_title);
        TextView post_body = postitem.findViewById(R.id.post_body);
        TextView post_name = postitem.findViewById(R.id.post_name);
        TextView post_email = postitem.findViewById(R.id.post_email);

        postclass row  = posts.get(position);
        post_id.setText(row.getId());
        post_title.setText(row.getTitle());
        post_body.setText(row.getBody());
        post_name.setText(row.getName());
        if (row.getEmail() == null){
            post_email.setText("");
        }else{
            post_email.setText("("+row.getEmail()+")");
        }
//        post_email.setText(row.getEmail());


        return postitem;

    }
}
