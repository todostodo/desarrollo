package com.example.desarrollo.ExportJSON.Filter;

import android.content.Context;
import android.widget.Filter;

import com.example.desarrollo.Entidades.Foro;
import com.example.desarrollo.ExportJSON.RecycrerView.RecyclerViewForoRecetas;

import java.util.ArrayList;
import java.util.List;

public class FilteredHelperForoRecetas extends Filter {

    private List<Foro> currentList;
    private RecyclerViewForoRecetas adapter;
    private Context context;

    public FilteredHelperForoRecetas(List<Foro> currentList, RecyclerViewForoRecetas adapter, Context context) {
        this.currentList = currentList;
        this.adapter = adapter;
        this.context = context;
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {

        FilterResults filterResults = new FilterResults();

        if (constraint != null && constraint.length() > 0) {
            constraint = constraint.toString().toUpperCase();

            List<Foro> foundFilter = new ArrayList<>();
            Foro holidays = null;

            for (int i = 0; i < currentList.size(); i++) {
                holidays = currentList.get(i);
                if (holidays.getTituloReceta().toUpperCase().contains(constraint)) {
                    foundFilter.add(holidays);
                }
            }

            filterResults.count = foundFilter.size();
            filterResults.values = foundFilter;
        } else {
            filterResults.count = currentList.size();
            filterResults.values = currentList;
        }

        return filterResults;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        adapter.setRecetasForo((ArrayList<Foro>) results.values);
        adapter.notifyDataSetChanged();
    }
}
