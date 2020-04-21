package com.example.desarrollo.LogicaNegocio.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.desarrollo.Entidades.IntroduccionScreenItem;
import com.example.desarrollo.R;

import java.util.List;

public class IntroduccionViewPagerAdapter extends PagerAdapter {

    private Context context;
    private List<IntroduccionScreenItem> listScreemItem;

    public IntroduccionViewPagerAdapter(Context context, List<IntroduccionScreenItem> listScreemItem) {
        this.context = context;
        this.listScreemItem = listScreemItem;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layoutScreem = inflater.inflate(R.layout.introduccion_screen_activity, null);

        ImageView imgSlide = layoutScreem.findViewById(R.id.intro_img);
        TextView titulo = layoutScreem.findViewById(R.id.intro_title);
        TextView descripcion = layoutScreem.findViewById(R.id.intro_description);

        titulo.setText(listScreemItem.get(position).getTitulo());
        descripcion.setText(listScreemItem.get(position).getDescripcion());
        imgSlide.setImageResource(listScreemItem.get(position).getScreemImg());

        container.addView(layoutScreem);

        return layoutScreem;
    }

    @Override
    public int getCount() {
        return listScreemItem.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
