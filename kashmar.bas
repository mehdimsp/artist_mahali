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
	ostanha.Button(Button1,"وحید علوی")
	ostanha.Button(Button2,"محمدجان علوی")
	ostanha.Button(Button3,"حسن عابدینی")
	ostanha.Button(Button4,"حسین رضایی")
	ostanha.Button(Button5,"رحمت حسن زاده")
	ostanha.Button(Button6,"عباس چوپان")
	ostanha.Button(Button7,"رضا ژیان")
	ostanha.Button(Button8,"سلمان برقی")
	ostanha.Button(Button9,"محسن خاکشور")
	ostanha.Button(Button10,"مهدی صدوقیان")
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
	
	StartActivity(alavivahid)
	Activity.Finish
End Sub
Sub Button2_Click
	
	StartActivity(mamadjan)
	Activity.Finish
End Sub

Sub Button3_Click
	StartActivity(abdini)
	Activity.Finish
End Sub

Sub Button4_Click
	StartActivity(rezaee)
	Activity.Finish
End Sub

Sub Button5_Click
	StartActivity(hasanzadeh)
	Activity.Finish
End Sub

Sub Button6_Click

	StartActivity(choopan)
	Activity.Finish

End Sub

Sub Button7_Click

	StartActivity(zhiyan)
	Activity.Finish

End Sub

Sub Button8_Click
	
	StartActivity(barghi)
	Activity.Finish

End Sub


Sub Button9_Click
	StartActivity(khakshoor)
	Activity.Finish
End Sub

Sub Button10_Click

	StartActivity(sedooghi)
	Activity.Finish
End Sub

Sub Button11_Click

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
