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

public class main extends Activity implements B4AActivity{
	public static main mostCurrent;
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
			processBA = new BA(this.getApplicationContext(), null, null, "com.doomadi.artist", "com.doomadi.artist.main");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (main).");
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
		activityBA = new BA(this, layout, processBA, "com.doomadi.artist", "com.doomadi.artist.main");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "com.doomadi.artist.main", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (main) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (main) Resume **");
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
		return main.class;
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
        BA.LogInfo("** Activity (main) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
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
            BA.LogInfo("** Activity (main) Resume **");
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
public anywheresoftware.b4a.phone.Phone.PhoneWakeState _f = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _imageview1 = null;
public anywheresoftware.b4a.objects.ButtonWrapper _button1 = null;
public anywheresoftware.b4a.objects.ButtonWrapper _button2 = null;
public anywheresoftware.b4a.objects.ButtonWrapper _button3 = null;
public anywheresoftware.b4a.objects.ButtonWrapper _button4 = null;
public anywheresoftware.b4a.objects.ButtonWrapper _button5 = null;
public anywheresoftware.b4a.objects.ButtonWrapper _button6 = null;
public anywheresoftware.b4a.samples.httputils2.httputils2service _httputils2service = null;
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

public static boolean isAnyActivityVisible() {
    boolean vis = false;
vis = vis | (main.mostCurrent != null);
vis = vis | (ostan.mostCurrent != null);
vis = vis | (sistan.mostCurrent != null);
vis = vis | (mazhabi.mostCurrent != null);
vis = vis | (rezaee.mostCurrent != null);
vis = vis | (yusofi.mostCurrent != null);
vis = vis | (jafari.mostCurrent != null);
vis = vis | (neyshboor.mostCurrent != null);
vis = vis | (tamas.mostCurrent != null);
vis = vis | (kashmar.mostCurrent != null);
vis = vis | (mashhad.mostCurrent != null);
vis = vis | (golestan.mostCurrent != null);
vis = vis | (behrozvazefeh.mostCurrent != null);
vis = vis | (siyavashyosofi.mostCurrent != null);
vis = vis | (yasermahmoodi.mostCurrent != null);
vis = vis | (azgharbi.mostCurrent != null);
vis = vis | (tehran.mostCurrent != null);
vis = vis | (azsharghi.mostCurrent != null);
vis = vis | (elam.mostCurrent != null);
vis = vis | (zanjan.mostCurrent != null);
vis = vis | (aliabdol.mostCurrent != null);
vis = vis | (ammar.mostCurrent != null);
vis = vis | (barshan.mostCurrent != null);
vis = vis | (tarkesh.mostCurrent != null);
vis = vis | (raeesi.mostCurrent != null);
vis = vis | (razavi.mostCurrent != null);
vis = vis | (moosazadeh.mostCurrent != null);
vis = vis | (torbat.mostCurrent != null);
vis = vis | (yaghoobi.mostCurrent != null);
vis = vis | (bejstan.mostCurrent != null);
vis = vis | (sohyli.mostCurrent != null);
vis = vis | (dori.mostCurrent != null);
vis = vis | (changi.mostCurrent != null);
vis = vis | (hormozgan.mostCurrent != null);
vis = vis | (rajabzadeh.mostCurrent != null);
vis = vis | (sharee.mostCurrent != null);
vis = vis | (fars.mostCurrent != null);
vis = vis | (gonabad.mostCurrent != null);
vis = vis | (barati.mostCurrent != null);
vis = vis | (alinzhad.mostCurrent != null);
vis = vis | (mazandaran.mostCurrent != null);
vis = vis | (kermanshah.mostCurrent != null);
vis = vis | (azizvisi.mostCurrent != null);
vis = vis | (pazvari.mostCurrent != null);
vis = vis | (shomalikh.mostCurrent != null);
vis = vis | (esfarayen.mostCurrent != null);
vis = vis | (taghizadeh.mostCurrent != null);
vis = vis | (shirvan.mostCurrent != null);
vis = vis | (eghbali.mostCurrent != null);
vis = vis | (torbatjam.mostCurrent != null);
vis = vis | (delnavaz.mostCurrent != null);
vis = vis | (doomadi.mostCurrent != null);
vis = vis | (alborz.mostCurrent != null);
vis = vis | (javan.mostCurrent != null);
vis = vis | (alavivahid.mostCurrent != null);
vis = vis | (seydi.mostCurrent != null);
vis = vis | (sedooghi.mostCurrent != null);
vis = vis | (a.mostCurrent != null);
vis = vis | (mamadjan.mostCurrent != null);
vis = vis | (abdini.mostCurrent != null);
vis = vis | (hasanzadeh.mostCurrent != null);
vis = vis | (choopan.mostCurrent != null);
vis = vis | (zhiyan.mostCurrent != null);
vis = vis | (barghi.mostCurrent != null);
vis = vis | (amjadiyan.mostCurrent != null);
vis = vis | (abdi.mostCurrent != null);
vis = vis | (khakshoor.mostCurrent != null);
vis = vis | (falah.mostCurrent != null);
vis = vis | (jafarzadeh.mostCurrent != null);
vis = vis | (dolat.mostCurrent != null);
vis = vis | (asheghi.mostCurrent != null);
vis = vis | (delafkar.mostCurrent != null);
vis = vis | (baghshani.mostCurrent != null);
vis = vis | (hydari.mostCurrent != null);
vis = vis | (bakerdar.mostCurrent != null);
vis = vis | (ghaemi.mostCurrent != null);
vis = vis | (mirzadeh.mostCurrent != null);
vis = vis | (karaj.mostCurrent != null);
vis = vis | (asadyan.mostCurrent != null);
vis = vis | (yousof.mostCurrent != null);
vis = vis | (sabzevar.mostCurrent != null);
vis = vis | (tozih.mostCurrent != null);
vis = vis | (mortzajafarzadeh.mostCurrent != null);
vis = vis | (charmahal.mostCurrent != null);
vis = vis | (didar.mostCurrent != null);
vis = vis | (zilabi.mostCurrent != null);
vis = vis | (lorestan.mostCurrent != null);
vis = vis | (yazd.mostCurrent != null);
vis = vis | (jonoobi.mostCurrent != null);
vis = vis | (ehsantavakoli.mostCurrent != null);
vis = vis | (amirhosinazari.mostCurrent != null);
vis = vis | (jalalfarajiyan.mostCurrent != null);
vis = vis | (sajadrazmjoo.mostCurrent != null);
vis = vis | (mohamadazimi.mostCurrent != null);
vis = vis | (ardabil.mostCurrent != null);
return vis;}
public static String  _activity_create(boolean _firsttime) throws Exception{
anywheresoftware.b4j.object.JavaObject _jo = null;
 //BA.debugLineNum = 34;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 36;BA.debugLine="Activity.LoadLayout(\"2\")";
mostCurrent._activity.LoadLayout("2",mostCurrent.activityBA);
 //BA.debugLineNum = 37;BA.debugLine="f.KeepAlive(True)";
mostCurrent._f.KeepAlive(processBA,anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 38;BA.debugLine="Dim jo As JavaObject";
_jo = new anywheresoftware.b4j.object.JavaObject();
 //BA.debugLineNum = 39;BA.debugLine="jo.InitializeContext";
_jo.InitializeContext(processBA);
 //BA.debugLineNum = 43;BA.debugLine="End Sub";
return "";
}
public static boolean  _activity_keypress(int _keycode) throws Exception{
 //BA.debugLineNum = 96;BA.debugLine="Sub Activity_KeyPress (KeyCode As Int) As Boolean";
 //BA.debugLineNum = 98;BA.debugLine="If KeyCode = KeyCodes.KEYCODE_BACK Then";
if (_keycode==anywheresoftware.b4a.keywords.Common.KeyCodes.KEYCODE_BACK) { 
 //BA.debugLineNum = 100;BA.debugLine="If (Msgbox2(\"ایا قصد خروج از برنامه را دارین؟\",\"";
if ((anywheresoftware.b4a.keywords.Common.Msgbox2(BA.ObjectToCharSequence("ایا قصد خروج از برنامه را دارین؟"),BA.ObjectToCharSequence("خروج از برنامه !"),"بله","نه","",(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null),mostCurrent.activityBA)==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE)) { 
 //BA.debugLineNum = 102;BA.debugLine="ExitApplication";
anywheresoftware.b4a.keywords.Common.ExitApplication();
 //BA.debugLineNum = 104;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 }else {
 //BA.debugLineNum = 107;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 };
 };
 //BA.debugLineNum = 114;BA.debugLine="End Sub";
return false;
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 52;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 54;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 48;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 50;BA.debugLine="End Sub";
return "";
}
public static String  _button1_click() throws Exception{
 //BA.debugLineNum = 92;BA.debugLine="Sub Button1_Click";
 //BA.debugLineNum = 93;BA.debugLine="StartActivity(ostan)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._ostan.getObject()));
 //BA.debugLineNum = 94;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 95;BA.debugLine="End Sub";
return "";
}
public static String  _button2_click() throws Exception{
 //BA.debugLineNum = 87;BA.debugLine="Sub Button2_Click";
 //BA.debugLineNum = 88;BA.debugLine="Msgbox(\"این قسمت در نسخه بعدی فعال میگردد با تشکر";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("این قسمت در نسخه بعدی فعال میگردد با تشکر"),BA.ObjectToCharSequence("توجه!"),mostCurrent.activityBA);
 //BA.debugLineNum = 90;BA.debugLine="End Sub";
return "";
}
public static String  _button3_click() throws Exception{
 //BA.debugLineNum = 80;BA.debugLine="Sub Button3_Click";
 //BA.debugLineNum = 82;BA.debugLine="StartActivity(mazhabi)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._mazhabi.getObject()));
 //BA.debugLineNum = 83;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 84;BA.debugLine="End Sub";
return "";
}
public static String  _button4_click() throws Exception{
 //BA.debugLineNum = 70;BA.debugLine="Sub Button4_Click";
 //BA.debugLineNum = 73;BA.debugLine="StartActivity(doomadi)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._doomadi.getObject()));
 //BA.debugLineNum = 74;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 77;BA.debugLine="End Sub";
return "";
}
public static String  _button5_click() throws Exception{
 //BA.debugLineNum = 65;BA.debugLine="Sub Button5_Click";
 //BA.debugLineNum = 66;BA.debugLine="StartActivity(tamas)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._tamas.getObject()));
 //BA.debugLineNum = 67;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 68;BA.debugLine="End Sub";
return "";
}
public static String  _button6_click() throws Exception{
 //BA.debugLineNum = 60;BA.debugLine="Sub Button6_Click";
 //BA.debugLineNum = 61;BA.debugLine="StartActivity(tozih)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._tozih.getObject()));
 //BA.debugLineNum = 62;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 63;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 21;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 24;BA.debugLine="Dim f As PhoneWakeState";
mostCurrent._f = new anywheresoftware.b4a.phone.Phone.PhoneWakeState();
 //BA.debugLineNum = 25;BA.debugLine="Private ImageView1 As ImageView";
mostCurrent._imageview1 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 26;BA.debugLine="Private Button1 As Button";
mostCurrent._button1 = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 27;BA.debugLine="Private Button2 As Button";
mostCurrent._button2 = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 28;BA.debugLine="Private Button3 As Button";
mostCurrent._button3 = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 29;BA.debugLine="Private Button4 As Button";
mostCurrent._button4 = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 30;BA.debugLine="Private Button5 As Button";
mostCurrent._button5 = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 31;BA.debugLine="Private Button6 As Button";
mostCurrent._button6 = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 32;BA.debugLine="End Sub";
return "";
}

public static void initializeProcessGlobals() {
    
    if (main.processGlobalsRun == false) {
	    main.processGlobalsRun = true;
		try {
		        anywheresoftware.b4a.samples.httputils2.httputils2service._process_globals();
main._process_globals();
starter._process_globals();
ostan._process_globals();
ostanha._process_globals();
sistan._process_globals();
mazhabi._process_globals();
rezaee._process_globals();
yusofi._process_globals();
jafari._process_globals();
neyshboor._process_globals();
tamas._process_globals();
kashmar._process_globals();
mashhad._process_globals();
golestan._process_globals();
behrozvazefeh._process_globals();
siyavashyosofi._process_globals();
yasermahmoodi._process_globals();
azgharbi._process_globals();
tehran._process_globals();
azsharghi._process_globals();
elam._process_globals();
zanjan._process_globals();
aliabdol._process_globals();
ammar._process_globals();
barshan._process_globals();
tarkesh._process_globals();
raeesi._process_globals();
razavi._process_globals();
moosazadeh._process_globals();
torbat._process_globals();
yaghoobi._process_globals();
bejstan._process_globals();
sohyli._process_globals();
dori._process_globals();
changi._process_globals();
hormozgan._process_globals();
rajabzadeh._process_globals();
sharee._process_globals();
fars._process_globals();
gonabad._process_globals();
barati._process_globals();
alinzhad._process_globals();
mazandaran._process_globals();
kermanshah._process_globals();
azizvisi._process_globals();
pazvari._process_globals();
shomalikh._process_globals();
esfarayen._process_globals();
taghizadeh._process_globals();
shirvan._process_globals();
eghbali._process_globals();
torbatjam._process_globals();
delnavaz._process_globals();
doomadi._process_globals();
alborz._process_globals();
javan._process_globals();
alavivahid._process_globals();
seydi._process_globals();
sedooghi._process_globals();
a._process_globals();
mamadjan._process_globals();
abdini._process_globals();
hasanzadeh._process_globals();
choopan._process_globals();
zhiyan._process_globals();
barghi._process_globals();
amjadiyan._process_globals();
abdi._process_globals();
khakshoor._process_globals();
falah._process_globals();
jafarzadeh._process_globals();
dolat._process_globals();
asheghi._process_globals();
delafkar._process_globals();
baghshani._process_globals();
hydari._process_globals();
bakerdar._process_globals();
ghaemi._process_globals();
mirzadeh._process_globals();
karaj._process_globals();
asadyan._process_globals();
yousof._process_globals();
sabzevar._process_globals();
tozih._process_globals();
imagedownloader._process_globals();
mortzajafarzadeh._process_globals();
charmahal._process_globals();
didar._process_globals();
zilabi._process_globals();
lorestan._process_globals();
yazd._process_globals();
jonoobi._process_globals();
ehsantavakoli._process_globals();
amirhosinazari._process_globals();
jalalfarajiyan._process_globals();
sajadrazmjoo._process_globals();
mohamadazimi._process_globals();
ardabil._process_globals();
		
        } catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
}public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 17;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 19;BA.debugLine="End Sub";
return "";
}
}
