B4A=true
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
	Private Button1, Button2, Button3, Button4 , Button5 , Button6 , Button7 , Button8 , Button9 , Button10 As Button
	Private Button11, Button12, Button13 As Button
	
End Sub

Sub Activity_Create(FirstTime As Boolean)
	Activity.LoadLayout("scrol")
	ScrollView1.Panel.LoadLayout("ahang")
	ScrollView1.Panel.Height = 160%y
	ostanha.Button(Button1,"مرتضی جعفرزاده")
	ostanha.Button(Button2,"وحید اقبالی")
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
	
	StartActivity(mortzajafarzadeh)
	Activity.Finish
End Sub

Sub Button2_Click
	
	StartActivity(eghbali)
	Activity.Finish
End Sub
Sub Button3_Click

	Msgbox("اگر نام شما در لیست نیست از قسمت ارتباط با ما اقدام نمایید با تشکر","توجه!")

End Sub


Sub Activity_KeyPress (KeyCode As Int) As Boolean 'Return True to consume the event
	
	If KeyCode = KeyCodes.KEYCODE_BACK Then
		StartActivity("shomalikh")
		Activity.Finish
		Return True
	Else
		Return True
	End If
End Sub
