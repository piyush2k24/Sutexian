package com.piyush2k24.sutexian.View

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.piyush2k24.sutexian.Adapter.UserAdapter
import com.piyush2k24.sutexian.Model.User
import com.piyush2k24.sutexian.R
import com.piyush2k24.sutexian.databinding.DemoUsersListBinding

class DemoUsersList : AppCompatActivity() {
    private lateinit var binding: DemoUsersListBinding
    var list= ArrayList<User>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DemoUsersListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Caller()
    }
    private fun Caller(){
        list.add(
            User(
                R.drawable.ajay_frag,
                "Ajay Mali",
                "ak@sutexian.inc",
                "+913636345674",
                "Developer"
            )
        )

        list.add(
            User(
                R.drawable.ajith_frag,
                "Ajith Shaji",
                "aj@sutexian.inc",
                "+915856746674",
                "Engineer"
            )
        )

        list.add(
            User(
                R.drawable.anamika_saini,
                "Anamika Saini",
                "anu@sutexian.inc",
                "+914676854644",
                "Engineer"
            )
        )

        list.add(
            User(
                R.drawable.anju,
                "Anjli parmar",
                "anju@sutexian.inc",
                "+916474546476",
                "Developer"
            )
        )

        list.add(
            User(
                R.drawable.arpit_frag,
                "Arpit Kakdiya",
                "ark@sutexian.inc",
                "+913636345674",
                "Dev Ops"
            )
        )

        list.add(
            User(
                R.drawable.dharmendra_frag,
                "Dharmendra",
                "dh@sutexian.inc",
                "+914647365855",
                "Administrator"
            )
        )

        list.add(
            User(
                R.drawable.hitixa_patel,
                "Hitixa Patel",
                "hp@sutexian.inc",
                "+9147475865433",
                "Engineer"
            )
        )

        list.add(
            User(
                R.drawable.mayank_frag,
                "Mayank",
                "mayank@sutexian.inc",
                "+914695646464",
                "Ui/Ux Designer"
            )
        )

        list.add(
            User(
                R.drawable.nikhil_frag,
                "Nikhil Mishra",
                "nikhil@sutexian.inc",
                "+914764745747",
                "Web Developer"
            )
        )

        list.add(
            User(
                R.drawable.nimi_jagid,
                "Nimi Jagid",
                "nmz@sutexian.inc",
                "+925560967443",
                "Blogger"
            )
        )

        list.add(
            User(
                R.drawable.noor_jahan,
                "Noor Jahan",
                "noor@sutexian.inc",
                "+918585684645",
                "Engineer"
            )
        )

        list.add(
            User(
                R.drawable.piyush_frag,
                "Piyush Makwana",
                "admin@sutexian.inc",
                "+917990764192",
                "Administrator"
            )
        )

        list.add(
            User(
                R.drawable.rajat_frag,
                "Rajat Kevat",
                "rdx@sutexian.inc",
                "+915986598875",
                "Azure devOps"
            )
        )

        list.add(
            User(
                R.drawable.saima_khanjpg,
                "Saima Khan",
                "saima@sutexian.inc",
                "+917447748743",
                "Designer"
            )
        )

        list.add(
            User(
                R.drawable.sheetal_sinha,
                "Sheetal Sinha ",
                "ssl@sutexian.inc",
                "+917347475858",
                "Designer"
            )
        )

        list.add(
            User(
                R.drawable.umesh_frag,
                "Umesh",
                "umesh@sutexian.inc",
                "+914748695484",
                "Android Dev"
            )
        )
        binding.lstView.adapter=UserAdapter(this,R.layout.list_item,list)
    }
}