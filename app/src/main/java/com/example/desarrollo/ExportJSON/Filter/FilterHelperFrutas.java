package com.example.desarrollo.ExportJSON.Filter;

import android.content.Context;
import android.widget.Filter;

import com.example.desarrollo.Entidades.Frutas;
import com.example.desarrollo.ExportJSON.RecycrerView.RecyclerViewAdapterFrutas;

import java.util.ArrayList;
import java.util.List;

public class FilterHelperFrutas extends Filter {
    List<Frutas> currentList;
    RecyclerViewAdapterFrutas adapter;
    Context context;

    public FilterHelperFrutas(List<Frutas> currentList, RecyclerViewAdapterFrutas adapter, Context context) {
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
            List<Frutas> foundFilters = new ArrayList<>();

            Frutas holidays = null;

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

        adapter.setFrutas((ArrayList<Frutas>) results.values);
        adapter.notifyDataSetChanged();
    }
}
