package com.doomadi.artist.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_layout2{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);
views.get("scrollview1").vw.setLeft((int)(0d));
views.get("scrollview1").vw.setWidth((int)((100d / 100 * width) - (0d)));
views.get("scrollview1").vw.setTop((int)((23.2d / 100 * height)));
views.get("scrollview1").vw.setHeight((int)((100d / 100 * height) - ((23.2d / 100 * height))));
views.get("panel1").vw.setLeft((int)(0d));
views.get("panel1").vw.setWidth((int)((100d / 100 * width) - (0d)));
views.get("panel1").vw.setTop((int)(0d));
views.get("panel1").vw.setHeight((int)((100d / 100 * height) - (0d)));
views.get("label3").vw.setLeft((int)((65d / 100 * width)));
views.get("label3").vw.setWidth((int)((100d / 100 * width) - ((65d / 100 * width))));
views.get("label3").vw.setTop((int)(0d));
views.get("label3").vw.setHeight((int)((8d / 100 * height) - (0d)));
views.get("label1").vw.setLeft((int)((0d / 100 * width)));
views.get("label1").vw.setWidth((int)((70d / 100 * width) - ((0d / 100 * width))));
views.get("label1").vw.setTop((int)(0d));
views.get("label1").vw.setHeight((int)((8d / 100 * height) - (0d)));
views.get("panel2").vw.setLeft((int)(0d));
views.get("panel2").vw.setWidth((int)((100d / 100 * width) - (0d)));
views.get("panel2").vw.setTop((int)(0d));
views.get("panel2").vw.setHeight((int)((8d / 100 * height) - (0d)));
views.get("imageview1").vw.setLeft((int)(0d));
views.get("imageview1").vw.setWidth((int)((100d / 100 * width) - (0d)));
views.get("imageview1").vw.setTop((int)(0d));
views.get("imageview1").vw.setHeight((int)((60d / 100 * height) - (0d)));
views.get("xname").vw.setLeft((int)(0d));
views.get("xname").vw.setWidth((int)((100d / 100 * width) - (0d)));
views.get("xname").vw.setTop((int)((60.5d / 100 * height)));
views.get("xname").vw.setHeight((int)((70d / 100 * height) - ((60.5d / 100 * height))));
views.get("play").vw.setLeft((int)((0.5d / 100 * width)));
views.get("play").vw.setWidth((int)((17d / 100 * width) - ((0.5d / 100 * width))));
views.get("play").vw.setTop((int)((70.5d / 100 * height)));
views.get("play").vw.setHeight((int)((81d / 100 * height) - ((70.5d / 100 * height))));
views.get("pause").vw.setLeft((int)((17.5d / 100 * width)));
views.get("pause").vw.setWidth((int)((34d / 100 * width) - ((17.5d / 100 * width))));
views.get("pause").vw.setTop((int)((70.5d / 100 * height)));
views.get("pause").vw.setHeight((int)((81d / 100 * height) - ((70.5d / 100 * height))));
views.get("seekbar1").vw.setLeft((int)((34.5d / 100 * width)));
views.get("seekbar1").vw.setWidth((int)((99d / 100 * width) - ((34.5d / 100 * width))));
views.get("seekbar1").vw.setTop((int)((72.5d / 100 * height)));
views.get("seekbar1").vw.setHeight((int)((81d / 100 * height) - ((72.5d / 100 * height))));
views.get("time").vw.setLeft((int)((65d / 100 * width)));
views.get("time").vw.setWidth((int)((99d / 100 * width) - ((65d / 100 * width))));
views.get("time").vw.setTop((int)((82d / 100 * height)));
views.get("time").vw.setHeight((int)((90d / 100 * height) - ((82d / 100 * height))));
views.get("imageview3").vw.setLeft((int)(0d));
views.get("imageview3").vw.setWidth((int)((25d / 100 * width) - (0d)));
views.get("imageview3").vw.setTop((int)((8d / 100 * height)));
views.get("imageview3").vw.setHeight((int)((23d / 100 * height) - ((8d / 100 * height))));
views.get("imageview4").vw.setLeft((int)((25d / 100 * width)));
views.get("imageview4").vw.setWidth((int)((50d / 100 * width) - ((25d / 100 * width))));
views.get("imageview4").vw.setTop((int)((8d / 100 * height)));
views.get("imageview4").vw.setHeight((int)((23d / 100 * height) - ((8d / 100 * height))));
views.get("imageview5").vw.setLeft((int)((50d / 100 * width)));
views.get("imageview5").vw.setWidth((int)((75d / 100 * width) - ((50d / 100 * width))));
views.get("imageview5").vw.setTop((int)((8d / 100 * height)));
views.get("imageview5").vw.setHeight((int)((23d / 100 * height) - ((8d / 100 * height))));
views.get("imageview6").vw.setLeft((int)((75d / 100 * width)));
views.get("imageview6").vw.setWidth((int)((100d / 100 * width) - ((75d / 100 * width))));
views.get("imageview6").vw.setTop((int)((8d / 100 * height)));
views.get("imageview6").vw.setHeight((int)((23d / 100 * height) - ((8d / 100 * height))));
views.get("button1").vw.setLeft((int)((1d / 100 * width)));
views.get("button1").vw.setWidth((int)((21d / 100 * width) - ((1d / 100 * width))));
views.get("button1").vw.setTop((int)((83d / 100 * height)));
views.get("button1").vw.setHeight((int)((90d / 100 * height) - ((83d / 100 * height))));
views.get("button2").vw.setLeft((int)((22d / 100 * width)));
views.get("button2").vw.setWidth((int)((50d / 100 * width) - ((22d / 100 * width))));
views.get("button2").vw.setTop((int)((83d / 100 * height)));
views.get("button2").vw.setHeight((int)((90d / 100 * height) - ((83d / 100 * height))));
views.get("imageview2").vw.setLeft((int)(0d));
views.get("imageview2").vw.setWidth((int)((15d / 100 * width) - (0d)));
views.get("imageview2").vw.setTop((int)((91d / 100 * height)));
views.get("imageview2").vw.setHeight((int)((99d / 100 * height) - ((91d / 100 * height))));
views.get("label2").vw.setLeft((int)((16d / 100 * width)));
views.get("label2").vw.setWidth((int)((31d / 100 * width) - ((16d / 100 * width))));
views.get("label2").vw.setTop((int)((91d / 100 * height)));
views.get("label2").vw.setHeight((int)((99d / 100 * height) - ((91d / 100 * height))));
views.get("button5").vw.setLeft((int)((32d / 100 * width)));
views.get("button5").vw.setWidth((int)((63d / 100 * width) - ((32d / 100 * width))));
views.get("button5").vw.setTop((int)((91d / 100 * height)));
views.get("button5").vw.setHeight((int)((99d / 100 * height) - ((91d / 100 * height))));
views.get("button4").vw.setLeft((int)((65d / 100 * width)));
views.get("button4").vw.setWidth((int)((95d / 100 * width) - ((65d / 100 * width))));
views.get("button4").vw.setTop((int)((91d / 100 * height)));
views.get("button4").vw.setHeight((int)((99d / 100 * height) - ((91d / 100 * height))));

}
}