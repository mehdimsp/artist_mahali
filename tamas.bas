B4A=true
Group=Default Group
ModulesStructureVersion=1
Type=Activity
Version=7.8
@EndOfDesignText@
#Region  Activity Attributes 
	#FullScreen: False
	#IncludeTitle: False
#End Region

Sub Process_Globals
	'These global variables will be declared once when the application starts.
	'These variables can be accessed from all modules.

End Sub

Sub Globals
	'These global variables will be redeclared each time the activity is created.
	'These variables can only be accessed from this module.

End Sub

Sub Activity_Create(FirstTime As Boolean)
	
	Activity.LoadLayout("tamas")

End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub
Sub Button1_Click
	Private i As Intent
	i.Initialize(i.ACTION_VIEW,"http://telegram.me/music_mahali")
	StartActivity(i)
End Sub

Sub Button2_Click
	Private i As Intent
	i.Initialize(i.ACTION_VIEW,"https://www.instagram.com/doomadi")
	StartActivity(i)
End Sub

Sub Button3_Click
	Private i As Intent
	i.Initialize(i.ACTION_VIEW,"https://sapp.ir/doomade")
	StartActivity(i)
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
	
	StartActivity(ostanha)
	Activity.Finish


End Sub

Sub ImageView4_Click
	
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
