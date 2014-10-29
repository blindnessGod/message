/**
 * 
 */
package org.mc.protocol.mq.util;

import java.util.Hashtable;

import org.mc.protocol.config.ConstantValue;
import org.mc.protocol.mq.thread.QMThreadPool;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.framework.component.config.LoadMsgConfig;

/**   
 *    
 * ��Ŀ���ƣ�MessageComponent   
 * �����ƣ�MCBeginer   
 * ��������   
 * �����ˣ�����Ⱥ
 * ����ʱ�䣺2014-3-11  
 * @version    
 *    
 */
public class MCBeginer {
	public static ApplicationContext context = null;
	/**
	 * ����
	 */
	private static MCBeginer mCBeginer = null;
	/**
	 * ���캯������ʼ������
	 */
	public MCBeginer() {
		mCBeginer = this;
	}
	/**
	 * ��õ���
	 * @return ���ص���
	 */
	public static MCBeginer getInstance() {
		if (mCBeginer == null)
			mCBeginer = new MCBeginer();
		return mCBeginer;
	}
	/**
	 * ��������߳�
	 */
	public void startThread() {
		//���������ļ�
		System.out.println("[MCBeginer] begin init LoadMsgConfig!");
		LoadMsgConfig.getInstance(ConstantValue.configName);
		System.out.println("[MCBeginer] finish init LoadMsgConfig!");
		//�����̳߳�
		System.out.println("[MCBeginer] begin init QMThreadPool!");
		QMThreadPool.getInstance();
		System.out.println("[MCBeginer] finish init QMThreadPool!");
		//����spring����
//		MCBeginer.context = new ClassPathXmlApplicationContext("applicationContext-MsgComnet.xml");
	}
	
	public void destroy(){
		try{
			QMThreadPool.getInstance().interruptAll();
		}catch(Exception ex){
			ex.printStackTrace();
		}

	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
