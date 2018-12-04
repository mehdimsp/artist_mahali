B4A=true
Group=Default Group
ModulesStructureVersion=1
Type=Activity
Version=7.8
@EndOfDesignText@
#Region  Activity Attributes 
	#FullScreen: False
	#IncludeTitle: True
#End Region

Sub Process_Globals
	

End Sub

Sub Globals
	
	Dim url As PhoneIntents

	Private WebView1 As WebView
End Sub

Sub Activity_Create(FirstTime As Boolean)
	
	
	
	
	
	
	StartActivity(url.OpenBrowser("http://doomadi.com"))
	
End Sub


Sub Activity_Resume
	
End Sub

Sub Activity_Pause (UserClosed As Boolean)

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

