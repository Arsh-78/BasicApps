package com.example.a56book.userDAO

import com.example.a56book.models.User
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UserDAO
{
   private val db= FirebaseFirestore.getInstance()
   private val userCollection = db.collection("users")
    fun adduser(user : User?){
        user?.let{
            //To avoid lagging of UI while running applications we use coroutines to perform all the read and write operations on the background thread for that we use the Gloval Scope launc dispatchers code ,Explained well in MVVM

            GlobalScope.launch(Dispatchers.IO) {
                userCollection.document(user.uid).set(it)
            }
        }
    }
    fun getUserById( uID :String) : Task<DocumentSnapshot> //The calls are caaled ASYNCHRONUS CALLS as they do not give instant output,we have to wai for it,we use either await(){routines} or put a function oncompletelistener
    {
        return  userCollection.document(uID).get()
    }
}