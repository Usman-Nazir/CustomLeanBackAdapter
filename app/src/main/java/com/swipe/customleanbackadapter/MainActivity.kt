package com.swipe.customleanbackadapter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.swipe.custom_leanback_adapter.LeanBackAdapter
import com.swipe.custom_leanback_adapter.leanBackMultiAdapter.MultiHorizontalAdapter
import com.swipe.custom_leanback_adapter.models.menuList.MenuItems
import com.swipe.customleanbackadapter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var binding:ActivityMainBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        supportActionBar?.hide()

        var items:MutableList< MenuItems> = arrayListOf()
        items.add(MenuItems("Abc", arrayListOf("Abc","ABc","Abc","ABc","Abc","ABc","Abc","ABc","Abc","ABc","Abc","ABc","Abc","ABc","Abc","ABc","Abc","ABc","Abc","ABc","Abc","ABc","Abc","ABc","Abc","ABc","Abc","ABc","Abc","ABc"),RowTags.MOVIES.name , com.swipe.custom_leanback_adapter.R.layout.item_movies))
        items.add(MenuItems("Abc", arrayListOf("Abc","ABc","Abc","ABc","Abc","ABc","Abc","ABc","Abc","ABc","Abc","ABc","Abc","ABc","Abc","ABc","Abc","ABc","Abc","ABc","Abc","ABc","Abc","ABc","Abc","ABc","Abc","ABc","Abc","ABc"),RowTags.MOVIES.name , com.swipe.custom_leanback_adapter.R.layout.item_movies))
        items.add(MenuItems("Abc", arrayListOf("Abc","ABc","Abc","ABc","Abc","ABc","Abc","ABc","Abc","ABc","Abc","ABc","Abc","ABc","Abc","ABc","Abc","ABc","Abc","ABc","Abc","ABc","Abc","ABc","Abc","ABc","Abc","ABc","Abc","ABc"),RowTags.MOVIES.name , com.swipe.custom_leanback_adapter.R.layout.item_movies))
        items.add(MenuItems("Abc", arrayListOf("Abc","ABc","Abc","ABc","Abc","ABc","Abc","ABc","Abc","ABc","Abc","ABc","Abc","ABc","Abc","ABc","Abc","ABc","Abc","ABc","Abc","ABc","Abc","ABc","Abc","ABc","Abc","ABc","Abc","ABc"),RowTags.MOVIES.name , com.swipe.custom_leanback_adapter.R.layout.item_movies))
        items.add(MenuItems("Abc", arrayListOf("Abc","ABc","Abc","ABc","Abc","ABc","Abc","ABc","Abc","ABc","Abc","ABc","Abc","ABc","Abc","ABc","Abc","ABc","Abc","ABc","Abc","ABc","Abc","ABc","Abc","ABc","Abc","ABc","Abc","ABc"),RowTags.MOVIES.name , com.swipe.custom_leanback_adapter.R.layout.item_movies))
        items.add(MenuItems("Abc", arrayListOf("Abc","ABc","Abc","ABc","Abc","ABc","Abc","ABc","Abc","ABc","Abc","ABc","Abc","ABc","Abc","ABc","Abc","ABc","Abc","ABc","Abc","ABc","Abc","ABc","Abc","ABc","Abc","ABc","Abc","ABc"),RowTags.MOVIES.name , com.swipe.custom_leanback_adapter.R.layout.item_movies))
        items.add(MenuItems("Abc", arrayListOf("Abc","ABc","Abc","ABc","Abc","ABc","Abc","ABc","Abc","ABc","Abc","ABc","Abc","ABc","Abc","ABc","Abc","ABc","Abc","ABc","Abc","ABc","Abc","ABc","Abc","ABc","Abc","ABc","Abc","ABc"),RowTags.MOVIES.name , com.swipe.custom_leanback_adapter.R.layout.item_movies))
        LeanBackAdapter(this)
            .withScaleY(1.15f)
            .withRecyclerView(binding?.leanBackAdapter)
            .loadItems(items, verticalRowBind = {item ,rowType, binding->
                when(rowType){
                    RowTags.MOVIES.name->{
                        (binding.dynamicRecyclerView.adapter as? MultiHorizontalAdapter)?.updateData(item.helperObj as MutableList<Any>, rowType!!)
                    }
                }
            }, clickListener = { item, rowType ->

            }, horizontalRowBind = {item ,rowType ,view ,layoutResource->

            })
    }

    enum class RowTags {
        MOVIES, DRAMAS
    }

}