package ua.com.lviv.fly.touristhelper.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ua.com.lviv.fly.touristhelper.R;
import ua.com.lviv.fly.touristhelper.data.JsonVO;


public class FeedAdapter extends BaseAdapter {
    List<JsonVO> data = new ArrayList<>();
    Context context;

    public FeedAdapter(Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public void setData(List<JsonVO> data) {
        this.data = data;
        notifyDataSetChanged();
    }


    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {

        final ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_feed, viewGroup, false);

            viewHolder = new ViewHolder();
            viewHolder.name = (TextView) convertView.findViewById(R.id.name);
            viewHolder.location = (TextView) convertView.findViewById(R.id.location);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        JsonVO jsonVO = data.get(position);
        viewHolder.name.setText(jsonVO.getName());
        viewHolder.location.setText(jsonVO.getAddress());

        return convertView;
    }

    private class ViewHolder {
        TextView name;
        TextView location;
        TextView title3;
    }


}
