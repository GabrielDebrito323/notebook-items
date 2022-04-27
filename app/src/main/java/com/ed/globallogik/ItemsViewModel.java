package com.ed.globallogik;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.ed.globallogik.data.CustomCallback;
import com.ed.globallogik.data.Resource;
import com.ed.globallogik.model.Item;
import java.util.ArrayList;
import javax.inject.Inject;


public class ItemsViewModel extends ViewModel {

    @Inject
    Repository repository;
    Item selectedItem;
    MutableLiveData<Resource<ArrayList<Item>>> itemsRequest = new MutableLiveData<>();

    @Inject
    public ItemsViewModel(Repository repository){
        this.repository = repository;
    }

    public void requestItems() {
        itemsRequest.postValue(new Resource.Loading<>(null, null));
        repository.requestItems(new CustomCallback<ArrayList<Item>>(){
            @Override
            public void onSuccess(ArrayList<Item> responseBody) {
                super.onSuccess(responseBody);
                itemsRequest.postValue(new Resource.Success<>(responseBody, null));
            }

            @Override
            public void onError(String errorMessage) {
                super.onError(errorMessage);
                itemsRequest.postValue(new Resource.Error<>(null, errorMessage));
            }
        });
    }

    public Item getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(Item selectedItem){
        this.selectedItem = selectedItem;
    }


}
