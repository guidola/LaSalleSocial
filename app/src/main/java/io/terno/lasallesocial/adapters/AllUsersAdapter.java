package io.terno.lasallesocial.adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

import java.util.List;

import io.terno.lasallesocial.model.SocialUser;

/**
 * Created by Guillermo on 11/5/17.
 */

public class AllUsersAdapter extends ArrayAdapter<SocialUser> {

    public AllUsersAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<SocialUser> objects) {
        super(context, resource, objects);
    }
}
