package com.doomadi.artist.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_2{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);
views.get("panel1").vw.setLeft((int)(0d));
views.get("panel1").vw.setWidth((int)((100d / 100 * width) - (0d)));
views.get("panel1").vw.setTop((int)((0d / 100 * height)));
views.get("panel1").vw.setHeight((int)((5d / 100 * height) - ((0d / 100 * height))));
views.get("imageview1").vw.setLeft((int)(0d));
views.get("imageview1").vw.setWidth((int)((100d / 100 * width) - (0d)));
views.get("imageview1").vw.setTop((int)((0d / 100 * height)));
views.get("imageview1").vw.setHeight((int)((5d / 100 * height) - ((0d / 100 * height))));
views.get("panel2").vw.setLeft((int)(0d));
views.get("panel2").vw.setWidth((int)((100d / 100 * width) - (0d)));
views.get("panel2").vw.setTop((int)((5d / 100 * height)));
views.get("panel2").vw.setHeight((int)((34d / 100 * height) - ((5d / 100 * height))));
views.get("button1").vw.setLeft((int)((1d / 100 * width)));
views.get("button1").vw.setWidth((int)((60d / 100 * width) - ((1d / 100 * width))));
views.get("button1").vw.setTop((int)((35d / 100 * height)));
views.get("button1").vw.setHeight((int)((54d / 100 * height) - ((35d / 100 * height))));
views.get("button2").vw.setLeft((int)((61d / 100 * width)));
views.get("button2").vw.setWidth((int)((99d / 100 * width) - ((61d / 100 * width))));
views.get("button2").vw.setTop((int)((35d / 100 * height)));
views.get("button2").vw.setHeight((int)((54d / 100 * height) - ((35d / 100 * height))));
views.get("button3").vw.setLeft((int)((1d / 100 * width)));
views.get("button3").vw.setWidth((int)((38d / 100 * width) - ((1d / 100 * width))));
views.get("button3").vw.setTop((int)((55d / 100 * height)));
views.get("button3").vw.setHeight((int)((74d / 100 * height) - ((55d / 100 * height))));
views.get("button4").vw.setLeft((int)((39d / 100 * width)));
views.get("button4").vw.setWidth((int)((99d / 100 * width) - ((39d / 100 * width))));
views.get("button4").vw.setTop((int)((55d / 100 * height)));
views.get("button4").vw.setHeight((int)((74d / 100 * height) - ((55d / 100 * height))));
views.get("button5").vw.setLeft((int)((1d / 100 * width)));
views.get("button5").vw.setWidth((int)((60d / 100 * width) - ((1d / 100 * width))));
views.get("button5").vw.setTop((int)((75d / 100 * height)));
views.get("button5").vw.setHeight((int)((94d / 100 * height) - ((75d / 100 * height))));
views.get("button6").vw.setLeft((int)((61d / 100 * width)));
views.get("button6").vw.setWidth((int)((99d / 100 * width) - ((61d / 100 * width))));
views.get("button6").vw.setTop((int)((75d / 100 * height)));
views.get("button6").vw.setHeight((int)((94d / 100 * height) - ((75d / 100 * height))));
views.get("imageview2").vw.setLeft((int)(0d));
views.get("imageview2").vw.setWidth((int)((100d / 100 * width) - (0d)));
views.get("imageview2").vw.setTop((int)((95d / 100 * height)));
views.get("imageview2").vw.setHeight((int)((100d / 100 * height) - ((95d / 100 * height))));

}
}