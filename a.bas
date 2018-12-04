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
	Dim p As Panel
	Dim music As MusicStramer
	Private SeekBar1 As SeekBar
	Dim string1 As String
	Dim timer1 As Timer
	Dim http As HttpJob
	Private ScrollView1 As ScrollView
	Dim Artist,Name,Image,linkMusic,sahr,id As List
	Dim josn As  JSONParser
	Dim list As List
	Dim map As Map
	Dim left , top As Int
	left =2%x
	top = 1%y
	Private nameLabel As Label
	Private ArtistLabel As Label
	Private Imagev As ImageView
	Dim anim As Animation
	Private Panel1 As Panel
	Private ImageView1 As ImageView
	Private xname As Label
	Private play As Button
	Private pause As Button
	Private time As Label
	Dim linkplay As String
	Dim isplaying As Boolean
	isplaying=False
	Dim tin As Timer

	Private ImageView3 As ImageView
	Private ImageView4 As ImageView
	Private ImageView5 As ImageView
	Private ImageView6 As ImageView
	
	Private sahr1 As Label
	Private Button3 As Button
	Private Button2 As Button
	Private Button1 As Button
	Dim sql1 As SQL
	Dim cursor1 As Cursor
	
	Dim idi As String
	
	Private Button5 As Button
End Sub

Sub Activity_Create(FirstTime As Boolean)
	Activity.LoadLayout("layout2")

	Button5.Text="حذف علاقمندی"
	ProgressDialogShow2("Loading...",False)
	
	http.Initialize("http",Me)
	
	Artist.Initialize
	Name.Initialize
	Image.Initialize
	linkMusic.Initialize
	sahr.Initialize
	music.initialize(SeekBar1,string1)
	id.Initialize
	
	If File.Exists(File.DirRootExternal,"data.db")=False Then
		File.Copy(File.DirAssets,"data.db",File.DirRootExternal,"data.db")
	End If
	If sql1.IsInitialized=False Then
		sql1.Initialize(File.DirRootExternal,"data.db",False)
	End If
	load_data
End Sub
Sub Activity_Resume

End Sub
Sub Activity_Pause (UserClosed As Boolean)
	If isplaying=True Then
		
		timer1.Enabled=False
	End If
	
	
End Sub


Sub load_data

	
	cursor1 = sql1.ExecQuery("SELECT * FROM My_DB")
	Dim i As Int
	For i=0 To cursor1.RowCount-1
		cursor1.Position=i
		Log(cursor1.GetString("id")&  "|" &cursor1.GetString("artist")& "|" &cursor1.GetString("name")& "|" &cursor1.GetString("image")& "|" &cursor1.GetString("music")& "|" &cursor1.GetString("sahr")& "|" &cursor1.GetString("telegram")& "|" &cursor1.GetString("link1")& "|" &cursor1.GetString("VEIW"))


	
		Artist.Add(cursor1.GetString("artist"))
		Name.Add(cursor1.GetString("name"))
		Image.Add(cursor1.GetString("image"))
		linkMusic.Add(cursor1.GetString("music"))
		sahr.Add(cursor1.GetString("sahr"))
		id.Add(cursor1.GetString("id"))
		Dim p As Panel
		p.Initialize("p")
		Dim clr As ColorDrawable
		clr.Initialize(Colors.White,2)
		p.Visible=False
		ScrollView1.Panel.AddView(p,left,top,47%x,43%y)
		p.SetVisibleAnimated(600,True)
		left = left + 49%x
		p.Tag = i
		If i Mod 2 = 1 Then
			top = top + 44%y
			ScrollView1.Panel.Height = top + 50%y
			left = 2%x
		End If
		p.LoadLayout("jk")
		p.Background=clr
		download(Imagev,cursor1.GetString("image"))
		ArtistLabel.Text=Artist.Get(i)
		nameLabel.Text=Name.Get(i)
		sahr1.Text=sahr.Get(i)
	Next
	ProgressDialogHide
End Sub

Private Sub download(Imagex As ImageView,link As String)
	Dim links As Map
	links.Initialize
	links.Put(Imagex,link)
	CallSubDelayed2(ImageDownloader, "Download", links)

End Sub
Sub p_Click
	Dim v As Panel
	v=Sender
	Panel1.SetVisibleAnimated(500,True)
	
	download(ImageView1,Image.Get(v.Tag))
	xname.Text=Artist.Get(v.Tag)&""&" "&Name.Get(v.Tag)
	linkplay=linkMusic.Get(v.Tag)
	idi=id.Get(v.Tag)
	ImageView6.Visible=False

	ImageView5.Visible=False

	ImageView4.Visible=False

	ImageView3.Visible=False
	
'	Button1.Visible=False
'	Button2.Visible=False
'	Button3.Visible=False
End Sub


Sub Button5_Click
	Dim v As Button
	v=Sender

	sql1.ExecNonQuery("DELETE FROM My_DB where id ='"&idi&"' ")
	Activity.Finish
	StartActivity(Me)
End Sub
	
Sub play_Click
	
	If isplaying=False Then
		music.Create(linkplay)
		music.Play
		timer1.Initialize("t",5)
		timer1.Enabled=True
		isplaying=True
		SeekBar1.Visible=True
	Else
		music.Play
		isplaying=True
	End If
End Sub
Sub pause_Click
	If isplaying=True Then
		music.pause
		isplaying=True
	End If
End Sub
Sub t_Tick
	
	time.Text=music.PlayingTime
	
	timer1.Enabled=True
	
End Sub

Sub Panel1_Touch (Action As Int, X As Float, Y As Float)
	
End Sub

Sub tin_Tick
	ImageView1.SetBackgroundImage(Null)

	tin.Enabled=False
End Sub



Sub ImageView6_Click


End Sub

Sub ImageView5_Click


End Sub

Sub ImageView4_Click

End Sub

Sub ImageView3_Click
	StartActivity(Main)
	Activity.Finish
End Sub

Sub Button1_Click
	
End Sub

Sub Button2_Click
	
End Sub

Sub Button3_Click
	
End Sub


Sub Activity_KeyPress (KeyCode As Int) As Boolean 'Return True to consume the event
	If KeyCode=KeyCodes.KEYCODE_BACK Then
		If Panel1.Visible=True Then
			If isplaying=True Then
				Dim str As String
				str=Msgbox2("موزیک در حال پخش قطع شود؟؟","doomadi music?","آره","نه","",Null)
				If str=DialogResponse.POSITIVE Then
					music.stop
					Panel1.SetVisibleAnimated(500,False)
					isplaying=False
					time.Text="ـ"
					SeekBar1.Visible=False
					timer1.Enabled=False
					tin.Initialize("tin",1000)
					tin.Enabled=True
					ImageView6.Visible=True

					ImageView5.Visible=True

					ImageView4.Visible=True

					ImageView3.Visible=True
					
'					Button1.Visible=True
'					Button2.Visible=True
'					Button3.Visible=True
				Else
					Return True
				End If
			Else
				ImageView6.Visible=True

				ImageView5.Visible=True

				ImageView4.Visible=True

				ImageView3.Visible=True
'				Button1.Visible=True
'				Button2.Visible=True
'				Button3.Visible=True
			
				Panel1.SetVisibleAnimated(500,False)
				tin.Initialize("tin",500)
				tin.Enabled=True
				
			
			End If
			Return True
		End If
	End If
	
	If KeyCode = KeyCodes.KEYCODE_BACK Then
		
	
		StartActivity(Main)
		Activity.Finish
		Return True
	End If


End Sub
