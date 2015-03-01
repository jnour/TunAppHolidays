package com.squareup.timessquare;

import android.content.Context;

import java.util.Date;

public interface CalendarCellDecorator {
  void decorate(CalendarCellView cellView, Date date,Context context);
}
