package world.thecoder.agecalculator.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import java.io.FileOutputStream;

import world.thecoder.agecalculator.R;

public class InputFragment extends Fragment {

    TextView date;
    public InputFragment(){
        super(R.layout.input_fragment);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

//    public void showDatePickerDialog(View view) {
//        DialogFragment newFragment = new DatePickerFragment(getActivity(),date);
//        newFragment.show(getActivity().getSupportFragmentManager(),"datePicker");
//    }
}
