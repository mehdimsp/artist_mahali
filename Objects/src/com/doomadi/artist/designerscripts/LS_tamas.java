package com.doomadi.artist.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_tamas{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);
views.get("panel1").vw.setLeft((int)(0d));
views.get("panel1").vw.setWidth((int)((100d / 100 * width) - (0d)));
views.get("panel1").vw.setTop((int)((0d / 100 * height)));
views.get("panel1").vw.setHeight((int)((9d / 100 * height) - ((0d / 100 * height))));
views.get("imageview1").vw.setLeft((int)(0d));
views.get("imageview1").vw.setWidth((int)((25d / 100 * width) - (0d)));
views.get("imageview1").vw.setTop((int)((9d / 100 * height)));
views.get("imageview1").vw.setHeight((int)((27d / 100 * height) - ((9d / 100 * height))));
views.get("imageview2").vw.setLeft((int)((25d / 100 * width)));
views.get("imageview2").vw.setWidth((int)((50d / 100 * width) - ((25d / 100 * width))));
views.get("imageview2").vw.setTop((int)((9d / 100 * height)));
views.get("imageview2").vw.setHeight((int)((27d / 100 * height) - ((9d / 100 * height))));
views.get("imageview3").vw.setLeft((int)((50d / 100 * width)));
views.get("imageview3").vw.setWidth((int)((75d / 100 * width) - ((50d / 100 * width))));
views.get("imageview3").vw.setTop((int)((9d / 100 * height)));
views.get("imageview3").vw.setHeight((int)((27d / 100 * height) - ((9d / 100 * height))));
views.get("imageview4").vw.setLeft((int)((75d / 100 * width)));
views.get("imageview4").vw.setWidth((int)((100d / 100 * width) - ((75d / 100 * width))));
views.get("imageview4").vw.setTop((int)((9d / 100 * height)));
views.get("imageview4").vw.setHeight((int)((27d / 100 * height) - ((9d / 100 * height))));
views.get("label1").vw.setLeft((int)((0d / 100 * width)));
views.get("label1").vw.setWidth((int)((100d / 100 * width) - ((0d / 100 * width))));
views.get("label1").vw.setTop((int)((27.5d / 100 * height)));
views.get("label1").vw.setHeight((int)((38d / 100 * height) - ((27.5d / 100 * height))));
views.get("button1").vw.setLeft((int)(0d));
views.get("button1").vw.setWidth((int)((33d / 100 * width) - (0d)));
views.get("button1").vw.setTop((int)((38d / 100 * height)));
views.get("button1").vw.setHeight((int)((60d / 100 * height) - ((38d / 100 * height))));
views.get("button2").vw.setLeft((int)((33d / 100 * width)));
views.get("button2").vw.setWidth((int)((66d / 100 * width) - ((33d / 100 * width))));
views.get("button2").vw.setTop((int)((38d / 100 * height)));
views.get("button2").vw.setHeight((int)((60d / 100 * height) - ((38d / 100 * height))));
views.get("button3").vw.setLeft((int)((66d / 100 * width)));
views.get("button3").vw.setWidth((int)((100d / 100 * width) - ((66d / 100 * width))));
views.get("button3").vw.setTop((int)((38d / 100 * height)));
views.get("button3").vw.setHeight((int)((60d / 100 * height) - ((38d / 100 * height))));
views.get("label2").vw.setLeft((int)((0d / 100 * width)));
views.get("label2").vw.setWidth((int)((100d / 100 * width) - ((0d / 100 * width))));
views.get("label2").vw.setTop((int)((60d / 100 * height)));
views.get("label2").vw.setHeight((int)((75d / 100 * height) - ((60d / 100 * height))));
views.get("label3").vw.setLeft((int)((0d / 100 * width)));
views.get("label3").vw.setWidth((int)((100d / 100 * width) - ((0d / 100 * width))));
views.get("label3").vw.setTop((int)((75d / 100 * height)));
views.get("label3").vw.setHeight((int)((100d / 100 * height) - ((75d / 100 * height))));

}
}