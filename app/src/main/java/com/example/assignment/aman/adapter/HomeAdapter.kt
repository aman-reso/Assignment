package com.example.assignment.aman.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment.R
import com.example.assignment.aman.models.SingleItemResponse
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import java.lang.Exception

class HomeAdapter: RecyclerView.Adapter<HomeAdapter.ViewHolder>() {
    private var suggestedFriendList= ArrayList<SingleItemResponse>()

    fun submitList(list:ArrayList<SingleItemResponse>){
        this.suggestedFriendList.clear()
        this.suggestedFriendList.addAll(list)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.each_item_layout,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item=suggestedFriendList[position]
        holder.bindValue(item)
    }

    override fun getItemCount(): Int {
        return suggestedFriendList.size
    }

     //need to write base url for loading profile picture
    var baseURl="https://gupsup.com/"
    inner  class ViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView)
    {
        var profileImageView:CircleImageView=itemView.findViewById(R.id.profileImage)
        var nameTV:TextView=itemView.findViewById(R.id.nameTV)
        var connectTV:TextView=itemView.findViewById(R.id.connectTV)
        var removeTV:TextView=itemView.findViewById(R.id.removeTV)
        var subtitleTV:TextView=itemView.findViewById(R.id.subtitleTV)

        fun bindValue(item: SingleItemResponse) {
            nameTV.text=item.firstName
            val formattedText="followed by "+ item.tickTokName +"1 more"
            subtitleTV.text=formattedText
            val formattedImageUrl=baseURl+item.profilePic

            try {
                Picasso.get().load(formattedImageUrl).into(profileImageView)
            }catch (e:Exception){

            }
        }

    }


}