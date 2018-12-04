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
	'These global variables will be declared once when the application starts.
	'These variables can be accessed from all modules.

End Sub

Sub Globals
	

	Private WebView1 As WebView
End Sub

Sub Activity_Create(FirstTime As Boolean)
	
	Activity.LoadLayout("tozih")
	WebView1.LoadUrl("file:///android_asset/"&"tozih.html")
	WebView1.ZoomEnabled = False
	WebView1.Back
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
