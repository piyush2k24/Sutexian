package com.piyush2k24.sutexian.View

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.piyush2k24.sutexian.Adapter.StudentAdapter
import com.piyush2k24.sutexian.Model.Students
import com.piyush2k24.sutexian.databinding.ActivityGetDataFromRealtimeFirebaseBinding

class GetDataFromRealtimeFirebase : AppCompatActivity() {
    private lateinit var binding: ActivityGetDataFromRealtimeFirebaseBinding
    private lateinit var StudentList : ArrayList<Students>
    private lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityGetDataFromRealtimeFirebaseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.StudListRecycle.layoutManager=LinearLayoutManager(this)
        binding.StudListRecycle.setHasFixedSize(true)
        StudentList= arrayListOf<Students>()
        databaseReference=FirebaseDatabase.getInstance().getReference("Users")
        getData()
    }

    private fun getData(){
        databaseReference.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for (stud in snapshot.children){
                        val studData=stud.getValue(Students::class.java)
                        StudentList.add(studData!!)
                    }
                    val mAdapter= StudentAdapter(StudentList)
                    binding.StudListRecycle.adapter=mAdapter
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(applicationContext,error.toString(),Toast.LENGTH_SHORT).show()
            }

        })
    }
}