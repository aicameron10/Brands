package com.stylight.brands.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.stylight.brands.R;
import com.stylight.brands.activities.MainActivity;
import com.stylight.brands.model.Names;
import com.stylight.brands.model.SelectedNames;

import java.util.List;


public class ListAdapterSelected extends BaseAdapter {


    private List<SelectedNames> itemList;

    private Activity activity;

    ViewHolder viewHolder;


    public ListAdapterSelected(Activity activity, List<SelectedNames> itemList) {

        this.itemList = itemList;
        this.activity = activity;

    }

    public int getCount() {
        return itemList.size();
    }

    public Object getItem(int position) {
        return itemList.get(position);
    }

    public long getItemId(int position) {
        return position;
    }


    /**
     * Holds child views for one row.
     */
    public static class ViewHolder {
        private CheckBox checkBox;
        private TextView textView;

        public ViewHolder() {
        }


        public ViewHolder(TextView textView, CheckBox checkBox) {
            this.checkBox = checkBox;
            this.textView = textView;

        }

        public CheckBox getCheckBox() {
            return checkBox;
        }

        public void setCheckBox(CheckBox checkBox) {
            this.checkBox = checkBox;
        }

        public TextView getTextView() {
            return textView;
        }

        public void setTextView(TextView textView) {
            this.textView = textView;
        }

    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final ViewHolder holder;
        final SelectedNames name = (SelectedNames) this.getItem(position);
        CheckBox checkBox;
        TextView textView;
        if (convertView == null) {

            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // well set up the ViewHolder


            convertView = inflater.inflate(R.layout.list_item_brand, parent, false);

            holder = new ViewHolder();

            holder.textView = (TextView) convertView.findViewById(R.id.brand);
            holder.checkBox = (CheckBox) convertView.findViewById(R.id.checkBoxBrand);
            holder.checkBox.setFocusable(false);
            holder.checkBox.setFocusableInTouchMode(false);

            holder.checkBox.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    CheckBox cb = (CheckBox) v;
                    if (!cb.isChecked()) {
                        System.out.println("Delete me selected");
                        SelectedNames cbb = (SelectedNames) cb.getTag();
                        cbb.setIsChecked(false);

                        System.out.println(name.getId() + "id");
                        ((MainActivity) activity).removeFromTopList(name.getId());

                    }

                }
            });

            convertView.setTag(holder);


        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        holder.checkBox.setTag(name);
        holder.checkBox.setChecked(name.isChecked());
        holder.textView.setText(name.getName());
        return convertView;

    }


}