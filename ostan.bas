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
	Private Button11, Button12, Button13, Button14 , Button15 , Button16 , Button17 , Button18 , Button19 , Button20 As Button
	Private Button21, Button22, Button23, Button24 , Button25 , Button26 , Button27 , Button28 , Button29 , Button30 As Button
	Private Button31 As Button
End Sub

Sub Activity_Create(FirstTime As Boolean)
	Activity.LoadLayout("scrol")
	ScrollView1.Panel.LoadLayout("11")
	ScrollView1.Panel.Height = 170%y
	
	
		
		
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
Sub Button2_Click
	StartActivity(shomalikh)
	Activity.Finish
End Sub
Sub Button3_Click
	Msgbox("برای فعال شدن این استان از طریق پشتیبانی برنامه با ما در ارتباط باشید","توجه!")
	
End Sub
Sub Button4_Click
	StartActivity(tehran)
	Activity.Finish
End Sub
Sub Button5_Click
	StartActivity(mazandaran)
	Activity.Finish
End Sub
Sub Button6_Click
	StartActivity(azgharbi)
	Activity.Finish
End Sub
Sub Button7_Click
	StartActivity(azsharghi)
	Activity.Finish
End Sub
Sub Button8_Click
	Msgbox("برای فعال شدن این استان از طریق پشتیبانی برنامه با ما در ارتباط باشید","توجه!")
	
End Sub
Sub Button9_Click
	StartActivity(lorestan)
	Activity.Finish
End Sub
Sub Button10_Click
	StartActivity(hormozgan)
	Activity.Finish
End Sub
Sub Button11_Click
	StartActivity(golestan)
	Activity.Finish
End Sub
Sub Button12_Click
	StartActivity(sistan)
	Activity.Finish
End Sub
Sub Button13_Click
	Msgbox("برای فعال شدن این استان از طریق پشتیبانی برنامه با ما در ارتباط باشید","توجه!")
	
End Sub
Sub Button14_Click
	
	Msgbox("برای فعال شدن این استان از طریق پشتیبانی برنامه با ما در ارتباط باشید","توجه!")
	
End Sub
Sub Button15_Click
	Msgbox("برای فعال شدن این استان از طریق پشتیبانی برنامه با ما در ارتباط باشید","توجه!")
	
End Sub
Sub Button16_Click
	StartActivity(yazd)
	Activity.Finish
End Sub
Sub Button17_Click
	Msgbox("برای فعال شدن این استان از طریق پشتیبانی برنامه با ما در ارتباط باشید","توجه!")
	
End Sub
Sub Button18_Click
	Msgbox("برای فعال شدن این استان از طریق پشتیبانی برنامه با ما در ارتباط باشید","توجه!")
	
End Sub
Sub Button19_Click
	Msgbox("برای فعال شدن این استان از طریق پشتیبانی برنامه با ما در ارتباط باشید","توجه!")
	
End Sub
Sub Button20_Click
	Msgbox("برای فعال شدن این استان از طریق پشتیبانی برنامه با ما در ارتباط باشید","توجه!")
	
End Sub


Sub Button21_Click
	StartActivity(kermanshah)
	Activity.Finish
End Sub
Sub Button22_Click
	StartActivity(fars)
	Activity.Finish
End Sub
Sub Button23_Click
	Msgbox("برای فعال شدن این استان از طریق پشتیبانی برنامه با ما در ارتباط باشید","توجه!")
		
End Sub
Sub Button24_Click
	Msgbox("برای فعال شدن این استان از طریق پشتیبانی برنامه با ما در ارتباط باشید","توجه!")
	
End Sub
Sub Button25_Click
	Msgbox("برای فعال شدن این استان از طریق پشتیبانی برنامه با ما در ارتباط باشید","توجه!")
	
End Sub
Sub Button26_Click
	Msgbox("برای فعال شدن این استان از طریق پشتیبانی برنامه با ما در ارتباط باشید","توجه!")
	
End Sub

Sub Button27_Click
	StartActivity(charmahal)
	Activity.Finish
End Sub
Sub Button28_Click
	Msgbox("برای فعال شدن این استان از طریق پشتیبانی برنامه با ما در ارتباط باشید","توجه!")
	
End Sub
Sub Button29_Click
	StartActivity(elam)
	Activity.Finish
End Sub
Sub Button30_Click
	StartActivity(zanjan)
	Activity.Finish
End Sub
Sub Button31_Click
	StartActivity(ardabil)
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
