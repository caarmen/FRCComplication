package ca.rmen.android.frc.complications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.ConcatAdapter;

public class ListPreferenceDialogFragment extends DialogFragment {

    private static final String TITLE = "title";
    private static final String ENTRIES = "entries";
    private static final String INITIAL_SELECTED_ENTRY = "initial_selected_entry";

    interface OnEntryClickedListener {
        void onEntryClicked(int position);
    }

    static ListPreferenceDialogFragment create(
            CharSequence title,
            CharSequence[] entries,
            CharSequence initialSelectedEntry
    ) {
        ListPreferenceDialogFragment fragment = new ListPreferenceDialogFragment();
        Bundle args = new Bundle();
        args.putCharSequence(TITLE, title);
        args.putCharSequenceArray(ENTRIES, entries);
        args.putCharSequence(INITIAL_SELECTED_ENTRY, initialSelectedEntry);
        fragment.setArguments(args);
        return fragment;
    }

    void setOnEntryClickedListener(OnEntryClickedListener listener) {
        View view = getView();
        if (view == null) return;
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        if (recyclerView == null) return;
        ConcatAdapter concatAdapter = (ConcatAdapter)  recyclerView.getAdapter();
        ListPreferenceRecyclerViewAdapter adapter = (ListPreferenceRecyclerViewAdapter) concatAdapter.getAdapters().get(1);
        if (adapter == null) return;
        adapter.setOnEntryClickedListener(listener);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.list_preference, container, false);
        RecyclerView recyclerView = root.findViewById(R.id.recycler_view);
        CharSequence title = getArguments().getCharSequence(TITLE);
        CharSequence[] entries = getArguments().getCharSequenceArray(ENTRIES);
        CharSequence initialSelectedEntry = getArguments().getCharSequence(INITIAL_SELECTED_ENTRY);
        ListPreferenceHeaderAdapter headerAdapter = new ListPreferenceHeaderAdapter(title);
        ListPreferenceRecyclerViewAdapter adapter = new ListPreferenceRecyclerViewAdapter(entries, initialSelectedEntry);
        ListPreferenceFooterAdapter footerAdapter = new ListPreferenceFooterAdapter();

        recyclerView.setAdapter(new ConcatAdapter(headerAdapter, adapter, footerAdapter));
        return root;
    }

    private static class HeaderViewHolder extends RecyclerView.ViewHolder {

        public HeaderViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    private static class FooterViewHolder extends RecyclerView.ViewHolder {

        public FooterViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    private static class ListPreferenceViewHolder extends RecyclerView.ViewHolder {

        public ListPreferenceViewHolder(@NonNull CheckedTextView itemView) {
            super(itemView);
        }
    }

    private static class ListPreferenceHeaderAdapter extends RecyclerView.Adapter<HeaderViewHolder> {

        private final CharSequence mTitle;

        ListPreferenceHeaderAdapter(CharSequence title) {
            mTitle = title;
        }

        @NonNull
        @Override
        public HeaderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new HeaderViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_preference_title, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull HeaderViewHolder holder, int position) {
            TextView textView = holder.itemView.findViewById(R.id.title);
            textView.setText(mTitle);
        }

        @Override
        public int getItemCount() {
            return 1;
        }
    }

    private static class ListPreferenceFooterAdapter extends RecyclerView.Adapter<FooterViewHolder> {

        ListPreferenceFooterAdapter() { }

        @NonNull
        @Override
        public FooterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new FooterViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_preference_footer, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull FooterViewHolder holder, int position) {
        }

        @Override
        public int getItemCount() {
            return 1;
        }
    }

    private static class ListPreferenceRecyclerViewAdapter extends RecyclerView.Adapter<ListPreferenceViewHolder> {

        private final CharSequence[] mEntries;
        private final CharSequence mSelectedEntry;

        private OnEntryClickedListener mListener = null;

        ListPreferenceRecyclerViewAdapter(CharSequence[] entries, CharSequence initialSelectedEntry) {
            mEntries = entries;
            mSelectedEntry = initialSelectedEntry;
        }

        void setOnEntryClickedListener(OnEntryClickedListener listener) {
            mListener = listener;
        }

        @NonNull
        @Override
        public ListPreferenceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            CheckedTextView item = (CheckedTextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.select_dialog_singlechoice_material, parent, false);
            return new ListPreferenceViewHolder(item);
        }

        @Override
        public void onBindViewHolder(@NonNull ListPreferenceViewHolder holder, int position) {
            CharSequence entry = mEntries[position];
            CheckedTextView checkedTextView = (CheckedTextView) holder.itemView;
            checkedTextView.setText(entry);
            boolean isChecked = entry.equals(mSelectedEntry);
            checkedTextView.setChecked(isChecked);
            checkedTextView.setOnClickListener(v -> {
                if (mListener != null) {
                    mListener.onEntryClicked(holder.getAdapterPosition());
                }
            });
        }

        @Override
        public int getItemCount() {
            return mEntries.length;
        }
    }
}
