package com.example.desarrollo.ExportJSON.RecycrerView;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.desarrollo.Datos.Calculos;
import com.example.desarrollo.Datos.MotivadoresDao;
import com.example.desarrollo.Datos.NinoDao;
import com.example.desarrollo.Entidades.MotivadoresSelect;
import com.example.desarrollo.ExportJSON.Filter.FilteredHelperUltraprocesados;
import com.example.desarrollo.Entidades.UltraProcesados;
import com.example.desarrollo.LogicaNegocio.Adapter.RecyclerViewMotivadoresSelectNino;
import com.example.desarrollo.R;
import com.example.desarrollo.Ultilidades.Toastp;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapterUltraprocesados extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Filterable {

    private Context context;
    private String alimento;
    private List<UltraProcesados> listUltraprocesados;
    public ArrayList<UltraProcesados> currentList;
    private FilteredHelperUltraprocesados filterHelper;
    private Toastp toast;
    private Calculos calculos;
    private Dialog dialogUltraprocesados, ninoDialog;

    //todo: Seleccionar nino para registrar el consumo
    private MotivadoresDao motivadoresDao;
    private NinoDao ninoDao;
    private TextView txtMensajeDialogNino;
    private int idNino;
    private RecyclerView _myRecyclerViewNino;
    private RecyclerViewMotivadoresSelectNino adapterSelectNino;
    private String horaRegistro, fechaRegistro;
    private boolean spinnerActive = false;

    private ArrayList<MotivadoresSelect.MotivadoresNinoDisponible> listNinoDisponible;

    private static final String TAG = "RecyclerViewAdapterUltr";

    public RecyclerViewAdapterUltraprocesados(Context context, ArrayList<UltraProcesados> listUltraprocesados, String alimento) {
        this.context = context;
        this.listUltraprocesados = listUltraprocesados;
        this.currentList = listUltraprocesados;
        this.alimento = alimento;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView nombreBebida;
        TextView caloriasBebida;
        TextView txtContenidoUltrapItem;
        CardView view_container;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            view_container = (CardView) itemView.findViewById(R.id.containerUltraprocesados);
            nombreBebida = (TextView) itemView.findViewById(R.id.nombreUltraprocesados);
            caloriasBebida = (TextView) itemView.findViewById(R.id.caloriasUltraprocesados);
            txtContenidoUltrapItem = (TextView) itemView.findViewById(R.id.txtContenidoUltrapItem);

        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
        final View view;

        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ultraprocesados_items, parent, false);

        final ItemViewHolder viewHolder = new ItemViewHolder(view);

        viewHolder.view_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialogUltraprocesados = new Dialog(parent.getContext());
                dialogUltraprocesados.setContentView(R.layout.ultraprocesados_dialog);
                dialogUltraprocesados.setCancelable(false);
                dialogUltraprocesados.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialogUltraprocesados.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                TextView txtNombreAlimentoUltraProcesado = (TextView) dialogUltraprocesados.findViewById(R.id.txtNombreAlimentoUltraProcesado);
                TextView txtPorcionUltrap = (TextView) dialogUltraprocesados.findViewById(R.id.txtPorcionUltrap);
                final TextView txtContenidoUltrap = (TextView) dialogUltraprocesados.findViewById(R.id.txtContenidoUltrap);
                final TextView txtKcaloriasUltrap = (TextView) dialogUltraprocesados.findViewById(R.id.txtKcaloriasUltrap);
                final TextView txtUnidadMedida = (TextView) dialogUltraprocesados.findViewById(R.id.txtUnidadMedida);
                final EditText txtCantidadConsumoUltrap = (EditText) dialogUltraprocesados.findViewById(R.id.txtCantidadConsumoUltrap);
                final Spinner spinnerUnidadMedida = (Spinner) dialogUltraprocesados.findViewById(R.id.spinnerUnidadMedida);
                Button btnRegistrarUltrap = (Button) dialogUltraprocesados.findViewById(R.id.btnRegistrarUltrap);
                Button btnCancelarUltrap = (Button) dialogUltraprocesados.findViewById(R.id.btnCancelarUltrap);

                txtNombreAlimentoUltraProcesado.setText(listUltraprocesados.get(viewHolder.getAdapterPosition()).getNombre());
                txtPorcionUltrap.setText(String.valueOf(listUltraprocesados.get(viewHolder.getAdapterPosition()).getPorcion()));
                txtContenidoUltrap.setText(listUltraprocesados.get(viewHolder.getAdapterPosition()).getContenido());
                txtKcaloriasUltrap.setText(String.valueOf(listUltraprocesados.get(viewHolder.getAdapterPosition()).getKcalorias()));

                if (alimento.equals("Bebidas")) {
                    spinnerUnidadMedida.setVisibility(View.GONE);
                    txtUnidadMedida.setText("Pieza");
                    txtUnidadMedida.setVisibility(View.VISIBLE);
                    spinnerActive = false;
                } else if (alimento.equals("Frituras")) {
                    spinnerUnidadMedida.setVisibility(View.GONE);
                    txtUnidadMedida.setText("Paquete");
                    txtUnidadMedida.setVisibility(View.VISIBLE);
                    spinnerActive = false;
                } else if (alimento.equals("Galletas y panesillos")) {
                    spinnerUnidadMedida.setVisibility(View.VISIBLE);
                    txtUnidadMedida.setVisibility(View.GONE);
                    spinnerActive = true;
                } else if (alimento.equals("Golosinas")) {
                    spinnerUnidadMedida.setVisibility(View.GONE);
                    txtUnidadMedida.setVisibility(View.VISIBLE);

                    String nombreGolosina = txtNombreAlimentoUltraProcesado.getText().toString();
                    if (nombreGolosina.equals("Bubulubu") || nombreGolosina.equals("Paleta payaso") || nombreGolosina.equals("Pelón pelo rico") || nombreGolosina.equals("Pulparindo") || nombreGolosina.equals("Mazapan") || nombreGolosina.equals("Malvabon") || nombreGolosina.equals("Carlos V")) {
                        txtUnidadMedida.setText("Pieza");
                    } else {
                        txtUnidadMedida.setText("Bolsa");
                    }
                    spinnerActive = false;
                }

                btnCancelarUltrap.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogUltraprocesados.dismiss();
                    }
                });

                btnRegistrarUltrap.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        double equivalencia = 0;
                        int idAlimentoUltrap = listUltraprocesados.get(viewHolder.getAdapterPosition()).getIdAlimentoUltrap();
                        String cantidad = txtCantidadConsumoUltrap.getText().toString();
                        double kcalorias = Double.valueOf(txtKcaloriasUltrap.getText().toString());
                        if (cantidad.equals("")) {
                            toast.toastp(parent.getContext(), "Ingrese la cantidad");
                        } else {
                            if (spinnerActive == false) {
                                if (txtUnidadMedida.getText().toString().equals("Pieza")) {
                                    equivalencia = calculos.obtenerEquivalenciaPieza(Double.valueOf(cantidad), kcalorias);
                                } else if (txtUnidadMedida.getText().toString().equals("Bolsa")) {
                                    String contenido = txtContenidoUltrap.getText().toString();
                                    String nuevoContendio = contenido.replaceAll("[a-zA-Z ]", "");

                                    equivalencia = calculos.obtenerEquivalenciaPaquete(Double.valueOf(nuevoContendio), kcalorias);
                                }
                            }
                            if (spinnerActive == true) {
                                if (spinnerUnidadMedida.getSelectedItem().toString().equals("Pieza")) {
                                    equivalencia = calculos.obtenerEquivalenciaPieza(Double.valueOf(cantidad), kcalorias);
                                } else if (spinnerUnidadMedida.getSelectedItem().toString().equals("Paquete")) {
                                    String contenido = txtContenidoUltrap.getText().toString();
                                    String nuevoContendio = contenido.replaceAll("[a-zA-Z ]", "");

                                    equivalencia = calculos.obtenerEquivalenciaPaquete(Double.valueOf(nuevoContendio), kcalorias);
                                }
                            }

                            getHoraFecha();
                            obtenerIdNino(parent.getContext(), idAlimentoUltrap, kcalorias, cantidad, equivalencia);
                            dialogUltraprocesados.dismiss();
                        }
                    }
                });
                dialogUltraprocesados.show();

            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        UltraProcesados readerUltraProcesados = (UltraProcesados) this.listUltraprocesados.get(position);

        itemViewHolder.nombreBebida.setText(readerUltraProcesados.getNombre());
        itemViewHolder.caloriasBebida.setText(readerUltraProcesados.getKcalorias() + " kcal");
        itemViewHolder.txtContenidoUltrapItem.setText(readerUltraProcesados.getContenido());

    }

    @Override
    public int getItemCount() {
        return listUltraprocesados.size();
    }


    public void setListUltraprocesados(ArrayList<UltraProcesados> filteredSpacecrafts) {
        this.listUltraprocesados = filteredSpacecrafts;

    }

    @Override
    public Filter getFilter() {
        if (filterHelper == null) {
            filterHelper = new FilteredHelperUltraprocesados(currentList, this, context);
        }

        return filterHelper;
    }

    private boolean obtenerIdNino(final Context context, final int idAlimentoUltrap, final double kcalorias, final String cantidad, final double equivalencia) {
        try {
            listNinoDisponible = new ArrayList<>();
            motivadoresDao.consultarNino(TAG, context, listNinoDisponible);
            int cantidadNino = ninoDao.countNino(TAG, context);

            if (cantidadNino > 1) {
                ninoDialog = new Dialog(context);
                ninoDialog.setContentView(R.layout.motivadores_select_nino);
                ninoDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                ninoDialog.setCanceledOnTouchOutside(false);

                _myRecyclerViewNino = (RecyclerView) ninoDialog.findViewById(R.id.myRecyclerViewMotivadoresSelectNino);
                txtMensajeDialogNino = (TextView) ninoDialog.findViewById(R.id.txtMensajeComprobacion);
                txtMensajeDialogNino.setText("Seleccione al niño que le registrara el alimento");

                _myRecyclerViewNino.setLayoutManager(new LinearLayoutManager(context));
                adapterSelectNino = new RecyclerViewMotivadoresSelectNino(context, listNinoDisponible);
                _myRecyclerViewNino.setAdapter(adapterSelectNino);

                final TextView cancelar = (TextView) ninoDialog.findViewById(R.id.btnCancelarSelectNinoMotivador);
                adapterSelectNino.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        idNino = listNinoDisponible.get(_myRecyclerViewNino.getChildAdapterPosition(v)).getIdNino();
                        registrarConsumo(context, idAlimentoUltrap, kcalorias, cantidad, equivalencia);
                        ninoDialog.dismiss();
                    }
                });

                cancelar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ninoDialog.dismiss();
                    }
                });
                ninoDialog.show();
            } else {
                idNino = listNinoDisponible.get(0).getIdNino();
                registrarConsumo(context, idAlimentoUltrap, kcalorias, cantidad, equivalencia);
            }

            return true;

        } catch (Exception e) {
            return false;
        }
    }

    private void registrarConsumo(Context context, int idAlimentoUltrap, double kcalorias, String cantidad, double equivalencia) {
        calculos.registrarDetalleReg(
                TAG,
                context,
                idNino,
                idAlimentoUltrap,
                kcalorias,
                Double.valueOf(cantidad),
                equivalencia,
                horaRegistro,
                fechaRegistro,
                "ULtraProcesado",
                1);
    }

    private void getHoraFecha() {
        calculos = new Calculos();
        horaRegistro = calculos.getHora();
        fechaRegistro = calculos.getFecha();
    }
}
