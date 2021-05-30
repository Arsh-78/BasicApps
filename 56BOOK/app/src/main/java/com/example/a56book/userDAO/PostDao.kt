package com.example.a56book.userDAO

import android.view.HapticFeedbackConstants
import com.example.a56book.models.Post
import com.example.a56book.models.User
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class PostDao
{
    val db= FirebaseFirestore.getInstance()
    val postCollection  = db.collection("posts")
    val auth = Firebase.auth
    fun addPost(text:String) {
        val currentUserId = auth.currentUser!!.uid // !! whenever we use this !! we tell th app that I am making sure that there is signed in user liking or doing something,It cant be that a user not signed in is there if there due !! bull exception occcurs and app crashes

        GlobalScope.launch{
            val userDAO = UserDAO()
            val user = userDAO.getUserById(currentUserId).await().toObject(User::class.java)!! //We put here also DAO thats why we crash if userdao goes null, cz if user dao was null then also post was created it would be illegal action
            val currentTime = System.currentTimeMillis()
            val post = Post(text, user , currentTime)
            postCollection.document().set(post)
        }

    }
    fun getPostbyID(postId : String) : Task<DocumentSnapshot> {  //We do this to get the post by the help of its ID we then intun use it to update the likes
        return postCollection.document(postId).get()
    }

    fun updateLikes(postID:String) {
        GlobalScope.launch {
            val currentUserId = auth.currentUser!!.uid
            val post = getPostbyID(postID).await().toObject(Post::class.java)!!
            val isLiked = post.likedBy.contains(currentUserId)

            if(isLiked)
            {
                post.likedBy.remove(currentUserId)
            }
            else
            {
                post.likedBy.add(currentUserId)
            }
            postCollection.document(postID).set(post)


        }
    }

}