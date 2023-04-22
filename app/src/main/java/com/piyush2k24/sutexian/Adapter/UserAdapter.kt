package com.piyush2k24.sutexian.Adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.google.android.material.textview.MaterialTextView
import com.piyush2k24.sutexian.Model.User
import com.piyush2k24.sutexian.R
import de.hdodenhof.circleimageview.CircleImageView

class UserAdapter(private var context: Activity, private var resources: Int, private var arrayList: ArrayList<User>):ArrayAdapter<User>(context,
    R.layout.list_item,arrayList) {

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {

        val layoutInflater:LayoutInflater = LayoutInflater.from(context)
        val view:View = layoutInflater.inflate(R.layout.list_item,null)

        val UPhoto:CircleImageView=view.findViewById(R.id.UserImage)
        val UName:MaterialTextView=view.findViewById(R.id.UserName)
        val UEmail:MaterialTextView=view.findViewById(R.id.UserEmail)
        val UPhone:MaterialTextView=view.findViewById(R.id.UserPhone)
        val UDesignation:MaterialTextView=view.findViewById(R.id.UserDesignation)

        val user:User=arrayList[position]
        UPhoto.setImageResource(user.UserPhoto)
        UName.setText(user.UserName)
        UEmail.setText(user.UserEmail)
        UPhone.setText(user.UserPhone)
        UDesignation.setText(user.UserDesignation)
        return view
    }
}