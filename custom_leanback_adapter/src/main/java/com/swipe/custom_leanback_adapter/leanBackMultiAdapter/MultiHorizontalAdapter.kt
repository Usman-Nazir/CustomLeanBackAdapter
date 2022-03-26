package com.swipe.custom_leanback_adapter.leanBackMultiAdapter


import android.app.Activity
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.swipe.custom_leanback_adapter.models.menuList.MultiRowType


class MultiHorizontalAdapter(private var items: MutableList<Any>,private  var context: Context,private var clickListener: (Any, multiRowType: String?) -> Unit,
                             private var isFocusedRowWise: (Any, multiRowType: String?, View, isHorizontal: Boolean, selectedPos: Int, rowSize: Int, layoutType: Int?) -> Unit,
                             private var isFocused: (Any, multiRowType: String?, View, Boolean, selectedPos: Int, rowSize: Int, layoutType: Int?) -> Unit,
                             private var horizontalRowBind: (Any, multiRowType: String?, View, layoutType: Int?) -> Unit) : RecyclerView.Adapter<MultiHorizontalAdapter.ViewHolder>() {

    private var listType: String? = null
    var layoutType: Int? = null
    var selectedPosition = 0
    var scaleX =1f
    var scaleY =1f

    init {
        listType = MultiRowType.GENERAL.name
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val view = layoutType?.let { LayoutInflater.from(viewGroup.context).inflate(it, viewGroup, false) }!!
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        items.getOrNull(i)?.let {
            viewHolder.bind(it)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun updateData(searchData: MutableList<Any>?, listType: String) {
        this.listType = listType
        items.clear()
//        notifyDataSetChanged()
        searchData.let {
            items.addAll(it!!.toMutableList())
        }
        notifyDataSetChanged()
    }

    inner class ViewHolder(var binding: View) : RecyclerView.ViewHolder(binding) {
        fun bind(it: Any) {
            horizontalRowBind?.invoke(it, listType, binding, layoutType)
        }

        init {

            binding.setOnKeyListener { v, keyCode, event ->
                //Log.i("test", "working ${keyCode}  selectedPosition ${selectedPosition}  ==  ${items.size - 1}")
                when (event.action) {
                    KeyEvent.ACTION_UP -> {
                        when (keyCode) {
                            KeyEvent.KEYCODE_DPAD_UP, KeyEvent.KEYCODE_DPAD_DOWN -> {
                                items?.get(bindingAdapterPosition)?.let {
//                                    Log.i("test" ," up down called")
                                    isFocusedRowWise.invoke(it, listType, binding, false, selectedPosition, itemCount, layoutType)
                                }
                            }
                            KeyEvent.KEYCODE_DPAD_LEFT, KeyEvent.KEYCODE_DPAD_RIGHT -> {
                                items?.get(bindingAdapterPosition)?.let {
//                                    Log.i("test" ," left right called")
                                    isFocusedRowWise.invoke(it, listType, binding, true, selectedPosition, itemCount, layoutType)
                                }
                            }
                        }
                    }
                }

//                if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) {
//                    if (selectedPosition == items.size - 1) {
//                        if (context is Activity)
//                            (context as Activity).currentFocus?.clearFocus()
//                        Handler(Looper.getMainLooper()).post {
//                            if (context is Activity)
//                                (context as Activity).currentFocus?.clearFocus()
//                            binding.requestFocus()
//                        }
//                        true
//                    }
//                }
                false
            }
            binding.onFocusChangeListener = View.OnFocusChangeListener { view: View?, b: Boolean ->
                if (b) {
                    selectedPosition = bindingAdapterPosition
                    binding.scaleX = scaleX
                    binding.scaleY = scaleY
                } else {
                    binding.scaleX = 1.0f
                    binding.scaleY = 1.0f
                }
                items?.get(bindingAdapterPosition)?.let {
                    isFocused.invoke(it, listType, binding, b, selectedPosition, itemCount, layoutType)
                }
            }

            binding.setOnLongClickListener {
                true
            }
            binding.setOnClickListener {
                items?.getOrNull(bindingAdapterPosition)?.let {
                    clickListener?.invoke(it, listType)
                }
            }
        }
    }
}
