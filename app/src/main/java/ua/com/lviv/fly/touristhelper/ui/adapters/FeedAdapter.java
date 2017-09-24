package ua.com.lviv.fly.touristhelper.ui.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.RelativeSizeSpan;
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
            viewHolder.telephone = (TextView) convertView.findViewById(R.id.telephone);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        JsonVO jsonVO = data.get(position);
        setText(viewHolder.name, jsonVO.getName());
        setText(viewHolder.location, jsonVO.getAddress() );
        setText(viewHolder.telephone, jsonVO.getTelephone() );

        return convertView;
    }

    private class ViewHolder {
        TextView name;
        TextView location;
        TextView telephone;

    }

    private Spannable setSpannable(String text1, String text2) {
        Spannable span = new SpannableString(text1 + text2);
        span.setSpan(new RelativeSizeSpan(0.8f), text1.length(), text1.length() + text2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return span;
    }

    private void setText(TextView view, String text) {
        if (TextUtils.isEmpty(text)) {
            view.setVisibility(View.GONE);
        } else {
            view.setVisibility(View.VISIBLE);
            view.setText(text);
        }
    }


}
