package com.mazzeom.app.armstrong.root.sign_in

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.mazzeom.app.armstrong.R
import com.mazzeom.app.armstrong.libs.api.dto.ProfileDTO

class ProfileAdapter(private var context: Context, private var profiles: Array<ProfileDTO>): BaseAdapter() {
    override fun getCount(): Int {
        return when(profiles.size) {
            4 -> 4
            else -> profiles.size + 1
        }
    }

    override fun getItem(position: Int): ProfileDTO {
        if (position == profiles.size) {
            return ProfileDTO(0, "")
        } else {
            return profiles[position]
        }
    }

    override fun getItemId(position: Int): Long {
        return profiles[position].id.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val item = getItem(position)
        var view = convertView

        if (view == null) {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.sign_in_profile_item, parent, false) as View
        }

        val profileNickName = view.findViewById<TextView>(R.id.profileNickName)
        val profileImageText = view.findViewById<TextView>(R.id.profileImageText)

        profileNickName.text = item.nickname
        profileImageText.text = when(item.nickname.length) {
            0 -> "+"
            2 -> item.nickname.slice(0..1).uppercase()
            else -> item.nickname[0].toString().uppercase()
        }

        return view
    }

}