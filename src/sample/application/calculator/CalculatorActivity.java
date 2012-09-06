package sample.application.calculator;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import android.os.Bundle;
import android.app.Activity;
import android.text.ClipboardManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.content.SharedPreferences;

public class CalculatorActivity extends Activity {

    
    public String num1 = new String();
    public String strTemp ="";
    public int operator = 0;
    public String strNum = "";
    public String strResult ="";
	
    String str = null;
	public String hoge = this.strTemp;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        readPreferences();
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_calculator, menu);
        return true;
    }
    /*
    public void numKeyOnClick(View v){
    	Button button = (Button)v;
    	Log.d("[buttonのtext]", button.getText().toString());
    	TextView tv = (TextView) this.findViewById(R.id.displayPanel);
    	Log.d("[tvのインスタンスが確認]", "tv.text: " + tv.getText().toString());
    	tv.setText(tv.getText().toString() + button.getText().toString());
	}*/
    
    public void numKeyOnClick(View v){
    	String strInKey = (String) ((Button)v).getText();
    	
    	if(strInKey.equals(".")){
    		if(this.strTemp.length() ==0){
    			this.strTemp = "0.";
    		}else{
    			if(this.strTemp.indexOf(".")==-1){
    				this.strTemp = strTemp+".";
    			}
    		}
    }else{
    	this.strTemp = this.strTemp+strInKey;
    }
    
    this.showNumber(this.strTemp);
    	
    	DecimalFormat form = new DecimalFormat("#,##0");
    	String strDecimal = "";
    	String strInt = "";
    	String fText = "";
    	
		if(strNum.length() > 0){
    		int decimalPoint = strNum.indexOf(".");
    		if(decimalPoint > -1){
    			strDecimal = strNum.substring(0, decimalPoint);
    		
    		}else{
    			strInt = strNum;
    		}
    		fText = form.format(Double.parseDouble(strInt)) + strDecimal;
    		
    	}else{
    		fText = "0";
    		
    		((TextView)this.findViewById(R.id.displayPanel)).setText(fText);
    	}
    	
    }
    
    public void showNumber(String strNum){
    	
    }
    
    public void addKeyOnClick(View v){
    	TextView tv = (TextView) this.findViewById(R.id.displayPanel);
    	str = tv.getText().toString();
    	tv.setText("0");
	}

    public void operatorKeyOnClick(View v){
    	if(null != ""){
    		if(this.strTemp.length()>0){
    			this.strResult = this.doCalc();
    			this.showNumber(this.strResult);
    		}
    	}
    	else{
    		if(this.strTemp.length()>0){
    			this.strResult = this.strTemp;
    		}
    	}
    	
    	strTemp = "";
    	
    	if(v.getId() ==R.id.keypadEq){
    		operator = 0;
    	}else{
    		operator = v.getId();
    	}
    	
    }
    
    private String doCalc(){
    	BigDecimal bd1 = new BigDecimal(strResult);
    	BigDecimal bd2 = new BigDecimal(strTemp);
    	BigDecimal result = BigDecimal.ZERO;
    	
    	switch(operator){
    	case R.id.keypadAdd:
    		result = bd1.add(bd2);
    		break;
    	case R.id.keypadSub:
    		result = bd1.subtract(bd2);
    		break;
    	case R.id.keypadMulti:
    		result = bd1.multiply(bd2);
    		break;
    	case R.id.keypadDiv:
    		if(!bd2.equals(BigDecimal.ZERO)){
    			result =bd1.divide(bd2, 12, 3);
    		}else{
    			Toast toast = Toast.makeText(this,R.string.toast_div_by_zero,1000);
    			toast.show();
    		}
    		break;
    	}
    	if(result.toString().indexOf(".")>=0){
    		return result.toString().replaceAll("¥¥.0+$|0+$","");
    	}else{
    		return result.toString();
    	}
    }
    
    public void addkeyOnClick(View v){
    	Log.d("[addkeyが呼ばれたか確認]","テスト");
    	//String num1 = null; //表示されている数字の保存領域
    	TextView tv = (TextView) this.findViewById(R.id.displayPanel);
    	Log.d("[tvのインスタンスが確認]", "tv.text: "+tv.getText().toString());
    	Log.d("addkeyが呼ばれてすぐ",this.num1);
    	this.num1 = tv.getText().toString();
    	Log.d("num1にアドレスをいれたあと",this.num1);
    	//num1 = tv.getText().toString();
    	tv.setText("0");
    }
    
    public void equalkeyOnClick(View v){
    	Log.d("[equalkeyが呼ばれたか確認]","テスト");
    	Log.d("equalkey でのnum1",this.num1);
    	//新しく表示された数字を取得
    	//num1に保存した最初の数字を取得
    	//上二つを足す。	
    }
    
    public void writePreferences(){
    	SharedPreferences prefs = getSharedPreferences("CalcPrefs", MODE_PRIVATE);
    	SharedPreferences.Editor editor = prefs.edit();
    	editor.putString("strTemp", strTemp);
    	editor.putString("strResult", strResult);
    	editor.putInt("operator", operator);
    	editor.putString("strDisplay",
    	((TextView)findViewById(R.id.displayPanel)).getText().toString());
    	editor.commit();
    }
    
    public void readPreferences(){
    	SharedPreferences prefs = getSharedPreferences("CalcPrefs", MODE_PRIVATE);
    	strTemp = prefs.getString("strTemp", "");
    	strResult = prefs.getString("strResult", "0");
    	operator = prefs.getInt("operator", 0);
    	((TextView)findViewById(R.id.displayPanel)) .setText(
    		prefs.getString("strDisplay", "0"));
    	}
    	
    public void functionKeyOnClick(View v){
    	switch(v.getId()){
    	    case R.id.keypadAC:
    	    	strTemp = "";
    	    	strResult = "0";
    	    	operator = 0;
    	    	break;
    	    case R.id.keypadC:
    	    	strTemp = "";
    	    	break;
    	    case R.id.keypadBS:
    	    	if(strTemp.length() ==0)return;
    	    	else strTemp = strTemp.substring(0,strTemp.length()-1);
    	    	break;
    	    case R.id.keypadCopy:
    	    	ClipboardManager cm = (ClipboardManager) getSystemService(
    	    			CLIPBOARD_SERVICE);
    	    	cm.setText(((TextView)findViewById(R.id.displayPanel)).getText());
    	    return;	
    	 }
    	showNumber(strTemp);
    	}

	@Override
	protected void onStop() {
		super.onStop();
		writePreferences();
	}

    }