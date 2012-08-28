package sample.application.calculator;

import java.text.DecimalFormat;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CalculatorActivity extends Activity {

	String str = null;
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
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
    
    public String num1 = new String();
    public String strTemp ="";
}