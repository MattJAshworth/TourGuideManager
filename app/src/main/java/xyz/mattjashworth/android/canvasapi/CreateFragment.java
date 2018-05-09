package xyz.mattjashworth.android.canvasapi;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;


/**
 * A simple {@link Fragment} subclass.
 */
public class CreateFragment extends Fragment {

    View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView =  inflater.inflate(R.layout.fragment_create, container, false);

        final EditText newUser = (EditText) rootView.findViewById(R.id.et_NewUser);
        final EditText newPass = (EditText) rootView.findViewById(R.id.et_NewPass);
        final Switch newOrganiser = (Switch) rootView.findViewById(R.id.switch_Organiser);
        Button createNewUser = (Button) rootView.findViewById(R.id.btn_CreateAccount);
        Spinner selectUser = (Spinner) rootView.findViewById(R.id.spinner_SelectUser);
        

        final String currentUser;

        currentUser = ParseUser.getCurrentUser().getSessionToken();

        createNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strNewUser = newUser.getText().toString();
                String strNewPass = newPass.getText().toString();
                Boolean booNewOrg = newOrganiser.isChecked();

                ParseUser parseUser = new ParseUser();
                parseUser.setUsername(strNewUser);
                parseUser.setPassword(strNewPass);
                parseUser.put("Organiser", booNewOrg);
                parseUser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e != null) {
                            //Success
                            try {
                                ParseUser.become(currentUser);
                                ShowSuccessDialog();
                            } catch (ParseException e1) {
                                e1.printStackTrace();
                            }
                        } else {
                            //Failed
                        }

                    }
                });
            }
        });


        return rootView;
    }

    private void ShowSuccessDialog() {

    }

}
