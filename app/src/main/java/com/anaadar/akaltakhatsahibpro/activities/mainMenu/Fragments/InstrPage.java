package com.anaadar.akaltakhatsahibpro.activities.mainMenu.Fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.anaadar.akaltakhatsahibpro.R;


/**
 * Created by Sumit Singh on 3/12/2018.
 */

public class InstrPage extends Fragment {
    TextView instruction, instructionNote, sgpcMessage;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pageinstr, container, false);
        //initInstruction(view);
        return view;
    }

 /*   private void initInstruction(View view) {
        instruction = (TextView) view.findViewById(R.id.instruction);
        instructionNote = (TextView) view.findViewById(R.id.instructionnote);
        sgpcMessage = (TextView) view.findViewById(R.id.sgpc_messagetv);
        Typeface custom_font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/amrlipi_.ttf");
        sgpcMessage.setTypeface(custom_font);
        instruction.setTypeface(custom_font);
        instructionNote.setTypeface(custom_font);
        instruction.setText(getResources().getString(R.string.instruction));
        instructionNote.setText(getResources().getString(R.string.instructionnote));
    }*/

}
