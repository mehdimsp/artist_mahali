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

public class sabzevar extends Activity implements B4AActivity{
	public static sabzevar mostCurrent;
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
			processBA = new BA(this.getApplicationContext(), null, null, "com.doomadi.artist", "com.doomadi.artist.sabzevar");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (sabzevar).");
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
		activityBA = new BA(this, layout, processBA, "com.doomadi.artist", "com.doomadi.artist.sabzevar");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "com.doomadi.artist.sabzevar", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (sabzevar) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (sabzevar) Resume **");
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
		return sabzevar.class;
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
        BA.LogInfo("** Activity (sabzevar) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
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
            BA.LogInfo("** Activity (sabzevar) Resume **");
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
public anywheresoftware.b4a.objects.ScrollViewWrapper _scrollview1 = null;
public anywheresoftware.b4a.objects.ButtonWrapper _button1 = null;
public anywheresoftware.b4a.objects.ButtonWrapper _button2 = null;
public anywheresoftware.b4a.objects.ButtonWrapper _button3 = null;
public anywheresoftware.b4a.objects.ButtonWrapper _button4 = null;
public anywheresoftware.b4a.objects.ButtonWrapper _button5 = null;
public anywheresoftware.b4a.objects.ButtonWrapper _button6 = null;
public anywheresoftware.b4a.objects.ButtonWrapper _button7 = null;
public anywheresoftware.b4a.objects.ButtonWrapper _button8 = null;
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
 //BA.debugLineNum = 15;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 16;BA.debugLine="Activity.LoadLayout(\"scrol\")";
mostCurrent._activity.LoadLayout("scrol",mostCurrent.activityBA);
 //BA.debugLineNum = 17;BA.debugLine="ScrollView1.Panel.LoadLayout(\"ahang\")";
mostCurrent._scrollview1.getPanel().LoadLayout("ahang",mostCurrent.activityBA);
 //BA.debugLineNum = 18;BA.debugLine="ScrollView1.Panel.Height = 120%y";
mostCurrent._scrollview1.getPanel().setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (120),mostCurrent.activityBA));
 //BA.debugLineNum = 19;BA.debugLine="ostanha.Button(Button1,\"علی براتی\")";
mostCurrent._ostanha._button(mostCurrent.activityBA,mostCurrent._button1,"علی براتی");
 //BA.debugLineNum = 20;BA.debugLine="ostanha.Button(Button2,\"محسن دولت\")";
mostCurrent._ostanha._button(mostCurrent.activityBA,mostCurrent._button2,"محسن دولت");
 //BA.debugLineNum = 21;BA.debugLine="ostanha.Button(Button3,\"حسین عاشقی\")";
mostCurrent._ostanha._button(mostCurrent.activityBA,mostCurrent._button3,"حسین عاشقی");
 //BA.debugLineNum = 22;BA.debugLine="ostanha.Button(Button4,\"دل افکار\")";
mostCurrent._ostanha._button(mostCurrent.activityBA,mostCurrent._button4,"دل افکار");
 //BA.debugLineNum = 23;BA.debugLine="ostanha.Button(Button5,\"باغشنی\")";
mostCurrent._ostanha._button(mostCurrent.activityBA,mostCurrent._button5,"باغشنی");
 //BA.debugLineNum = 24;BA.debugLine="ostanha.Button(Button6,\"حسین حیدری\")";
mostCurrent._ostanha._button(mostCurrent.activityBA,mostCurrent._button6,"حسین حیدری");
 //BA.debugLineNum = 25;BA.debugLine="ostanha.Button(Button7,\"اصغر باکردار\")";
mostCurrent._ostanha._button(mostCurrent.activityBA,mostCurrent._button7,"اصغر باکردار");
 //BA.debugLineNum = 27;BA.debugLine="End Sub";
