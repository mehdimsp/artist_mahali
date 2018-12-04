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
	ostanha.Button(Button1,"مشهد")
	ostanha.Button(Button2,"سبزوار")
	ostanha.Button(Button3,"نیشابور")
	ostanha.Button(Button4,"کاشمر")
	ostanha.Button(Button5,"تربت حیدریه")
	ostanha.Button(Button6,"قوچان")
	ostanha.Button(Button7,"گناباد")
	ostanha.Button(Button8,"تربت جام")
	ostanha.Button(Button9,"بجستان و محولات")
	


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
	
	StartActivity(mashhad)
	Activity.Finish
End Sub

Sub Button2_Click
	
	StartActivity(sabzevar)
	Activity.Finish
End Sub
Sub Button3_Click
	
	StartActivity(neyshboor)
	Activity.Finish
End Sub


Sub Button4_Click
	
	StartActivity(kashmar)
	Activity.Finish
End Sub
Sub Button5_Click
	
	StartActivity(torbat)
	Activity.Finish
End Sub

Sub Button7_Click
	
	StartActivity(gonabad)
	Activity.Finish
End Sub


Sub Button8_Click
	
	StartActivity(torbatjam)
	Activity.Finish
End Sub
Sub Button9_Click
	
	StartActivity(bejstan)
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

