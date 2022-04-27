package com.ed.globallogik;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.ed.globallogik.recycler_view.RecyclerViewAdapter.CellType.ITEM;
import static com.ed.globallogik.utils.CustomAlertDialog.Type.ERROR;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import com.ed.globallogik.base.ItemDetailFragment;
import com.ed.globallogik.recycler_view.RecyclerViewAdapter;
import com.ed.globallogik.utils.CustomAlertDialog;
import com.ed.globallogik.view.CustomAlert;

import java.util.ArrayList;

import javax.inject.Inject;

public class ListItemsFragment extends Fragment {

    ProgressBar pBar;
    RecyclerView rView;
    CustomAlert cAlert;
    @Inject
    ItemsViewModel vm;
    RecyclerViewAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((App) requireContext().getApplicationContext()).getNetComponent().inject(this);
        adapter = new RecyclerViewAdapter(ITEM, requireContext(), item -> {
            vm.setSelectedItem(item);
            showDetail();
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_list_items, container, false);


        pBar = view.findViewById(R.id.progress_bar);
        rView = view.findViewById(R.id.recycler_view);
        cAlert = view.findViewById(R.id.error_alert);

        rView.setLayoutManager(new LinearLayoutManager(requireContext()));
        rView.setAdapter(adapter);

        cAlert.setOnButtonPressedListener(new CustomAlert.OnButtonPressed() {
            @Override
            public void onClick() {
                requestItems();
            }
        });
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        vm.itemsRequest.observe(getViewLifecycleOwner(), r -> {
            switch (r.getStatus()){
                case LOADING: {
                    pBar.setVisibility(VISIBLE);
                    cAlert.setVisibility(GONE);
                    break;
                }
                case SUCCESS: {
                    pBar.setVisibility(View.GONE);
                    cAlert.setVisibility(GONE);
                    adapter.updateList(new ArrayList<>(r.getData()));
                    break;
                }
                case ERROR: {
                    pBar.setVisibility(GONE);
                    cAlert.setVisibility(VISIBLE);
                    buildDialog(r.getErrorMessage(), ERROR);
                    break;
                }
            }
        });

        requestItems();
    }

    public void showDetail(){
        Fragment fragment = ItemDetailFragment.newInstance();

        requireActivity().getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right)
                .addToBackStack(fragment.getClass().getName())
                .add(R.id.container, fragment, fragment.getClass().getName())
                .commit();
    }

    public void requestItems() {
        if (vm.itemsRequest.getValue() == null){
            vm.requestItems();
        } else {
            if (vm.itemsRequest.getValue().getData() == null){
                vm.requestItems();
            }
        }
    }

    public void buildDialog(String message, CustomAlertDialog.Type type) {
        new CustomAlertDialog(requireContext()).setType(type)
                .setMessage(message)
                .setNeutralButton(R.string.try_again, (dialogInterface, i) -> {
                    requestItems();
                })
                .setPositiveButton(R.string.accept, (dialogInterface, i) -> {
                })
                .show();
    }

    public static ListItemsFragment newInstance() {
        return new ListItemsFragment();
    }
}