package com.swipe.custom_leanback_adapter.models.menuList

data class MenuItems(
    val name: String? = null,
    val helperObj: Any? = null,
    val rowType: String? = MultiRowType.GENERAL.name,
    val layoutResource: Int? = null
)

enum class MultiRowType {
    GENERAL, GENERAL_SPORT, VOD,
}