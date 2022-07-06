package com.example.model2.util;

import com.example.model2.dto.CalendarDto;

import java.util.List;

public class CallUtil {

    public boolean nvl(String msg) {
        return msg == null || msg.trim().equals("") ? true : false;
    }

    public String two(String msg) {
        return msg.trim().length() < 2 ? "0" + msg.trim() : msg.trim();
    }

    public String callist(int year, int month, int day) {
        String str = "";

        str += String.format("$nbsp:<a href='callist.jsp?year=%d&month=%d&day=%d'>", year, month, day);
        str += String.format("%2d", day);
        str += String.format("</a>");

        return str;

    }

    public String showPen(int year, int month, int day) {
        String str = "";

        String image = "<img src='image/pen2.png' width='18px' height='18px'>";
        str = String.format("<a href='calwrite.jsp?year=%d&month=%d&day=%d'>%s</a>", year, month, day, image);

        return str;

    }

    public String dot3(String msg) {
        String str = "";
        if (msg.length() >= 10) {
            str = msg.substring(0, 10);
            str += "...";
        } else {
            str = msg.trim();
        }

        return str;

    }

    public String makeTable(int year, int month, int day, List<CalendarDto> list) {
        String str = "";

        String dates = (year + "") + two(month + "") + two(day + "");

        str += "<table>";
        for (CalendarDto dto : list) {
            if (dto.getRdate().substring(0, 8).equals(dates)) {
                str += "<tr>" +
                        "<td style='padding:0px;border:1px;background-color:white;border-color:blue;radius:3'>" +
                        "<a href='caldetail.jsp?seq=" + dto.getSeq() + "'>" +
                        "<font style='font-size:10px'>" +
                        dot3(dto.getTitle()) +
                        "</font></a>";


                str += "</td>";
                str += "</tr>";


            }


        }
        str += "</table>";
        return str;
    }

}
