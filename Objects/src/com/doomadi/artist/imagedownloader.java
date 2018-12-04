package com.doomadi.artist;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.objects.ServiceHelper;
import anywheresoftware.b4a.debug.*;

public class imagedownloader extends  android.app.Service{
	public static class imagedownloader_BR extends android.content.BroadcastReceiver {

		@Override
		public void onReceive(android.content.Context context, android.content.Intent intent) {
			android.content.Intent in = new android.content.Intent(context, imagedownloader.class);
			if (intent != null)
				in.putExtra("b4a_internal_intent", intent);
			context.startService(in);
		}

	}
    static imagedownloader mostCurrent;
	public static BA processBA;
    private ServiceHelper _service;
    public static Class<?> getObject() {
		return imagedownloader.class;
	}
	@Override
	public void onCreate() {
        super.onCreate();
        mostCurrent = this;
        if (processBA == null) {
		    processBA = new BA(this, null, null, "com.doomadi.artist", "com.doomadi.artist.imagedownloader");
            if (BA.isShellModeRuntimeCheck(processBA)) {
                processBA.raiseEvent2(null, true, "SHELL", false);
		    }
            try {
                Class.forName(BA.applicationContext.getPackageName() + ".main").getMethod("initializeProcessGlobals").invoke(null, null);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            processBA.loadHtSubs(this.getClass());
            ServiceHelper.init();
        }
        _service = new ServiceHelper(this);
        processBA.service = this;
        
        if (BA.isShellModeRuntimeCheck(processBA)) {
			processBA.raiseEvent2(null, true, "CREATE", true, "com.doomadi.artist.imagedownloader", processBA, _service, anywheresoftware.b4a.keywords.Common.Density);
		}
        if (!false && ServiceHelper.StarterHelper.startFromServiceCreate(processBA, false) == false) {
				
		}
		else {
            processBA.setActivityPaused(false);
            BA.LogInfo("*** Service (imagedownloader) Create ***");
            processBA.raiseEvent(null, "service_create");
        }
        processBA.runHook("oncreate", this, null);
        if (false) {
			ServiceHelper.StarterHelper.runWaitForLayouts();
		}
    }
		@Override
	public void onStart(android.content.Intent intent, int startId) {
		onStartCommand(intent, 0, 0);
    }
    @Override
    public int onStartCommand(final android.content.Intent intent, int flags, int startId) {
    	if (ServiceHelper.StarterHelper.onStartCommand(processBA, new Runnable() {
            public void run() {
                handleStart(intent);
            }}))
			;
		else {
			ServiceHelper.StarterHelper.addWaitForLayout (new Runnable() {
				public void run() {
                    processBA.setActivityPaused(false);
                    BA.LogInfo("** Service (imagedownloader) Create **");
                    processBA.raiseEvent(null, "service_create");
					handleStart(intent);
                    ServiceHelper.StarterHelper.removeWaitForLayout();
				}
			});
		}
        processBA.runHook("onstartcommand", this, new Object[] {intent, flags, startId});
		return android.app.Service.START_NOT_STICKY;
    }
    public void onTaskRemoved(android.content.Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
        if (false)
            processBA.raiseEvent(null, "service_taskremoved");
            
    }
    private void handleStart(android.content.Intent intent) {
    	BA.LogInfo("** Service (imagedownloader) Start **");
    	java.lang.reflect.Method startEvent = processBA.htSubs.get("service_start");
    	if (startEvent != null) {
    		if (startEvent.getParameterTypes().length > 0) {
    			anywheresoftware.b4a.objects.IntentWrapper iw = new anywheresoftware.b4a.objects.IntentWrapper();
    			if (intent != null) {
    				if (intent.hasExtra("b4a_internal_intent"))
    					iw.setObject((android.content.Intent) intent.getParcelableExtra("b4a_internal_intent"));
    				else
    					iw.setObject(intent);
    			}
    			processBA.raiseEvent(null, "service_start", iw);
    		}
    		else {
    			processBA.raiseEvent(null, "service_start");
    		}
    	}
    }
	
	@Override
	public void onDestroy() {
        super.onDestroy();
        BA.LogInfo("** Service (imagedownloader) Destroy **");
		processBA.raiseEvent(null, "service_destroy");
        processBA.service = null;
		mostCurrent = null;
		processBA.setActivityPaused(true);
        processBA.runHook("ondestroy", this, null);
	}

@Override
	public android.os.IBinder onBind(android.content.Intent intent) {
		return null;
	}public anywheresoftware.b4a.keywords.Common __c = null;
public static anywheresoftware.b4a.objects.collections.Map _cache = null;
public static anywheresoftware.b4a.objects.collections.Map _tasks = null;
public static anywheresoftware.b4a.objects.collections.Map _ongoingtasks = null;
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
public com.doomadi.artist.sabzevar _sabzevar = null;
public com.doomadi.artist.tozih _tozih = null;
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
public static String  _activityispaused() throws Exception{
 //BA.debugLineNum = 63;BA.debugLine="Sub ActivityIsPaused";
 //BA.debugLineNum = 64;BA.debugLine="tasks.Clear";
_tasks.Clear();
 //BA.debugLineNum = 65;BA.debugLine="End Sub";
return "";
}
public static String  _download(anywheresoftware.b4a.objects.collections.Map _imageviewsmap) throws Exception{
int _i = 0;
String _link = "";
anywheresoftware.b4a.objects.ImageViewWrapper _iv = null;
anywheresoftware.b4a.samples.httputils2.httpjob _j = null;
 //BA.debugLineNum = 25;BA.debugLine="Sub Download (ImageViewsMap As Map)";
 //BA.debugLineNum = 26;BA.debugLine="Try";
try { //BA.debugLineNum = 27;BA.debugLine="For i = 0 To ImageViewsMap.Size - 1";
{
final int step2 = 1;
final int limit2 = (int) (_imageviewsmap.getSize()-1);
_i = (int) (0) ;
for (;(step2 > 0 && _i <= limit2) || (step2 < 0 && _i >= limit2) ;_i = ((int)(0 + _i + step2))  ) {
 //BA.debugLineNum = 28;BA.debugLine="tasks.Put(ImageViewsMap.GetKeyAt(i), ImageViews";
_tasks.Put(_imageviewsmap.GetKeyAt(_i),_imageviewsmap.GetValueAt(_i));
 //BA.debugLineNum = 29;BA.debugLine="Dim link As String = ImageViewsMap.GetValueAt(i";
_link = BA.ObjectToString(_imageviewsmap.GetValueAt(_i));
 //BA.debugLineNum = 30;BA.debugLine="If cache.ContainsKey(link) Then";
if (_cache.ContainsKey((Object)(_link))) { 
 //BA.debugLineNum = 31;BA.debugLine="Dim iv As ImageView = ImageViewsMap.GetKeyAt(i";
_iv = new anywheresoftware.b4a.objects.ImageViewWrapper();
_iv.setObject((android.widget.ImageView)(_imageviewsmap.GetKeyAt(_i)));
 //BA.debugLineNum = 32;BA.debugLine="iv.SetBackgroundImage(cache.Get(link))";
_iv.SetBackgroundImageNew((android.graphics.Bitmap)(_cache.Get((Object)(_link))));
 }else if(_ongoingtasks.ContainsKey((Object)(_link))==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 34;BA.debugLine="ongoingTasks.Put(link, \"\")";
_ongoingtasks.Put((Object)(_link),(Object)(""));
 //BA.debugLineNum = 35;BA.debugLine="Dim j As HttpJob";
_j = new anywheresoftware.b4a.samples.httputils2.httpjob();
 //BA.debugLineNum = 36;BA.debugLine="j.Initialize(link, Me)";
_j._initialize(processBA,_link,imagedownloader.getObject());
 //BA.debugLineNum = 37;BA.debugLine="j.Download(link)";
_j._download(_link);
 };
 }
};
 } 
       catch (Exception e16) {
			processBA.setLastException(e16); };
 //BA.debugLineNum = 42;BA.debugLine="End Sub";
return "";
}
public static String  _jobdone(anywheresoftware.b4a.samples.httputils2.httpjob _job) throws Exception{
anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _bmp = null;
int _i = 0;
String _link = "";
anywheresoftware.b4a.objects.ImageViewWrapper _iv = null;
 //BA.debugLineNum = 44;BA.debugLine="Sub JobDone(Job As HttpJob)";
 //BA.debugLineNum = 45;BA.debugLine="ongoingTasks.Remove(Job.JobName)";
_ongoingtasks.Remove((Object)(_job._jobname));
 //BA.debugLineNum = 46;BA.debugLine="If Job.Success Then";
if (_job._success) { 
 //BA.debugLineNum = 47;BA.debugLine="Dim bmp As Bitmap = Job.GetBitmap";
_bmp = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_bmp = _job._getbitmap();
 //BA.debugLineNum = 48;BA.debugLine="cache.Put(Job.JobName, bmp)";
_cache.Put((Object)(_job._jobname),(Object)(_bmp.getObject()));
 //BA.debugLineNum = 49;BA.debugLine="If tasks.IsInitialized Then";
if (_tasks.IsInitialized()) { 
 //BA.debugLineNum = 50;BA.debugLine="For i = 0 To tasks.Size - 1";
{
final int step6 = 1;
final int limit6 = (int) (_tasks.getSize()-1);
_i = (int) (0) ;
for (;(step6 > 0 && _i <= limit6) || (step6 < 0 && _i >= limit6) ;_i = ((int)(0 + _i + step6))  ) {
 //BA.debugLineNum = 51;BA.debugLine="Dim link As String = tasks.GetValueAt(i)";
_link = BA.ObjectToString(_tasks.GetValueAt(_i));
 //BA.debugLineNum = 52;BA.debugLine="If link = Job.JobName Then";
if ((_link).equals(_job._jobname)) { 
 //BA.debugLineNum = 53;BA.debugLine="Dim iv As ImageView = tasks.GetKeyAt(i)";
_iv = new anywheresoftware.b4a.objects.ImageViewWrapper();
_iv.setObject((android.widget.ImageView)(_tasks.GetKeyAt(_i)));
 //BA.debugLineNum = 54;BA.debugLine="iv.SetBackgroundImage(bmp)";
_iv.SetBackgroundImageNew((android.graphics.Bitmap)(_bmp.getObject()));
 };
 }
};
 };
 }else {
 //BA.debugLineNum = 59;BA.debugLine="Log(\"Error downloading image: \" & Job.JobName &";
anywheresoftware.b4a.keywords.Common.Log("Error downloading image: "+_job._jobname+anywheresoftware.b4a.keywords.Common.CRLF+_job._errormessage);
 };
 //BA.debugLineNum = 61;BA.debugLine="Job.Release";
_job._release();
 //BA.debugLineNum = 62;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 5;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 6;BA.debugLine="Private cache As Map";
_cache = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 7;BA.debugLine="Private tasks As Map";
_tasks = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 8;BA.debugLine="Private ongoingTasks As Map";
_ongoingtasks = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 9;BA.debugLine="End Sub";
return "";
}
public static String  _service_create() throws Exception{
 //BA.debugLineNum = 11;BA.debugLine="Sub Service_Create";
 //BA.debugLineNum = 12;BA.debugLine="tasks.Initialize";
_tasks.Initialize();
 //BA.debugLineNum = 13;BA.debugLine="cache.Initialize";
_cache.Initialize();
 //BA.debugLineNum = 14;BA.debugLine="ongoingTasks.Initialize";
_ongoingtasks.Initialize();
 //BA.debugLineNum = 15;BA.debugLine="End Sub";
return "";
}
public static String  _service_destroy() throws Exception{
 //BA.debugLineNum = 21;BA.debugLine="Sub Service_Destroy";
 //BA.debugLineNum = 23;BA.debugLine="End Sub";
return "";
}
public static String  _service_start(anywheresoftware.b4a.objects.IntentWrapper _startingintent) throws Exception{
 //BA.debugLineNum = 17;BA.debugLine="Sub Service_Start (StartingIntent As Intent)";
 //BA.debugLineNum = 19;BA.debugLine="End Sub";
return "";
}
}
