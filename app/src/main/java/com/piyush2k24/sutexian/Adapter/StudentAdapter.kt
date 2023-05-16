package com.piyush2k24.sutexian.Adapter

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.piyush2k24.sutexian.Model.Students
import com.piyush2k24.sutexian.R
import org.w3c.dom.Text
import java.util.Base64

class StudentAdapter(
    private val StudentsList: ArrayList<Students>,
) :
    RecyclerView.Adapter<StudentAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView=LayoutInflater.from(parent.context)
            .inflate(R.layout.firedb_stud_list,parent,false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return StudentsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentStudent=StudentsList[position]

        val bytes=android.util.Base64.decode(currentStudent.UserImage,android.util.Base64.DEFAULT)
        val bitmap=BitmapFactory.decodeByteArray(bytes,0,bytes.size)
        holder.UImage.setImageBitmap(bitmap)
        holder.UName.text=currentStudent.UserFname
        holder.UEmail.text=currentStudent.UserEmail
        holder.UPhone.text=currentStudent.UserPhone
    }

    class ViewHolder(itemView: android.view.View) : RecyclerView.ViewHolder(itemView) {
        val UImage:ImageView =itemView.findViewById(R.id.UImage)
        val UName:TextView = itemView.findViewById(R.id.UName)
        val UEmail:TextView=itemView.findViewById(R.id.UEmail)
        val UPhone:TextView=itemView.findViewById(R.id.UPhone)
    }
}