/**
 * 
 */
package org.mc.protocol.mq.product.impl;

import org.mc.protocol.mq.SandR.IConnectionReceive;
import org.mc.protocol.mq.SandR.IConnectionSend;
import org.mc.protocol.mq.SandR.impl.MqIBMReceive;
import org.mc.protocol.mq.SandR.impl.MqIBMSend;
import org.mc.protocol.mq.product.IProduct;

/**   
 *    
 * 项目名称：MessageComponent   
 * 类名称：MqProIBM   
 * 类描述：   
 * 创建人：王开群
 * 创建时间：2014-3-6  
 * @version    
 *    
 */
public class MqProIBM implements IProduct {

	/* (non-Javadoc)
	 * @see org.protocol.mq.product.IProduct#getMsg()
	 */
	@Override
	public byte[] getMsg() throws Exception {
		IConnectionReceive ir = new MqIBMReceive();
		
		return ir.getMsg();
	}

	/* (non-Javadoc)
	 * @see org.protocol.mq.product.IProduct#putMsg(byte[])
	 */
	@Override
	public void putMsg(byte[] msg) throws Exception {
		IConnectionSend is = new MqIBMSend();
		
		is.putMsg(msg);
	}

	@Override
	public int getQueueDeep() throws Exception {
		IConnectionReceive ir = new MqIBMReceive();
		ir.getDeep();
		return 0;
	}

}
