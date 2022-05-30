package com.example.singlerowcalendar.calendar

import java.util.*

/**
 * Callback used for binding views to singlerowcalendar
 * @author Michal Švec - misosvec01@gmail.com
 * @since v1.0.0
 */

interface CalendarViewManager {

    /**
     * @param position of specific view in the singlerowcalendar
     * @param date of specific view in the singlerowcalendar
     * @param isSelected returns true if item in the singlerowcalendar is selected else returns false
     * @return a resource id for singlerowcalendar itemView
     */
    fun setCalendarViewResourceId(position: Int, date: Date, isSelected: Boolean): Int

    /**
     * @param holder is CalendarViewHolder used in singlerowcalendar
     * @param date value of singlerowcalendar item
     * @param position of specific view in the singlerowcalendar
     * @param isSelected returns true if item in the singlerowcalendar is selected else returns false
     */
    fun bindDataToCalendarView(holder: SingleRowCalendarAdapter.CalendarViewHolder, date: Date, position: Int, isSelected: Boolean)

}