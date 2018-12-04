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
	Private Button11, Button12, Button14, Button15, Button16, Button17, Button18, Button19, Button20 As Button
	Private Button21, Button22, Button23, Button24 , Button25 , Button26 , Button27 , Button28 , Button29 , Button30 , Button31 As Button
	
End Sub

Sub Activity_Create(FirstTime As Boolean)
	Activity.LoadLayout("scrol")
	ScrollView1.Panel.LoadLayout("shahr")
	ScrollView1.Panel.Height = 120%y
	ostanha.Button(Button1,"کرج")
	

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
	
	StartActivity(karaj)
	Activity.Finish
End Sub



Sub Activity_KeyPress (KeyCode As Int) As Boolean 'Return True to consume the event
	
	If KeyCode = KeyCodes.KEYCODE_BACK Then
		StartActivity("ostan")
		Activity.Finish
		Return True
	Else
		Return True
	End If
End Sub

