package com.example.mapapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Custom extends BaseAdapter {

    private ArrayList<News> modelArrayList;
    private Context context;
    private int layout;
    //generate constructor

    public Custom(ArrayList<News> modelArrayList, Context context, int layout){
        this.modelArrayList = modelArrayList;
        this.context=context;
        this.layout=layout;
    }

    @Override
    public int getCount() {return modelArrayList.size();}

    @Override
    public Object getItem(int position) {return modelArrayList.get(position);}

    @Override
    public long getItemId(int position){return position;}

    //create view holder innter class
    private class ViewHolder{
        TextView idtxt, titletxt, bodytxt,xt,txt1,t,lt,txt;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = layoutInflater.inflate(layout, null);
        //id type casting
        viewHolder.idtxt = convertView.findViewById ( R.id.idtxt );
        viewHolder.titletxt = convertView.findViewById ( R.id.titletxt );
        viewHolder.bodytxt = convertView.findViewById ( R.id.bodytxt );
        viewHolder.txt1 = convertView.findViewById ( R.id.txt1 );
        viewHolder.xt = convertView.findViewById ( R.id.xt );
        viewHolder.t = convertView.findViewById ( R.id.t );
        viewHolder.txt = convertView.findViewById ( R.id.txt );
        viewHolder.lt = convertView.findViewById ( R.id.lt );

        //set position
        News model = modelArrayList.get ( position );
        viewHolder.idtxt.setText ("Title:-"+ model.getHzType ()+ "\n");
        viewHolder.titletxt.setText ("Content:-"+ model.getHzDesc ()+"\n" );
        viewHolder.bodytxt.setText ( "Location:-"+model.getHzLocation () );
        viewHolder.txt.setText ( "Longitude:-"+model.getHzLng () );
        viewHolder.lt.setText ( "Latitude:-"+model.getHzLtd () );
        viewHolder.txt1.setText ( "Reporter Name:-"+model.getHzRpt () );
        viewHolder.xt.setText ( "Date:-"+model.getHzDate () );
        viewHolder.t.setText ( "Time:-"+model.getHzTime () );



        return convertView;
    }
}




