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
	Private Button1 As Button
	Private Button2 As Button
	Private Button3 As Button
	Private Button4 As Button
	Private Button5 As Button
	Private Button6 As Button
	Private Button7 As Button
	Private Button8 As Button
	Private Button9 As Button
	Private Button10 As Button
	Private Button11 As Button
	Private Button12 As Button
	Private Button13 As Button
	Private Button14 As Button
	Private Button15 As Button
	Private Button16 As Button
	Private Button17 As Button
	Private Button18 As Button
	Private Button19 As Button
	Private Button20 As Button
	Private Button21 As Button
	Private Button22 As Button
	Private Button23 As Button
	Private Button24 As Button
	Private Button25 As Button
	Private Button26 As Button
	Private Button27 As Button
	Private Button28 As Button
	Private Button29 As Button
	Private Button30 As Button
	Private Button31 As Button
	
End Sub

Sub Activity_Create(FirstTime As Boolean)
	Activity.LoadLayout("scrol")
	ScrollView1.Panel.LoadLayout("11")
	ScrollView1.Panel.Height = 150%y
	ostanha.Button(Button1,"خراسان رضوی")
	ostanha.Button(Button2,"خراسان شمالی")
	ostanha.Button(Button3,"خراسان جنوبی")
	ostanha.Button(Button4,"تهران")
	ostanha.Button(Button5,"مازندران")
	ostanha.Button(Button6,"آذربایجان غربی")
	ostanha.Button(Button7,"آذربایجان شرقی")
	ostanha.Button(Button8,"کردستان")
	ostanha.Button(Button9,"لرستان")
	ostanha.Button(Button10,"هرمزگان")
	ostanha.Button(Button11,"گلستان")
	ostanha.Button(Button12,"سیستان و بلوچستان")
	ostanha.Button(Button13,"اصفهان")
	ostanha.Button(Button14,"البرز")
	ostanha.Button(Button15,"سمنان")
	ostanha.Button(Button16,"یزد")
	ostanha.Button(Button17,"همدان")
	ostanha.Button(Button18,"مرکزی")
	ostanha.Button(Button19,"گیلان")
	ostanha.Button(Button20,"کهکیلویه")
	ostanha.Button(Button21,"کرمانشاه")
	ostanha.Button(Button22,"فارس")
	ostanha.Button(Button23,"قزوین")
	ostanha.Button(Button24,"بوشهر")
	ostanha.Button(Button25,"خوزستان")
	ostanha.Button(Button26,"کرمان")
	ostanha.Button(Button27,"چهارمحال بختیاری")
	ostanha.Button(Button28,"قم")
	ostanha.Button(Button29,"ایلام")
	ostanha.Button(Button30,"زنجان")
	ostanha.Button(Button31,"اردبیل")
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
	
	
	

End Sub
Sub ImageView4_Click
	
	
	StartActivity(tamas)
	Activity.Finish

End Sub



Sub Button1_Click
	StartActivity(razavi)
	Activity.Finish
End Sub












Sub Activity_KeyPress (KeyCode As Int) As Boolean 'Return True to consume the event
	
	If KeyCode = KeyCodes.KEYCODE_BACK Then
		StartActivity("Main")
		Activity.Finish
		Return True
	Else
		Return True
	End If
End Sub
