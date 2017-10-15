package com.xli.calfootballticketcalc;

import android.annotation.TargetApi;
import android.icu.text.DecimalFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * A placeholder fragment containing a simple view.
 */
public class CalFootballTicketCalcFragment extends Fragment {
    double costPerTicket = 75.00;
    int numberOfTickets;
    double totalCost;
    String gameChoice;
    EditText tickets;
    Spinner game;
    TextView result;
    Button cost;

    @Override
    public  void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        tickets = (EditText)getActivity().findViewById(R.id.cal_tickets_number);
        game = (Spinner)getActivity().findViewById(R.id.cal_tickets_game);
        result = ((TextView) getActivity().findViewById(R.id.cal_tickets_result));
        cost = (Button)getActivity().findViewById(R.id.cal_tickets_cost);
        cost.setOnClickListener(new View.OnClickListener(){
            @Override
                public void onClick(View v){
                    getTicketCost();
            }
        });
    }
    @TargetApi(Build.VERSION_CODES.N)
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void getTicketCost(){
        gameChoice = game.getSelectedItem().toString();
        String ticketsNo = tickets.getText().toString();
        if(!TextUtils.isEmpty(ticketsNo)) {
            numberOfTickets = Integer.parseInt(ticketsNo);
            totalCost = costPerTicket * numberOfTickets;
            DecimalFormat currency = new DecimalFormat("$###,###.##");

            String message = getResources().getString(R.string.total_cost) + " " + gameChoice + " "
                    + getResources().getString(R.string.is) + " " + currency.format(totalCost);
            result.setText(message);
        }else {
            result.setText(getResources().getString(R.string.need_number));
        }
    }

    public CalFootballTicketCalcFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cal_football_ticket_calc, container, false);
    }
}
