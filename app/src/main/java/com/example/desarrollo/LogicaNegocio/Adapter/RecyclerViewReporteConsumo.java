package com.example.desarrollo.LogicaNegocio.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.desarrollo.Entidades.ReporteConsumo;
import com.example.desarrollo.R;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class RecyclerViewReporteConsumo extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ReporteConsumo> reporteConsumoList;
    private String alimento;

    public RecyclerViewReporteConsumo(List<ReporteConsumo> reporteConsumoList, String alimento) {
        this.reporteConsumoList = reporteConsumoList;
        this.alimento = alimento;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView txtNombreAlimento;
        private TextView txtCantidad;
        private TextView txtFecha;
        private TextView txtHoraConsumo;
        private TextView txtPorciones;
        private TextView txtUnidadMedidaReporteConsumo;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            txtNombreAlimento = (TextView) itemView.findViewById(R.id.txtNombreAlimentoReporteConsumo);
            txtCantidad = (TextView) itemView.findViewById(R.id.txtCantidadReporteConsumo);
            txtFecha = (TextView) itemView.findViewById(R.id.txtFechaReporteConsumo);
            txtHoraConsumo = (TextView) itemView.findViewById(R.id.txtHoraConsumoReporte);
            txtPorciones = (TextView) itemView.findViewById(R.id.txtPorcionReporteConsumo);
            txtUnidadMedidaReporteConsumo = (TextView) itemView.findViewById(R.id.txtUnidadMedidaReporteConsumo);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.reporte_consumo_items, parent, false);
        ItemViewHolder viewHolder = new ItemViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ItemViewHolder viewHolder = (ItemViewHolder) holder;
        ReporteConsumo reporteConsumo = this.reporteConsumoList.get(position);

        viewHolder.txtNombreAlimento.setText(reporteConsumo.getNombreAlimento());
        viewHolder.txtCantidad.setText(String.valueOf(reporteConsumo.getCantidad()));
        viewHolder.txtHoraConsumo.setText(reporteConsumo.getHoraConsumo());
        viewHolder.txtPorciones.setText(String.valueOf(reporteConsumo.getEquivalencia()));

        if (alimento.equals("FrutasVerduras"))
            viewHolder.txtUnidadMedidaReporteConsumo.setText("prc");
        else
            viewHolder.txtUnidadMedidaReporteConsumo.setText("kcal");

        String fecha = reporteConsumo.getFechaConsumo();
        String[] partsFecha = fecha.split("-");
        String anio = partsFecha[0];
        String mes = partsFecha[1];
        String dia = partsFecha[2];

        String diaSemana = diaSemana(Integer.valueOf(mes), Integer.valueOf(dia), Integer.valueOf(anio));

        viewHolder.txtFecha.setText(diaSemana + " " + dia);

    }

    @Override
    public int getItemCount() {
        return reporteConsumoList.size();
    }

    private static String diaSemana(int mes, int dias, int anio) {
        String dia = "";
        int numD;
        Calendar calendar = Calendar.getInstance(Locale.US);
        calendar.set(anio, mes - 1, dias);
        numD = calendar.get(Calendar.DAY_OF_WEEK);
        if (numD == Calendar.SUNDAY)
            dia = "Domingo";
        else if (numD == Calendar.MONDAY)
            dia = "Lunes";
        else if (numD == Calendar.TUESDAY)
            dia = "Martes";
        else if (numD == Calendar.WEDNESDAY)
            dia = "Miercoles";
        else if (numD == Calendar.THURSDAY)
            dia = "Jeves";
        else if (numD == Calendar.FRIDAY)
            dia = "Viernes";
        else if (numD == Calendar.SATURDAY)
            dia = "Sabado";

        return dia;
    }
}
