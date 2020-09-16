package io.simpo.simpobutton.model;

import androidx.fragment.app.FragmentActivity;

public interface ISSimpo {
    void open();
    void close();
    void deinitialize(FragmentActivity activity);
    void updateParams(String jsonParams);
    Boolean getIsInitialized();
}
