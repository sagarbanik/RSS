package com.rss.rss.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

public class SpinnerAdapter extends BaseAdapter implements Filterable {

    private Context context;
    private String[] itemArray;
    private String[] filterArray;
    private LayoutInflater mInflater;
    private ItemFilter mFilter = new ItemFilter();

    public SpinnerAdapter(Context context, String[] itemArray) {
        this.context = context;
        this.itemArray = itemArray;
        this.filterArray = itemArray;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return itemArray.length;
    }

    @Override
    public Object getItem(int i) {
        return itemArray[i];
    }

    @Override
    public long getItemId(int i) {

        return Long.valueOf(i);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = mInflater.inflate(android.R.layout.simple_list_item_1,viewGroup,false);

        TextView textView = view.findViewById(android.R.id.text1);
        textView.setText(itemArray[i].trim());

        return view;
    }

    @Override
    public Filter getFilter() {
        return mFilter;
    }


    private class ItemFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            String filterString = constraint.toString().toLowerCase();

            FilterResults results = new FilterResults();

            final String[] list = itemArray;

            int count = list.length;
            final String[] nlist = new String[count];

            String filterableString ;

            for (int i = 0; i < count; i++) {
                filterableString = list[i];
                if (filterableString.toLowerCase().contains(filterString)) {
                    nlist[i] = filterableString;
                }
            }

            results.values = nlist;
            results.count = nlist.length;

            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            notifyDataSetChanged();
        }

    }

}
