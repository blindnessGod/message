/**
 * 
 */
package org.mc.protocol.mq.thread;

import org.mc.protocol.biz.Interface.IBizReceiver;
import org.mc.protocol.config.ConstantValue;
import org.mc.protocol.mq.product.IProduct;
import org.mc.protocol.mq.util.MCBeginer;

import com.framework.component.config.LoadMsgConfig;
import com.framework.component.config.MessageComponent;
import com.framework.component.config.ConfigObj.Parm;

/**
 * 
 * 项目名称：FileCheck 类名称：MyThreadWorkTask 类描述： 创建人：王开群 创建时间：2014-3-6
 * 
 * @version
 * 
 */
public class QMThreadWorkTask extends Thread {
	private int index = -1;

	/**
	 * 初始化线程的序列号，并启动它
	 */
	public QMThreadWorkTask(int index) {
		this.index = index;
		start();
	}

	public void run() {
		// TODO Auto-generated method stub
		System.out.println(Thread.currentThread() + "   " + index
				+ "Thread is started!");
		MessageComponent messageComponent = LoadMsgConfig.getInstance(ConstantValue.configName).getMessageComponent();
//		Parm parm = (Parm) LoadMsgConfig.getInstance(ConstantValue.configName)
//				.getMessageComponent().get("IBizReceiverImpl");
		String strImplClassReceiver = messageComponent.getIBizReceiverImpl();
		System.out.println("strImplClassReceiver = "+strImplClassReceiver);
//		parm = (Parm) LoadMsgConfig.getInstance(ConstantValue.configName)
//				.getHtConfigParameters().get("Product");
		 String strImplClassProduct = messageComponent.getProduct();
		 System.out.println("strImplClassProduct = "+strImplClassProduct);
		 
		IProduct product = (IProduct) getProductImpl(strImplClassProduct);
		
		IBizReceiver mqReceiver = (IBizReceiver) getReceiverImpl(strImplClassReceiver);
		
		while (true) {
			if (Thread.currentThread().isInterrupted()){
				System.out.println(Thread.currentThread()+" is interrupted exit!!");
				break;
			}
			try {
				mqReceiver.BizProc(product.getMsg());
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	private IBizReceiver getReceiverImpl(String str) {

		// String strActionTypeImpl = str.concat(actionTypeImpl);
		String strImpl = str.concat("");

		if (null == strImpl || strImpl.equals("")) {
			System.out
					.println("[QMThreadWorkTask] "+str+" Impl is not exist!!!!");
			return null;
		}
		try {
			return (IBizReceiver) Class.forName(strImpl)
					.newInstance();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	private IProduct getProductImpl(String str) {

		// String strActionTypeImpl = str.concat(actionTypeImpl);
		String strImpl = str.concat("");

		if (null == strImpl || strImpl.equals("")) {
			System.out
					.println("[QMThreadWorkTask] "+str+" Impl is not exist!!!!");
			return null;
		}
		try {
			System.out.println("[QMThreadWorkTask] strImpl = "+strImpl);
			return (IProduct) Class.forName(strImpl)
					.newInstance();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
}
