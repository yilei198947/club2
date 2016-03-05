package com.iscard.club.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class AddAndSubView extends LinearLayout {
	
	Context context;
	int num;
	
	int editTextLayoutWidth;  //editText��ͼ�Ŀ��
	int editTextLayoutHeight;  //editText��ͼ�Ŀ��
	int editTextMinimumWidth;  //editText��ͼ����С���
	int editTextMinimumHeight;  //editText��ͼ����С�߶�
	int editTextMinHeight;  //editText�ı��������С�߶�
	int editTextHeight;  //editText�ı�����ĸ߶�
	
	LinearLayout mainLinearLayout;   //��View����AddAndSubView
	LinearLayout leftLinearLayout;   //�ڲ���view
	LinearLayout centerLinearLayout;   //�м�view
	LinearLayout rightLinearLayout;  //�ڲ���view
	
	Button addButton;
	Button subButton;
	EditText editText;
	
	OnNumChangeListener onNumChangeListener;

	public AddAndSubView(Context context) {
		super(context);
		this.context = context;
		num = 0;
	}
	
	public AddAndSubView(Context context, final int number) {
		super(context);
		this.context = context;
		this.num = number;
		control(number);
		subButton.setClickable(false);
	}
	
	private void control(int number) {
		initTextWithHeight();
		initialise();          //ʵ�����ڲ�view
		setViewsLayoutParm();  //�����ڲ�view�Ĳ��ֲ���
		insertView();            //����view����linearlayout��
		setViewListener(number);
	}
	
	/**
	 * ��ʼ��EditText��߲���
	 */
	private void initTextWithHeight() {
		editTextLayoutWidth = -1;
		editTextLayoutHeight = -1;
		editTextMinimumWidth = -1;
		editTextMinimumHeight = -1;
		editTextMinHeight = -1;
		editTextHeight = -1;
	}
	
	/**
	 * ʵ�����ڲ�View
	 */
	private void initialise() {
		mainLinearLayout = new LinearLayout(context);
		leftLinearLayout = new LinearLayout(context);
		centerLinearLayout = new LinearLayout(context);
		rightLinearLayout = new LinearLayout(context);
		
		addButton = new Button(context);
		subButton = new Button(context);
		editText = new EditText(context);
		
		addButton.setText("+");
		subButton.setText("-");
		addButton.setTag("+");
		subButton.setTag("-");
		//������������Ϊ����
		editText.setInputType(android.text.InputType.TYPE_CLASS_NUMBER);
		editText.setText(String.valueOf(num));
	}
	
	/**
	 * �����ڲ�view�Ĳ��ֲ���
	 */
	private void setViewsLayoutParm() {

		LayoutParams viewLayoutParams = new LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT, 
				LinearLayout.LayoutParams.WRAP_CONTENT);
		
		addButton.setLayoutParams(viewLayoutParams);
		subButton.setLayoutParams(viewLayoutParams);
		editText.setLayoutParams(viewLayoutParams);
		editText.setGravity(Gravity.CENTER);
		setTextWidthHeight();
		
		viewLayoutParams.gravity = Gravity.CENTER;
		centerLinearLayout.setLayoutParams(viewLayoutParams);
		//��editText���Զ���ý���
		centerLinearLayout.setFocusable(true);
		centerLinearLayout.setFocusableInTouchMode(true);
		
		viewLayoutParams.width = LinearLayout.LayoutParams.WRAP_CONTENT;
		viewLayoutParams.weight = 1.0f;
		leftLinearLayout.setLayoutParams(viewLayoutParams);  //���������ߡ����أ�����Ϊ1.0
		rightLinearLayout.setLayoutParams(viewLayoutParams);  //���������ߡ����أ�����Ϊ1.0
		
		viewLayoutParams.width = LinearLayout.LayoutParams.MATCH_PARENT;
		mainLinearLayout.setLayoutParams(viewLayoutParams);
		mainLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
	
	}
	
	/**
	 * ����EditText��ͼ���ı�������
	 */
	private void setTextWidthHeight()
	{
		float fPx;
		
		//������ͼ��С���
		if (editTextMinimumWidth < 0)
		{
			// �����ݴ�dip(��dp)ת����px����һ����Ϊ����ԭ��λ����ΪDIP�����ڶ�����ΪҪת��������ֵ
			fPx = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
					80f, context.getResources().getDisplayMetrics());
			editTextMinimumWidth = Math.round(fPx);
		}
		editText.setMinimumWidth(editTextMinimumWidth);
		
		//�����ı�����߶�
		if (editTextHeight > 0)
		{
			if (editTextMinHeight >= 0 && editTextMinHeight > editTextHeight)
			{
				editTextHeight = editTextMinHeight;
			}
			editText.setHeight(editTextHeight);
		}
		
		//������ͼ�߶�
		if (editTextLayoutHeight > 0)
		{
			if (editTextMinimumHeight > 0  && 
					editTextMinimumHeight > editTextLayoutHeight)
			{
				editTextLayoutHeight = editTextMinimumHeight;
			}
			
			LayoutParams layoutParams = (LayoutParams) editText.getLayoutParams();
			layoutParams.height = editTextLayoutHeight;
			editText.setLayoutParams(layoutParams);
		}
		
		//������ͼ���
		if (editTextLayoutWidth > 0)
		{
			if (editTextMinimumWidth > 0  && 
					editTextMinimumWidth > editTextLayoutWidth)
			{
				editTextLayoutWidth = editTextMinimumWidth;
			}
			
			LayoutParams layoutParams = (LayoutParams) editText.getLayoutParams();
			layoutParams.width = editTextLayoutWidth;
			editText.setLayoutParams(layoutParams);
		}
	}
	
	/**
	 * ����view����linearlayout��
	 */
	private void insertView()
	{
		mainLinearLayout.addView(leftLinearLayout, 0);
		mainLinearLayout.addView(centerLinearLayout, 1);
		mainLinearLayout.addView(rightLinearLayout, 2);
		leftLinearLayout.addView(addButton);
		centerLinearLayout.addView(editText);
		rightLinearLayout.addView(subButton);

		addView(mainLinearLayout);  //��������ͼ��ӽ���ǰAddAndSubView��
	}
	
	/**
	 * ����editText�е�ֵ
	 * @param num
	 */
	public void setNum(int num)
	{
		this.num = num;
		editText.setText(String.valueOf(num));
	}
	
	/**
	 * ��ȡeditText�е�ֵ
	 * @return
	 */
	public int getNum()
	{
		if ( editText.getText().toString() != null )
		{
			return Integer.parseInt(editText.getText().toString());
		}
		else {
			return 0;
		}
	}

	
	/**
	 * ����EditText��ͼ����С�߶�
	 * @param minimumWidth EditText����С�߶ȣ���λpx
	 */
	public void setEditTextMinimumWidth(int editTextMinimumWidth)
	{
		//������ͼ��С���
		if (editTextMinimumWidth > 0)
		{
			this.editTextMinimumWidth = editTextMinimumWidth;
			editText.setMinimumWidth(editTextMinimumWidth);
		}
		
	}

	/**
	 * ����EditText��ͼ����С�߶�
	 * @param editTextMinimumHeight EditText��ͼ����С�߶�,��λ��px
	 */
	public void setEditTextMinimumHeight(int editTextMinimumHeight)
	{
		//������ͼ��С�߶�
		if (editTextMinimumHeight > 0)
		{
			this.editTextMinimumHeight = editTextMinimumHeight;
			editText.setMinimumHeight(editTextMinimumHeight);
		}
	}

	/**
	 * ����EditText�ı��������С�߶�
	 * @param editTextMinHeight EditText�ı��������С�߶�,��λ��px
	 */
	public void setEditTextMinHeight(int editTextMinHeight)
	{
		//�����ı�������С�߶�
		if (editTextMinHeight > 0)
		{
			this.editTextMinHeight = editTextMinHeight;
			editText.setMinHeight(editTextMinHeight);
		}
	}

	/**
	 * ����EditText�ı�����ĸ߶�
	 * @param editTextHeight EditText�ı�����ĸ߶�,��λ��px
	 */
	public void setEditTextHeight(int editTextHeight)
	{
		this.editTextHeight = editTextHeight;
		setTextWidthHeight();
	}

	/**
	 * ����EditText��ͼ�Ŀ��
	 * @param editTextLayoutWidth ����EditText��ͼ�Ŀ��,��λpx
	 */
	public void setEditTextLayoutWidth(int editTextLayoutWidth)
	{
		this.editTextLayoutWidth = editTextLayoutWidth;
		setTextWidthHeight();
	}

	/**
	 * ����EditText��ͼ�ĸ߶�
	 * @param editTextLayoutHeight EditText��ͼ����С�߶ȣ���λpx
	 */
	public void setEditTextLayoutHeight(int editTextLayoutHeight)
	{
		this.editTextLayoutHeight = editTextLayoutHeight;
		setTextWidthHeight();
	}

	/**
	 * ��Drawable��ʽ ���ð�ť����ͼ
	 * @param addBtnDrawable �Ӻű���ͼ
	 * @param subBtnDrawable ���ű���ͼ
	 */
	public void Drawable(Drawable addBtnDrawable, Drawable subBtnDrawable)
	{
		//���Ƽ���setBackgroundDrawable����API�Ƽ���setBackground����API 16�У�
		addButton.setBackgroundDrawable(addBtnDrawable);
		subButton.setBackgroundDrawable(subBtnDrawable);
		addButton.setText("");
		subButton.setText("");
	}
	
	/**
	 * ����ԴResource��ʽ ���ð�ť����ͼ
	 * @param addBtnResource �Ӻű���ͼ
	 * @param subBtnResource ���ű���ͼ
	 */
	public void setButtonBgResource(int addBtnResource, int subBtnResource)
	{
		addButton.setBackgroundResource(addBtnResource);
		subButton.setBackgroundResource(subBtnResource);
		addButton.setText("");
		subButton.setText("");
	}
	
	/**
	 * ���ð�ť����ɫ
	 * @param addBtnColor �Ӻű���ɫ
	 * @param subBtnColor ���ű���ɫ
	 */
	public void setButtonBgColor(int addBtnColor, int subBtnColor)
	{
		addButton.setBackgroundColor(addBtnColor);
		subButton.setBackgroundColor(subBtnColor);
	}
	
	/**
	 * ����EditText�ı��仯����
	 * @param onNumChangeListener
	 */
	public void setOnNumChangeListener(OnNumChangeListener onNumChangeListener)
	{
		this.onNumChangeListener = onNumChangeListener;
	}
	
	
	/**
	 * �����ı��仯��ؼ����¼�
	 */
	private void setViewListener(int number)
	{
		addButton.setOnClickListener(new OnButtonClickListener());
		subButton.setOnClickListener(new OnButtonClickListener());
		editText.addTextChangedListener(new OnTextChangeListener(number));
	}
	
	
	/**
	 * �Ӽ���ť�¼�������
	 * @author ZJJ
	 *
	 */
	class OnButtonClickListener implements OnClickListener
	{

		@Override
		public void onClick(View v)
		{
			String numString = editText.getText().toString();
			if (numString == null || numString.equals(""))
			{
				num = 0;
				editText.setText("0");
			} else
			{
				if (v.getTag().equals("+"))
				{
					if (++num < 0)  //�ȼӣ����ж�
					{
						num--;
						Toast.makeText(context, "������һ������0������",
								Toast.LENGTH_SHORT).show();
					} else
					{
						editText.setText(String.valueOf(num));
						
						if (onNumChangeListener != null)
						{
							onNumChangeListener.onNumChange(AddAndSubView.this, num);
						}
					}
				} else if (v.getTag().equals("-"))
				{
					if (--num < 0)  //�ȼ������ж�
					{
						num++;
						Toast.makeText(context, "������һ������0������",
								Toast.LENGTH_SHORT).show();
					} else
					{
						editText.setText(String.valueOf(num));
						if (onNumChangeListener != null)
						{
							onNumChangeListener.onNumChange(AddAndSubView.this, num);
						}
					}
				}
			}
		}
	}
	
	
	/**
	 * EditText����仯�¼�������
	 * @author ZJJ
	 *
	 */
	class OnTextChangeListener implements TextWatcher
	{
		private int number;
		public OnTextChangeListener(int number) {
			this.number = number;
		}
		@Override
		public void afterTextChanged(Editable s)
		{
			if(num <= number) {
				subButton.setClickable(false);
			}else{
				subButton.setClickable(true);
			}
			String numString = s.toString();
			if(numString == null || numString.equals(""))
			{
				num = 0;
				if (onNumChangeListener != null)
				{
					onNumChangeListener.onNumChange(AddAndSubView.this, num);
				}
			}
			else {
				int numInt = Integer.parseInt(numString);
				if (numInt < 0)
				{
					Toast.makeText(context, "������һ������0������",
							Toast.LENGTH_SHORT).show();
				} else
				{
					//����EditText���λ�� Ϊ�ı�ĩ��
					editText.setSelection(editText.getText().toString().length());
					num = numInt;
					if (onNumChangeListener != null)
					{
						onNumChangeListener.onNumChange(AddAndSubView.this, num);
					}
				}
			}
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after)
		{

		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count)
		{
			
		}
		
	}
	
	
	public interface OnNumChangeListener
	{
		/**
		 * ������е���ֵ�ı��¼�
		 * @param view ����AddAndSubView
		 * @param num ��������ֵ
		 */
		public void onNumChange(View view, int num);
	}

}
