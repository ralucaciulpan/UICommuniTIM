package rr.uicommunitim;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.zip.Inflater;


public class ProblemsListAdapter extends BaseAdapter {
    Context context;
    String[] categoryList;
    String[] subcategoryList;
    String[] addressList;
    LayoutInflater inflater;

    public ProblemsListAdapter(Context appContext,String[] categoryList, String[] subcategoryList, String[] addressList){
        this.context=appContext;
        this.categoryList=categoryList;
        this.subcategoryList=subcategoryList;
        this.addressList=addressList;
        inflater=(LayoutInflater.from(appContext));
    }
    @Override
    public int getCount() {
        return categoryList.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        convertView = inflater.inflate(R.layout.activity_problems_list_view_row,null);
        TextView category = convertView.findViewById(R.id.categorie);
        TextView subcategory = convertView.findViewById(R.id.subcategorie);
        TextView address = convertView.findViewById(R.id.adresa);
        category.setText(categoryList[position]);
        subcategory.setText(subcategoryList[position]);
        address.setText(addressList[position]);
        return convertView;
    }
}
