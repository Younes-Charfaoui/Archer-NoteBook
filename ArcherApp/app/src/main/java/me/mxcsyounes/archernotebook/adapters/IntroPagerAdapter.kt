package me.mxcsyounes.archernotebook.adapters;

import android.content.Context
import android.support.annotation.NonNull
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


class IntroPagerAdapter(val context: Context, private val layouts: Array<Int>) : PagerAdapter() {


    override fun instantiateItem(@NonNull container: ViewGroup, position: Int): Any {

        val layoutInflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater;


        val view = layoutInflater.inflate(layouts[position], container, false);
        container.addView(view);

        return view;
    }

    override fun getCount(): Int = layouts.size


    override fun isViewFromObject(view: View, obj: Any): Boolean = view == obj


    override fun  destroyItem(container : ViewGroup , position :Int ,obj : Any) {
        val view = obj as View
        container.removeView(view)
    }

}
