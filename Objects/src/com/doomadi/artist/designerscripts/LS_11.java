package com.doomadi.artist.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_11{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
//BA.debugLineNum = 2;BA.debugLine="AutoScaleAll"[11/General script]
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);
//BA.debugLineNum = 3;BA.debugLine="Panel1.SetLeftAndRight(0,100%x)"[11/General script]
views.get("panel1").vw.setLeft((int)(0d));
views.get("panel1").vw.setWidth((int)((100d / 100 * width) - (0d)));
//BA.debugLineNum = 4;BA.debugLine="Panel1.SetTopAndBottom(0%y,10%y)"[11/General script]
views.get("panel1").vw.setTop((int)((0d / 100 * height)));
views.get("panel1").vw.setHeight((int)((10d / 100 * height) - ((0d / 100 * height))));
//BA.debugLineNum = 5;BA.debugLine="ImageView1.SetLeftAndRight(0,25%x)"[11/General script]
views.get("imageview1").vw.setLeft((int)(0d));
views.get("imageview1").vw.setWidth((int)((25d / 100 * width) - (0d)));
//BA.debugLineNum = 6;BA.debugLine="ImageView1.SetTopAndBottom(10%y,29%y)"[11/General script]
views.get("imageview1").vw.setTop((int)((10d / 100 * height)));
views.get("imageview1").vw.setHeight((int)((29d / 100 * height) - ((10d / 100 * height))));
//BA.debugLineNum = 7;BA.debugLine="ImageView2.SetLeftAndRight(25%x,50%x)"[11/General script]
views.get("imageview2").vw.setLeft((int)((25d / 100 * width)));
views.get("imageview2").vw.setWidth((int)((50d / 100 * width) - ((25d / 100 * width))));
//BA.debugLineNum = 8;BA.debugLine="ImageView2.SetTopAndBottom(10%y,29%y)"[11/General script]
views.get("imageview2").vw.setTop((int)((10d / 100 * height)));
views.get("imageview2").vw.setHeight((int)((29d / 100 * height) - ((10d / 100 * height))));
//BA.debugLineNum = 9;BA.debugLine="ImageView3.SetLeftAndRight(50%x,75%x)"[11/General script]
views.get("imageview3").vw.setLeft((int)((50d / 100 * width)));
views.get("imageview3").vw.setWidth((int)((75d / 100 * width) - ((50d / 100 * width))));
//BA.debugLineNum = 10;BA.debugLine="ImageView3.SetTopAndBottom(10%y,29%y)"[11/General script]
views.get("imageview3").vw.setTop((int)((10d / 100 * height)));
views.get("imageview3").vw.setHeight((int)((29d / 100 * height) - ((10d / 100 * height))));
//BA.debugLineNum = 11;BA.debugLine="ImageView4.SetLeftAndRight(75%x,100%x)"[11/General script]
views.get("imageview4").vw.setLeft((int)((75d / 100 * width)));
views.get("imageview4").vw.setWidth((int)((100d / 100 * width) - ((75d / 100 * width))));
//BA.debugLineNum = 12;BA.debugLine="ImageView4.SetTopAndBottom(10%y,29%y)"[11/General script]
views.get("imageview4").vw.setTop((int)((10d / 100 * height)));
views.get("imageview4").vw.setHeight((int)((29d / 100 * height) - ((10d / 100 * height))));
//BA.debugLineNum = 13;BA.debugLine="Label1.SetLeftAndRight(75%x,100%x)"[11/General script]
views.get("label1").vw.setLeft((int)((75d / 100 * width)));
views.get("label1").vw.setWidth((int)((100d / 100 * width) - ((75d / 100 * width))));
//BA.debugLineNum = 14;BA.debugLine="Label1.SetTopAndBottom(29%y,38%y)"[11/General script]
views.get("label1").vw.setTop((int)((29d / 100 * height)));
views.get("label1").vw.setHeight((int)((38d / 100 * height) - ((29d / 100 * height))));
//BA.debugLineNum = 15;BA.debugLine="Label2.SetLeftAndRight(50%x,75%x)"[11/General script]
views.get("label2").vw.setLeft((int)((50d / 100 * width)));
views.get("label2").vw.setWidth((int)((75d / 100 * width) - ((50d / 100 * width))));
//BA.debugLineNum = 16;BA.debugLine="Label2.SetTopAndBottom(29%y,38%y)"[11/General script]
views.get("label2").vw.setTop((int)((29d / 100 * height)));
views.get("label2").vw.setHeight((int)((38d / 100 * height) - ((29d / 100 * height))));
//BA.debugLineNum = 17;BA.debugLine="Label3.SetLeftAndRight(25%x,50%x)"[11/General script]
views.get("label3").vw.setLeft((int)((25d / 100 * width)));
views.get("label3").vw.setWidth((int)((50d / 100 * width) - ((25d / 100 * width))));
//BA.debugLineNum = 18;BA.debugLine="Label3.SetTopAndBottom(29%y,38%y)"[11/General script]
views.get("label3").vw.setTop((int)((29d / 100 * height)));
views.get("label3").vw.setHeight((int)((38d / 100 * height) - ((29d / 100 * height))));
//BA.debugLineNum = 19;BA.debugLine="Label4.SetLeftAndRight(0%x,25%x)"[11/General script]
views.get("label4").vw.setLeft((int)((0d / 100 * width)));
views.get("label4").vw.setWidth((int)((25d / 100 * width) - ((0d / 100 * width))));
//BA.debugLineNum = 20;BA.debugLine="Label4.SetTopAndBottom(29%y,38%y)"[11/General script]
views.get("label4").vw.setTop((int)((29d / 100 * height)));
views.get("label4").vw.setHeight((int)((38d / 100 * height) - ((29d / 100 * height))));
//BA.debugLineNum = 21;BA.debugLine="Button1.SetLeftAndRight(0,25%x)"[11/General script]
views.get("button1").vw.setLeft((int)(0d));
views.get("button1").vw.setWidth((int)((25d / 100 * width) - (0d)));
//BA.debugLineNum = 22;BA.debugLine="Button1.SetTopAndBottom(38%y,57%y)"[11/General script]
views.get("button1").vw.setTop((int)((38d / 100 * height)));
views.get("button1").vw.setHeight((int)((57d / 100 * height) - ((38d / 100 * height))));
//BA.debugLineNum = 23;BA.debugLine="Button2.SetLeftAndRight(25%x,50%x)"[11/General script]
views.get("button2").vw.setLeft((int)((25d / 100 * width)));
views.get("button2").vw.setWidth((int)((50d / 100 * width) - ((25d / 100 * width))));
//BA.debugLineNum = 24;BA.debugLine="Button2.SetTopAndBottom(38%y,57%y)"[11/General script]
views.get("button2").vw.setTop((int)((38d / 100 * height)));
views.get("button2").vw.setHeight((int)((57d / 100 * height) - ((38d / 100 * height))));
//BA.debugLineNum = 25;BA.debugLine="Button3.SetLeftAndRight(50%x,75%x)"[11/General script]
views.get("button3").vw.setLeft((int)((50d / 100 * width)));
views.get("button3").vw.setWidth((int)((75d / 100 * width) - ((50d / 100 * width))));
//BA.debugLineNum = 26;BA.debugLine="Button3.SetTopAndBottom(38%y,57%y)"[11/General script]
views.get("button3").vw.setTop((int)((38d / 100 * height)));
views.get("button3").vw.setHeight((int)((57d / 100 * height) - ((38d / 100 * height))));
//BA.debugLineNum = 27;BA.debugLine="Button4.SetLeftAndRight(75%x,100%x)"[11/General script]
views.get("button4").vw.setLeft((int)((75d / 100 * width)));
views.get("button4").vw.setWidth((int)((100d / 100 * width) - ((75d / 100 * width))));
//BA.debugLineNum = 28;BA.debugLine="Button4.SetTopAndBottom(38%y,57%y)"[11/General script]
views.get("button4").vw.setTop((int)((38d / 100 * height)));
views.get("button4").vw.setHeight((int)((57d / 100 * height) - ((38d / 100 * height))));
//BA.debugLineNum = 29;BA.debugLine="Button5.SetLeftAndRight(0,25%x)"[11/General script]
views.get("button5").vw.setLeft((int)(0d));
views.get("button5").vw.setWidth((int)((25d / 100 * width) - (0d)));
//BA.debugLineNum = 30;BA.debugLine="Button5.SetTopAndBottom(57%y,76%y)"[11/General script]
views.get("button5").vw.setTop((int)((57d / 100 * height)));
views.get("button5").vw.setHeight((int)((76d / 100 * height) - ((57d / 100 * height))));
//BA.debugLineNum = 31;BA.debugLine="Button6.SetLeftAndRight(25%x,50%x)"[11/General script]
views.get("button6").vw.setLeft((int)((25d / 100 * width)));
views.get("button6").vw.setWidth((int)((50d / 100 * width) - ((25d / 100 * width))));
//BA.debugLineNum = 32;BA.debugLine="Button6.SetTopAndBottom(57%y,76%y)"[11/General script]
views.get("button6").vw.setTop((int)((57d / 100 * height)));
views.get("button6").vw.setHeight((int)((76d / 100 * height) - ((57d / 100 * height))));
//BA.debugLineNum = 33;BA.debugLine="Button7.SetLeftAndRight(50%x,75%x)"[11/General script]
views.get("button7").vw.setLeft((int)((50d / 100 * width)));
views.get("button7").vw.setWidth((int)((75d / 100 * width) - ((50d / 100 * width))));
//BA.debugLineNum = 34;BA.debugLine="Button7.SetTopAndBottom(57%y,76%y)"[11/General script]
views.get("button7").vw.setTop((int)((57d / 100 * height)));
views.get("button7").vw.setHeight((int)((76d / 100 * height) - ((57d / 100 * height))));
//BA.debugLineNum = 35;BA.debugLine="Button8.SetLeftAndRight(75%x,100%x)"[11/General script]
views.get("button8").vw.setLeft((int)((75d / 100 * width)));
views.get("button8").vw.setWidth((int)((100d / 100 * width) - ((75d / 100 * width))));
//BA.debugLineNum = 36;BA.debugLine="Button8.SetTopAndBottom(57%y,76%y)"[11/General script]
views.get("button8").vw.setTop((int)((57d / 100 * height)));
views.get("button8").vw.setHeight((int)((76d / 100 * height) - ((57d / 100 * height))));
//BA.debugLineNum = 37;BA.debugLine="Button9.SetLeftAndRight(0,25%x)"[11/General script]
views.get("button9").vw.setLeft((int)(0d));
views.get("button9").vw.setWidth((int)((25d / 100 * width) - (0d)));
//BA.debugLineNum = 38;BA.debugLine="Button9.SetTopAndBottom(76%y,95%y)"[11/General script]
views.get("button9").vw.setTop((int)((76d / 100 * height)));
views.get("button9").vw.setHeight((int)((95d / 100 * height) - ((76d / 100 * height))));
//BA.debugLineNum = 39;BA.debugLine="Button10.SetLeftAndRight(25%x,50%x)"[11/General script]
views.get("button10").vw.setLeft((int)((25d / 100 * width)));
views.get("button10").vw.setWidth((int)((50d / 100 * width) - ((25d / 100 * width))));
//BA.debugLineNum = 40;BA.debugLine="Button10.SetTopAndBottom(76%y,95%y)"[11/General script]
views.get("button10").vw.setTop((int)((76d / 100 * height)));
views.get("button10").vw.setHeight((int)((95d / 100 * height) - ((76d / 100 * height))));
//BA.debugLineNum = 41;BA.debugLine="Button11.SetLeftAndRight(50%x,75%x)"[11/General script]
views.get("button11").vw.setLeft((int)((50d / 100 * width)));
views.get("button11").vw.setWidth((int)((75d / 100 * width) - ((50d / 100 * width))));
//BA.debugLineNum = 42;BA.debugLine="Button11.SetTopAndBottom(76%y,95%y)"[11/General script]
views.get("button11").vw.setTop((int)((76d / 100 * height)));
views.get("button11").vw.setHeight((int)((95d / 100 * height) - ((76d / 100 * height))));
//BA.debugLineNum = 43;BA.debugLine="Button12.SetLeftAndRight(75%x,100%x)"[11/General script]
views.get("button12").vw.setLeft((int)((75d / 100 * width)));
views.get("button12").vw.setWidth((int)((100d / 100 * width) - ((75d / 100 * width))));
//BA.debugLineNum = 44;BA.debugLine="Button12.SetTopAndBottom(76%y,95%y)"[11/General script]
views.get("button12").vw.setTop((int)((76d / 100 * height)));
views.get("button12").vw.setHeight((int)((95d / 100 * height) - ((76d / 100 * height))));
//BA.debugLineNum = 45;BA.debugLine="Button13.SetLeftAndRight(0,25%x)"[11/General script]
views.get("button13").vw.setLeft((int)(0d));
views.get("button13").vw.setWidth((int)((25d / 100 * width) - (0d)));
//BA.debugLineNum = 46;BA.debugLine="Button13.SetTopAndBottom(95%y,114%y)"[11/General script]
views.get("button13").vw.setTop((int)((95d / 100 * height)));
views.get("button13").vw.setHeight((int)((114d / 100 * height) - ((95d / 100 * height))));
//BA.debugLineNum = 47;BA.debugLine="Button14.SetLeftAndRight(25%x,50%x)"[11/General script]
views.get("button14").vw.setLeft((int)((25d / 100 * width)));
views.get("button14").vw.setWidth((int)((50d / 100 * width) - ((25d / 100 * width))));
//BA.debugLineNum = 48;BA.debugLine="Button14.SetTopAndBottom(95%y,114%y)"[11/General script]
views.get("button14").vw.setTop((int)((95d / 100 * height)));
views.get("button14").vw.setHeight((int)((114d / 100 * height) - ((95d / 100 * height))));
//BA.debugLineNum = 49;BA.debugLine="Button15.SetLeftAndRight(50%x,75%x)"[11/General script]
views.get("button15").vw.setLeft((int)((50d / 100 * width)));
views.get("button15").vw.setWidth((int)((75d / 100 * width) - ((50d / 100 * width))));
//BA.debugLineNum = 50;BA.debugLine="Button15.SetTopAndBottom(95%y,114%y)"[11/General script]
views.get("button15").vw.setTop((int)((95d / 100 * height)));
views.get("button15").vw.setHeight((int)((114d / 100 * height) - ((95d / 100 * height))));
//BA.debugLineNum = 51;BA.debugLine="Button16.SetLeftAndRight(75%x,100%x)"[11/General script]
views.get("button16").vw.setLeft((int)((75d / 100 * width)));
views.get("button16").vw.setWidth((int)((100d / 100 * width) - ((75d / 100 * width))));
//BA.debugLineNum = 52;BA.debugLine="Button16.SetTopAndBottom(95%y,114%y)"[11/General script]
views.get("button16").vw.setTop((int)((95d / 100 * height)));
views.get("button16").vw.setHeight((int)((114d / 100 * height) - ((95d / 100 * height))));
//BA.debugLineNum = 53;BA.debugLine="Button17.SetLeftAndRight(0,25%x)"[11/General script]
views.get("button17").vw.setLeft((int)(0d));
views.get("button17").vw.setWidth((int)((25d / 100 * width) - (0d)));
//BA.debugLineNum = 54;BA.debugLine="Button17.SetTopAndBottom(114%y,133%y)"[11/General script]
views.get("button17").vw.setTop((int)((114d / 100 * height)));
views.get("button17").vw.setHeight((int)((133d / 100 * height) - ((114d / 100 * height))));
//BA.debugLineNum = 55;BA.debugLine="Button18.SetLeftAndRight(25%x,50%x)"[11/General script]
views.get("button18").vw.setLeft((int)((25d / 100 * width)));
views.get("button18").vw.setWidth((int)((50d / 100 * width) - ((25d / 100 * width))));
//BA.debugLineNum = 56;BA.debugLine="Button18.SetTopAndBottom(114%y,133%y)"[11/General script]
views.get("button18").vw.setTop((int)((114d / 100 * height)));
views.get("button18").vw.setHeight((int)((133d / 100 * height) - ((114d / 100 * height))));
//BA.debugLineNum = 57;BA.debugLine="Button19.SetLeftAndRight(50%x,75%x)"[11/General script]
views.get("button19").vw.setLeft((int)((50d / 100 * width)));
views.get("button19").vw.setWidth((int)((75d / 100 * width) - ((50d / 100 * width))));
//BA.debugLineNum = 58;BA.debugLine="Button19.SetTopAndBottom(114%y,133%y)"[11/General script]
views.get("button19").vw.setTop((int)((114d / 100 * height)));
views.get("button19").vw.setHeight((int)((133d / 100 * height) - ((114d / 100 * height))));
//BA.debugLineNum = 59;BA.debugLine="Button20.SetLeftAndRight(75%x,100%x)"[11/General script]
views.get("button20").vw.setLeft((int)((75d / 100 * width)));
views.get("button20").vw.setWidth((int)((100d / 100 * width) - ((75d / 100 * width))));
//BA.debugLineNum = 60;BA.debugLine="Button20.SetTopAndBottom(114%y,133%y)"[11/General script]
views.get("button20").vw.setTop((int)((114d / 100 * height)));
views.get("button20").vw.setHeight((int)((133d / 100 * height) - ((114d / 100 * height))));
//BA.debugLineNum = 61;BA.debugLine="Button21.SetLeftAndRight(0,25%x)"[11/General script]
views.get("button21").vw.setLeft((int)(0d));
views.get("button21").vw.setWidth((int)((25d / 100 * width) - (0d)));
//BA.debugLineNum = 62;BA.debugLine="Button21.SetTopAndBottom(133%y,152%y)"[11/General script]
views.get("button21").vw.setTop((int)((133d / 100 * height)));
views.get("button21").vw.setHeight((int)((152d / 100 * height) - ((133d / 100 * height))));
//BA.debugLineNum = 63;BA.debugLine="Button22.SetLeftAndRight(25%x,50%x)"[11/General script]
views.get("button22").vw.setLeft((int)((25d / 100 * width)));
views.get("button22").vw.setWidth((int)((50d / 100 * width) - ((25d / 100 * width))));
//BA.debugLineNum = 64;BA.debugLine="Button22.SetTopAndBottom(133%y,152%y)"[11/General script]
views.get("button22").vw.setTop((int)((133d / 100 * height)));
views.get("button22").vw.setHeight((int)((152d / 100 * height) - ((133d / 100 * height))));
//BA.debugLineNum = 65;BA.debugLine="Button23.SetLeftAndRight(50%x,75%x)"[11/General script]
views.get("button23").vw.setLeft((int)((50d / 100 * width)));
views.get("button23").vw.setWidth((int)((75d / 100 * width) - ((50d / 100 * width))));
//BA.debugLineNum = 66;BA.debugLine="Button23.SetTopAndBottom(133%y,152%y)"[11/General script]
views.get("button23").vw.setTop((int)((133d / 100 * height)));
views.get("button23").vw.setHeight((int)((152d / 100 * height) - ((133d / 100 * height))));
//BA.debugLineNum = 67;BA.debugLine="Button24.SetLeftAndRight(75%x,100%x)"[11/General script]
views.get("button24").vw.setLeft((int)((75d / 100 * width)));
views.get("button24").vw.setWidth((int)((100d / 100 * width) - ((75d / 100 * width))));
//BA.debugLineNum = 68;BA.debugLine="Button24.SetTopAndBottom(133%y,152%y)"[11/General script]
views.get("button24").vw.setTop((int)((133d / 100 * height)));
views.get("button24").vw.setHeight((int)((152d / 100 * height) - ((133d / 100 * height))));
//BA.debugLineNum = 69;BA.debugLine="Button25.SetLeftAndRight(0,25%x)"[11/General script]
views.get("button25").vw.setLeft((int)(0d));
views.get("button25").vw.setWidth((int)((25d / 100 * width) - (0d)));
//BA.debugLineNum = 70;BA.debugLine="Button25.SetTopAndBottom(152%y,171%y)"[11/General script]
views.get("button25").vw.setTop((int)((152d / 100 * height)));
views.get("button25").vw.setHeight((int)((171d / 100 * height) - ((152d / 100 * height))));
//BA.debugLineNum = 71;BA.debugLine="Button26.SetLeftAndRight(25%x,50%x)"[11/General script]
views.get("button26").vw.setLeft((int)((25d / 100 * width)));
views.get("button26").vw.setWidth((int)((50d / 100 * width) - ((25d / 100 * width))));
//BA.debugLineNum = 72;BA.debugLine="Button26.SetTopAndBottom(152%y,171%y)"[11/General script]
views.get("button26").vw.setTop((int)((152d / 100 * height)));
views.get("button26").vw.setHeight((int)((171d / 100 * height) - ((152d / 100 * height))));
//BA.debugLineNum = 73;BA.debugLine="Button27.SetLeftAndRight(50%x,75%x)"[11/General script]
views.get("button27").vw.setLeft((int)((50d / 100 * width)));
views.get("button27").vw.setWidth((int)((75d / 100 * width) - ((50d / 100 * width))));
//BA.debugLineNum = 74;BA.debugLine="Button27.SetTopAndBottom(152%y,171%y)"[11/General script]
views.get("button27").vw.setTop((int)((152d / 100 * height)));
views.get("button27").vw.setHeight((int)((171d / 100 * height) - ((152d / 100 * height))));
//BA.debugLineNum = 75;BA.debugLine="Button28.SetLeftAndRight(75%x,100%x)"[11/General script]
views.get("button28").vw.setLeft((int)((75d / 100 * width)));
views.get("button28").vw.setWidth((int)((100d / 100 * width) - ((75d / 100 * width))));
//BA.debugLineNum = 76;BA.debugLine="Button28.SetTopAndBottom(152%y,171%y)"[11/General script]
views.get("button28").vw.setTop((int)((152d / 100 * height)));
views.get("button28").vw.setHeight((int)((171d / 100 * height) - ((152d / 100 * height))));
//BA.debugLineNum = 77;BA.debugLine="Button29.SetLeftAndRight(0,25%x)"[11/General script]
views.get("button29").vw.setLeft((int)(0d));
views.get("button29").vw.setWidth((int)((25d / 100 * width) - (0d)));
//BA.debugLineNum = 78;BA.debugLine="Button29.SetTopAndBottom(171%y,190%y)"[11/General script]
views.get("button29").vw.setTop((int)((171d / 100 * height)));
views.get("button29").vw.setHeight((int)((190d / 100 * height) - ((171d / 100 * height))));
//BA.debugLineNum = 79;BA.debugLine="Button30.SetLeftAndRight(25%x,50%x)"[11/General script]
views.get("button30").vw.setLeft((int)((25d / 100 * width)));
views.get("button30").vw.setWidth((int)((50d / 100 * width) - ((25d / 100 * width))));
//BA.debugLineNum = 80;BA.debugLine="Button30.SetTopAndBottom(171%y,190%y)"[11/General script]
views.get("button30").vw.setTop((int)((171d / 100 * height)));
views.get("button30").vw.setHeight((int)((190d / 100 * height) - ((171d / 100 * height))));
//BA.debugLineNum = 81;BA.debugLine="Button31.SetLeftAndRight(50%x,75%x)"[11/General script]
views.get("button31").vw.setLeft((int)((50d / 100 * width)));
views.get("button31").vw.setWidth((int)((75d / 100 * width) - ((50d / 100 * width))));
//BA.debugLineNum = 82;BA.debugLine="Button31.SetTopAndBottom(171%y,190%y)"[11/General script]
views.get("button31").vw.setTop((int)((171d / 100 * height)));
views.get("button31").vw.setHeight((int)((190d / 100 * height) - ((171d / 100 * height))));

}
}