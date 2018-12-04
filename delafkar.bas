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
	Dim chk As Int=0
	Dim p As Panel
	Dim music As MusicStramer
	Private SeekBar1 As SeekBar
	Dim string1 As String
	Dim timer1 As Timer
	Dim http As HttpJob
	Private ScrollView1 As ScrollView
	Dim Artist,Name,Image,linkMusic,sahr,telegram1,link1,VEIW,id As List
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
	
	Dim TELEGRAM2,w,ww,www,wwww,wwwww,wwwwww As String
	Dim LINK2 As String
	Dim SITE2 As String
	
	Dim isplaying As Boolean
	isplaying=False
	Dim tin As Timer

	Private ImageView3 As ImageView
	Private ImageView4 As ImageView
	Private ImageView5 As ImageView
	Private ImageView6 As ImageView
	
	Private sahr1,number1 As Label
	Private Button3 As Button
	Private Button2 As Button
	Private Button1 As Button
	Private Button4 As Button
	
	Dim ht As HttpJob
	
	
	Dim no As Notification
	Dim link3,Name3,dier As String
	Dim s() As String
	Dim ss As String
	Dim sound As MediaPlayer
	
	Dim asa As Int
	Private id1 As Label
	
	
	
	Dim qqq,qqqq,sahr11 As String
	Private Label20 As Label
	Private Label2 As Label
	
	
	
	Dim sql1 As SQL
	Dim cursor1 As Cursor
	Private Button100 As Button
	Private Button5 As Button
	
	
	Dim tmback As Timer
	Dim intback As Byte = 0
	Private Label3 As Label
	
	
End Sub

Sub Activity_Create(FirstTime As Boolean)
	Activity.LoadLayout("layout2")
	File.MakeDir(File.DirRootExternal, "doholimusic")

	ProgressDialogShow2("Loading...",False)
	
	
	
	
	http.Initialize("http",Me)
	LoadDB("SELECT * FROM delafkar ORDER BY id DESC LIMIT 0,10000000000000")
	Artist.Initialize
	Name.Initialize
	Image.Initialize
	linkMusic.Initialize
	sahr.Initialize
	VEIW.Initialize
	telegram1.Initialize
	link1.Initialize
	id.Initialize
	music.initialize(SeekBar1,string1)
	
	
	If File.Exists(File.DirRootExternal,"data.db")=False Then
		File.Copy(File.DirAssets,"data.db",File.DirRootExternal,"data.db")
	End If
'	If sql1.IsInitialized=False Then
	sql1.Initialize(File.DirRootExternal,"data.db",False)
'	End If
	
	
	
	tmback.Initialize("tmExit",1500)
End Sub
Sub Activity_Resume

End Sub
Sub Activity_Pause (UserClosed As Boolean)
	If isplaying=True Then
		
		timer1.Enabled=False
	End If
End Sub







Sub LoadDB(DB As String)
	http.PostString("http://doholi.com/muzzik/json.php",DB)

End Sub
Sub JobDone(job As HttpJob)
	If job.Success = True Then
		Select Case job.JobName
		
		
			Case "http"
		
				ProgressDialogHide
				list.Initialize
				Dim res As String
				res = job.GetString
				josn.Initialize(res)
				list=josn.NextArray
				For i=0 To list.Size-1
					map=list.Get(i)
					Artist.Add(map.Get("artist"))
					Name.Add(map.Get("name"))
					Image.Add(map.Get("image"))
					linkMusic.Add(map.Get("music"))
					sahr.Add(map.Get("sahr"))
					VEIW.Add(map.Get("VEIW"))
					id.Add(map.Get("id"))
					telegram1.Add(map.Get("telegram"))
					link1.Add(map.Get("link1"))
					
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
					download(Imagev,map.Get("image"))
					ArtistLabel.Text=Artist.Get(i)
					nameLabel.Text=Name.Get(i)
					sahr1.Text=sahr.Get(i)
					number1.text=VEIW.Get(i)
					id1.Text=id.Get(i)
					
				Next
		

			Case "job1"
				Dim o As OutputStream
				o = File.OpenOutput(dier, Name3, False)
				File.Copy2(job.GetInputStream, o)
				o.Close
				job.Release
			Case "job2"
				Dim o As OutputStream
				o = File.OpenOutput(dier, Name3, False)
				File.Copy2(job.GetInputStream, o)
				o.Close
				job.Release
				ToastMessageShow("فایل دانلود و ذخیره شد",True)