return "";
}
public static boolean  _activity_keypress(int _keycode) throws Exception{
 //BA.debugLineNum = 111;BA.debugLine="Sub Activity_KeyPress (KeyCode As Int) As Boolean";
 //BA.debugLineNum = 113;BA.debugLine="If KeyCode = KeyCodes.KEYCODE_BACK Then";
if (_keycode==anywheresoftware.b4a.keywords.Common.KeyCodes.KEYCODE_BACK) { 
 //BA.debugLineNum = 114;BA.debugLine="StartActivity(\"razavi\")";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)("razavi"));
 //BA.debugLineNum = 115;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 116;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 }else {
 //BA.debugLineNum = 118;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 };
 //BA.debugLineNum = 120;BA.debugLine="End Sub";
return false;
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 33;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 35;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 29;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 31;BA.debugLine="End Sub";
return "";
}
public static String  _button1_click() throws Exception{
 //BA.debugLineNum = 62;BA.debugLine="Sub Button1_Click";
 //BA.debugLineNum = 64;BA.debugLine="StartActivity(barati)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._barati.getObject()));
 //BA.debugLineNum = 65;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 66;BA.debugLine="End Sub";
return "";
}
public static String  _button2_click() throws Exception{
 //BA.debugLineNum = 67;BA.debugLine="Sub Button2_Click";
 //BA.debugLineNum = 69;BA.debugLine="StartActivity(dolat)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._dolat.getObject()));
 //BA.debugLineNum = 70;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 71;BA.debugLine="End Sub";
return "";
}
public static String  _button3_click() throws Exception{
 //BA.debugLineNum = 73;BA.debugLine="Sub Button3_Click";
 //BA.debugLineNum = 74;BA.debugLine="StartActivity(asheghi)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._asheghi.getObject()));
 //BA.debugLineNum = 75;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 76;BA.debugLine="End Sub";
return "";
}
public static String  _button4_click() throws Exception{
 //BA.debugLineNum = 78;BA.debugLine="Sub Button4_Click";
 //BA.debugLineNum = 79;BA.debugLine="StartActivity(delafkar)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._delafkar.getObject()));
 //BA.debugLineNum = 80;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 81;BA.debugLine="End Sub";
return "";
}
public static String  _button5_click() throws Exception{
 //BA.debugLineNum = 83;BA.debugLine="Sub Button5_Click";
 //BA.debugLineNum = 84;BA.debugLine="StartActivity(baghshani)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._baghshani.getObject()));
 //BA.debugLineNum = 85;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 86;BA.debugLine="End Sub";
return "";
}
public static String  _button6_click() throws Exception{
 //BA.debugLineNum = 88;BA.debugLine="Sub Button6_Click";
 //BA.debugLineNum = 90;BA.debugLine="StartActivity(hydari)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._hydari.getObject()));
 //BA.debugLineNum = 91;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 93;BA.debugLine="End Sub";
return "";
}
public static String  _button7_click() throws Exception{
 //BA.debugLineNum = 95;BA.debugLine="Sub Button7_Click";
 //BA.debugLineNum = 97;BA.debugLine="StartActivity(bakerdar)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._bakerdar.getObject()));
 //BA.debugLineNum = 98;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 100;BA.debugLine="End Sub";
return "";
}
public static String  _button8_click() throws Exception{
 //BA.debugLineNum = 104;BA.debugLine="Sub Button8_Click";
 //BA.debugLineNum = 106;BA.debugLine="Msgbox(\"اگر نام شما در لیست نیست از قسمت ارتباط ب";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("اگر نام شما در لیست نیست از قسمت ارتباط با ما اقدام نمایید با تشکر"),BA.ObjectToCharSequence("توجه!"),mostCurrent.activityBA);
 //BA.debugLineNum = 108;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 10;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 11;BA.debugLine="Private ScrollView1 As ScrollView";
mostCurrent._scrollview1 = new anywheresoftware.b4a.objects.ScrollViewWrapper();
 //BA.debugLineNum = 12;BA.debugLine="Private Button1, Button2, Button3, Button4 , Butt";
mostCurrent._button1 = new anywheresoftware.b4a.objects.ButtonWrapper();
mostCurrent._button2 = new anywheresoftware.b4a.objects.ButtonWrapper();
mostCurrent._button3 = new anywheresoftware.b4a.objects.ButtonWrapper();
mostCurrent._button4 = new anywheresoftware.b4a.objects.ButtonWrapper();
mostCurrent._button5 = new anywheresoftware.b4a.objects.ButtonWrapper();
mostCurrent._button6 = new anywheresoftware.b4a.objects.ButtonWrapper();
mostCurrent._button7 = new anywheresoftware.b4a.objects.ButtonWrapper();
mostCurrent._button8 = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 13;BA.debugLine="End Sub";
return "";
}
public static String  _imageview1_click() throws Exception{
 //BA.debugLineNum = 36;BA.debugLine="Sub ImageView1_Click";
 //BA.debugLineNum = 37;BA.debugLine="StartActivity(Main)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._main.getObject()));
 //BA.debugLineNum = 38;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 39;BA.debugLine="End Sub";
return "";
}
public static String  _imageview2_click() throws Exception{
 //BA.debugLineNum = 42;BA.debugLine="Sub ImageView2_Click";
 //BA.debugLineNum = 43;BA.debugLine="StartActivity(doomadi)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._doomadi.getObject()));
 //BA.debugLineNum = 44;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 45;BA.debugLine="End Sub";
return "";
}
public static String  _imageview3_click() throws Exception{
 //BA.debugLineNum = 46;BA.debugLine="Sub ImageView3_Click";
 //BA.debugLineNum = 49;BA.debugLine="StartActivity(ostan)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._ostan.getObject()));
 //BA.debugLineNum = 50;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 52;BA.debugLine="End Sub";
return "";
}
public static String  _imageview4_click() throws Exception{
 //BA.debugLineNum = 53;BA.debugLine="Sub ImageView4_Click";
 //BA.debugLineNum = 56;BA.debugLine="StartActivity(tamas)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._tamas.getObject()));
 //BA.debugLineNum = 57;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 59;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 8;BA.debugLine="End Sub";
return "";
}
}
