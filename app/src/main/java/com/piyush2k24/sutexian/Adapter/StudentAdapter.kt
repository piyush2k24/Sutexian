package com.piyush2k24.sutexian.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.piyush2k24.sutexian.Model.Students
import com.piyush2k24.sutexian.R

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
        holder.StudFname.text= currentStudent.Fname
        holder.StudLname.text=currentStudent.Lname
        holder.StudBate.text=currentStudent.Bdate
        holder.StudPhone.text=currentStudent.Phone
        holder.StudCollege.text=currentStudent.Clg
        holder.StudEmail.text=currentStudent.Email
    }

    class ViewHolder(itemView: android.view.View) : RecyclerView.ViewHolder(itemView) {
        val StudFname : TextView = itemView.findViewById(R.id.StudFname)
        val StudLname : TextView = itemView.findViewById(R.id.StudLname)
        val StudBate : TextView = itemView.findViewById(R.id.StudBirthdate)
        val StudPhone : TextView = itemView.findViewById(R.id.StudPhoneNo)
        val StudCollege : TextView= itemView.findViewById(R.id.StudCollege)
        val StudEmail : TextView = itemView.findViewById(R.id.StudEmail)
    }
}