package com.swipe.custom_leanback_adapter.leanBackMultiAdapter

import android.app.Activity
import android.content.Context
import android.graphics.Point
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.swipe.custom_leanback_adapter.databinding.ItemMultiRowBinding
import com.swipe.custom_leanback_adapter.models.menuList.MenuItems


class MultiItemAdapter(private var items: MutableList<MenuItems>, var context: Context,
                       private var focusChangedHrRow: (Any, multiRowType:String?, View, Boolean, Int?) -> Unit,
                       private var isFocusedRowWise: (Any, multiRowType:String?, View, Boolean, Int?) -> Unit,
                       private var clickListener: (Any, multiRowType:String?) -> Unit,
                       private var horizontalRowBind: (Any, multiRowType:String?, View, Int?) -> Unit,
                       private var verticalRowBind: (MenuItems, multiRowType:String?, ItemMultiRowBinding) -> Unit) : RecyclerView.Adapter<MultiItemAdapter.ViewHolder>() {

    var selectedPosition = 0
    var selectedPositionHr = 0
    var hrRowSize = 0
    val size = Point()
    var width: Int = -1
    var height: Int = -1


    init {
        val display = (context as Activity).windowManager.defaultDisplay
        display.getSize(size)
        width = size.x
        height = size.y
    }

    override fun getItemViewType(position: Int): Int {
        return  position

//        return MultiRowType.valueOf(items.get(position).rowType
//            ?: MultiRowType.GENERAL.name).ordinal
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val view = ItemMultiRowBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)//LayoutInflater.from(viewGroup.context).inflate(R.layout.item_multi_movies, viewGroup, false)
        val rowType = items.get(i).rowType!!
        return ViewHolder(view, rowType)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        items.getOrNull(i)?.let {
            viewHolder.bind(it)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun clearAdapter() {
        items.clear()
        notifyDataSetChanged()
    }

    fun updateData(searchData: MutableList<MenuItems>) {
        items.clear()
//        notifyDataSetChanged()
        Handler(Looper.getMainLooper()).post {
            searchData.let {
                items.addAll(it.toMutableList())
            }
            notifyDataSetChanged()
        }
    }

    fun updateDataOnly(searchData: MutableList<MenuItems>) {
        searchData?.forEach {
            items.add(it)
            notifyItemInserted(items.size)
        }
    }

    inner class ViewHolder(var binding: ItemMultiRowBinding, multiRowType: String) : RecyclerView.ViewHolder(binding.root) {
        fun bind(it: MenuItems) {
//            binding.titleText.text = it.nam
            verticalRowBind.invoke(it, it.rowType , binding)
        }

        //        var root_: LinearLayout = itemView.root_
//        var titleText = itemView.titleText
//        var dynamicRecyclerView = itemView.dynamicRecyclerView
        var multiHorizontalAdapter: MultiHorizontalAdapter? = null

        init {

//            binding.browseContainer.setOnFocusSearchListener { focused, direction ->
//                if (binding.dynamicRecyclerView.hasFocus())
//                     focused; // keep focus on recyclerview! DO NOT return recyclerview, but focused, which is a child of the recyclerview
//                else
//                     null; // someone else will find the next focus
//            }
            val layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            multiHorizontalAdapter = MultiHorizontalAdapter(arrayListOf(), context, { items, listType ->
                clickListener.invoke(items, listType)
            }, { items, listType , hrBinding, isHorizontal,selectedPos ,rowSize ,layoutType ->
                hrRowSize = rowSize
                selectedPositionHr = selectedPos
                selectedPosition = bindingAdapterPosition
                if (isHorizontal){
                    if (width != -1) {
                        val selectedView = binding.dynamicRecyclerView.findViewHolderForAdapterPosition(multiHorizontalAdapter?.selectedPosition
                            ?: 0)?.itemView//dynamicRecyclerView.getChildAt(itemAt)
                        if (selectedView != null) {
                            val scrollXNew: Int = (((binding.dynamicRecyclerView.width / 2) - selectedView.width / 2) - selectedView.left) * -1
                            binding.dynamicRecyclerView.smoothScrollBy((scrollXNew), 0)
                        }
                    }
                }
                isFocusedRowWise.invoke(items, listType , hrBinding , isHorizontal,layoutType)
            }, { items, listType , hrBinding, isFocused,selectedPos ,rowSize ,layoutType ->
                focusChangedHrRow.invoke(items, listType , hrBinding , isFocused,layoutType)
//                if (isFocused) {
////                    hrRowSize = rowSize
////                    selectedPositionHr = selectedPos
////                    selectedPosition = bindingAdapterPosition
////                    if (width != -1) {
////                        val selectedView = binding.dynamicRecyclerView.findViewHolderForAdapterPosition(multiHorizontalAdapter?.selectedPosition
////                            ?: 0)?.itemView//dynamicRecyclerView.getChildAt(itemAt)
////                        if (selectedView != null) {
////                            val scrollXNew: Int = (((width / 2) - selectedView.width / 2) - selectedView.left) * -1
////                            binding.dynamicRecyclerView.smoothScrollBy((scrollXNew), 0)
////                        }
////                    }
//                }
            }, { items, listType, binding ,layoutType ->
                horizontalRowBind.invoke(items, listType, binding,layoutType)
            })
            binding.dynamicRecyclerView.setHasFixedSize(true)
            binding.dynamicRecyclerView.layoutManager = layoutManager
            binding.dynamicRecyclerView.adapter = multiHorizontalAdapter
            binding.dynamicRecyclerView.animation = null
            binding.dynamicRecyclerView.itemAnimator = null
        }
    }
}
