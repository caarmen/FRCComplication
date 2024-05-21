package ca.rmen.android.frc.complications;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.preference.ListPreference;
import android.util.AttributeSet;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class CustomListPreference extends ListPreference {
    public CustomListPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public CustomListPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CustomListPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomListPreference(Context context) {
        super(context);
    }

    @Override
    protected void onPrepareDialogBuilder(AlertDialog.Builder builder) {
    }

    @Override
    protected void showDialog(Bundle state) {
        ListPreferenceDialogFragment fragment = ListPreferenceDialogFragment.create(
                getTitle(),
                getEntries(),
                getEntry()
        );
        FragmentManager fragmentManager = ((FragmentActivity) getContext()).getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(fragment, "dialog");
        transaction.runOnCommit(() -> {
            fragment.setOnEntryClickedListener(position -> {
                setValueIndex(position);
                fragment.dismiss();
            });
        });
        transaction.commit();
    }


}
