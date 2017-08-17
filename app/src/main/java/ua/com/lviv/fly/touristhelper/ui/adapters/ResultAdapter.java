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
import ua.com.lviv.fly.touristhelper.data.ResultsVO;


public class ResultAdapter extends BaseAdapter {
    List<ResultsVO> data = new ArrayList<>();
    Context context;

    public ResultAdapter(Context context) {
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

    public void setData(List<ResultsVO> data) {
        this.data = data;
        notifyDataSetChanged();
    }



    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.item_feed, viewGroup, false);

        TextView name = (TextView) rowView.findViewById(R.id.name);
        name.setText(data.get(i).getName());
        return rowView;
    }
}
