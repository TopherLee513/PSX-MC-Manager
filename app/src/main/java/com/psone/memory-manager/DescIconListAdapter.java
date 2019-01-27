package com.psone.memory-manager;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.topher-dev.psxmc.R;

public class DescIconListAdapter extends BaseAdapter{
	/** Remember our context so we can use it when constructing views. */
    private Context mContext;
    private ColorStateList defaultColors;

    private List<DescIconList> mItems = new ArrayList<DescIconList>();

    public void clear(){
    	mItems.clear();
    }

    public DescIconListAdapter(Context context) {
    	TextView temp = new TextView(context);
    	defaultColors = temp.getTextColors();
        mContext = context;
    }

    public void addItem(DescIconList it) { mItems.add(it); }

    public void setListItems(List<DescIconList> lit) { mItems = lit; }

    /** @return The number of items in the */
    public int getCount() { return mItems.size(); }

    public Object getItem(int position) { return mItems.get(position); }

    public boolean areAllItemsSelectable() { return false; }

    public boolean isSelectable(int position) {
            try{
                    return mItems.get(position).isSelectable();
            }catch (IndexOutOfBoundsException aioobe){
                    return isSelectable(position);
            }
    }

    /** Use the array index as a unique id. */
    public long getItemId(int position) {
            return position;
    }

    /** @param convertView The old view to overwrite, if one is passed
     * @returns a IconifiedTextView that holds wraps around an IconifiedText */
    public View getView(int position, View convertView, ViewGroup parent) {
            DescIconView div;

            if (convertView == null) {
                    div = new DescIconView(mContext, mItems.get(position));
            } else { // Reuse/Overwrite the View passed
                    // We are assuming(!) that it is castable!
                    div = (DescIconView) convertView;

                    if(mItems.get(position).isDeleted()){
                    	div.setColor(0xFFFF0000);
                    }
                    else if(mItems.get(position).isLinked()){
                    	div.setColor(0xFF00FF00);
                    }
                    else if(mItems.get(position).isEmpty()){
                    	div.setColor(0xFF0000FF);
                    }
                    else{
                    	div.setColor(defaultColors);
                    }
                div.setAnimated(mItems.get(position).isAnimated());
                div.setText(mItems.get(position).getText());
                div.setIcon(mItems.get(position).getIcon());


                    div.setProd(mItems.get(position).getmProd());

                    div.setID(mItems.get(position).getmID());

                    div.setRegion(mItems.get(position).getmRegion());

            }
            return div;
    }
}
