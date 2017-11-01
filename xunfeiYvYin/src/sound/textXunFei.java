package sound;

import com.iflytek.cloud.speech.RecognizerListener;
import com.iflytek.cloud.speech.RecognizerResult;
import com.iflytek.cloud.speech.SpeechError;
import com.iflytek.cloud.speech.SpeechRecognizer;
import com.iflytek.cloud.speech.SpeechUtility;
import com.iflytek.util.DebugLog;
import com.iflytek.util.JsonParser;
import com.iflytek.util.Version;

public class textXunFei {

	
	// 语音听写对象
	SpeechRecognizer speechRecognize;
	
	public textXunFei() {
		// 初始化听写对象
		speechRecognize = SpeechRecognizer.createRecognizer();
	}

	//开始监听
	public int startListen() {
		
		if (!speechRecognize.isListening())
			speechRecognize.startListening(mRecoListener);
		else
			speechRecognize.stopListening();	
		return 0;
	}

	
	/**
	* 监听器
	*/
	private RecognizerListener mRecoListener = new RecognizerListener(){
		//听写结果回调接口(返回Json格式结果，用户可参见附录)；
		//一般情况下会通过onResults接口多次返回结果，完整的识别内容是多次结果的累加；
		//关于解析Json的代码可参见MscDemo中JsonParser类；
		//isLast等于true时会话结束。
		public void onResult(RecognizerResult results, boolean isLast){
			//DebugLog.Log(results.getResultString ());
			
		    String text = results.getResultString();
		    JsonParser json = new JsonParser();
		    String newTest = json.parseIatResult(text);
			System.out.print(newTest);
		}
		
		//会话发生错误回调接口
		public void onError(SpeechError error) {
			//error.getPlainDescription(true); //获取错误码描述
	    }
		//开始录音
		public void onBeginOfSpeech() {}
		//音量值0~30
		public void onVolumeChanged(int volume){}
		//结束录音
		public void onEndOfSpeech() {}
		//扩展用接口
		public void onEvent(int eventType,int arg1,int arg2,String msg) {}
	};

	public static void main(String[] args) {
		//听写对象
		textXunFei t=new textXunFei();
		StringBuffer param=new StringBuffer();
		t=new textXunFei();
		//初始化听写对象
		param = new StringBuffer();
		param.append( "appid=" + Version.getAppid() );
		SpeechUtility.createUtility(param.toString() );
		t.startListen();
	}
}