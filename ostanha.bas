B4A=true
Group=Default Group
ModulesStructureVersion=1
Type=StaticCode
Version=7.8
@EndOfDesignText@
'Code module
'Subs in this code module will be accessible from all modules.
Sub Process_Globals
	'These global variables will be declared once when the application starts.
	'These variables can be accessed from all modules.

End Sub

Sub Button(Btn As Button,Text As String)
	Btn.Text = Text
	Btn.TextSize = 14
	Btn.Gravity = Gravity.CENTER
	Btn.TextColor = Colors.Black
	
End Sub