'			sound.Initialize2("")
'			sound.Load(File.DirRootExternal & "/doholimusic", ss)
'			sound.Looping=False
'			sound.Play
			Case "job3"
				Dim o As OutputStream
				o = File.OpenOutput(dier, Name3, False)
				File.Copy2(job.GetInputStream, o)
				o.Close
				job.Release
				no.Cancel(1)
				ToastMessageShow("فایل دانلود و ذخیره شد",True)
'			sound.Initialize2("")
'			sound.Load(File.DirRootExternal & "/doholimusic",  ss)
'			sound.Looping=False
'			sound.Play
'			Button1.Text="پخش شدن"

			Case "edit"
				If job.GetString.Contains("successfully") = True Then
				
					qqq=qqq+1
				
					Label2.Text=qqq
					ToastMessageShow("بازدیدی دیگر از این آهنگ",False)
					

				Else
					ToastMessageShow("اشکالی به وجود امده",False)

				End If
				
		End Select
	Else
		ToastMessageShow(http.ErrorMessage,True)
	End If
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
	
	sahr11=sahr.Get(v.Tag)
	qqq=VEIW.Get(v.Tag)
	qqqq=id.Get(v.Tag)
	TELEGRAM2=telegram1.Get(v.Tag)
	LINK2=link1.Get(v.Tag)
	
	w=Artist.Get(v.Tag)
	ww=Name.Get(v.Tag)
	www=Image.Get(v.Tag)

	ImageView6.Visible=False

	ImageView5.Visible=False

	ImageView4.Visible=False

	ImageView3.Visible=False
	
	Label2.Text=qqq
	
End Sub
Sub p_LongClick
	

	Dim v As Panel
	v=Sender
	


	cursor1=sql1.ExecQuery("SELECT * FROM My_DB")
	For i=0 To cursor1.RowCount-1
		cursor1.Position=i
		LogColor(i,Colors.red)
		If cursor1.GetString("artist")=Artist.Get(v.Tag) Then
			chk=2
			LogColor(i,Colors.Blue)
		End If
	
	Next
	
	If chk=2 Then
		chk=0
		ToastMessageShow("از قبل در لیست علاقه مندیها قرار گرفته است",False)
	Else
		ToastMessageShow("به لیست علاقمندی اضافه شد",False)
		cursor1=sql1.ExecQuery("SELECT id FROM My_DB")
		If cursor1.RowCount>0 Then
			For i=0 To cursor1.RowCount-1
				cursor1.Position=i
				Dim new_num As Int
				new_num=cursor1.GetInt("id")
			Next
		End If
		new_num=new_num+1
		sql1.ExecNonQuery("INSERT INTO My_DB VALUES('" &new_num&"','" &w&"','"&ww&"','"&www&"','"&linkplay&"','"&sahr11&"','"&TELEGRAM2&"','"&linkplay&"','"&qqq&"')")
	
	End If

End Sub

Sub Button5_Click
	
	
	Dim v As Button
	v=Sender


	cursor1=sql1.ExecQuery("SELECT * FROM My_DB")
	For i=0 To cursor1.RowCount-1
		cursor1.Position=i
		LogColor(i,Colors.red)
		If cursor1.GetString("artist")=w Then
			chk=2
			LogColor(i,Colors.Blue)
		End If
	
	Next
	
	If chk=2 Then
		chk=0
		ToastMessageShow("از قبل در لیست علاقه مندیها قرار گرفته است",False)
	Else
		ToastMessageShow("به لیست علاقمندی اضافه شد",False)
		cursor1=sql1.ExecQuery("SELECT id FROM My_DB")
		If cursor1.RowCount>0 Then
			For i=0 To cursor1.RowCount-1
				cursor1.Position=i
				Dim new_num As Int
				new_num=cursor1.GetInt("id")
			Next
		End If
		new_num=new_num+1
		sql1.ExecNonQuery("INSERT INTO My_DB VALUES('" &new_num&"','" &w&"','"&ww&"','"&www&"','"&linkplay&"','"&sahr11&"','"&TELEGRAM2&"','"&linkplay&"','"&qqq&"')")
	
	End If
