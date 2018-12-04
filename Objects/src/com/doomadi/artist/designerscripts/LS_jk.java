package com.doomadi.artist.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_jk{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);
views.get("imagev").vw.setLeft((int)((2d / 100 * width)));
views.get("imagev").vw.setWidth((int)((98.2d / 100 * width) - ((2d / 100 * width))));
views.get("imagev").vw.setTop((int)((2d / 100 * height)));
views.get("imagev").vw.setHeight((int)((75d / 100 * height) - ((2d / 100 * height))));
views.get("artistlabel").vw.setLeft((int)((2d / 100 * width)));
views.get("artistlabel").vw.setWidth((int)((98d / 100 * width) - ((2d / 100 * width))));
views.get("artistlabel").vw.setTop((int)((76d / 100 * height)));
views.get("artistlabel").vw.setHeight((int)((87d / 100 * height) - ((76d / 100 * height))));
views.get("namelabel").vw.setLeft((int)((2d / 100 * width)));
views.get("namelabel").vw.setWidth((int)((98d / 100 * width) - ((2d / 100 * width))));
views.get("namelabel").vw.setTop((int)((88d / 100 * height)));
views.get("namelabel").vw.setHeight((int)((99d / 100 * height) - ((88d / 100 * height))));
views.get("sahr1").vw.setLeft((int)((1d / 100 * width)));
views.get("sahr1").vw.setWidth((int)((33.5d / 100 * width) - ((1d / 100 * width))));
views.get("sahr1").vw.setTop((int)((65d / 100 * height)));
views.get("sahr1").vw.setHeight((int)((75d / 100 * height) - ((65d / 100 * height))));
views.get("number1").vw.setLeft((int)((33.5d / 100 * width)));
views.get("number1").vw.setWidth((int)((67d / 100 * width) - ((33.5d / 100 * width))));
views.get("number1").vw.setTop((int)((65d / 100 * height)));
views.get("number1").vw.setHeight((int)((75d / 100 * height) - ((65d / 100 * height))));
views.get("id1").vw.setLeft((int)((67d / 100 * width)));
views.get("id1").vw.setWidth((int)((99d / 100 * width) - ((67d / 100 * width))));
views.get("id1").vw.setTop((int)((65d / 100 * height)));
views.get("id1").vw.setHeight((int)((75d / 100 * height) - ((65d / 100 * height))));

}
}