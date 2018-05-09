package xyz.mattjashworth.android.canvasapi;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.content.ContentValues.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class SendMessagesFragment extends Fragment {


    View rootView;

    List<String> listItems=new ArrayList<String>();
    List<String> listUsers=new ArrayList<String>();
    ArrayAdapter<String> adapter;
    ListView messageList;
    String [] strListUsers;
    String selectedUser;
    ArrayList<String> strListArrayUsers;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_send_messages, container, false);

        messageList = (ListView) rootView.findViewById(R.id.list_MessagesOrganiser);
        recieveMessageFromParse();


        Button sendMessageButton = (Button) rootView.findViewById(R.id.btn_sendMessageOrganiser);
        sendMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectRecipient();
            }
        });

        return rootView;
    }


    private void recieveMessageFromParse() {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Messages");
        // Configure limit and sort order
        query.setLimit(10);

        // get the latest 10 messages, order will show up newest to oldest of this group
        query.orderByDescending("createdAt");
        // Execute query to fetch all messages from Parse asynchronously
        // This is equivalent to a SELECT query with SQL
        query.whereContains("Recipient", ParseUser.getCurrentUser().getUsername());
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> messages, ParseException e) {
                if (e == null) {
                    for (int i = 0; i < messages.size(); i++) {
                        //listItems.add(i, messages.get(i).toString());
                        Log.e("WOW", messages.toString());
                        String sender = messages.get(i).getString("User");
                        String message = messages.get(i).getString("Message");
                        String response = "From: " + sender + "\n" + message;
                        listItems.add(i, response);
                    }
                }
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                        getContext(),
                        android.R.layout.simple_list_item_1,
                        listItems);

                messageList.setAdapter(arrayAdapter);


            }
        });
    }

    private void selectRecipient() {

        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> userObjects, ParseException e) {
                if (userObjects != null) {
                    for (int i = 0; i < userObjects.size(); i++) {
                        listUsers.add(userObjects.get(i).getUsername());
                        /*strListUsers[i] = userObjects.get(i).getUsername();
                        strListArrayUsers.add(userObjects.get(i).getUsername());*/
                    }
                }
            }
        });

        AlertDialog.Builder builderSingle = new AlertDialog.Builder(getContext());
        builderSingle.setTitle("Select Recipient");

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.select_dialog_singlechoice);
        for (int i = 0; i < listUsers.size(); i ++) {
            arrayAdapter.add(listUsers.get(i));
        }

        builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                selectedUser = arrayAdapter.getItem(which);
                sendMessageDialog();
            }
        });

        builderSingle.show();
    }

    private void sendMessageDialog() {
        LayoutInflater layoutInflater = getLayoutInflater();
        final View messageView = layoutInflater.inflate(R.layout.sendmessage_layout, null);
        AlertDialog messageDialog = new AlertDialog.Builder(getContext()).create();
        messageDialog.setTitle("Send a message");

        final EditText message = (EditText) messageView.findViewById(R.id.et_Message);

        messageDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Send", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //Send message
                sendMessageToParse(message.getText().toString());
                Toast.makeText(getContext(), getString(R.string.send_message_response_good), Toast.LENGTH_LONG).show();
            }
        });

        messageDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //Don't send message
            }
        });

        messageDialog.setView(messageView);
        messageDialog.show();
    }

    private void sendMessageToParse(String strMessage) {

        ParseObject message = ParseObject.create("Messages");
        message.put("User", ParseUser.getCurrentUser().getUsername());
        message.put("Message", strMessage);
        message.put("Recipient", selectedUser);
        message.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e == null) {
                    Toast.makeText(getContext(), "Successfully created message on Parse",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Log.e(TAG, "Failed to save message", e);
                }
            }
        });
    }

}