End Sub
Sub play_Click
	
	ToastMessageShow("چند لحظه صبر نمایید...",  True)
	asa =qqq + 1
	
	Log(asa)

	DoEvents
	Dim edit As HttpJob
	edit.Initialize("edit",Me)
	edit.PostString("http://doholi.com/edit/edit8.php","VEIW="&asa&"&id="&qqqq)

	
	
	
	s=Regex.Split("/",linkplay)
'		Log(s(5))
	ss=s(5)
	If File.Exists(File.DirRootExternal & "/doholimusic",  ss) = False Then
	
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
	
	
	Else
		ToastMessageShow("فایل موجود است",  True)
		Dim sound As MediaPlayer
		sound.Initialize2("")
		sound.Load(File.DirRootExternal & "/doholimusic", ss)
		sound.Looping=False
		sound.Play

	End If
	
End Sub
Sub pause_Click
	ToastMessageShow("توقف",  True)
	If isplaying=True Then
		music.pause
		isplaying=True
	End If
	
	Try
	
		sound.Load(File.DirRootExternal & "/doholimusic", ss)
		sound.Looping=False
		sound.Stop
		
	Catch
		Log(LastException)
	End Try
	
End Sub
Sub t_Tick
	
	time.Text=music.PlayingTime
	timer1.Enabled=True
	
End Sub

Sub Panel1_Touch (Action As Int, X As Float, Y As Float)
	
End Sub



Sub ImageView6_Click
	StartActivity(tamas)
	Activity.Finish

End Sub

Sub ImageView5_Click
	StartActivity(ostan)
	Activity.Finish

End Sub

Sub ImageView4_Click
	StartActivity(doomadi)
	Activity.Finish
End Sub

Sub Label3_Click
	StartActivity(a)
	Activity.Finish
End Sub
Sub ImageView3_Click
	
	StartActivity(Main)
	Activity.Finish

End Sub

Sub Button1_Click

	Try
		
		Dim far As Intent
		Dim pm As PackageManager
		far=pm.GetApplicationIntent("com.hanista.mobogram")
		If far.IsInitialized Then
			'کد اولی
			Dim i As Intent
			i.Initialize(i.ACTION_VIEW, "http://telegram.me/"&TELEGRAM2)
			Dim jo As JavaObject = i
			jo.RunMethod("setPackage", Array As Object("com.hanista.mobogram"))
			StartActivity(i)

		

		Else

			'کد دومی
			Dim i2 As Intent
			i2.initialize (i2.action_view,"tg://resolve?domain="&TELEGRAM2)
			StartActivity (i2)
		
		End If
	
	Catch


	End Try
End Sub

Sub Button2_Click
	Try
		Dim i As Intent
		i.Initialize(i.ACTION_VIEW,LINK2)
		StartActivity(i)
	Catch
		Log(LastException)
	End Try
End Sub

Sub Button3_Click

	s=Regex.Split("/",linkplay)
'		Log(s(5))
	ss=s(5)
	If File.Exists(File.DirRootExternal & "/doholimusic",  ss) = False Then
		Name3= ss
		dier=File.DirRootExternal& "/doholimusic"


		http.Initialize("job3",Me)
		http.Download(linkplay)

		no.Initialize
		no.Icon="icon"
		no.Light=False
		no.Vibrate = False
		no.sound = True
		no.AutoCancel=True
		no.OnGoingEvent=True
		no.SetInfo("در حال دانلود",Name3,Null)
		no.Notify(1)
		ProgressDialogShow("در حال دانلود فایل...صبر کنید")
		ToastMessageShow("لطفا منتظر بمانید,",  True)
	Else
		ToastMessageShow("فایل موجود است",  True)
	End If
End Sub

Sub Button4_Click

	Dim share As Intent
	share.Initialize(share.ACTION_SEND,"")
	
	share.SetType("text/plain")
	share.PutExtra("android.intent.extra.TEXT", linkplay)
	share.WrapAsIntentChooser("اشتراک گذاری آهنگ")
	StartActivity(share)
	Log( linkplay)
End Sub




Sub tin_Tick
	ImageView1.SetBackgroundImage(Null)

	tin.Enabled=False
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
		
	
		StartActivity(sabzevar)
		Activity.Finish
		Return True
	End If


End Sub