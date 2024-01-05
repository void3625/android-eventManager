package com.example.myapplication
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView


class MyAdapter (context: Context, private val layout: Int, data: ArrayList<homepage_layout.Item>):
    ArrayAdapter<homepage_layout.Item>(context, layout, data){
    override fun  getView(position: Int, converView: View?, parent: ViewGroup): View {


        val view = View.inflate(parent.context, layout, null)


        val item = getItem(position) ?: return  view


        val img_photo = view.findViewById<ImageView>(R.id.img_photo)
        img_photo.setImageResource(item.photo)

        val tv_msg = view.findViewById<TextView>(R.id.tv_msg)
        tv_msg.text = "${item.con}"


        return view
    }


}
