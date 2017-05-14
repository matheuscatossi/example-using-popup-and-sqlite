package com.project.matheuscatossi.example_using_popup_and_sqlite.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.project.matheuscatossi.example_using_popup_and_sqlite.R;
import com.project.matheuscatossi.example_using_popup_and_sqlite.model.User;

import java.util.ArrayList;

/**
 * Created by matheuscatossi on 5/14/17.
 */


public class UserCustomAdapter extends ArrayAdapter<User> implements View.OnClickListener {

    private ArrayList<User> dataSet;
    private Context mContext;

    @Override
    public void onClick(View v) {

    }

    private static class ViewHolder {
        TextView tv_name;
        TextView tv_age;
        LinearLayout ll_line;
    }

    public UserCustomAdapter(ArrayList<User> data, Context context) {
        super(context, R.layout.row_item_user, data);

        this.dataSet = data;
        this.mContext = context;
    }

    private int lastPosition = -1;

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        final User user = getItem(position);
        ViewHolder viewHolder;

        final View result;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_item_user, parent, false);

            viewHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.tv_age = (TextView) convertView.findViewById(R.id.tv_age);
            viewHolder.ll_line = (LinearLayout) convertView.findViewById(R.id.ll_line);

            result = convertView;
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);
        lastPosition = position;

        viewHolder.tv_name.setText("" + user.getName());
        viewHolder.tv_age.setText(", " + user.getAge() + " ano(s)");

        return convertView;
    }
}
