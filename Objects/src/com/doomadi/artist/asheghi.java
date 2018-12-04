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

public class asheghi extends Activity implements B4AActivity{
	public static asheghi mostCurrent;
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
			processBA = new BA(this.getApplicationContext(), null, null, "com.doomadi.artist", "com.doomadi.artist.asheghi");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (asheghi).");
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
		activityBA = new BA(this, layout, processBA, "com.doomadi.artist", "com.doomadi.artist.asheghi");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "com.doomadi.artist.asheghi", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (asheghi) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (asheghi) Resume **");
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
		return asheghi.class;
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
        BA.LogInfo("** Activity (asheghi) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
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
            BA.LogInfo("** Activity (asheghi) Resume **");
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
public static int _chk = 0;
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
public anywheresoftware.b4a.objects.collections.List _telegram1 = null;
public anywheresoftware.b4a.objects.collections.List _link1 = null;
public anywheresoftware.b4a.objects.collections.List _veiw = null;
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
public static String _telegram2 = "";
public static String _w = "";
public static String _ww = "";
public static String _www = "";
public static String _wwww = "";
public static String _wwwww = "";
public static String _wwwwww = "";
public static String _link2 = "";
public static String _site2 = "";
public static boolean _isplaying = false;
public anywheresoftware.b4a.objects.Timer _tin = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _imageview3 = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _imageview4 = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _imageview5 = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _imageview6 = null;
public anywheresoftware.b4a.objects.LabelWrapper _sahr1 = null;
public anywheresoftware.b4a.objects.LabelWrapper _number1 = null;
public anywheresoftware.b4a.objects.ButtonWrapper _button3 = null;
public anywheresoftware.b4a.objects.ButtonWrapper _button2 = null;
public anywheresoftware.b4a.objects.ButtonWrapper _button1 = null;
public anywheresoftware.b4a.objects.ButtonWrapper _button4 = null;
public anywheresoftware.b4a.samples.httputils2.httpjob _ht = null;
public anywheresoftware.b4a.objects.NotificationWrapper _no = null;
public static String _link3 = "";
public static String _name3 = "";
public static String _dier = "";
public static String[] _s = null;
public static String _ss = "";
public anywheresoftware.b4a.objects.MediaPlayerWrapper _sound = null;
public static int _asa = 0;
public anywheresoftware.b4a.objects.LabelWrapper _id1 = null;
public static String _qqq = "";
public static String _qqqq = "";
public static String _sahr11 = "";
public anywheresoftware.b4a.objects.LabelWrapper _label20 = null;
public anywheresoftware.b4a.objects.LabelWrapper _label2 = null;
public anywheresoftware.b4a.sql.SQL _sql1 = null;
public anywheresoftware.b4a.sql.SQL.CursorWrapper _cursor1 = null;
public anywheresoftware.b4a.objects.ButtonWrapper _button100 = null;
public anywheresoftware.b4a.objects.ButtonWrapper _button5 = null;
public anywheresoftware.b4a.objects.Timer _tmback = null;
public static byte _intback = (byte)0;
public anywheresoftware.b4a.objects.LabelWrapper _label3 = null;
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
public com.doomadi.artist.a _a = null;
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
 //BA.debugLineNum = 92;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 93;BA.debugLine="Activity.LoadLayout(\"layout2\")";
mostCurrent._activity.LoadLayout("layout2",mostCurrent.activityBA);
 //BA.debugLineNum = 94;BA.debugLine="File.MakeDir(File.DirRootExternal, \"doholimusic\")";
anywheresoftware.b4a.keywords.Common.File.MakeDir(anywheresoftware.b4a.keywords.Common.File.getDirRootExternal(),"doholimusic");
 //BA.debugLineNum = 96;BA.debugLine="ProgressDialogShow2(\"Loading...\",False)";
anywheresoftware.b4a.keywords.Common.ProgressDialogShow2(mostCurrent.activityBA,BA.ObjectToCharSequence("Loading..."),anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 101;BA.debugLine="http.Initialize(\"http\",Me)";
mostCurrent._http._initialize(processBA,"http",asheghi.getObject());
 //BA.debugLineNum = 102;BA.debugLine="LoadDB(\"SELECT * FROM ashghi ORDER BY id DESC LIM";
_loaddb("SELECT * FROM ashghi ORDER BY id DESC LIMIT 0,10000000000000");
 //BA.debugLineNum = 103;BA.debugLine="Artist.Initialize";
mostCurrent._artist.Initialize();
 //BA.debugLineNum = 104;BA.debugLine="Name.Initialize";
mostCurrent._name.Initialize();
 //BA.debugLineNum = 105;BA.debugLine="Image.Initialize";
mostCurrent._image.Initialize();
 //BA.debugLineNum = 106;BA.debugLine="linkMusic.Initialize";
mostCurrent._linkmusic.Initialize();
 //BA.debugLineNum = 107;BA.debugLine="sahr.Initialize";
mostCurrent._sahr.Initialize();
 //BA.debugLineNum = 108;BA.debugLine="VEIW.Initialize";
mostCurrent._veiw.Initialize();
 //BA.debugLineNum = 109;BA.debugLine="telegram1.Initialize";
mostCurrent._telegram1.Initialize();
 //BA.debugLineNum = 110;BA.debugLine="link1.Initialize";
mostCurrent._link1.Initialize();
 //BA.debugLineNum = 111;BA.debugLine="id.Initialize";
mostCurrent._id.Initialize();
 //BA.debugLineNum = 112;BA.debugLine="music.initialize(SeekBar1,string1)";
mostCurrent._music.initialize((android.widget.SeekBar)(mostCurrent._seekbar1.getObject()),mostCurrent._string1);
 //BA.debugLineNum = 115;BA.debugLine="If File.Exists(File.DirRootExternal,\"data.db\")=Fa";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirRootExternal(),"data.db")==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 116;BA.debugLine="File.Copy(File.DirAssets,\"data.db\",File.DirRootE";
anywheresoftware.b4a.keywords.Common.File.Copy(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"data.db",anywheresoftware.b4a.keywords.Common.File.getDirRootExternal(),"data.db");
 };
 //BA.debugLineNum = 119;BA.debugLine="sql1.Initialize(File.DirRootExternal,\"data.db\",Fa";
mostCurrent._sql1.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirRootExternal(),"data.db",anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 124;BA.debugLine="tmback.Initialize(\"tmExit\",1500)";
mostCurrent._tmback.Initialize(processBA,"tmExit",(long) (1500));
 //BA.debugLineNum = 125;BA.debugLine="End Sub";
return "";
}
public static boolean  _activity_keypress(int _keycode) throws Exception{
String _str = "";
 //BA.debugLineNum = 557;BA.debugLine="Sub Activity_KeyPress (KeyCode As Int) As Boolean";
 //BA.debugLineNum = 558;BA.debugLine="If KeyCode=KeyCodes.KEYCODE_BACK Then";
if (_keycode==anywheresoftware.b4a.keywords.Common.KeyCodes.KEYCODE_BACK) { 
 //BA.debugLineNum = 559;BA.debugLine="If Panel1.Visible=True Then";
if (mostCurrent._panel1.getVisible()==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 560;BA.debugLine="If isplaying=True Then";
if (_isplaying==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 561;BA.debugLine="Dim str As String";
_str = "";
 //BA.debugLineNum = 562;BA.debugLine="str=Msgbox2(\"موزیک در حال پخش قطع شود؟؟\",\"doom";
_str = BA.NumberToString(anywheresoftware.b4a.keywords.Common.Msgbox2(BA.ObjectToCharSequence("موزیک در حال پخش قطع شود؟؟"),BA.ObjectToCharSequence("doomadi music?"),"آره","نه","",(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null),mostCurrent.activityBA));
 //BA.debugLineNum = 563;BA.debugLine="If str=DialogResponse.POSITIVE Then";
if ((_str).equals(BA.NumberToString(anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE))) { 
 //BA.debugLineNum = 564;BA.debugLine="music.stop";
mostCurrent._music.stop();
 //BA.debugLineNum = 565;BA.debugLine="Panel1.SetVisibleAnimated(500,False)";
mostCurrent._panel1.SetVisibleAnimated((int) (500),anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 566;BA.debugLine="isplaying=False";
_isplaying = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 567;BA.debugLine="time.Text=\"ـ\"";
mostCurrent._time.setText(BA.ObjectToCharSequence("ـ"));
 //BA.debugLineNum = 568;BA.debugLine="SeekBar1.Visible=False";
mostCurrent._seekbar1.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 569;BA.debugLine="timer1.Enabled=False";
mostCurrent._timer1.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 570;BA.debugLine="tin.Initialize(\"tin\",1000)";
mostCurrent._tin.Initialize(processBA,"tin",(long) (1000));
 //BA.debugLineNum = 571;BA.debugLine="tin.Enabled=True";
mostCurrent._tin.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 572;BA.debugLine="ImageView6.Visible=True";
mostCurrent._imageview6.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 574;BA.debugLine="ImageView5.Visible=True";
mostCurrent._imageview5.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 576;BA.debugLine="ImageView4.Visible=True";
mostCurrent._imageview4.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 578;BA.debugLine="ImageView3.Visible=True";
mostCurrent._imageview3.setVisible(anywheresoftware.b4a.keywords.Common.True);
 }else {
 //BA.debugLineNum = 584;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 };
 }else {
 //BA.debugLineNum = 587;BA.debugLine="ImageView6.Visible=True";
mostCurrent._imageview6.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 589;BA.debugLine="ImageView5.Visible=True";
mostCurrent._imageview5.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 591;BA.debugLine="ImageView4.Visible=True";
mostCurrent._imageview4.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 593;BA.debugLine="ImageView3.Visible=True";
mostCurrent._imageview3.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 598;BA.debugLine="Panel1.SetVisibleAnimated(500,False)";
mostCurrent._panel1.SetVisibleAnimated((int) (500),anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 599;BA.debugLine="tin.Initialize(\"tin\",500)";
mostCurrent._tin.Initialize(processBA,"tin",(long) (500));
 //BA.debugLineNum = 600;BA.debugLine="tin.Enabled=True";
mostCurrent._tin.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 };
 //BA.debugLineNum = 604;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 };
 };
 //BA.debugLineNum = 608;BA.debugLine="If KeyCode = KeyCodes.KEYCODE_BACK Then";
if (_keycode==anywheresoftware.b4a.keywords.Common.KeyCodes.KEYCODE_BACK) { 
 //BA.debugLineNum = 611;BA.debugLine="StartActivity(sabzevar)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._sabzevar.getObject()));
 //BA.debugLineNum = 612;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 613;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 };
 //BA.debugLineNum = 617;BA.debugLine="End Sub";
return false;
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 129;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 130;BA.debugLine="If isplaying=True Then";
if (_isplaying==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 132;BA.debugLine="timer1.Enabled=False";
mostCurrent._timer1.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 };
 //BA.debugLineNum = 134;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 126;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 128;BA.debugLine="End Sub";
return "";
}
public static String  _button1_click() throws Exception{
anywheresoftware.b4a.objects.IntentWrapper _far = null;
anywheresoftware.b4a.phone.PackageManagerWrapper _pm = null;
anywheresoftware.b4a.objects.IntentWrapper _i = null;
anywheresoftware.b4j.object.JavaObject _jo = null;
anywheresoftware.b4a.objects.IntentWrapper _i2 = null;
 //BA.debugLineNum = 467;BA.debugLine="Sub Button1_Click";
 //BA.debugLineNum = 469;BA.debugLine="Try";
try { //BA.debugLineNum = 471;BA.debugLine="Dim far As Intent";
_far = new anywheresoftware.b4a.objects.IntentWrapper();
 //BA.debugLineNum = 472;BA.debugLine="Dim pm As PackageManager";
_pm = new anywheresoftware.b4a.phone.PackageManagerWrapper();
 //BA.debugLineNum = 473;BA.debugLine="far=pm.GetApplicationIntent(\"com.hanista.mobogra";
_far = _pm.GetApplicationIntent("com.hanista.mobogram");
 //BA.debugLineNum = 474;BA.debugLine="If far.IsInitialized Then";
if (_far.IsInitialized()) { 
 //BA.debugLineNum = 476;BA.debugLine="Dim i As Intent";
_i = new anywheresoftware.b4a.objects.IntentWrapper();
 //BA.debugLineNum = 477;BA.debugLine="i.Initialize(i.ACTION_VIEW, \"http://telegram.me";
_i.Initialize(_i.ACTION_VIEW,"http://telegram.me/"+mostCurrent._telegram2);
 //BA.debugLineNum = 478;BA.debugLine="Dim jo As JavaObject = i";
_jo = new anywheresoftware.b4j.object.JavaObject();
_jo.setObject((java.lang.Object)(_i.getObject()));
 //BA.debugLineNum = 479;BA.debugLine="jo.RunMethod(\"setPackage\", Array As Object(\"com";
_jo.RunMethod("setPackage",new Object[]{(Object)("com.hanista.mobogram")});
 //BA.debugLineNum = 480;BA.debugLine="StartActivity(i)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(_i.getObject()));
 }else {
 //BA.debugLineNum = 487;BA.debugLine="Dim i2 As Intent";
_i2 = new anywheresoftware.b4a.objects.IntentWrapper();
 //BA.debugLineNum = 488;BA.debugLine="i2.initialize (i2.action_view,\"tg://resolve?dom";
_i2.Initialize(_i2.ACTION_VIEW,"tg://resolve?domain="+mostCurrent._telegram2);
 //BA.debugLineNum = 489;BA.debugLine="StartActivity (i2)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(_i2.getObject()));
 };
 } 
       catch (Exception e17) {
			processBA.setLastException(e17); };
 //BA.debugLineNum = 497;BA.debugLine="End Sub";
return "";
}
public static String  _button2_click() throws Exception{
anywheresoftware.b4a.objects.IntentWrapper _i = null;
 //BA.debugLineNum = 499;BA.debugLine="Sub Button2_Click";
 //BA.debugLineNum = 500;BA.debugLine="Try";
try { //BA.debugLineNum = 501;BA.debugLine="Dim i As Intent";
_i = new anywheresoftware.b4a.objects.IntentWrapper();
 //BA.debugLineNum = 502;BA.debugLine="i.Initialize(i.ACTION_VIEW,LINK2)";
_i.Initialize(_i.ACTION_VIEW,mostCurrent._link2);
 //BA.debugLineNum = 503;BA.debugLine="StartActivity(i)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(_i.getObject()));
 } 
       catch (Exception e6) {
			processBA.setLastException(e6); //BA.debugLineNum = 505;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.Log(BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)));
 };
 //BA.debugLineNum = 507;BA.debugLine="End Sub";
return "";
}
public static String  _button3_click() throws Exception{
 //BA.debugLineNum = 509;BA.debugLine="Sub Button3_Click";
 //BA.debugLineNum = 511;BA.debugLine="s=Regex.Split(\"/\",linkplay)";
mostCurrent._s = anywheresoftware.b4a.keywords.Common.Regex.Split("/",mostCurrent._linkplay);
 //BA.debugLineNum = 513;BA.debugLine="ss=s(5)";
mostCurrent._ss = mostCurrent._s[(int) (5)];
 //BA.debugLineNum = 514;BA.debugLine="If File.Exists(File.DirRootExternal & \"/doholimus";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirRootExternal()+"/doholimusic",mostCurrent._ss)==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 515;BA.debugLine="Name3= ss";
mostCurrent._name3 = mostCurrent._ss;
 //BA.debugLineNum = 516;BA.debugLine="dier=File.DirRootExternal& \"/doholimusic\"";
mostCurrent._dier = anywheresoftware.b4a.keywords.Common.File.getDirRootExternal()+"/doholimusic";
 //BA.debugLineNum = 519;BA.debugLine="http.Initialize(\"job3\",Me)";
mostCurrent._http._initialize(processBA,"job3",asheghi.getObject());
 //BA.debugLineNum = 520;BA.debugLine="http.Download(linkplay)";
mostCurrent._http._download(mostCurrent._linkplay);
 //BA.debugLineNum = 522;BA.debugLine="no.Initialize";
mostCurrent._no.Initialize();
 //BA.debugLineNum = 523;BA.debugLine="no.Icon=\"icon\"";
mostCurrent._no.setIcon("icon");
 //BA.debugLineNum = 524;BA.debugLine="no.Light=False";
mostCurrent._no.setLight(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 525;BA.debugLine="no.Vibrate = False";
mostCurrent._no.setVibrate(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 526;BA.debugLine="no.sound = True";
mostCurrent._no.setSound(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 527;BA.debugLine="no.AutoCancel=True";
mostCurrent._no.setAutoCancel(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 528;BA.debugLine="no.OnGoingEvent=True";
mostCurrent._no.setOnGoingEvent(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 529;BA.debugLine="no.SetInfo(\"در حال دانلود\",Name3,Null)";
mostCurrent._no.SetInfo(processBA,"در حال دانلود",mostCurrent._name3,anywheresoftware.b4a.keywords.Common.Null);
 //BA.debugLineNum = 530;BA.debugLine="no.Notify(1)";
mostCurrent._no.Notify((int) (1));
 //BA.debugLineNum = 531;BA.debugLine="ProgressDialogShow(\"در حال دانلود فایل...صبر کنی";
anywheresoftware.b4a.keywords.Common.ProgressDialogShow(mostCurrent.activityBA,BA.ObjectToCharSequence("در حال دانلود فایل...صبر کنید"));
 //BA.debugLineNum = 532;BA.debugLine="ToastMessageShow(\"لطفا منتظر بمانید,\",  True)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("لطفا منتظر بمانید,"),anywheresoftware.b4a.keywords.Common.True);
 }else {
 //BA.debugLineNum = 534;BA.debugLine="ToastMessageShow(\"فایل موجود است\",  True)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("فایل موجود است"),anywheresoftware.b4a.keywords.Common.True);
 };
 //BA.debugLineNum = 536;BA.debugLine="End Sub";
return "";
}
public static String  _button4_click() throws Exception{
anywheresoftware.b4a.objects.IntentWrapper _share = null;
 //BA.debugLineNum = 538;BA.debugLine="Sub Button4_Click";
 //BA.debugLineNum = 540;BA.debugLine="Dim share As Intent";
_share = new anywheresoftware.b4a.objects.IntentWrapper();
 //BA.debugLineNum = 541;BA.debugLine="share.Initialize(share.ACTION_SEND,\"\")";
_share.Initialize(_share.ACTION_SEND,"");
 //BA.debugLineNum = 543;BA.debugLine="share.SetType(\"text/plain\")";
_share.SetType("text/plain");
 //BA.debugLineNum = 544;BA.debugLine="share.PutExtra(\"android.intent.extra.TEXT\", linkp";
_share.PutExtra("android.intent.extra.TEXT",(Object)(mostCurrent._linkplay));
 //BA.debugLineNum = 545;BA.debugLine="share.WrapAsIntentChooser(\"اشتراک گذاری آهنگ\")";
_share.WrapAsIntentChooser("اشتراک گذاری آهنگ");
 //BA.debugLineNum = 546;BA.debugLine="StartActivity(share)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(_share.getObject()));
 //BA.debugLineNum = 547;BA.debugLine="Log( linkplay)";
anywheresoftware.b4a.keywords.Common.Log(mostCurrent._linkplay);
 //BA.debugLineNum = 548;BA.debugLine="End Sub";
return "";
}
public static String  _button5_click() throws Exception{
anywheresoftware.b4a.objects.ButtonWrapper _v = null;
int _i = 0;
int _new_num = 0;
 //BA.debugLineNum = 328;BA.debugLine="Sub Button5_Click";
 //BA.debugLineNum = 331;BA.debugLine="Dim v As Button";
_v = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 332;BA.debugLine="v=Sender";
_v.setObject((android.widget.Button)(anywheresoftware.b4a.keywords.Common.Sender(mostCurrent.activityBA)));
 //BA.debugLineNum = 335;BA.debugLine="cursor1=sql1.ExecQuery(\"SELECT * FROM My_DB\")";
mostCurrent._cursor1.setObject((android.database.Cursor)(mostCurrent._sql1.ExecQuery("SELECT * FROM My_DB")));
 //BA.debugLineNum = 336;BA.debugLine="For i=0 To cursor1.RowCount-1";
{
final int step4 = 1;
final int limit4 = (int) (mostCurrent._cursor1.getRowCount()-1);
_i = (int) (0) ;
for (;(step4 > 0 && _i <= limit4) || (step4 < 0 && _i >= limit4) ;_i = ((int)(0 + _i + step4))  ) {
 //BA.debugLineNum = 337;BA.debugLine="cursor1.Position=i";
mostCurrent._cursor1.setPosition(_i);
 //BA.debugLineNum = 338;BA.debugLine="LogColor(i,Colors.red)";
anywheresoftware.b4a.keywords.Common.LogColor(BA.NumberToString(_i),anywheresoftware.b4a.keywords.Common.Colors.Red);
 //BA.debugLineNum = 339;BA.debugLine="If cursor1.GetString(\"artist\")=w Then";
if ((mostCurrent._cursor1.GetString("artist")).equals(mostCurrent._w)) { 
 //BA.debugLineNum = 340;BA.debugLine="chk=2";
_chk = (int) (2);
 //BA.debugLineNum = 341;BA.debugLine="LogColor(i,Colors.Blue)";
anywheresoftware.b4a.keywords.Common.LogColor(BA.NumberToString(_i),anywheresoftware.b4a.keywords.Common.Colors.Blue);
 };
 }
};
 //BA.debugLineNum = 346;BA.debugLine="If chk=2 Then";
if (_chk==2) { 
 //BA.debugLineNum = 347;BA.debugLine="chk=0";
_chk = (int) (0);
 //BA.debugLineNum = 348;BA.debugLine="ToastMessageShow(\"از قبل در لیست علاقه مندیها قر";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("از قبل در لیست علاقه مندیها قرار گرفته است"),anywheresoftware.b4a.keywords.Common.False);
 }else {
 //BA.debugLineNum = 350;BA.debugLine="ToastMessageShow(\"به لیست علاقمندی اضافه شد\",Fal";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("به لیست علاقمندی اضافه شد"),anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 351;BA.debugLine="cursor1=sql1.ExecQuery(\"SELECT id FROM My_DB\")";
mostCurrent._cursor1.setObject((android.database.Cursor)(mostCurrent._sql1.ExecQuery("SELECT id FROM My_DB")));
 //BA.debugLineNum = 352;BA.debugLine="If cursor1.RowCount>0 Then";
if (mostCurrent._cursor1.getRowCount()>0) { 
 //BA.debugLineNum = 353;BA.debugLine="For i=0 To cursor1.RowCount-1";
{
final int step19 = 1;
final int limit19 = (int) (mostCurrent._cursor1.getRowCount()-1);
_i = (int) (0) ;
for (;(step19 > 0 && _i <= limit19) || (step19 < 0 && _i >= limit19) ;_i = ((int)(0 + _i + step19))  ) {
 //BA.debugLineNum = 354;BA.debugLine="cursor1.Position=i";
mostCurrent._cursor1.setPosition(_i);
 //BA.debugLineNum = 355;BA.debugLine="Dim new_num As Int";
_new_num = 0;
 //BA.debugLineNum = 356;BA.debugLine="new_num=cursor1.GetInt(\"id\")";
_new_num = mostCurrent._cursor1.GetInt("id");
 }
};
 };
 //BA.debugLineNum = 359;BA.debugLine="new_num=new_num+1";
_new_num = (int) (_new_num+1);
 //BA.debugLineNum = 360;BA.debugLine="sql1.ExecNonQuery(\"INSERT INTO My_DB VALUES('\" &";
mostCurrent._sql1.ExecNonQuery("INSERT INTO My_DB VALUES('"+BA.NumberToString(_new_num)+"','"+mostCurrent._w+"','"+mostCurrent._ww+"','"+mostCurrent._www+"','"+mostCurrent._linkplay+"','"+mostCurrent._sahr11+"','"+mostCurrent._telegram2+"','"+mostCurrent._linkplay+"','"+mostCurrent._qqq+"')");
 };
 //BA.debugLineNum = 363;BA.debugLine="End Sub";
return "";
}
public static String  _download(anywheresoftware.b4a.objects.ImageViewWrapper _imagex,String _link) throws Exception{
anywheresoftware.b4a.objects.collections.Map _links = null;
 //BA.debugLineNum = 250;BA.debugLine="Private Sub download(Imagex As ImageView,link As S";
 //BA.debugLineNum = 251;BA.debugLine="Dim links As Map";
_links = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 252;BA.debugLine="links.Initialize";
_links.Initialize();
 //BA.debugLineNum = 253;BA.debugLine="links.Put(Imagex,link)";
_links.Put((Object)(_imagex.getObject()),(Object)(_link));
 //BA.debugLineNum = 254;BA.debugLine="CallSubDelayed2(ImageDownloader, \"Download\", link";
anywheresoftware.b4a.keywords.Common.CallSubDelayed2(processBA,(Object)(mostCurrent._imagedownloader.getObject()),"Download",(Object)(_links));
 //BA.debugLineNum = 256;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 12;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 13;BA.debugLine="Dim chk As Int=0";
_chk = (int) (0);
 //BA.debugLineNum = 14;BA.debugLine="Dim p As Panel";
mostCurrent._p = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 15;BA.debugLine="Dim music As MusicStramer";
mostCurrent._music = new MuiscStramer.MusicStramer();
 //BA.debugLineNum = 16;BA.debugLine="Private SeekBar1 As SeekBar";
mostCurrent._seekbar1 = new anywheresoftware.b4a.objects.SeekBarWrapper();
 //BA.debugLineNum = 17;BA.debugLine="Dim string1 As String";
mostCurrent._string1 = "";
 //BA.debugLineNum = 18;BA.debugLine="Dim timer1 As Timer";
mostCurrent._timer1 = new anywheresoftware.b4a.objects.Timer();
 //BA.debugLineNum = 19;BA.debugLine="Dim http As HttpJob";
mostCurrent._http = new anywheresoftware.b4a.samples.httputils2.httpjob();
 //BA.debugLineNum = 20;BA.debugLine="Private ScrollView1 As ScrollView";
mostCurrent._scrollview1 = new anywheresoftware.b4a.objects.ScrollViewWrapper();
 //BA.debugLineNum = 21;BA.debugLine="Dim Artist,Name,Image,linkMusic,sahr,telegram1,li";
mostCurrent._artist = new anywheresoftware.b4a.objects.collections.List();
mostCurrent._name = new anywheresoftware.b4a.objects.collections.List();
mostCurrent._image = new anywheresoftware.b4a.objects.collections.List();
mostCurrent._linkmusic = new anywheresoftware.b4a.objects.collections.List();
mostCurrent._sahr = new anywheresoftware.b4a.objects.collections.List();
mostCurrent._telegram1 = new anywheresoftware.b4a.objects.collections.List();
mostCurrent._link1 = new anywheresoftware.b4a.objects.collections.List();
mostCurrent._veiw = new anywheresoftware.b4a.objects.collections.List();
mostCurrent._id = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 22;BA.debugLine="Dim josn As  JSONParser";
mostCurrent._josn = new anywheresoftware.b4a.objects.collections.JSONParser();
 //BA.debugLineNum = 23;BA.debugLine="Dim list As List";
mostCurrent._list = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 24;BA.debugLine="Dim map As Map";
mostCurrent._map = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 25;BA.debugLine="Dim left , top As Int";
_left = 0;
_top = 0;
 //BA.debugLineNum = 26;BA.debugLine="left =2%x";
_left = anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (2),mostCurrent.activityBA);
 //BA.debugLineNum = 27;BA.debugLine="top = 1%y";
_top = anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (1),mostCurrent.activityBA);
 //BA.debugLineNum = 28;BA.debugLine="Private nameLabel As Label";
mostCurrent._namelabel = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 29;BA.debugLine="Private ArtistLabel As Label";
mostCurrent._artistlabel = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 30;BA.debugLine="Private Imagev As ImageView";
mostCurrent._imagev = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 31;BA.debugLine="Dim anim As Animation";
mostCurrent._anim = new anywheresoftware.b4a.objects.AnimationWrapper();
 //BA.debugLineNum = 32;BA.debugLine="Private Panel1 As Panel";
mostCurrent._panel1 = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 33;BA.debugLine="Private ImageView1 As ImageView";
mostCurrent._imageview1 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 34;BA.debugLine="Private xname As Label";
mostCurrent._xname = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 35;BA.debugLine="Private play As Button";
mostCurrent._play = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 36;BA.debugLine="Private pause As Button";
mostCurrent._pause = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 37;BA.debugLine="Private time As Label";
mostCurrent._time = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 38;BA.debugLine="Dim linkplay As String";
mostCurrent._linkplay = "";
 //BA.debugLineNum = 40;BA.debugLine="Dim TELEGRAM2,w,ww,www,wwww,wwwww,wwwwww As Strin";
mostCurrent._telegram2 = "";
mostCurrent._w = "";
mostCurrent._ww = "";
mostCurrent._www = "";
mostCurrent._wwww = "";
mostCurrent._wwwww = "";
mostCurrent._wwwwww = "";
 //BA.debugLineNum = 41;BA.debugLine="Dim LINK2 As String";
mostCurrent._link2 = "";
 //BA.debugLineNum = 42;BA.debugLine="Dim SITE2 As String";
mostCurrent._site2 = "";
 //BA.debugLineNum = 44;BA.debugLine="Dim isplaying As Boolean";
_isplaying = false;
 //BA.debugLineNum = 45;BA.debugLine="isplaying=False";
_isplaying = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 46;BA.debugLine="Dim tin As Timer";
mostCurrent._tin = new anywheresoftware.b4a.objects.Timer();
 //BA.debugLineNum = 48;BA.debugLine="Private ImageView3 As ImageView";
mostCurrent._imageview3 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 49;BA.debugLine="Private ImageView4 As ImageView";
mostCurrent._imageview4 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 50;BA.debugLine="Private ImageView5 As ImageView";
mostCurrent._imageview5 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 51;BA.debugLine="Private ImageView6 As ImageView";
mostCurrent._imageview6 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 53;BA.debugLine="Private sahr1,number1 As Label";
mostCurrent._sahr1 = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._number1 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 54;BA.debugLine="Private Button3 As Button";
mostCurrent._button3 = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 55;BA.debugLine="Private Button2 As Button";
mostCurrent._button2 = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 56;BA.debugLine="Private Button1 As Button";
mostCurrent._button1 = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 57;BA.debugLine="Private Button4 As Button";
mostCurrent._button4 = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 59;BA.debugLine="Dim ht As HttpJob";
mostCurrent._ht = new anywheresoftware.b4a.samples.httputils2.httpjob();
 //BA.debugLineNum = 62;BA.debugLine="Dim no As Notification";
mostCurrent._no = new anywheresoftware.b4a.objects.NotificationWrapper();
 //BA.debugLineNum = 63;BA.debugLine="Dim link3,Name3,dier As String";
mostCurrent._link3 = "";
mostCurrent._name3 = "";
mostCurrent._dier = "";
 //BA.debugLineNum = 64;BA.debugLine="Dim s() As String";
mostCurrent._s = new String[(int) (0)];
java.util.Arrays.fill(mostCurrent._s,"");
 //BA.debugLineNum = 65;BA.debugLine="Dim ss As String";
mostCurrent._ss = "";
 //BA.debugLineNum = 66;BA.debugLine="Dim sound As MediaPlayer";
mostCurrent._sound = new anywheresoftware.b4a.objects.MediaPlayerWrapper();
 //BA.debugLineNum = 68;BA.debugLine="Dim asa As Int";
_asa = 0;
 //BA.debugLineNum = 69;BA.debugLine="Private id1 As Label";
mostCurrent._id1 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 73;BA.debugLine="Dim qqq,qqqq,sahr11 As String";
mostCurrent._qqq = "";
mostCurrent._qqqq = "";
mostCurrent._sahr11 = "";
 //BA.debugLineNum = 74;BA.debugLine="Private Label20 As Label";
mostCurrent._label20 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 75;BA.debugLine="Private Label2 As Label";
mostCurrent._label2 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 79;BA.debugLine="Dim sql1 As SQL";
mostCurrent._sql1 = new anywheresoftware.b4a.sql.SQL();
 //BA.debugLineNum = 80;BA.debugLine="Dim cursor1 As Cursor";
mostCurrent._cursor1 = new anywheresoftware.b4a.sql.SQL.CursorWrapper();
 //BA.debugLineNum = 81;BA.debugLine="Private Button100 As Button";
mostCurrent._button100 = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 82;BA.debugLine="Private Button5 As Button";
mostCurrent._button5 = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 85;BA.debugLine="Dim tmback As Timer";
mostCurrent._tmback = new anywheresoftware.b4a.objects.Timer();
 //BA.debugLineNum = 86;BA.debugLine="Dim intback As Byte = 0";
_intback = (byte) (0);
 //BA.debugLineNum = 87;BA.debugLine="Private Label3 As Label";
mostCurrent._label3 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 90;BA.debugLine="End Sub";
return "";
}
public static String  _imageview3_click() throws Exception{
 //BA.debugLineNum = 460;BA.debugLine="Sub ImageView3_Click";
 //BA.debugLineNum = 462;BA.debugLine="StartActivity(Main)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._main.getObject()));
 //BA.debugLineNum = 463;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 465;BA.debugLine="End Sub";
return "";
}
public static String  _imageview4_click() throws Exception{
 //BA.debugLineNum = 451;BA.debugLine="Sub ImageView4_Click";
 //BA.debugLineNum = 452;BA.debugLine="StartActivity(doomadi)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._doomadi.getObject()));
 //BA.debugLineNum = 453;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 454;BA.debugLine="End Sub";
return "";
}
public static String  _imageview5_click() throws Exception{
 //BA.debugLineNum = 445;BA.debugLine="Sub ImageView5_Click";
 //BA.debugLineNum = 446;BA.debugLine="StartActivity(ostan)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._ostan.getObject()));
 //BA.debugLineNum = 447;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 449;BA.debugLine="End Sub";
return "";
}
public static String  _imageview6_click() throws Exception{
 //BA.debugLineNum = 439;BA.debugLine="Sub ImageView6_Click";
 //BA.debugLineNum = 440;BA.debugLine="StartActivity(tamas)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._tamas.getObject()));
 //BA.debugLineNum = 441;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 443;BA.debugLine="End Sub";
return "";
}
public static String  _jobdone(anywheresoftware.b4a.samples.httputils2.httpjob _job) throws Exception{
String _res = "";
int _i = 0;
anywheresoftware.b4a.objects.drawable.ColorDrawable _clr = null;
anywheresoftware.b4a.objects.streams.File.OutputStreamWrapper _o = null;
 //BA.debugLineNum = 146;BA.debugLine="Sub JobDone(job As HttpJob)";
 //BA.debugLineNum = 147;BA.debugLine="If job.Success = True Then";
if (_job._success==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 148;BA.debugLine="Select Case job.JobName";
switch (BA.switchObjectToInt(_job._jobname,"http","job1","job2","job3","edit")) {
case 0: {
 //BA.debugLineNum = 153;BA.debugLine="ProgressDialogHide";
anywheresoftware.b4a.keywords.Common.ProgressDialogHide();
 //BA.debugLineNum = 154;BA.debugLine="list.Initialize";
mostCurrent._list.Initialize();
 //BA.debugLineNum = 155;BA.debugLine="Dim res As String";
_res = "";
 //BA.debugLineNum = 156;BA.debugLine="res = job.GetString";
_res = _job._getstring();
 //BA.debugLineNum = 157;BA.debugLine="josn.Initialize(res)";
mostCurrent._josn.Initialize(_res);
 //BA.debugLineNum = 158;BA.debugLine="list=josn.NextArray";
mostCurrent._list = mostCurrent._josn.NextArray();
 //BA.debugLineNum = 159;BA.debugLine="For i=0 To list.Size-1";
{
final int step10 = 1;
final int limit10 = (int) (mostCurrent._list.getSize()-1);
_i = (int) (0) ;
for (;(step10 > 0 && _i <= limit10) || (step10 < 0 && _i >= limit10) ;_i = ((int)(0 + _i + step10))  ) {
 //BA.debugLineNum = 160;BA.debugLine="map=list.Get(i)";
mostCurrent._map.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(mostCurrent._list.Get(_i)));
 //BA.debugLineNum = 161;BA.debugLine="Artist.Add(map.Get(\"artist\"))";
mostCurrent._artist.Add(mostCurrent._map.Get((Object)("artist")));
 //BA.debugLineNum = 162;BA.debugLine="Name.Add(map.Get(\"name\"))";
mostCurrent._name.Add(mostCurrent._map.Get((Object)("name")));
 //BA.debugLineNum = 163;BA.debugLine="Image.Add(map.Get(\"image\"))";
mostCurrent._image.Add(mostCurrent._map.Get((Object)("image")));
 //BA.debugLineNum = 164;BA.debugLine="linkMusic.Add(map.Get(\"music\"))";
mostCurrent._linkmusic.Add(mostCurrent._map.Get((Object)("music")));
 //BA.debugLineNum = 165;BA.debugLine="sahr.Add(map.Get(\"sahr\"))";
mostCurrent._sahr.Add(mostCurrent._map.Get((Object)("sahr")));
 //BA.debugLineNum = 166;BA.debugLine="VEIW.Add(map.Get(\"VEIW\"))";
mostCurrent._veiw.Add(mostCurrent._map.Get((Object)("VEIW")));
 //BA.debugLineNum = 167;BA.debugLine="id.Add(map.Get(\"id\"))";
mostCurrent._id.Add(mostCurrent._map.Get((Object)("id")));
 //BA.debugLineNum = 168;BA.debugLine="telegram1.Add(map.Get(\"telegram\"))";
mostCurrent._telegram1.Add(mostCurrent._map.Get((Object)("telegram")));
 //BA.debugLineNum = 169;BA.debugLine="link1.Add(map.Get(\"link1\"))";
mostCurrent._link1.Add(mostCurrent._map.Get((Object)("link1")));
 //BA.debugLineNum = 171;BA.debugLine="Dim p As Panel";
mostCurrent._p = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 172;BA.debugLine="p.Initialize(\"p\")";
mostCurrent._p.Initialize(mostCurrent.activityBA,"p");
 //BA.debugLineNum = 173;BA.debugLine="Dim clr As ColorDrawable";
_clr = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 174;BA.debugLine="clr.Initialize(Colors.White,2)";
_clr.Initialize(anywheresoftware.b4a.keywords.Common.Colors.White,(int) (2));
 //BA.debugLineNum = 175;BA.debugLine="p.Visible=False";
mostCurrent._p.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 176;BA.debugLine="ScrollView1.Panel.AddView(p,left,top,47%x,43%";
mostCurrent._scrollview1.getPanel().AddView((android.view.View)(mostCurrent._p.getObject()),_left,_top,anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (47),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (43),mostCurrent.activityBA));
 //BA.debugLineNum = 177;BA.debugLine="p.SetVisibleAnimated(600,True)";
mostCurrent._p.SetVisibleAnimated((int) (600),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 178;BA.debugLine="left = left + 49%x";
_left = (int) (_left+anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (49),mostCurrent.activityBA));
 //BA.debugLineNum = 179;BA.debugLine="p.Tag = i";
mostCurrent._p.setTag((Object)(_i));
 //BA.debugLineNum = 180;BA.debugLine="If i Mod 2 = 1 Then";
if (_i%2==1) { 
 //BA.debugLineNum = 181;BA.debugLine="top = top + 44%y";
_top = (int) (_top+anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (44),mostCurrent.activityBA));
 //BA.debugLineNum = 182;BA.debugLine="ScrollView1.Panel.Height = top + 50%y";
mostCurrent._scrollview1.getPanel().setHeight((int) (_top+anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (50),mostCurrent.activityBA)));
 //BA.debugLineNum = 183;BA.debugLine="left = 2%x";
_left = anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (2),mostCurrent.activityBA);
 };
 //BA.debugLineNum = 185;BA.debugLine="p.LoadLayout(\"jk\")";
mostCurrent._p.LoadLayout("jk",mostCurrent.activityBA);
 //BA.debugLineNum = 186;BA.debugLine="p.Background=clr";
mostCurrent._p.setBackground((android.graphics.drawable.Drawable)(_clr.getObject()));
 //BA.debugLineNum = 187;BA.debugLine="download(Imagev,map.Get(\"image\"))";
_download(mostCurrent._imagev,BA.ObjectToString(mostCurrent._map.Get((Object)("image"))));
 //BA.debugLineNum = 188;BA.debugLine="ArtistLabel.Text=Artist.Get(i)";
mostCurrent._artistlabel.setText(BA.ObjectToCharSequence(mostCurrent._artist.Get(_i)));
 //BA.debugLineNum = 189;BA.debugLine="nameLabel.Text=Name.Get(i)";
mostCurrent._namelabel.setText(BA.ObjectToCharSequence(mostCurrent._name.Get(_i)));
 //BA.debugLineNum = 190;BA.debugLine="sahr1.Text=sahr.Get(i)";
mostCurrent._sahr1.setText(BA.ObjectToCharSequence(mostCurrent._sahr.Get(_i)));
 //BA.debugLineNum = 191;BA.debugLine="number1.text=VEIW.Get(i)";
mostCurrent._number1.setText(BA.ObjectToCharSequence(mostCurrent._veiw.Get(_i)));
 //BA.debugLineNum = 192;BA.debugLine="id1.Text=id.Get(i)";
mostCurrent._id1.setText(BA.ObjectToCharSequence(mostCurrent._id.Get(_i)));
 }
};
 break; }
case 1: {
 //BA.debugLineNum = 198;BA.debugLine="Dim o As OutputStream";
_o = new anywheresoftware.b4a.objects.streams.File.OutputStreamWrapper();
 //BA.debugLineNum = 199;BA.debugLine="o = File.OpenOutput(dier, Name3, False)";
_o = anywheresoftware.b4a.keywords.Common.File.OpenOutput(mostCurrent._dier,mostCurrent._name3,anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 200;BA.debugLine="File.Copy2(job.GetInputStream, o)";
anywheresoftware.b4a.keywords.Common.File.Copy2((java.io.InputStream)(_job._getinputstream().getObject()),(java.io.OutputStream)(_o.getObject()));
 //BA.debugLineNum = 201;BA.debugLine="o.Close";
_o.Close();
 //BA.debugLineNum = 202;BA.debugLine="job.Release";
_job._release();
 break; }
case 2: {
 //BA.debugLineNum = 204;BA.debugLine="Dim o As OutputStream";
_o = new anywheresoftware.b4a.objects.streams.File.OutputStreamWrapper();
 //BA.debugLineNum = 205;BA.debugLine="o = File.OpenOutput(dier, Name3, False)";
_o = anywheresoftware.b4a.keywords.Common.File.OpenOutput(mostCurrent._dier,mostCurrent._name3,anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 206;BA.debugLine="File.Copy2(job.GetInputStream, o)";
anywheresoftware.b4a.keywords.Common.File.Copy2((java.io.InputStream)(_job._getinputstream().getObject()),(java.io.OutputStream)(_o.getObject()));
 //BA.debugLineNum = 207;BA.debugLine="o.Close";
_o.Close();
 //BA.debugLineNum = 208;BA.debugLine="job.Release";
_job._release();
 //BA.debugLineNum = 209;BA.debugLine="ToastMessageShow(\"فایل دانلود و ذخیره شد\",True";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("فایل دانلود و ذخیره شد"),anywheresoftware.b4a.keywords.Common.True);
 break; }
case 3: {
 //BA.debugLineNum = 215;BA.debugLine="Dim o As OutputStream";
_o = new anywheresoftware.b4a.objects.streams.File.OutputStreamWrapper();
 //BA.debugLineNum = 216;BA.debugLine="o = File.OpenOutput(dier, Name3, False)";
_o = anywheresoftware.b4a.keywords.Common.File.OpenOutput(mostCurrent._dier,mostCurrent._name3,anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 217;BA.debugLine="File.Copy2(job.GetInputStream, o)";
anywheresoftware.b4a.keywords.Common.File.Copy2((java.io.InputStream)(_job._getinputstream().getObject()),(java.io.OutputStream)(_o.getObject()));
 //BA.debugLineNum = 218;BA.debugLine="o.Close";
_o.Close();
 //BA.debugLineNum = 219;BA.debugLine="job.Release";
_job._release();
 //BA.debugLineNum = 220;BA.debugLine="no.Cancel(1)";
mostCurrent._no.Cancel((int) (1));
 //BA.debugLineNum = 221;BA.debugLine="ToastMessageShow(\"فایل دانلود و ذخیره شد\",True";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("فایل دانلود و ذخیره شد"),anywheresoftware.b4a.keywords.Common.True);
 break; }
case 4: {
 //BA.debugLineNum = 229;BA.debugLine="If job.GetString.Contains(\"successfully\") = Tr";
if (_job._getstring().contains("successfully")==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 231;BA.debugLine="qqq=qqq+1";
mostCurrent._qqq = BA.NumberToString((double)(Double.parseDouble(mostCurrent._qqq))+1);
 //BA.debugLineNum = 233;BA.debugLine="Label2.Text=qqq";
mostCurrent._label2.setText(BA.ObjectToCharSequence(mostCurrent._qqq));
 //BA.debugLineNum = 234;BA.debugLine="ToastMessageShow(\"بازدیدی دیگر از این آهنگ\",F";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("بازدیدی دیگر از این آهنگ"),anywheresoftware.b4a.keywords.Common.False);
 }else {
 //BA.debugLineNum = 238;BA.debugLine="ToastMessageShow(\"اشکالی به وجود امده\",False)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("اشکالی به وجود امده"),anywheresoftware.b4a.keywords.Common.False);
 };
 break; }
}
;
 }else {
 //BA.debugLineNum = 244;BA.debugLine="ToastMessageShow(http.ErrorMessage,True)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(mostCurrent._http._errormessage),anywheresoftware.b4a.keywords.Common.True);
 };
 //BA.debugLineNum = 246;BA.debugLine="ProgressDialogHide";
anywheresoftware.b4a.keywords.Common.ProgressDialogHide();
 //BA.debugLineNum = 249;BA.debugLine="End Sub";
return "";
}
public static String  _label3_click() throws Exception{
 //BA.debugLineNum = 456;BA.debugLine="Sub Label3_Click";
 //BA.debugLineNum = 457;BA.debugLine="StartActivity(a)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._a.getObject()));
 //BA.debugLineNum = 458;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 459;BA.debugLine="End Sub";
return "";
}
public static String  _loaddb(String _db) throws Exception{
 //BA.debugLineNum = 142;BA.debugLine="Sub LoadDB(DB As String)";
 //BA.debugLineNum = 143;BA.debugLine="http.PostString(\"http://doholi.com/muzzik/json.ph";
mostCurrent._http._poststring("http://doholi.com/muzzik/json.php",_db);
 //BA.debugLineNum = 145;BA.debugLine="End Sub";
return "";
}
public static String  _p_click() throws Exception{
anywheresoftware.b4a.objects.PanelWrapper _v = null;
 //BA.debugLineNum = 257;BA.debugLine="Sub p_Click";
 //BA.debugLineNum = 260;BA.debugLine="Dim v As Panel";
_v = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 261;BA.debugLine="v=Sender";
_v.setObject((android.view.ViewGroup)(anywheresoftware.b4a.keywords.Common.Sender(mostCurrent.activityBA)));
 //BA.debugLineNum = 262;BA.debugLine="Panel1.SetVisibleAnimated(500,True)";
mostCurrent._panel1.SetVisibleAnimated((int) (500),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 264;BA.debugLine="download(ImageView1,Image.Get(v.Tag))";
_download(mostCurrent._imageview1,BA.ObjectToString(mostCurrent._image.Get((int)(BA.ObjectToNumber(_v.getTag())))));
 //BA.debugLineNum = 265;BA.debugLine="xname.Text=Artist.Get(v.Tag)&\"\"&\" \"&Name.Get(v.Ta";
mostCurrent._xname.setText(BA.ObjectToCharSequence(BA.ObjectToString(mostCurrent._artist.Get((int)(BA.ObjectToNumber(_v.getTag()))))+""+" "+BA.ObjectToString(mostCurrent._name.Get((int)(BA.ObjectToNumber(_v.getTag()))))));
 //BA.debugLineNum = 266;BA.debugLine="linkplay=linkMusic.Get(v.Tag)";
mostCurrent._linkplay = BA.ObjectToString(mostCurrent._linkmusic.Get((int)(BA.ObjectToNumber(_v.getTag()))));
 //BA.debugLineNum = 268;BA.debugLine="sahr11=sahr.Get(v.Tag)";
mostCurrent._sahr11 = BA.ObjectToString(mostCurrent._sahr.Get((int)(BA.ObjectToNumber(_v.getTag()))));
 //BA.debugLineNum = 269;BA.debugLine="qqq=VEIW.Get(v.Tag)";
mostCurrent._qqq = BA.ObjectToString(mostCurrent._veiw.Get((int)(BA.ObjectToNumber(_v.getTag()))));
 //BA.debugLineNum = 270;BA.debugLine="qqqq=id.Get(v.Tag)";
mostCurrent._qqqq = BA.ObjectToString(mostCurrent._id.Get((int)(BA.ObjectToNumber(_v.getTag()))));
 //BA.debugLineNum = 271;BA.debugLine="TELEGRAM2=telegram1.Get(v.Tag)";
mostCurrent._telegram2 = BA.ObjectToString(mostCurrent._telegram1.Get((int)(BA.ObjectToNumber(_v.getTag()))));
 //BA.debugLineNum = 272;BA.debugLine="LINK2=link1.Get(v.Tag)";
mostCurrent._link2 = BA.ObjectToString(mostCurrent._link1.Get((int)(BA.ObjectToNumber(_v.getTag()))));
 //BA.debugLineNum = 274;BA.debugLine="w=Artist.Get(v.Tag)";
mostCurrent._w = BA.ObjectToString(mostCurrent._artist.Get((int)(BA.ObjectToNumber(_v.getTag()))));
 //BA.debugLineNum = 275;BA.debugLine="ww=Name.Get(v.Tag)";
mostCurrent._ww = BA.ObjectToString(mostCurrent._name.Get((int)(BA.ObjectToNumber(_v.getTag()))));
 //BA.debugLineNum = 276;BA.debugLine="www=Image.Get(v.Tag)";
mostCurrent._www = BA.ObjectToString(mostCurrent._image.Get((int)(BA.ObjectToNumber(_v.getTag()))));
 //BA.debugLineNum = 278;BA.debugLine="ImageView6.Visible=False";
mostCurrent._imageview6.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 280;BA.debugLine="ImageView5.Visible=False";
mostCurrent._imageview5.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 282;BA.debugLine="ImageView4.Visible=False";
mostCurrent._imageview4.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 284;BA.debugLine="ImageView3.Visible=False";
mostCurrent._imageview3.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 286;BA.debugLine="Label2.Text=qqq";
mostCurrent._label2.setText(BA.ObjectToCharSequence(mostCurrent._qqq));
 //BA.debugLineNum = 288;BA.debugLine="End Sub";
return "";
}
public static String  _p_longclick() throws Exception{
anywheresoftware.b4a.objects.PanelWrapper _v = null;
int _i = 0;
int _new_num = 0;
 //BA.debugLineNum = 289;BA.debugLine="Sub p_LongClick";
 //BA.debugLineNum = 292;BA.debugLine="Dim v As Panel";
_v = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 293;BA.debugLine="v=Sender";
_v.setObject((android.view.ViewGroup)(anywheresoftware.b4a.keywords.Common.Sender(mostCurrent.activityBA)));
 //BA.debugLineNum = 297;BA.debugLine="cursor1=sql1.ExecQuery(\"SELECT * FROM My_DB\")";
mostCurrent._cursor1.setObject((android.database.Cursor)(mostCurrent._sql1.ExecQuery("SELECT * FROM My_DB")));
 //BA.debugLineNum = 298;BA.debugLine="For i=0 To cursor1.RowCount-1";
{
final int step4 = 1;
final int limit4 = (int) (mostCurrent._cursor1.getRowCount()-1);
_i = (int) (0) ;
for (;(step4 > 0 && _i <= limit4) || (step4 < 0 && _i >= limit4) ;_i = ((int)(0 + _i + step4))  ) {
 //BA.debugLineNum = 299;BA.debugLine="cursor1.Position=i";
mostCurrent._cursor1.setPosition(_i);
 //BA.debugLineNum = 300;BA.debugLine="LogColor(i,Colors.red)";
anywheresoftware.b4a.keywords.Common.LogColor(BA.NumberToString(_i),anywheresoftware.b4a.keywords.Common.Colors.Red);
 //BA.debugLineNum = 301;BA.debugLine="If cursor1.GetString(\"artist\")=Artist.Get(v.Tag)";
if ((mostCurrent._cursor1.GetString("artist")).equals(BA.ObjectToString(mostCurrent._artist.Get((int)(BA.ObjectToNumber(_v.getTag())))))) { 
 //BA.debugLineNum = 302;BA.debugLine="chk=2";
_chk = (int) (2);
 //BA.debugLineNum = 303;BA.debugLine="LogColor(i,Colors.Blue)";
anywheresoftware.b4a.keywords.Common.LogColor(BA.NumberToString(_i),anywheresoftware.b4a.keywords.Common.Colors.Blue);
 };
 }
};
 //BA.debugLineNum = 308;BA.debugLine="If chk=2 Then";
if (_chk==2) { 
 //BA.debugLineNum = 309;BA.debugLine="chk=0";
_chk = (int) (0);
 //BA.debugLineNum = 310;BA.debugLine="ToastMessageShow(\"از قبل در لیست علاقه مندیها قر";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("از قبل در لیست علاقه مندیها قرار گرفته است"),anywheresoftware.b4a.keywords.Common.False);
 }else {
 //BA.debugLineNum = 312;BA.debugLine="ToastMessageShow(\"به لیست علاقمندی اضافه شد\",Fal";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("به لیست علاقمندی اضافه شد"),anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 313;BA.debugLine="cursor1=sql1.ExecQuery(\"SELECT id FROM My_DB\")";
mostCurrent._cursor1.setObject((android.database.Cursor)(mostCurrent._sql1.ExecQuery("SELECT id FROM My_DB")));
 //BA.debugLineNum = 314;BA.debugLine="If cursor1.RowCount>0 Then";
if (mostCurrent._cursor1.getRowCount()>0) { 
 //BA.debugLineNum = 315;BA.debugLine="For i=0 To cursor1.RowCount-1";
{
final int step19 = 1;
final int limit19 = (int) (mostCurrent._cursor1.getRowCount()-1);
_i = (int) (0) ;
for (;(step19 > 0 && _i <= limit19) || (step19 < 0 && _i >= limit19) ;_i = ((int)(0 + _i + step19))  ) {
 //BA.debugLineNum = 316;BA.debugLine="cursor1.Position=i";
mostCurrent._cursor1.setPosition(_i);
 //BA.debugLineNum = 317;BA.debugLine="Dim new_num As Int";
_new_num = 0;
 //BA.debugLineNum = 318;BA.debugLine="new_num=cursor1.GetInt(\"id\")";
_new_num = mostCurrent._cursor1.GetInt("id");
 }
};
 };
 //BA.debugLineNum = 321;BA.debugLine="new_num=new_num+1";
_new_num = (int) (_new_num+1);
 //BA.debugLineNum = 322;BA.debugLine="sql1.ExecNonQuery(\"INSERT INTO My_DB VALUES('\" &";
mostCurrent._sql1.ExecNonQuery("INSERT INTO My_DB VALUES('"+BA.NumberToString(_new_num)+"','"+mostCurrent._w+"','"+mostCurrent._ww+"','"+mostCurrent._www+"','"+mostCurrent._linkplay+"','"+mostCurrent._sahr11+"','"+mostCurrent._telegram2+"','"+mostCurrent._linkplay+"','"+mostCurrent._qqq+"')");
 };
 //BA.debugLineNum = 326;BA.debugLine="End Sub";
return "";
}
public static String  _panel1_touch(int _action,float _x,float _y) throws Exception{
 //BA.debugLineNum = 433;BA.debugLine="Sub Panel1_Touch (Action As Int, X As Float, Y As";
 //BA.debugLineNum = 435;BA.debugLine="End Sub";
return "";
}
public static String  _pause_click() throws Exception{
 //BA.debugLineNum = 408;BA.debugLine="Sub pause_Click";
 //BA.debugLineNum = 409;BA.debugLine="ToastMessageShow(\"توقف\",  True)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("توقف"),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 410;BA.debugLine="If isplaying=True Then";
if (_isplaying==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 411;BA.debugLine="music.pause";
mostCurrent._music.pause();
 //BA.debugLineNum = 412;BA.debugLine="isplaying=True";
_isplaying = anywheresoftware.b4a.keywords.Common.True;
 };
 //BA.debugLineNum = 415;BA.debugLine="Try";
try { //BA.debugLineNum = 417;BA.debugLine="sound.Load(File.DirRootExternal & \"/doholimusic\"";
mostCurrent._sound.Load(anywheresoftware.b4a.keywords.Common.File.getDirRootExternal()+"/doholimusic",mostCurrent._ss);
 //BA.debugLineNum = 418;BA.debugLine="sound.Looping=False";
mostCurrent._sound.setLooping(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 419;BA.debugLine="sound.Stop";
mostCurrent._sound.Stop();
 } 
       catch (Exception e11) {
			processBA.setLastException(e11); //BA.debugLineNum = 422;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.Log(BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)));
 };
 //BA.debugLineNum = 425;BA.debugLine="End Sub";
return "";
}
public static String  _play_click() throws Exception{
anywheresoftware.b4a.samples.httputils2.httpjob _edit = null;
 //BA.debugLineNum = 364;BA.debugLine="Sub play_Click";
 //BA.debugLineNum = 366;BA.debugLine="ToastMessageShow(\"چند لحظه صبر نمایید...\",  True)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("چند لحظه صبر نمایید..."),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 367;BA.debugLine="asa =qqq + 1";
_asa = (int) ((double)(Double.parseDouble(mostCurrent._qqq))+1);
 //BA.debugLineNum = 369;BA.debugLine="Log(asa)";
anywheresoftware.b4a.keywords.Common.Log(BA.NumberToString(_asa));
 //BA.debugLineNum = 371;BA.debugLine="DoEvents";
anywheresoftware.b4a.keywords.Common.DoEvents();
 //BA.debugLineNum = 372;BA.debugLine="Dim edit As HttpJob";
_edit = new anywheresoftware.b4a.samples.httputils2.httpjob();
 //BA.debugLineNum = 373;BA.debugLine="edit.Initialize(\"edit\",Me)";
_edit._initialize(processBA,"edit",asheghi.getObject());
 //BA.debugLineNum = 374;BA.debugLine="edit.PostString(\"http://doholi.com/edit/edit9.php";
_edit._poststring("http://doholi.com/edit/edit9.php","VEIW="+BA.NumberToString(_asa)+"&id="+mostCurrent._qqqq);
 //BA.debugLineNum = 379;BA.debugLine="s=Regex.Split(\"/\",linkplay)";
mostCurrent._s = anywheresoftware.b4a.keywords.Common.Regex.Split("/",mostCurrent._linkplay);
 //BA.debugLineNum = 381;BA.debugLine="ss=s(5)";
mostCurrent._ss = mostCurrent._s[(int) (5)];
 //BA.debugLineNum = 382;BA.debugLine="If File.Exists(File.DirRootExternal & \"/doholimus";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirRootExternal()+"/doholimusic",mostCurrent._ss)==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 384;BA.debugLine="If isplaying=False Then";
if (_isplaying==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 385;BA.debugLine="music.Create(linkplay)";
mostCurrent._music.Create(processBA,mostCurrent._linkplay);
 //BA.debugLineNum = 386;BA.debugLine="music.Play";
mostCurrent._music.Play();
 //BA.debugLineNum = 387;BA.debugLine="timer1.Initialize(\"t\",5)";
mostCurrent._timer1.Initialize(processBA,"t",(long) (5));
 //BA.debugLineNum = 388;BA.debugLine="timer1.Enabled=True";
mostCurrent._timer1.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 389;BA.debugLine="isplaying=True";
_isplaying = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 390;BA.debugLine="SeekBar1.Visible=True";
mostCurrent._seekbar1.setVisible(anywheresoftware.b4a.keywords.Common.True);
 }else {
 //BA.debugLineNum = 392;BA.debugLine="music.Play";
mostCurrent._music.Play();
 //BA.debugLineNum = 393;BA.debugLine="isplaying=True";
_isplaying = anywheresoftware.b4a.keywords.Common.True;
 };
 }else {
 //BA.debugLineNum = 398;BA.debugLine="ToastMessageShow(\"فایل موجود است\",  True)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("فایل موجود است"),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 399;BA.debugLine="Dim sound As MediaPlayer";
mostCurrent._sound = new anywheresoftware.b4a.objects.MediaPlayerWrapper();
 //BA.debugLineNum = 400;BA.debugLine="sound.Initialize2(\"\")";
mostCurrent._sound.Initialize2(processBA,"");
 //BA.debugLineNum = 401;BA.debugLine="sound.Load(File.DirRootExternal & \"/doholimusic\"";
mostCurrent._sound.Load(anywheresoftware.b4a.keywords.Common.File.getDirRootExternal()+"/doholimusic",mostCurrent._ss);
 //BA.debugLineNum = 402;BA.debugLine="sound.Looping=False";
mostCurrent._sound.setLooping(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 403;BA.debugLine="sound.Play";
mostCurrent._sound.Play();
 };
 //BA.debugLineNum = 407;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 8;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 10;BA.debugLine="End Sub";
return "";
}
public static String  _t_tick() throws Exception{
 //BA.debugLineNum = 426;BA.debugLine="Sub t_Tick";
 //BA.debugLineNum = 428;BA.debugLine="time.Text=music.PlayingTime";
mostCurrent._time.setText(BA.ObjectToCharSequence(mostCurrent._music.PlayingTime()));
 //BA.debugLineNum = 429;BA.debugLine="timer1.Enabled=True";
mostCurrent._timer1.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 431;BA.debugLine="End Sub";
return "";
}
public static String  _tin_tick() throws Exception{
 //BA.debugLineNum = 552;BA.debugLine="Sub tin_Tick";
 //BA.debugLineNum = 553;BA.debugLine="ImageView1.SetBackgroundImage(Null)";
mostCurrent._imageview1.SetBackgroundImageNew((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 555;BA.debugLine="tin.Enabled=False";
mostCurrent._tin.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 556;BA.debugLine="End Sub";
return "";
}
}
