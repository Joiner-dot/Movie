package com.example.movierate.extensions

import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter

fun ListDelegationAdapter<*>.setData(data: List<*>) {
    items = data
    notifyDataSetChanged()
}