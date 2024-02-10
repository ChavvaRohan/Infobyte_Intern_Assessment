package com.chavvarohan.infobyteinternassessment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter(private val list: ArrayList<DataItem>, val context: Context):RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {

        val namee :TextView = itemView.findViewById(R.id.name)
        val changeInPRICE: TextView = itemView.findViewById(R.id.change_in_PRICE)
        val changeInPrice: TextView = itemView.findViewById(R.id.change_in_price)
        val ltp: TextView = itemView.findViewById(R.id.ltp)
        val pdCLOSE: TextView = itemView.findViewById(R.id.p_d_code)
        val percChange: TextView = itemView.findViewById(R.id.perc_change)
        val percChangeLong: TextView = itemView.findViewById(R.id.perc_change_long)
        val symbol:TextView = itemView.findViewById(R.id.symbol)
        val todayHIGH:TextView = itemView.findViewById(R.id.today_high)
        val todayLOW: TextView = itemView.findViewById(R.id.today_low)
        val todayOPEN: TextView = itemView.findViewById(R.id.today_open)
        val todayVOLUME: TextView = itemView.findViewById(R.id.today_volume)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.design_layout,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val currentItem = list[position]

        holder.apply {

            namee.text = currentItem.NAME
            symbol.text = currentItem.SYMBOLE
            pdCLOSE.text = currentItem.P_D_CLOSE.toString()
            todayHIGH.text = currentItem.TODAY_HIGH.toString()
            todayLOW.text = currentItem.TODAY_LOW.toString()
            ltp.text = currentItem.LTP.toString()
            todayVOLUME.text = currentItem.TODAY_VOLUME.toString()
            changeInPrice.text = currentItem.ChangeInPrice.toString()
            changeInPRICE.text = currentItem.ChangeInPRICE.toString()
            percChangeLong.text = currentItem.Perc_changeLong.toString()
            percChange.text = currentItem.Perc_change.toString()
            todayOPEN.text = currentItem.TODAY_OPEN.toString()


        }


    }

    override fun getItemCount(): Int {
        return list.size
    }

}