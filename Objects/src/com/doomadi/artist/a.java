package com.doomadi.artist;


import anywheresoftware.b4a.B4AMenuItem;
import android.app.Activity;
import android.os.Bundle;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.B4AActivity;
import anywheresoftware.b4a.ObjectWrapper;
import anywheresoftware.b4a.objects.ActivityWrapper;
import java.lang.reflect.InvocationTargetException;
import anywheresoftware.b4a.B4AUncaughtException;
import anywheresoftware.b4a.debug.*;
import java.lang.ref.WeakReference;

public class a extends Activity implements B4AActivity{
	public static a mostCurrent;
	static boolean afterFirstLayout;
	static boolean isFirst = true;
    private static boolean processGlobalsRun = false;
	BALayout layout;
	public static BA processBA;
	BA activityBA;
    ActivityWrapper _activity;
    java.util.ArrayList<B4AMenuItem> menuItems;
	public static final boolean fullScreen = false;
	public static final boolean includeTitle = false;
    public static WeakReference<Activity> previousOne;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (processBA == null) {
			processBA = new BA(this.getApplicationContext(), null, null, "com.doomadi.artist", "com.doomadi.artist.a");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (a).");
				p.finish();
			}
		}
        processBA.runHook("oncreate", this, null);
		if (!includeTitle) {
        	this.getWindow().requestFeature(android.view.Window.FEATURE_NO_TITLE);
        }
        if (fullScreen) {
        	getWindow().setFlags(android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN,   
        			android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
		mostCurrent = this;
        processBA.sharedProcessBA.activityBA = null;
		layout = new BALayout(this);
		setContentView(layout);
		afterFirstLayout = false;
        WaitForLayout wl = new WaitForLayout();
        if (anywheresoftware.b4a.objects.ServiceHelper.StarterHelper.startFromActivity(processBA, wl, false))
		    BA.handler.postDelayed(wl, 5);

	}
	static class WaitForLayout implements Runnable {
		public void run() {
			if (afterFirstLayout)
				return;
			if (mostCurrent == null)
				return;
            
			if (mostCurrent.layout.getWidth() == 0) {
				BA.handler.postDelayed(this, 5);
				return;
			}
			mostCurrent.layout.getLayoutParams().height = mostCurrent.layout.getHeight();
			mostCurrent.layout.getLayoutParams().width = mostCurrent.layout.getWidth();
			afterFirstLayout = true;
			mostCurrent.afterFirstLayout();
		}
	}
	private void afterFirstLayout() {
        if (this != mostCurrent)
			return;
		activityBA = new BA(this, layout, processBA, "com.doomadi.artist", "com.doomadi.artist.a");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "com.doomadi.artist.a", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (a) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (a) Resume **");
        processBA.raiseEvent(null, "activity_resume");
        if (android.os.Build.VERSION.SDK_INT >= 11) {
			try {
				android.app.Activity.class.getMethod("invalidateOptionsMenu").invoke(this,(Object[]) null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	public void addMenuItem(B4AMenuItem item) {
		if (menuItems == null)
			menuItems = new java.util.ArrayList<B4AMenuItem>();
		menuItems.add(item);
	}
	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		super.onCreateOptionsMenu(menu);
        try {
            if (processBA.subExists("activity_actionbarhomeclick")) {
                Class.forName("android.app.ActionBar").getMethod("setHomeButtonEnabled", boolean.class).invoke(
                    getClass().getMethod("getActionBar").invoke(this), true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (processBA.runHook("oncreateoptionsmenu", this, new Object[] {menu}))
            return true;
		if (menuItems == null)
			return false;
		for (B4AMenuItem bmi : menuItems) {
			android.view.MenuItem mi = menu.add(bmi.title);
			if (bmi.drawable != null)
				mi.setIcon(bmi.drawable);
            if (android.os.Build.VERSION.SDK_INT >= 11) {
				try {
                    if (bmi.addToBar) {
				        android.view.MenuItem.class.getMethod("setShowAsAction", int.class).invoke(mi, 1);
                    }
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			mi.setOnMenuItemClickListener(new B4AMenuItemsClickListener(bmi.eventName.toLowerCase(BA.cul)));
		}
        
		return true;
	}   
 @Override
 public boolean onOptionsItemSelected(android.view.MenuItem item) {
    if (item.getItemId() == 16908332) {
        processBA.raiseEvent(null, "activity_actionbarhomeclick");
        return true;
    }
    else
        return super.onOptionsItemSelected(item); 
}
@Override
 public boolean onPrepareOptionsMenu(android.view.Menu menu) {
    super.onPrepareOptionsMenu(menu);
    processBA.runHook("onprepareoptionsmenu", this, new Object[] {menu});
    return true;
    
 }
 protected void onStart() {
    super.onStart();
    processBA.runHook("onstart", this, null);
}
 protected void onStop() {
    super.onStop();
    processBA.runHook("onstop", this, null);
}
    public void onWindowFocusChanged(boolean hasFocus) {
       super.onWindowFocusChanged(hasFocus);
       if (processBA.subExists("activity_windowfocuschanged"))
           processBA.raiseEvent2(null, true, "activity_windowfocuschanged", false, hasFocus);
    }
	private class B4AMenuItemsClickListener implements android.view.MenuItem.OnMenuItemClickListener {
		private final String eventName;
		public B4AMenuItemsClickListener(String eventName) {
			this.eventName = eventName;
		}
		public boolean onMenuItemClick(android.view.MenuItem item) {
			processBA.raiseEventFromUI(item.getTitle(), eventName + "_click");
			return true;
		}
	}
    public static Class<?> getObject() {
		return a.class;
	}
    private Boolean onKeySubExist = null;
    private Boolean onKeyUpSubExist = null;
	@Override
	public boolean onKeyDown(int keyCode, android.view.KeyEvent event) {
        if (processBA.runHook("onkeydown", this, new Object[] {keyCode, event}))
            return true;
		if (onKeySubExist == null)
			onKeySubExist = processBA.subExists("activity_keypress");
		if (onKeySubExist) {
			if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK &&
					android.os.Build.VERSION.SDK_INT >= 18) {
				HandleKeyDelayed hk = new HandleKeyDelayed();
				hk.kc = keyCode;
				BA.handler.post(hk);
				return true;
			}
			else {
				boolean res = new HandleKeyDelayed().runDirectly(keyCode);
				if (res)
					return true;
			}
		}
		return super.onKeyDown(keyCode, event);
	}
	private class HandleKeyDelayed implements Runnable {
		int kc;
		public void run() {
			runDirectly(kc);
		}
		public boolean runDirectly(int keyCode) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keypress", false, keyCode);
			if (res == null || res == true) {
                return true;
            }
            else if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK) {
				finish();
				return true;
			}
            return false;
		}
		
	}
    @Override
	public boolean onKeyUp(int keyCode, android.view.KeyEvent event) {
        if (processBA.runHook("onkeyup", this, new Object[] {keyCode, event}))
            return true;
		if (onKeyUpSubExist == null)
			onKeyUpSubExist = processBA.subExists("activity_keyup");
		if (onKeyUpSubExist) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keyup", false, keyCode);
			if (res == null || res == true)
				return true;
		}
		return super.onKeyUp(keyCode, event);
	}
	@Override
	public void onNewIntent(android.content.Intent intent) {
        super.onNewIntent(intent);
		this.setIntent(intent);
        processBA.runHook("onnewintent", this, new Object[] {intent});
	}
    @Override 
	public void onPause() {
		super.onPause();
        if (_activity == null) //workaround for emulator bug (Issue 2423)
            return;
		anywheresoftware.b4a.Msgbox.dismiss(true);
        BA.LogInfo("** Activity (a) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        if (mostCurrent != null)
            processBA.raiseEvent2(_activity, true, "activity_pause", false, activityBA.activity.isFinishing());		
        processBA.setActivityPaused(true);
        mostCurrent = null;
        if (!activityBA.activity.isFinishing())
			previousOne = new WeakReference<Activity>(this);
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        processBA.runHook("onpause", this, null);
	}

	@Override
	public void onDestroy() {
        super.onDestroy();
		previousOne = null;
        processBA.runHook("ondestroy", this, null);
	}
    @Override 
	public void onResume() {
		super.onResume();
        mostCurrent = this;
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (activityBA != null) { //will be null during activity create (which waits for AfterLayout).
        	ResumeMessage rm = new ResumeMessage(mostCurrent);
        	BA.handler.post(rm);
        }
        processBA.runHook("onresume", this, null);
	}
    private static class ResumeMessage implements Runnable {
    	private final WeakReference<Activity> activity;
    	public ResumeMessage(Activity activity) {
    		this.activity = new WeakReference<Activity>(activity);
    	}
		public void run() {
			if (mostCurrent == null || mostCurrent != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (a) Resume **");
		    processBA.raiseEvent(mostCurrent._activity, "activity_resume", (Object[])null);
		}
    }
	@Override
	protected void onActivityResult(int requestCode, int resultCode,
	      android.content.Intent data) {
		processBA.onActivityResult(requestCode, resultCode, data);
        processBA.runHook("onactivityresult", this, new Object[] {requestCode, resultCode});
	}
	private static void initializeGlobals() {
		processBA.raiseEvent2(null, true, "globals", false, (Object[])null);
	}
    public void onRequestPermissionsResult(int requestCode,
        String permissions[], int[] grantResults) {
        for (int i = 0;i < permissions.length;i++) {
            Object[] o = new Object[] {permissions[i], grantResults[i] == 0};
            processBA.raiseEventFromDifferentThread(null,null, 0, "activity_permissionresult", true, o);
        }
            
    }

public anywheresoftware.b4a.keywords.Common __c = null;
public anywheresoftware.b4a.objects.PanelWrapper _p = null;
public MuiscStramer.MusicStramer _music = null;
public anywheresoftware.b4a.objects.SeekBarWrapper _seekbar1 = null;
public static String _string1 = "";
public anywheresoftware.b4a.objects.Timer _timer1 = null;
public anywheresoftware.b4a.samples.httputils2.httpjob _http = null;
public anywheresoftware.b4a.objects.ScrollViewWrapper _scrollview1 = null;
public anywheresoftware.b4a.objects.collections.List _artist = null;
public anywheresoftware.b4a.objects.collections.List _name = null;
public anywheresoftware.b4a.objects.collections.List _image = null;
public anywheresoftware.b4a.objects.collections.List _linkmusic = null;
public anywheresoftware.b4a.objects.collections.List _sahr = null;
public anywheresoftware.b4a.objects.collections.List _id = null;
public anywheresoftware.b4a.objects.collections.JSONParser _josn = null;
public anywheresoftware.b4a.objects.collections.List _list = null;
public anywheresoftware.b4a.objects.collections.Map _map = null;
public static int _left = 0;
public static int _top = 0;
public anywheresoftware.b4a.objects.LabelWrapper _namelabel = null;
public anywheresoftware.b4a.objects.LabelWrapper _artistlabel = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _imagev = null;
public anywheresoftware.b4a.objects.AnimationWrapper _anim = null;
public anywheresoftware.b4a.objects.PanelWrapper _panel1 = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _imageview1 = null;
public anywheresoftware.b4a.objects.LabelWrapper _xname = null;
public anywheresoftware.b4a.objects.ButtonWrapper _play = null;
public anywheresoftware.b4a.objects.ButtonWrapper _pause = null;
public anywheresoftware.b4a.objects.LabelWrapper _time = null;
public static String _linkplay = "";
public static boolean _isplaying = false;
public anywheresoftware.b4a.objects.Timer _tin = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _imageview3 = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _imageview4 = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _imageview5 = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _imageview6 = null;
public anywheresoftware.b4a.objects.LabelWrapper _sahr1 = null;
public anywheresoftware.b4a.objects.ButtonWrapper _button3 = null;
public anywheresoftware.b4a.objects.ButtonWrapper _button2 = null;
public anywheresoftware.b4a.objects.ButtonWrapper _button1 = null;
public anywheresoftware.b4a.sql.SQL _sql1 = null;
public anywheresoftware.b4a.sql.SQL.CursorWrapper _cursor1 = null;
public static String _idi = "";
public anywheresoftware.b4a.objects.ButtonWrapper _button5 = null;
public anywheresoftware.b4a.samples.httputils2.httputils2service _httputils2service = null;
public com.doomadi.artist.main _main = null;
public com.doomadi.artist.starter _starter = null;
public com.doomadi.artist.ostan _ostan = null;
public com.doomadi.artist.ostanha _ostanha = null;
public com.doomadi.artist.sistan _sistan = null;
public com.doomadi.artist.mazhabi _mazhabi = null;
public com.doomadi.artist.rezaee _rezaee = null;
public com.doomadi.artist.yusofi _yusofi = null;
public com.doomadi.artist.jafari _jafari = null;
public com.doomadi.artist.neyshboor _neyshboor = null;
public com.doomadi.artist.tamas _tamas = null;
public com.doomadi.artist.kashmar _kashmar = null;
public com.doomadi.artist.mashhad _mashhad = null;
public com.doomadi.artist.golestan _golestan = null;
public com.doomadi.artist.behrozvazefeh _behrozvazefeh = null;
public com.doomadi.artist.siyavashyosofi _siyavashyosofi = null;
public com.doomadi.artist.yasermahmoodi _yasermahmoodi = null;
public com.doomadi.artist.azgharbi _azgharbi = null;
public com.doomadi.artist.tehran _tehran = null;
public com.doomadi.artist.azsharghi _azsharghi = null;
public com.doomadi.artist.elam _elam = null;
public com.doomadi.artist.zanjan _zanjan = null;
public com.doomadi.artist.aliabdol _aliabdol = null;
public com.doomadi.artist.ammar _ammar = null;
public com.doomadi.artist.barshan _barshan = null;
public com.doomadi.artist.tarkesh _tarkesh = null;
public com.doomadi.artist.raeesi _raeesi = null;
public com.doomadi.artist.razavi _razavi = null;
public com.doomadi.artist.moosazadeh _moosazadeh = null;
public com.doomadi.artist.torbat _torbat = null;
public com.doomadi.artist.yaghoobi _yaghoobi = null;
public com.doomadi.artist.bejstan _bejstan = null;
public com.doomadi.artist.sohyli _sohyli = null;
public com.doomadi.artist.dori _dori = null;
public com.doomadi.artist.changi _changi = null;
public com.doomadi.artist.hormozgan _hormozgan = null;
public com.doomadi.artist.rajabzadeh _rajabzadeh = null;
public com.doomadi.artist.sharee _sharee = null;
public com.doomadi.artist.fars _fars = null;
public com.doomadi.artist.gonabad _gonabad = null;
public com.doomadi.artist.barati _barati = null;
public com.doomadi.artist.alinzhad _alinzhad = null;
public com.doomadi.artist.mazandaran _mazandaran = null;
public com.doomadi.artist.kermanshah _kermanshah = null;
public com.doomadi.artist.azizvisi _azizvisi = null;
public com.doomadi.artist.pazvari _pazvari = null;
public com.doomadi.artist.shomalikh _shomalikh = null;
public com.doomadi.artist.esfarayen _esfarayen = null;
public com.doomadi.artist.taghizadeh _taghizadeh = null;
public com.doomadi.artist.shirvan _shirvan = null;
public com.doomadi.artist.eghbali _eghbali = null;
public com.doomadi.artist.torbatjam _torbatjam = null;
public com.doomadi.artist.delnavaz _delnavaz = null;
public com.doomadi.artist.doomadi _doomadi = null;
public com.doomadi.artist.alborz _alborz = null;
public com.doomadi.artist.javan _javan = null;
public com.doomadi.artist.alavivahid _alavivahid = null;
public com.doomadi.artist.seydi _seydi = null;
public com.doomadi.artist.sedooghi _sedooghi = null;
public com.doomadi.artist.mamadjan _mamadjan = null;
public com.doomadi.artist.abdini _abdini = null;
public com.doomadi.artist.hasanzadeh _hasanzadeh = null;
public com.doomadi.artist.choopan _choopan = null;
public com.doomadi.artist.zhiyan _zhiyan = null;
public com.doomadi.artist.barghi _barghi = null;
public com.doomadi.artist.amjadiyan _amjadiyan = null;
public com.doomadi.artist.abdi _abdi = null;
public com.doomadi.artist.khakshoor _khakshoor = null;
public com.doomadi.artist.falah _falah = null;
public com.doomadi.artist.jafarzadeh _jafarzadeh = null;
public com.doomadi.artist.dolat _dolat = null;
public com.doomadi.artist.asheghi _asheghi = null;
public com.doomadi.artist.delafkar _delafkar = null;
public com.doomadi.artist.baghshani _baghshani = null;
public com.doomadi.artist.hydari _hydari = null;
public com.doomadi.artist.bakerdar _bakerdar = null;
public com.doomadi.artist.ghaemi _ghaemi = null;
public com.doomadi.artist.mirzadeh _mirzadeh = null;
public com.doomadi.artist.karaj _karaj = null;
public com.doomadi.artist.asadyan _asadyan = null;
public com.doomadi.artist.yousof _yousof = null;
public com.doomadi.artist.sabzevar _sabzevar = null;
public com.doomadi.artist.tozih _tozih = null;
public com.doomadi.artist.imagedownloader _imagedownloader = null;
public com.doomadi.artist.mortzajafarzadeh _mortzajafarzadeh = null;
public com.doomadi.artist.charmahal _charmahal = null;
public com.doomadi.artist.didar _didar = null;
public com.doomadi.artist.zilabi _zilabi = null;
public com.doomadi.artist.lorestan _lorestan = null;
public com.doomadi.artist.yazd _yazd = null;
public com.doomadi.artist.jonoobi _jonoobi = null;
public com.doomadi.artist.ehsantavakoli _ehsantavakoli = null;
public com.doomadi.artist.amirhosinazari _amirhosinazari = null;
public com.doomadi.artist.jalalfarajiyan _jalalfarajiyan = null;
public com.doomadi.artist.sajadrazmjoo _sajadrazmjoo = null;
public com.doomadi.artist.mohamadazimi _mohamadazimi = null;
public com.doomadi.artist.ardabil _ardabil = null;

public static void initializeProcessGlobals() {
             try {
                Class.forName(BA.applicationContext.getPackageName() + ".main").getMethod("initializeProcessGlobals").invoke(null, null);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
}
public static String  _activity_create(boolean _firsttime) throws Exception{
 //BA.debugLineNum = 59;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 60;BA.debugLine="Activity.LoadLayout(\"layout2\")";
mostCurrent._activity.LoadLayout("layout2",mostCurrent.activityBA);
 //BA.debugLineNum = 62;BA.debugLine="Button5.Text=\"حذف علاقمندی\"";
mostCurrent._button5.setText(BA.ObjectToCharSequence("حذف علاقمندی"));
 //BA.debugLineNum = 63;BA.debugLine="ProgressDialogShow2(\"Loading...\",False)";
anywheresoftware.b4a.keywords.Common.ProgressDialogShow2(mostCurrent.activityBA,BA.ObjectToCharSequence("Loading..."),anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 65;BA.debugLine="http.Initialize(\"http\",Me)";
mostCurrent._http._initialize(processBA,"http",a.getObject());
 //BA.debugLineNum = 67;BA.debugLine="Artist.Initialize";
mostCurrent._artist.Initialize();
 //BA.debugLineNum = 68;BA.debugLine="Name.Initialize";
mostCurrent._name.Initialize();
 //BA.debugLineNum = 69;BA.debugLine="Image.Initialize";
mostCurrent._image.Initialize();
 //BA.debugLineNum = 70;BA.debugLine="linkMusic.Initialize";
mostCurrent._linkmusic.Initialize();
 //BA.debugLineNum = 71;BA.debugLine="sahr.Initialize";
mostCurrent._sahr.Initialize();
 //BA.debugLineNum = 72;BA.debugLine="music.initialize(SeekBar1,string1)";
mostCurrent._music.initialize((android.widget.SeekBar)(mostCurrent._seekbar1.getObject()),mostCurrent._string1);
 //BA.debugLineNum = 73;BA.debugLine="id.Initialize";
mostCurrent._id.Initialize();
 //BA.debugLineNum = 75;BA.debugLine="If File.Exists(File.DirRootExternal,\"data.db\")=Fa";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirRootExternal(),"data.db")==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 76;BA.debugLine="File.Copy(File.DirAssets,\"data.db\",File.DirRootE";
anywheresoftware.b4a.keywords.Common.File.Copy(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"data.db",anywheresoftware.b4a.keywords.Common.File.getDirRootExternal(),"data.db");
 };
 //BA.debugLineNum = 78;BA.debugLine="If sql1.IsInitialized=False Then";
if (mostCurrent._sql1.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 79;BA.debugLine="sql1.Initialize(File.DirRootExternal,\"data.db\",F";
mostCurrent._sql1.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirRootExternal(),"data.db",anywheresoftware.b4a.keywords.Common.False);
 };
 //BA.debugLineNum = 81;BA.debugLine="load_data";
_load_data();
 //BA.debugLineNum = 82;BA.debugLine="End Sub";
return "";
}
public static boolean  _activity_keypress(int _keycode) throws Exception{
String _str = "";
 //BA.debugLineNum = 248;BA.debugLine="Sub Activity_KeyPress (KeyCode As Int) As Boolean";
 //BA.debugLineNum = 249;BA.debugLine="If KeyCode=KeyCodes.KEYCODE_BACK Then";
if (_keycode==anywheresoftware.b4a.keywords.Common.KeyCodes.KEYCODE_BACK) { 
 //BA.debugLineNum = 250;BA.debugLine="If Panel1.Visible=True Then";
if (mostCurrent._panel1.getVisible()==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 251;BA.debugLine="If isplaying=True Then";
if (_isplaying==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 252;BA.debugLine="Dim str As String";
_str = "";
 //BA.debugLineNum = 253;BA.debugLine="str=Msgbox2(\"موزیک در حال پخش قطع شود؟؟\",\"doom";
_str = BA.NumberToString(anywheresoftware.b4a.keywords.Common.Msgbox2(BA.ObjectToCharSequence("موزیک در حال پخش قطع شود؟؟"),BA.ObjectToCharSequence("doomadi music?"),"آره","نه","",(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null),mostCurrent.activityBA));
 //BA.debugLineNum = 254;BA.debugLine="If str=DialogResponse.POSITIVE Then";
if ((_str).equals(BA.NumberToString(anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE))) { 
 //BA.debugLineNum = 255;BA.debugLine="music.stop";
mostCurrent._music.stop();
 //BA.debugLineNum = 256;BA.debugLine="Panel1.SetVisibleAnimated(500,False)";
mostCurrent._panel1.SetVisibleAnimated((int) (500),anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 257;BA.debugLine="isplaying=False";
_isplaying = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 258;BA.debugLine="time.Text=\"ـ\"";
mostCurrent._time.setText(BA.ObjectToCharSequence("ـ"));
 //BA.debugLineNum = 259;BA.debugLine="SeekBar1.Visible=False";
mostCurrent._seekbar1.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 260;BA.debugLine="timer1.Enabled=False";
mostCurrent._timer1.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 261;BA.debugLine="tin.Initialize(\"tin\",1000)";
mostCurrent._tin.Initialize(processBA,"tin",(long) (1000));
 //BA.debugLineNum = 262;BA.debugLine="tin.Enabled=True";
mostCurrent._tin.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 263;BA.debugLine="ImageView6.Visible=True";
mostCurrent._imageview6.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 265;BA.debugLine="ImageView5.Visible=True";
mostCurrent._imageview5.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 267;BA.debugLine="ImageView4.Visible=True";
mostCurrent._imageview4.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 269;BA.debugLine="ImageView3.Visible=True";
mostCurrent._imageview3.setVisible(anywheresoftware.b4a.keywords.Common.True);
 }else {
 //BA.debugLineNum = 275;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 };
 }else {
 //BA.debugLineNum = 278;BA.debugLine="ImageView6.Visible=True";
mostCurrent._imageview6.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 280;BA.debugLine="ImageView5.Visible=True";
mostCurrent._imageview5.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 282;BA.debugLine="ImageView4.Visible=True";
mostCurrent._imageview4.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 284;BA.debugLine="ImageView3.Visible=True";
mostCurrent._imageview3.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 289;BA.debugLine="Panel1.SetVisibleAnimated(500,False)";
mostCurrent._panel1.SetVisibleAnimated((int) (500),anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 290;BA.debugLine="tin.Initialize(\"tin\",500)";
mostCurrent._tin.Initialize(processBA,"tin",(long) (500));
 //BA.debugLineNum = 291;BA.debugLine="tin.Enabled=True";
mostCurrent._tin.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 };
 //BA.debugLineNum = 295;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 };
 };
 //BA.debugLineNum = 299;BA.debugLine="If KeyCode = KeyCodes.KEYCODE_BACK Then";
if (_keycode==anywheresoftware.b4a.keywords.Common.KeyCodes.KEYCODE_BACK) { 
 //BA.debugLineNum = 302;BA.debugLine="StartActivity(Main)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._main.getObject()));
 //BA.debugLineNum = 303;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 304;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 };
 //BA.debugLineNum = 308;BA.debugLine="End Sub";
return false;
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 86;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 87;BA.debugLine="If isplaying=True Then";
if (_isplaying==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 89;BA.debugLine="timer1.Enabled=False";
mostCurrent._timer1.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 };
 //BA.debugLineNum = 93;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 83;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 85;BA.debugLine="End Sub";
return "";
}
public static String  _button1_click() throws Exception{
 //BA.debugLineNum = 235;BA.debugLine="Sub Button1_Click";
 //BA.debugLineNum = 237;BA.debugLine="End Sub";
return "";
}
public static String  _button2_click() throws Exception{
 //BA.debugLineNum = 239;BA.debugLine="Sub Button2_Click";
 //BA.debugLineNum = 241;BA.debugLine="End Sub";
return "";
}
public static String  _button3_click() throws Exception{
 //BA.debugLineNum = 243;BA.debugLine="Sub Button3_Click";
 //BA.debugLineNum = 245;BA.debugLine="End Sub";
return "";
}
public static String  _button5_click() throws Exception{
anywheresoftware.b4a.objects.ButtonWrapper _v = null;
 //BA.debugLineNum = 167;BA.debugLine="Sub Button5_Click";
 //BA.debugLineNum = 168;BA.debugLine="Dim v As Button";
_v = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 169;BA.debugLine="v=Sender";
_v.setObject((android.widget.Button)(anywheresoftware.b4a.keywords.Common.Sender(mostCurrent.activityBA)));
 //BA.debugLineNum = 171;BA.debugLine="sql1.ExecNonQuery(\"DELETE FROM My_DB where id ='\"";
mostCurrent._sql1.ExecNonQuery("DELETE FROM My_DB where id ='"+mostCurrent._idi+"' ");
 //BA.debugLineNum = 172;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 173;BA.debugLine="StartActivity(Me)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,a.getObject());
 //BA.debugLineNum = 174;BA.debugLine="End Sub";
return "";
}
public static String  _download(anywheresoftware.b4a.objects.ImageViewWrapper _imagex,String _link) throws Exception{
anywheresoftware.b4a.objects.collections.Map _links = null;
 //BA.debugLineNum = 137;BA.debugLine="Private Sub download(Imagex As ImageView,link As S";
 //BA.debugLineNum = 138;BA.debugLine="Dim links As Map";
_links = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 139;BA.debugLine="links.Initialize";
_links.Initialize();
 //BA.debugLineNum = 140;BA.debugLine="links.Put(Imagex,link)";
_links.Put((Object)(_imagex.getObject()),(Object)(_link));
 //BA.debugLineNum = 141;BA.debugLine="CallSubDelayed2(ImageDownloader, \"Download\", link";
anywheresoftware.b4a.keywords.Common.CallSubDelayed2(processBA,(Object)(mostCurrent._imagedownloader.getObject()),"Download",(Object)(_links));
 //BA.debugLineNum = 143;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 12;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 13;BA.debugLine="Dim p As Panel";
mostCurrent._p = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 14;BA.debugLine="Dim music As MusicStramer";
mostCurrent._music = new MuiscStramer.MusicStramer();
 //BA.debugLineNum = 15;BA.debugLine="Private SeekBar1 As SeekBar";
mostCurrent._seekbar1 = new anywheresoftware.b4a.objects.SeekBarWrapper();
 //BA.debugLineNum = 16;BA.debugLine="Dim string1 As String";
mostCurrent._string1 = "";
 //BA.debugLineNum = 17;BA.debugLine="Dim timer1 As Timer";
mostCurrent._timer1 = new anywheresoftware.b4a.objects.Timer();
 //BA.debugLineNum = 18;BA.debugLine="Dim http As HttpJob";
mostCurrent._http = new anywheresoftware.b4a.samples.httputils2.httpjob();
 //BA.debugLineNum = 19;BA.debugLine="Private ScrollView1 As ScrollView";
mostCurrent._scrollview1 = new anywheresoftware.b4a.objects.ScrollViewWrapper();
 //BA.debugLineNum = 20;BA.debugLine="Dim Artist,Name,Image,linkMusic,sahr,id As List";
mostCurrent._artist = new anywheresoftware.b4a.objects.collections.List();
mostCurrent._name = new anywheresoftware.b4a.objects.collections.List();
mostCurrent._image = new anywheresoftware.b4a.objects.collections.List();
mostCurrent._linkmusic = new anywheresoftware.b4a.objects.collections.List();
mostCurrent._sahr = new anywheresoftware.b4a.objects.collections.List();
mostCurrent._id = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 21;BA.debugLine="Dim josn As  JSONParser";
mostCurrent._josn = new anywheresoftware.b4a.objects.collections.JSONParser();
 //BA.debugLineNum = 22;BA.debugLine="Dim list As List";
mostCurrent._list = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 23;BA.debugLine="Dim map As Map";
mostCurrent._map = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 24;BA.debugLine="Dim left , top As Int";
_left = 0;
_top = 0;
 //BA.debugLineNum = 25;BA.debugLine="left =2%x";
_left = anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (2),mostCurrent.activityBA);
 //BA.debugLineNum = 26;BA.debugLine="top = 1%y";
_top = anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (1),mostCurrent.activityBA);
 //BA.debugLineNum = 27;BA.debugLine="Private nameLabel As Label";
mostCurrent._namelabel = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 28;BA.debugLine="Private ArtistLabel As Label";
mostCurrent._artistlabel = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 29;BA.debugLine="Private Imagev As ImageView";
mostCurrent._imagev = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 30;BA.debugLine="Dim anim As Animation";
mostCurrent._anim = new anywheresoftware.b4a.objects.AnimationWrapper();
 //BA.debugLineNum = 31;BA.debugLine="Private Panel1 As Panel";
mostCurrent._panel1 = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 32;BA.debugLine="Private ImageView1 As ImageView";
mostCurrent._imageview1 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 33;BA.debugLine="Private xname As Label";
mostCurrent._xname = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 34;BA.debugLine="Private play As Button";
mostCurrent._play = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 35;BA.debugLine="Private pause As Button";
mostCurrent._pause = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 36;BA.debugLine="Private time As Label";
mostCurrent._time = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 37;BA.debugLine="Dim linkplay As String";
mostCurrent._linkplay = "";
 //BA.debugLineNum = 38;BA.debugLine="Dim isplaying As Boolean";
_isplaying = false;
 //BA.debugLineNum = 39;BA.debugLine="isplaying=False";
_isplaying = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 40;BA.debugLine="Dim tin As Timer";
mostCurrent._tin = new anywheresoftware.b4a.objects.Timer();
 //BA.debugLineNum = 42;BA.debugLine="Private ImageView3 As ImageView";
mostCurrent._imageview3 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 43;BA.debugLine="Private ImageView4 As ImageView";
mostCurrent._imageview4 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 44;BA.debugLine="Private ImageView5 As ImageView";
mostCurrent._imageview5 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 45;BA.debugLine="Private ImageView6 As ImageView";
mostCurrent._imageview6 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 47;BA.debugLine="Private sahr1 As Label";
mostCurrent._sahr1 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 48;BA.debugLine="Private Button3 As Button";
mostCurrent._button3 = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 49;BA.debugLine="Private Button2 As Button";
mostCurrent._button2 = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 50;BA.debugLine="Private Button1 As Button";
mostCurrent._button1 = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 51;BA.debugLine="Dim sql1 As SQL";
mostCurrent._sql1 = new anywheresoftware.b4a.sql.SQL();
 //BA.debugLineNum = 52;BA.debugLine="Dim cursor1 As Cursor";
mostCurrent._cursor1 = new anywheresoftware.b4a.sql.SQL.CursorWrapper();
 //BA.debugLineNum = 54;BA.debugLine="Dim idi As String";
mostCurrent._idi = "";
 //BA.debugLineNum = 56;BA.debugLine="Private Button5 As Button";
mostCurrent._button5 = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 57;BA.debugLine="End Sub";
return "";
}
public static String  _imageview3_click() throws Exception{
 //BA.debugLineNum = 230;BA.debugLine="Sub ImageView3_Click";
 //BA.debugLineNum = 231;BA.debugLine="StartActivity(Main)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._main.getObject()));
 //BA.debugLineNum = 232;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 233;BA.debugLine="End Sub";
return "";
}
public static String  _imageview4_click() throws Exception{
 //BA.debugLineNum = 226;BA.debugLine="Sub ImageView4_Click";
 //BA.debugLineNum = 228;BA.debugLine="End Sub";
return "";
}
public static String  _imageview5_click() throws Exception{
 //BA.debugLineNum = 221;BA.debugLine="Sub ImageView5_Click";
 //BA.debugLineNum = 224;BA.debugLine="End Sub";
return "";
}
public static String  _imageview6_click() throws Exception{
 //BA.debugLineNum = 216;BA.debugLine="Sub ImageView6_Click";
 //BA.debugLineNum = 219;BA.debugLine="End Sub";
return "";
}
public static String  _load_data() throws Exception{
int _i = 0;
anywheresoftware.b4a.objects.drawable.ColorDrawable _clr = null;
 //BA.debugLineNum = 96;BA.debugLine="Sub load_data";
 //BA.debugLineNum = 99;BA.debugLine="cursor1 = sql1.ExecQuery(\"SELECT * FROM My_DB\")";
mostCurrent._cursor1.setObject((android.database.Cursor)(mostCurrent._sql1.ExecQuery("SELECT * FROM My_DB")));
 //BA.debugLineNum = 100;BA.debugLine="Dim i As Int";
_i = 0;
 //BA.debugLineNum = 101;BA.debugLine="For i=0 To cursor1.RowCount-1";
{
final int step3 = 1;
final int limit3 = (int) (mostCurrent._cursor1.getRowCount()-1);
_i = (int) (0) ;
for (;(step3 > 0 && _i <= limit3) || (step3 < 0 && _i >= limit3) ;_i = ((int)(0 + _i + step3))  ) {
 //BA.debugLineNum = 102;BA.debugLine="cursor1.Position=i";
mostCurrent._cursor1.setPosition(_i);
 //BA.debugLineNum = 103;BA.debugLine="Log(cursor1.GetString(\"id\")&  \"|\" &cursor1.GetSt";
anywheresoftware.b4a.keywords.Common.Log(mostCurrent._cursor1.GetString("id")+"|"+mostCurrent._cursor1.GetString("artist")+"|"+mostCurrent._cursor1.GetString("name")+"|"+mostCurrent._cursor1.GetString("image")+"|"+mostCurrent._cursor1.GetString("music")+"|"+mostCurrent._cursor1.GetString("sahr")+"|"+mostCurrent._cursor1.GetString("telegram")+"|"+mostCurrent._cursor1.GetString("link1")+"|"+mostCurrent._cursor1.GetString("VEIW"));
 //BA.debugLineNum = 107;BA.debugLine="Artist.Add(cursor1.GetString(\"artist\"))";
mostCurrent._artist.Add((Object)(mostCurrent._cursor1.GetString("artist")));
 //BA.debugLineNum = 108;BA.debugLine="Name.Add(cursor1.GetString(\"name\"))";
mostCurrent._name.Add((Object)(mostCurrent._cursor1.GetString("name")));
 //BA.debugLineNum = 109;BA.debugLine="Image.Add(cursor1.GetString(\"image\"))";
mostCurrent._image.Add((Object)(mostCurrent._cursor1.GetString("image")));
 //BA.debugLineNum = 110;BA.debugLine="linkMusic.Add(cursor1.GetString(\"music\"))";
mostCurrent._linkmusic.Add((Object)(mostCurrent._cursor1.GetString("music")));
 //BA.debugLineNum = 111;BA.debugLine="sahr.Add(cursor1.GetString(\"sahr\"))";
mostCurrent._sahr.Add((Object)(mostCurrent._cursor1.GetString("sahr")));
 //BA.debugLineNum = 112;BA.debugLine="id.Add(cursor1.GetString(\"id\"))";
mostCurrent._id.Add((Object)(mostCurrent._cursor1.GetString("id")));
 //BA.debugLineNum = 113;BA.debugLine="Dim p As Panel";
mostCurrent._p = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 114;BA.debugLine="p.Initialize(\"p\")";
mostCurrent._p.Initialize(mostCurrent.activityBA,"p");
 //BA.debugLineNum = 115;BA.debugLine="Dim clr As ColorDrawable";
_clr = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 116;BA.debugLine="clr.Initialize(Colors.White,2)";
_clr.Initialize(anywheresoftware.b4a.keywords.Common.Colors.White,(int) (2));
 //BA.debugLineNum = 117;BA.debugLine="p.Visible=False";
mostCurrent._p.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 118;BA.debugLine="ScrollView1.Panel.AddView(p,left,top,47%x,43%y)";
mostCurrent._scrollview1.getPanel().AddView((android.view.View)(mostCurrent._p.getObject()),_left,_top,anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (47),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (43),mostCurrent.activityBA));
 //BA.debugLineNum = 119;BA.debugLine="p.SetVisibleAnimated(600,True)";
mostCurrent._p.SetVisibleAnimated((int) (600),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 120;BA.debugLine="left = left + 49%x";
_left = (int) (_left+anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (49),mostCurrent.activityBA));
 //BA.debugLineNum = 121;BA.debugLine="p.Tag = i";
mostCurrent._p.setTag((Object)(_i));
 //BA.debugLineNum = 122;BA.debugLine="If i Mod 2 = 1 Then";
if (_i%2==1) { 
 //BA.debugLineNum = 123;BA.debugLine="top = top + 44%y";
_top = (int) (_top+anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (44),mostCurrent.activityBA));
 //BA.debugLineNum = 124;BA.debugLine="ScrollView1.Panel.Height = top + 50%y";
mostCurrent._scrollview1.getPanel().setHeight((int) (_top+anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (50),mostCurrent.activityBA)));
 //BA.debugLineNum = 125;BA.debugLine="left = 2%x";
_left = anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (2),mostCurrent.activityBA);
 };
 //BA.debugLineNum = 127;BA.debugLine="p.LoadLayout(\"jk\")";
mostCurrent._p.LoadLayout("jk",mostCurrent.activityBA);
 //BA.debugLineNum = 128;BA.debugLine="p.Background=clr";
mostCurrent._p.setBackground((android.graphics.drawable.Drawable)(_clr.getObject()));
 //BA.debugLineNum = 129;BA.debugLine="download(Imagev,cursor1.GetString(\"image\"))";
_download(mostCurrent._imagev,mostCurrent._cursor1.GetString("image"));
 //BA.debugLineNum = 130;BA.debugLine="ArtistLabel.Text=Artist.Get(i)";
mostCurrent._artistlabel.setText(BA.ObjectToCharSequence(mostCurrent._artist.Get(_i)));
 //BA.debugLineNum = 131;BA.debugLine="nameLabel.Text=Name.Get(i)";
mostCurrent._namelabel.setText(BA.ObjectToCharSequence(mostCurrent._name.Get(_i)));
 //BA.debugLineNum = 132;BA.debugLine="sahr1.Text=sahr.Get(i)";
mostCurrent._sahr1.setText(BA.ObjectToCharSequence(mostCurrent._sahr.Get(_i)));
 }
};
 //BA.debugLineNum = 134;BA.debugLine="ProgressDialogHide";
anywheresoftware.b4a.keywords.Common.ProgressDialogHide();
 //BA.debugLineNum = 135;BA.debugLine="End Sub";
return "";
}
public static String  _p_click() throws Exception{
anywheresoftware.b4a.objects.PanelWrapper _v = null;
 //BA.debugLineNum = 144;BA.debugLine="Sub p_Click";
 //BA.debugLineNum = 145;BA.debugLine="Dim v As Panel";
_v = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 146;BA.debugLine="v=Sender";
_v.setObject((android.view.ViewGroup)(anywheresoftware.b4a.keywords.Common.Sender(mostCurrent.activityBA)));
 //BA.debugLineNum = 147;BA.debugLine="Panel1.SetVisibleAnimated(500,True)";
mostCurrent._panel1.SetVisibleAnimated((int) (500),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 149;BA.debugLine="download(ImageView1,Image.Get(v.Tag))";
_download(mostCurrent._imageview1,BA.ObjectToString(mostCurrent._image.Get((int)(BA.ObjectToNumber(_v.getTag())))));
 //BA.debugLineNum = 150;BA.debugLine="xname.Text=Artist.Get(v.Tag)&\"\"&\" \"&Name.Get(v.Ta";
mostCurrent._xname.setText(BA.ObjectToCharSequence(BA.ObjectToString(mostCurrent._artist.Get((int)(BA.ObjectToNumber(_v.getTag()))))+""+" "+BA.ObjectToString(mostCurrent._name.Get((int)(BA.ObjectToNumber(_v.getTag()))))));
 //BA.debugLineNum = 151;BA.debugLine="linkplay=linkMusic.Get(v.Tag)";
mostCurrent._linkplay = BA.ObjectToString(mostCurrent._linkmusic.Get((int)(BA.ObjectToNumber(_v.getTag()))));
 //BA.debugLineNum = 152;BA.debugLine="idi=id.Get(v.Tag)";
mostCurrent._idi = BA.ObjectToString(mostCurrent._id.Get((int)(BA.ObjectToNumber(_v.getTag()))));
 //BA.debugLineNum = 153;BA.debugLine="ImageView6.Visible=False";
mostCurrent._imageview6.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 155;BA.debugLine="ImageView5.Visible=False";
mostCurrent._imageview5.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 157;BA.debugLine="ImageView4.Visible=False";
mostCurrent._imageview4.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 159;BA.debugLine="ImageView3.Visible=False";
mostCurrent._imageview3.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 164;BA.debugLine="End Sub";
return "";
}
public static String  _panel1_touch(int _action,float _x,float _y) throws Exception{
 //BA.debugLineNum = 204;BA.debugLine="Sub Panel1_Touch (Action As Int, X As Float, Y As";
 //BA.debugLineNum = 206;BA.debugLine="End Sub";
return "";
}
public static String  _pause_click() throws Exception{
 //BA.debugLineNum = 190;BA.debugLine="Sub pause_Click";
 //BA.debugLineNum = 191;BA.debugLine="If isplaying=True Then";
if (_isplaying==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 192;BA.debugLine="music.pause";
mostCurrent._music.pause();
 //BA.debugLineNum = 193;BA.debugLine="isplaying=True";
_isplaying = anywheresoftware.b4a.keywords.Common.True;
 };
 //BA.debugLineNum = 195;BA.debugLine="End Sub";
return "";
}
public static String  _play_click() throws Exception{
 //BA.debugLineNum = 176;BA.debugLine="Sub play_Click";
 //BA.debugLineNum = 178;BA.debugLine="If isplaying=False Then";
if (_isplaying==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 179;BA.debugLine="music.Create(linkplay)";
mostCurrent._music.Create(processBA,mostCurrent._linkplay);
 //BA.debugLineNum = 180;BA.debugLine="music.Play";
mostCurrent._music.Play();
 //BA.debugLineNum = 181;BA.debugLine="timer1.Initialize(\"t\",5)";
mostCurrent._timer1.Initialize(processBA,"t",(long) (5));
 //BA.debugLineNum = 182;BA.debugLine="timer1.Enabled=True";
mostCurrent._timer1.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 183;BA.debugLine="isplaying=True";
_isplaying = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 184;BA.debugLine="SeekBar1.Visible=True";
mostCurrent._seekbar1.setVisible(anywheresoftware.b4a.keywords.Common.True);
 }else {
 //BA.debugLineNum = 186;BA.debugLine="music.Play";
mostCurrent._music.Play();
 //BA.debugLineNum = 187;BA.debugLine="isplaying=True";
_isplaying = anywheresoftware.b4a.keywords.Common.True;
 };
 //BA.debugLineNum = 189;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 8;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 10;BA.debugLine="End Sub";
return "";
}
public static String  _t_tick() throws Exception{
 //BA.debugLineNum = 196;BA.debugLine="Sub t_Tick";
 //BA.debugLineNum = 198;BA.debugLine="time.Text=music.PlayingTime";
mostCurrent._time.setText(BA.ObjectToCharSequence(mostCurrent._music.PlayingTime()));
 //BA.debugLineNum = 200;BA.debugLine="timer1.Enabled=True";
mostCurrent._timer1.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 202;BA.debugLine="End Sub";
return "";
}
public static String  _tin_tick() throws Exception{
 //BA.debugLineNum = 208;BA.debugLine="Sub tin_Tick";
 //BA.debugLineNum = 209;BA.debugLine="ImageView1.SetBackgroundImage(Null)";
mostCurrent._imageview1.SetBackgroundImageNew((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 211;BA.debugLine="tin.Enabled=False";
mostCurrent._tin.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 212;BA.debugLine="End Sub";
return "";
}
}
