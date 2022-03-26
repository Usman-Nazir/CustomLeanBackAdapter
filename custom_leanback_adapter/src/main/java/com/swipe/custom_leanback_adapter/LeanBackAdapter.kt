package com.swipe.custom_leanback_adapter

import android.app.Activity
import android.content.Context
import android.graphics.Point
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.swipe.custom_leanback_adapter.databinding.ItemMultiRowBinding
import com.swipe.custom_leanback_adapter.leanBackMultiAdapter.MultiHorizontalAdapter
import com.swipe.custom_leanback_adapter.leanBackMultiAdapter.MultiItemAdapter
import com.swipe.custom_leanback_adapter.models.menuList.MenuItems
import com.swipe.custom_leanback_adapter.models.menuList.MultiRowType

class LeanBackAdapter(var context: Context) {

    private var scaleX = 1f
    private var scaleY = 1f

    fun withScaleX(scaleX: Float): LeanBackAdapter {
        this.scaleX = scaleX
        return this
    }

    fun withScaleY(scaleY: Float): LeanBackAdapter {
        this.scaleY = scaleY
        return this
    }

    private var focusChangedHrRow: ((item:Any, multiRowType: String?,holderView: View, Boolean,layoutResource: Int?) -> Unit)? = null
    private var clickListener: ((item:Any, multiRowType: String?) -> Unit)? = null
    private var horizontalRowBind: ((item:Any, multiRowType: String?,holderView: View,layoutResource: Int?) -> Unit)? = null
    private var verticalRowBind: ((hrRow:MenuItems, multiRowType: String?, ItemMultiRowBinding) -> Unit)? = null

    /*** new lean back ***/
    private var dynamicVerticalItemListAdapterNew: MultiItemAdapter? = null
    private var layoutManagerNew: LinearLayoutManager? = null

    fun loadItems(items: MutableList<MenuItems>,
                  focusChangedHrRow: ((item:Any, multiRowType: String?,viewHolderView: View,isFocus: Boolean,layoutResource: Int?) -> Unit)? = null,
                  clickListener: ((item:Any, multiRowType: String?) -> Unit)? = null,
                  horizontalRowBind: ((item:Any, multiRowType: String?, viewHolderView:  View,layoutResource: Int?) -> Unit)? = null,
                  verticalRowBind: ((menuItem: MenuItems, multiRowType: String?, ItemMultiRowBinding) -> Unit)? = null) {
        if (dynamicVerticalItemListAdapterNew == null) return
        this.focusChangedHrRow = focusChangedHrRow
        this.clickListener = clickListener
        this.horizontalRowBind = horizontalRowBind
        this.verticalRowBind = verticalRowBind
        dynamicVerticalItemListAdapterNew?.updateData(items)
    }

    fun withRecyclerView(recyclerView: RecyclerView? = null): LeanBackAdapter {

        if (recyclerView == null) return this
        dynamicVerticalItemListAdapterNew = MultiItemAdapter(
            arrayListOf(),
            context, { item, rowType, hrBinding, isFocused, layoutType ->
                focusChangedHrRow?.invoke(item, rowType, hrBinding, isFocused, layoutType)
            }, { item, rowType, hrBinding, isHorizontalFocused, layoutType ->
                Log.i("test" ,"selectedPosition  ${dynamicVerticalItemListAdapterNew?.selectedPosition}")
                if (!isHorizontalFocused) {
                    recyclerView?.let { dynamicVerticalItemList -> //focusListener
                        val selectedView = dynamicVerticalItemList.findViewHolderForAdapterPosition(dynamicVerticalItemListAdapterNew?.selectedPosition ?: 0)?.itemView
                        if (selectedView != null) {
                            val scrollXNew: Int = (((dynamicVerticalItemList.height / 2) - (selectedView.height) / 2) - selectedView.top) * -1
                            dynamicVerticalItemList.smoothScrollBy(0, scrollXNew)
                        }
                    }
                }
            }, { item, rowType -> //click listener
                clickListener?.invoke(item, rowType)
                Log.i("test", "sdsdsdsdsss  clicked")
            }, { item, rowType, binding, layoutType ->// hr row
                horizontalRowBind?.invoke(item, rowType, binding, layoutType)
//                when (rowType) {
//                    MultiRowType.GENERAL_SPORT -> {
//                        when {
//                            layoutType == R.layout.item_trending_movies_new && (item is ItemsItem) -> {
//                                ItemTrendingMoviesNewBinding.bind(binding).let { binding ->
//                                }
//                            }
//                        }
//                    }
//                }
            }, { item, rowType, binding -> //vrtical binding

                binding.titleText.text = item.name
                (binding.dynamicRecyclerView.adapter as? MultiHorizontalAdapter)?.scaleX = scaleX
                (binding.dynamicRecyclerView.adapter as? MultiHorizontalAdapter)?.scaleY = scaleY
                (binding.dynamicRecyclerView.adapter as? MultiHorizontalAdapter)?.layoutType = item.layoutResource
                verticalRowBind?.invoke(item, rowType, binding)
                binding.dynamicRecyclerView.invalidate()
                binding.dynamicRecyclerView.requestLayout()
//                when (rowType) {
//                    MultiRowType.GENERAL_SPORT.name -> {
//                        binding.titleText.text = item.name
//                        val dynamicRecyclerView = binding.dynamicRecyclerView
//                        when (rowType) {
//                            MultiRowType.VOD.name -> dynamicRecyclerView.layoutParams.height = Utility.dpToPx(150)
//                            MultiRowType.GENERAL_SPORT.name -> dynamicRecyclerView.layoutParams.height = Utility.dpToPx(160)
//                            else -> dynamicRecyclerView.layoutParams.height = Utility.dpToPx(160)
//                        }
//
//                        (binding.dynamicRecyclerView.adapter as? MultiHorizontalAdapter)?.layoutType = item.layoutResource
//                        (binding.dynamicRecyclerView.adapter as? MultiHorizontalAdapter)?.updateData(item.helperObj as MutableList<Any>, rowType)
//
//                        binding.dynamicRecyclerView.invalidate()
//                        binding.dynamicRecyclerView.requestLayout()
//                    }
//                }
            })

        recyclerView?.setHasFixedSize(true)
        layoutManagerNew = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recyclerView.layoutManager = layoutManagerNew
        recyclerView.adapter = dynamicVerticalItemListAdapterNew
        recyclerView.animation = null
        recyclerView.itemAnimator = null
        recyclerView.clearAnimation()
        recyclerView.requestFocus()
        return this

    }


}