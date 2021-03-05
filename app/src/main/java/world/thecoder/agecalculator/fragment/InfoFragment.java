package world.thecoder.agecalculator.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import world.thecoder.agecalculator.R;
import world.thecoder.agecalculator.models.Event;
import world.thecoder.agecalculator.util.EventAdapter;

public class InfoFragment extends Fragment {

    TextView textView;
    String data, day, month;
    View root;

    ListView listView;

    ArrayList<Event> textsList = new ArrayList<Event>();

    EventAdapter adapter;

    public  InfoFragment (){
        super(R.layout.info_fragment);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.info_fragment,container,false);

        textView = root.findViewById(R.id.textView_fragment_info);

        listView = root.findViewById(R.id.list_info_fragment);

        data = requireArguments().getString("test");
        month = String.valueOf( requireArguments().getInt("month"));
        day = String.valueOf(requireArguments().getInt("day"));

        String url = "https://history.muffinlabs.com/date/" + month + "/" + day;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            String theSearchedDate = response.getString("date");
                            JSONObject jsonObjectGeneral = response.getJSONObject("data");
                            JSONArray resultArray = jsonObjectGeneral.getJSONArray("Events");

                            textView.setText(theSearchedDate);

                            for (int i = 0; i < resultArray.length(); i++)
                            {
                                textsList.add(new Event(
                                        resultArray.getJSONObject(i).getString("year"),
                                        resultArray.getJSONObject(i).getString("text")
                                        )
                                );
                            }

                            adapter = new EventAdapter(getContext(),textsList);
                            listView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        textView.setText( "some thing went wrong" + error.getMessage());

                    }
                }
        );

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(jsonObjectRequest);



        return root;
    }
}