package manoj.com.networkpicassorecyclerview.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.widget.TextView;

import manoj.com.networkpicassorecyclerview.R;

/**
 * Created by Manoj.Kumar04 on 7/29/2018.
 */

public class Utility {
    /**
     * Validation dialog.
     *
     * @param ctxt      the ctxt
     * @param titleText the title text
     * @param bodyText  the body text
     */
    public static void validationDialog(Context ctxt, String titleText, String bodyText) {

        // 1. Instantiate an AlertDialog.Builder with its constructor
        AlertDialog.Builder builder = new AlertDialog.Builder(ctxt, android.app.AlertDialog.THEME_HOLO_LIGHT);

        // 2. Chain together various setter methods to set the dialog characteristics
        builder.setMessage(bodyText)
                .setTitle(titleText);

        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
            }
        });

        // 3. Get the AlertDialog from create()
        AlertDialog dialog = builder.create();
        dialog.show();
        TextView textView = dialog.findViewById(android.R.id.message);
        textView.setTextSize(14);
    }
}
