package com.mdc.myexampleskotlin2019

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*


class MainActivity : AppCompatActivity() {

    lateinit var mStoreReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mStoreReference = FirebaseDatabase.getInstance().getReference("NOD_STORE").child("nd_home")

        //ADD VALUE IN SPECIFIC CHILD
        mStoreReference!!.child("03").child("fd_description").setValue("McPollo - McDonalds")

        //ADD VALUE WITH RAMDON CHILD - PUSH()
        mStoreReference!!.push().child("productName").setValue("McPollo - McDonalds")

        //GET PUSH ID AND OTHER VALUES
        mStoreReference.orderByChild("03").addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (child in dataSnapshot.children) {
                    Log.d("ID:::", child.key)
                    Log.d("Firebase Reference:::", child.ref.toString())
                    Log.d("User value:::", child.value.toString())
                }
            }
            override fun onCancelled(error: DatabaseError) {}
        })
    }
}
