﻿B4A=true
Group=Default Group
ModulesStructureVersion=1
Type=Activity
Version=7.8
@EndOfDesignText@
#Region  Activity Attributes 
	#FullScreen: False
	#IncludeTitle: false
#End Region

Sub Process_Globals
	
End Sub

Sub Globals
	Private ScrollView1 As ScrollView
	Private Button1, Button2, Button3, Button4 , Button5 , Button6 , Button7 , Button8 As Button
End Sub

Sub Activity_Create(FirstTime As Boolean)
	Activity.LoadLayout("scrol")
	ScrollView1.Panel.LoadLayout("ahang")
	ScrollView1.Panel.Height = 120%y
	ostanha.Button(Button1,"علی براتی")
	ostanha.Button(Button2,"محسن دولت")
	ostanha.Button(Button3,"حسین عاشقی")
	ostanha.Button(Button4,"دل افکار")
	ostanha.Button(Button5,"باغشنی")
	ostanha.Button(Button6,"حسین حیدری")
	ostanha.Button(Button7,"اصغر باکردار")
	
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub
Sub ImageView1_Click
	StartActivity(Main)
	Activity.Finish
End Sub


Sub ImageView2_Click
	StartActivity(doomadi)
	Activity.Finish
End Sub
Sub ImageView3_Click
	
	
	StartActivity(ostan)
	Activity.Finish

End Sub
Sub ImageView4_Click
	
	
	StartActivity(tamas)
	Activity.Finish

End Sub


Sub Button1_Click
	
	StartActivity(barati)
	Activity.Finish
End Sub
Sub Button2_Click
	
	StartActivity(dolat)
	Activity.Finish
End Sub

Sub Button3_Click
	StartActivity(asheghi)
	Activity.Finish
End Sub

Sub Button4_Click
	StartActivity(delafkar)
	Activity.Finish
End Sub

Sub Button5_Click
	StartActivity(baghshani)
	Activity.Finish
End Sub

Sub Button6_Click

	StartActivity(hydari)
	Activity.Finish

End Sub

Sub Button7_Click

	StartActivity(bakerdar)
	Activity.Finish

End Sub



Sub Button8_Click

	Msgbox("اگر نام شما در لیست نیست از قسمت ارتباط با ما اقدام نمایید با تشکر","توجه!")

End Sub


Sub Activity_KeyPress (KeyCode As Int) As Boolean 'Return True to consume the event
	
	If KeyCode = KeyCodes.KEYCODE_BACK Then
		StartActivity("razavi")
		Activity.Finish
		Return True
	Else
		Return True
	End If
End Sub
