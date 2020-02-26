package com.example.desarrollo.ExportJSON.Filter;

import android.content.Context;
import android.widget.Filter;

import com.example.desarrollo.ExportJSON.Reader.ReaderFrutas;
import com.example.desarrollo.ExportJSON.RecycrerView.RecyclerViewAdapterFrutas;

import java.util.ArrayList;
import java.util.List;

public class FilterHelperFrutas extends Filter {
    List<ReaderFrutas> currentList;
    RecyclerViewAdapterFrutas adapter;
    Context context;

    public FilterHelperFrutas(List<ReaderFrutas> currentList, RecyclerViewAdapterFrutas adapter, Context context) {
        this.currentList = currentList;
        this.adapter = adapter;
        this.context = context;
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {

        FilterResults filterResults=new FilterResults();

        if(constraint != null && constraint.length()>0)
        {
            //CHANGE TO UPPER
            constraint = constraint.toString().toUpperCase();

            //HOLD FILTERS WE FIND
            List<ReaderFrutas> foundFilters = new ArrayList<>();

            ReaderFrutas holidays = null;

            //ITERATE CURRENT LIST
            for (int i = 0;i < currentList.size(); i++)
            {
                holidays = currentList.get(i);

                //SEARCH
                if(holidays.getNombre().toUpperCase().contains(constraint) )
                {
                    //ADD IF FOUND
                    foundFilters.add(holidays);
                }
            }

            //SET RESULTS TO FILTER LIST
            filterResults.count = foundFilters.size();
            filterResults.values = foundFilters;
        }else
        {
            //NO ITEM FOUND.LIST REMAINS INTACT
            filterResults.count = currentList.size();
            filterResults.values = currentList;
        }

        //RETURN RESULTS
        return filterResults;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {

        adapter.setReaderFrutas((ArrayList<ReaderFrutas>) results.values);
        adapter.notifyDataSetChanged();
    }
}
