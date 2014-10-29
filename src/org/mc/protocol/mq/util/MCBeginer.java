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
 * 项目名称：MessageComponent   
 * 类名称：MCBeginer   
 * 类描述：   
 * 创建人：王开群
 * 创建时间：2014-3-11  
 * @version    
 *    
 */
public class MCBeginer {
	public static ApplicationContext context = null;
	/**
	 * 单例
	 */
	private static MCBeginer mCBeginer = null;
	/**
	 * 构造函数。初始化单例
	 */
	public MCBeginer() {
		mCBeginer = this;
	}
	/**
	 * 获得单例
	 * @return 返回单例
	 */
	public static MCBeginer getInstance() {
		if (mCBeginer == null)
			mCBeginer = new MCBeginer();
		return mCBeginer;
	}
	/**
	 * 启动组件线程
	 */
	public void startThread() {
		//加载配置文件
		System.out.println("[MCBeginer] begin init LoadMsgConfig!");
		LoadMsgConfig.getInstance(ConstantValue.configName);
		System.out.println("[MCBeginer] finish init LoadMsgConfig!");
		//加载线程池
		System.out.println("[MCBeginer] begin init QMThreadPool!");
		QMThreadPool.getInstance();
		System.out.println("[MCBeginer] finish init QMThreadPool!");
		//加载spring容器
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
