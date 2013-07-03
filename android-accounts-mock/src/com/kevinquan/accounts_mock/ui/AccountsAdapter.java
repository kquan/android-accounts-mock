package com.kevinquan.accounts_mock.ui;

import java.util.List;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.kevinquan.accounts_mock.model.IAccountListItem;
import com.kevinquan.android_accounts_mock.R;

public class AccountsAdapter extends ArrayAdapter<IAccountListItem> {

    private static final String TAG = AccountsAdapter.class.getSimpleName();
    
    private static class AccountItemViewHolder {
        TextView name;
    }
    
    protected int mIconSize;
    
    public AccountsAdapter(Context context, List<IAccountListItem> objects) {
        super(context, -1, -1, objects);
        mIconSize = context.getResources().getDimensionPixelSize(R.dimen.accounts_list_drawable_size);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        IAccountListItem item = getItem(position);
        if (item == null) {
            return super.getView(position, convertView, parent);
        }
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.accounts_item, null);
            AccountItemViewHolder viewHolder = new AccountItemViewHolder();
            viewHolder.name = (TextView)convertView.findViewById(R.id.account_name);
            convertView.setTag(viewHolder);
        }
        AccountItemViewHolder viewHolder = null;
        try {
            viewHolder = (AccountItemViewHolder)convertView.getTag();
        } catch (ClassCastException e) {
            Log.w(TAG, "Incorrect type for view tag.  Found: "+convertView.getTag().getClass().getName());
        }
        
        viewHolder.name.setText(item.getName());
        if (item.getIcon() != IAccountListItem.NO_ICON) {
            Drawable icon = getContext().getResources().getDrawable(item.getIcon());
            icon.setBounds(0, 0, mIconSize, mIconSize);
            viewHolder.name.setCompoundDrawables(icon, null, null, null);
        } else {
            viewHolder.name.setCompoundDrawables(null, null, null, null);
        }
        return convertView;
    }

}
