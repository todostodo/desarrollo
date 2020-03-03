package com.example.desarrollo.LogicaNegocio.Adapter;


import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.desarrollo.Datos.TutorDao;
import com.example.desarrollo.Entidades.Tutor;
import com.example.desarrollo.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.List;

public class RecyclerViewTutor extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    List<Tutor> tutorList;
    Dialog dialogOptions;
    TutorDao tutorDao;


    public RecyclerViewTutor(Context context, List<Tutor> tutorList) {
        this.context = context;
        this.tutorList = tutorList;
    }

    public  class ItemViewHolder extends RecyclerView.ViewHolder{

        TextView nombre;
        TextView parentesco;
        LinearLayout _btnTutorSelect;
        LinearLayout layoutDialogOptios;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            nombre = (TextView) itemView.findViewById(R.id.txtTutorItemNombre);
            parentesco = (TextView) itemView.findViewById(R.id.txtTutorItemParentesco);

            layoutDialogOptios = (LinearLayout) itemView.findViewById(R.id.tutorDialogOptions);

            _btnTutorSelect = (LinearLayout) itemView.findViewById(R.id.btnTutorSelect);

        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(context).inflate(R.layout.tutor_items, parent, false);
        final ItemViewHolder viewHolder = new ItemViewHolder(view);

        //Dialog

        viewHolder._btnTutorSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialogOptions = new BottomSheetDialog(context);
                dialogOptions.setContentView(R.layout.tutor_dialog_options);
                dialogOptions.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                final TextView _txtPasswordTutor = (TextView) dialogOptions.findViewById(R.id.txtPasswordTutorDialog);
                _txtPasswordTutor.setKeyListener(null);
                final TextView _textContra = (TextView) dialogOptions.findViewById(R.id.textContraDialog);
                final RelativeLayout _layoutContra = (RelativeLayout) dialogOptions.findViewById(R.id.layourPasswordTutor);
                final TextView _txtReContra = (TextView) dialogOptions.findViewById(R.id.txtRePasswordTutorDialog);
                final TextView _txtNombre = (TextView) dialogOptions.findViewById(R.id.txtTutorNombreDialog);
                final LinearLayout layoutEditarTutor = (LinearLayout) dialogOptions.findViewById(R.id.layoutEditarTutor);
                final ImageButton _btnEditarTutor = (ImageButton) dialogOptions.findViewById(R.id.btnEditarTutorDialog);
                final ImageButton _btnEliminarTutorDialog = (ImageButton) dialogOptions.findViewById(R.id.btnEliminarTutorDialog);
                final Button _btnGuardarTutor = (Button) dialogOptions.findViewById(R.id.btnGuardarTutor);


                /*
                MOSTRAR DATOS
                 */
                final String nombre, apellidoP, apellidoM, contra;
                nombre = tutorList.get(viewHolder.getAdapterPosition()).getNombreTutor();
                apellidoP = tutorList.get(viewHolder.getAdapterPosition()).getApellidoPTutor();
                apellidoM = tutorList.get(viewHolder.getAdapterPosition()).getApellidoMTutor();
                _txtNombre.setText(nombre + " " + apellidoP + " " + apellidoM);
                _txtPasswordTutor.setText(String.valueOf(tutorList.get(viewHolder.getAdapterPosition()).getContraTutor()));
                //----------------------
                _btnEditarTutor.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        layoutEditarTutor.setVisibility(View.VISIBLE);
                        _txtPasswordTutor.setKeyListener(new EditText(context).getKeyListener());
                        _txtPasswordTutor.setText("");
                        _textContra.setText("Nueva contraseña");
                    }
                });
                _btnGuardarTutor.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //Validación
                        String password, rePassword;
                        password = _txtPasswordTutor.getText().toString();
                        rePassword = _txtReContra.getText().toString();
                        if (password.equals("")){
                            _layoutContra.setBackgroundResource(R.drawable.rectangulo_border_rojo);
                            Toast.makeText(context, "Ingrese la nueva contraseña", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            _layoutContra.setBackgroundResource(R.drawable.rectangulo_gris);
                            if (rePassword.equals("")){
                                _txtReContra.setBackgroundResource(R.drawable.rectangulo_border_rojo);
                                Toast.makeText(context, "Confirme la nueva contraseña", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                _txtReContra.setBackgroundResource(R.drawable.rectangulo_gris);

                                if (password.equals(rePassword)){
                                    _textContra.setText("Contraseña");
                                    _txtReContra.setText("");
                                    layoutEditarTutor.setVisibility(View.GONE);
                                    _txtPasswordTutor.setKeyListener(null);

                                    boolean editar = tutorDao.editarTutor
                                            (
                                                    context,
                                                    tutorList.get(viewHolder.getAdapterPosition()).getIdTutor(),
                                                    Integer.parseInt(password)
                                            );

                                    if (editar == true){
                                        Toast.makeText(context, "Tutor editado", Toast.LENGTH_SHORT).show();
                                    }
                                    else{
                                        Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                                    }
                                }
                                else {
                                    Toast.makeText(context, "La contraseña no coincide", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                        //Editar
                    }
                });

                _btnEliminarTutorDialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        boolean eliminar = tutorDao.eliminarTutor
                                (
                                        context,
                                        tutorList.get(viewHolder.getAdapterPosition()).getIdTutor()
                                );

                        if (eliminar == true){
                            String nombre = tutorList.get(viewHolder.getAdapterPosition()).getNombreTutor();
                            Toast.makeText(context, "Tutor " + nombre + " ,Eliminado", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                dialogOptions.show();
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ItemViewHolder viewHolder = (ItemViewHolder) holder;
        Tutor tutor = this.tutorList.get(position);

        viewHolder.nombre.setText(tutor.getNombreTutor());
        viewHolder.parentesco.setText(tutor.getParentesto());


    }

    @Override
    public int getItemCount() {
        return tutorList.size();
    }
}
