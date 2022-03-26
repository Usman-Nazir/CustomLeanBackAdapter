# CustomLeanBackAdapter
Custom Lean back adaper for tv developers

Installation:

Add in root gradle:

    allprojects {
        repositories {
	        maven { url 'https://jitpack.io' }
        }
    }

Add in app gradle:

    dependencies {
      implementation 'com.github.Usman-Nazir:CustomLeanBackAdapter:1.0.0'
    }

Usage:

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
         
          //defining tag for each row   
         enum class RowTags {
            MOVIES, DRAMAS
         }
         



<img width="1118" alt="image" src="https://user-images.githubusercontent.com/23031447/160252068-dfe961df-d5c4-46a2-b510-f1dbfacdde26.png">

