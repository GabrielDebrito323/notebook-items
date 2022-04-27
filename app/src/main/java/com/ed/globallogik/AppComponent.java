package com.ed.globallogik;

import com.ed.globallogik.base.ItemDetailFragment;
import com.ed.globallogik.base.MainActivity;
import javax.inject.Singleton;
import dagger.Component;

@Component(modules = {NetworkModule.class, AppModule.class})
@Singleton
public interface AppComponent {
    void inject(MainActivity c);
    void inject(ListItemsFragment c);
    void inject(ItemDetailFragment c);

}
