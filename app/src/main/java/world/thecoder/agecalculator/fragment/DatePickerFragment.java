package world.thecoder.agecalculator.fragment;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import world.thecoder.agecalculator.R;


public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private TextView textView;
    //private Context context;
    private int currentYear, currentMonth, currentDay;
    private Long today,selectedDated;

    public DatePickerFragment(Context context,TextView textView ){
        Activity act =(Activity) context;
        this.textView = (TextView) act.findViewById(R.id.textView_Picked_Birth_date);
        //this.context = context;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        final Calendar c = Calendar.getInstance();
        currentYear = c.get(Calendar.YEAR);
        currentMonth = c.get(Calendar.MONTH);
        currentDay = c.get(Calendar.DAY_OF_MONTH);

        today  = c.getTimeInMillis();

        return new DatePickerDialog(getActivity(),this,currentYear,currentMonth,currentDay);
    }



    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        int ageDays = 0, ageMonth = 0, ageYear = 0;
        month = month +1;
        currentMonth = currentMonth +1;

        if (year > currentYear){
            Toast.makeText(getActivity(), "Invalid Year selected", Toast.LENGTH_SHORT).show();
        }else{

            if (currentDay < dayOfMonth){
                currentMonth = currentMonth - 1;
                currentDay = currentDay + 30;
            }
            ageDays = currentDay - dayOfMonth;

            if (currentMonth < month){
                currentYear = currentYear - 1;
                currentMonth = currentMonth + 12;
            }
            ageMonth = currentMonth - month;

            if (currentYear >= year){
                ageYear = currentYear - year;

                String ageMessage =
                        getResources().getString(R.string.ageMessageyouare) + " " + ageYear +  " " +
                                getResources().getString(R.string.ageMessageYears) + " " + ageMonth +  " " +
                                getResources().getString(R.string.ageMessageMonths) + " " + ageDays +  " " +
                                getResources().getString(R.string.ageMessageDays)
                        ;

                textView.setText(ageMessage);
                String dateToPass = ageMessage;

                Bundle bundle = new Bundle();
                bundle.putString("test" ,dateToPass);
                bundle.putInt("month" ,month);
                bundle.putInt("day" ,dayOfMonth);

                getActivity().getSupportFragmentManager().beginTransaction()
                        .setReorderingAllowed(true)
                        .add(R.id.fragment_info, InfoFragment.class,bundle)
                        .commit();


            }else{
                Toast.makeText(getActivity(), "something wrong in the selected Data", Toast.LENGTH_LONG).show();
            }


        }






    }
}
